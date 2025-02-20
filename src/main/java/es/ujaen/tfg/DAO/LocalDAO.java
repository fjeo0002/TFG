/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Local;
import static es.ujaen.tfg.utils.Utils.LOCALES_COLECCION;
import static es.ujaen.tfg.utils.Utils.LOCALES_JSON;
import static es.ujaen.tfg.utils.Utils.TIEMPO_ACTUALIZACION_BBDD;
import static es.ujaen.tfg.utils.Utils.USUARIOS_COLECCION;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jota
 */
public final class LocalDAO implements InterfazDAO<Local> {

    private final Firestore db;
    private final String email;
    private List<Local> localesCache;
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LocalDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        if (this.localesCache == null) {
            sincronizarDesdeFirebase();
        }
        //iniciarSincronizacionPeriodica();
        //agregarShutdownHook();
    }

    // ✅ Guardar la caché localmente
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(LOCALES_JSON)) {
            new Gson().toJson(localesCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché de locales: " + e.getMessage());
        }
    }

    // ✅ Descargar la colección de locales desde Firebase al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Local> locales = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection(USUARIOS_COLECCION)
                    .document(email)
                    .collection(LOCALES_COLECCION)
                    .get();

            for (QueryDocumentSnapshot document : future.get().getDocuments()) {
                locales.add(document.toObject(Local.class));
            }

            localesCache = locales;
            guardarEnCache();
            cambiosPendientes = false;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error sincronizando locales desde Firebase: " + e.getMessage());
        }
    }

    // ✅ Sincronización periódica con Firebase solo si hay cambios
    private void iniciarSincronizacionPeriodica() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (cambiosPendientes) {
                    sincronizarConFirebase();
                }
            }
        }, 0, TIEMPO_ACTUALIZACION_BBDD); // 5 minutos
    }

    // ✅ Sincronización final al cerrar la aplicación
    private void agregarShutdownHook() {
        java.awt.EventQueue.invokeLater(() -> {
            Runtime.getRuntime().addShutdownHook(new Thread(() -> {
                if (cambiosPendientes) {
                    sincronizarConFirebase();
                }
                executorService.shutdown();
            }));
        });
    }

    // ✅ Sincroniza los datos locales con Firebase en segundo plano
    public void sincronizarConFirebase() {
        if (cambiosPendientes) {
            executorService.submit(() -> {
                for (Local local : localesCache) {
                    db.collection(USUARIOS_COLECCION).document(email)
                            .collection(LOCALES_COLECCION)
                            .document(local.getCodigo())
                            .set(local);
                }
                cambiosPendientes = false;
            });
        }
    }

    public void limpiarCache() throws IOException {
        if (Files.exists(Paths.get(LOCALES_JSON))) {
            Files.delete(Paths.get(LOCALES_JSON));
        }
    }

    // ✅ CREAR local (modifica la caché y luego sube a Firebase en segundo plano)
    @Override
    public boolean crear(Local local) {
        localesCache.add(local);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(LOCALES_COLECCION)
                    .document(local.getCodigo())
                    .set(local);
        });

        return true;
    }

    // ✅ CREAR local con index (modifica la caché y luego sube a Firebase en segundo plano)
    public boolean crear(Local local, int index) {
        localesCache.add(index, local);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(LOCALES_COLECCION)
                    .document(local.getCodigo())
                    .set(local);
        });

        return true;
    }

    // ✅ LEER local desde caché
    @Override
    public Local leer(String codigo) {
        return localesCache.stream()
                .filter(local -> local.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR local (modifica en caché y lo sube a Firebase en segundo plano)
    @Override
    public boolean actualizar(Local local) {
        for (int i = 0; i < localesCache.size(); i++) {
            if (localesCache.get(i).getCodigo().equals(local.getCodigo())) {
                localesCache.set(i, local);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection(USUARIOS_COLECCION).document(email)
                            .collection(LOCALES_COLECCION)
                            .document(local.getCodigo())
                            .set(local);
                });

                return true;
            }
        }
        return false;
    }

    // ✅ BORRAR local (elimina en caché y en Firebase en segundo plano)
    @Override
    public boolean borrar(Local local) {
        boolean eliminado = localesCache.remove(local);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection(USUARIOS_COLECCION).document(email)
                        .collection(LOCALES_COLECCION)
                        .document(local.getCodigo())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODOS los locales desde caché
    @Override
    public List<Local> leerTodos() {
        return new ArrayList<>(localesCache);
    }

}

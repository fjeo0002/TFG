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
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.LOCALES_COLECCION;
import static es.ujaen.tfg.utils.Utils.LOCALES_JSON;
import static es.ujaen.tfg.utils.Utils.calcularHashArchivo;
import static es.ujaen.tfg.utils.Utils.cargarDatosDesdeArchivo;
import static es.ujaen.tfg.utils.Utils.iniciarSincronizacionPeriodica;
import static es.ujaen.tfg.utils.Utils.sincronizarConFirebase;
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
    private final String userId;
    private List<Local> localesCache;
    private static final String CACHE_FILE = "locales_.json";
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public LocalDAO(String userId) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.userId = userId;
        this.localesCache = cargarDesdeCache();
        sincronizarDesdeFirebase();
        iniciarSincronizacionPeriodica();
        agregarShutdownHook();
    }

    // ✅ Cargar la caché localmente
    private List<Local> cargarDesdeCache() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(CACHE_FILE)));
            return new Gson().fromJson(json, new TypeToken<List<Local>>() {
            }.getType());
        } catch (IOException e) {
            System.err.println("No se pudo cargar la caché de locales. Se creará una nueva lista.");
            return new ArrayList<>();
        }
    }

    // ✅ Guardar la caché localmente
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(CACHE_FILE)) {
            new Gson().toJson(localesCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché de locales: " + e.getMessage());
        }
    }

    // ✅ Descargar la colección de locales desde Firebase al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Local> locales = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection("usuarios")
                    .document(userId)
                    .collection("locales")
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
        }, 0, 300000); // 5 minutos
    }

    // ✅ Sincronización final al cerrar la aplicación
    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (cambiosPendientes) {
                sincronizarConFirebase();
            }
            executorService.shutdown();
        }));
    }

    // ✅ Sincroniza los datos locales con Firebase en segundo plano
    private void sincronizarConFirebase() {
        executorService.submit(() -> {
            for (Local local : localesCache) {
                db.collection("usuarios").document(userId)
                        .collection("locales")
                        .document(local.getCodigo())
                        .set(local);
            }
            cambiosPendientes = false;
            System.out.println("Caché de locales sincronizada con Firebase.");
        });
    }

    // ✅ CREAR local (modifica la caché y luego sube a Firebase en segundo plano)
    public boolean crear(Local local) {
        localesCache.add(local);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection("usuarios").document(userId)
                    .collection("locales")
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
            db.collection("usuarios").document(userId)
                    .collection("locales")
                    .document(local.getCodigo())
                    .set(local);
        });

        return true;
    }

    // ✅ LEER local desde caché
    public Local leer(String codigo) {
        return localesCache.stream()
                .filter(local -> local.getCodigo().equals(codigo))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR local (modifica en caché y lo sube a Firebase en segundo plano)
    public boolean actualizar(Local local) {
        for (int i = 0; i < localesCache.size(); i++) {
            if (localesCache.get(i).getCodigo().equals(local.getCodigo())) {
                localesCache.set(i, local);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection("usuarios").document(userId)
                            .collection("locales")
                            .document(local.getCodigo())
                            .set(local);
                });

                return true;
            }
        }
        return false;
    }

    // ✅ BORRAR local (elimina en caché y en Firebase en segundo plano)
    public boolean borrar(Local local) {
        boolean eliminado = localesCache.remove(local);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection("usuarios").document(userId)
                        .collection("locales")
                        .document(local.getCodigo())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODOS los locales desde caché
    public List<Local> leerTodos() {
        return new ArrayList<>(localesCache);
    }

    /*
    private List<Local> locales;
    private final String ultimoHashArchivo;

    public LocalDAO() throws IOException {
        this.locales = cargarDatosDesdeArchivo(
                LOCALES_JSON,
                new TypeToken<List<Local>>() {
                }.getType()
        );
        /*
        this.locales = cargarDatosDesdeFirebase(
                LOCALES_JSON, 
                LOCALES_COLECCION, 
                Local.class
        );
     */
     /*
    this.ultimoHashArchivo  = calcularHashArchivo(LOCALES_JSON);

    iniciarSincronizacionPeriodica(
            LOCALES_JSON,
            LOCALES_COLECCION,
            ultimoHashArchivo,
            locales,
            Local

    ::getCodigo
        );
    agregarShutdownHook();
}

private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        LOCALES_JSON,
                        LOCALES_COLECCION,
                        ultimoHashArchivo,
                        locales,
                        Local::getCodigo
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
public boolean crear(Local t) {
        if (locales == null) {
            locales = new ArrayList<>();
        }
        locales.add(t);
        Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
        return true;
    }
    
    //@Override
    public boolean crear(Local local, int index) {
        if (locales == null) {
            locales = new ArrayList<>();
        }
        //locales.add(local);
        locales.add(index, local);
        Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
        return true;
    }

    @Override
public Local leer(String codigo) {
        for (Local local : locales) {
            if (local.getCodigo().equals(codigo)) {
                return local;
            }
        }
        return null;
    }

    @Override
public boolean actualizar(Local local) {
        for (int i = 0; i < locales.size(); i++) {
            if (locales.get(i).getCodigo().equals(local.getCodigo())) {
                locales.set(i, local);
                Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
                return true;
            }
        }
        return false;
    }

    @Override
public boolean borrar(Local local) {
        boolean removed = locales.remove(local);
        if (removed) {
            Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
        }
        return removed;
    }

    @Override
public List<Local> leerTodos() {
        return locales;
    }
*/
}

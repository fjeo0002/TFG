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
import com.google.gson.GsonBuilder;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.utils.LocalDateAdapterGson;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
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
public final class AnticipoDAO implements InterfazDAO<Anticipo> {

    private final Firestore db;
    private final String email;
    private List<Anticipo> anticiposCache;
    private static final String CACHE_FILE = "anticipos_.json";
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapterGson())
            .create();

    public AnticipoDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        if (this.anticiposCache == null) {
            sincronizarDesdeFirebase();
        }
        iniciarSincronizacionPeriodica();
        agregarShutdownHook();
    }

    // ✅ Guardar la caché localmente
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(CACHE_FILE)) {
            gson.toJson(anticiposCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché de anticipos: " + e.getMessage());
        }
    }

    // ✅ Descargar la colección de anticipos desde Firebase al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Anticipo> anticipos = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection("usuarios")
                    .document(email)
                    .collection("anticipos")
                    .get();

            for (QueryDocumentSnapshot document : future.get().getDocuments()) {
                anticipos.add(document.toObject(Anticipo.class));
            }

            anticiposCache = anticipos;
            guardarEnCache();
            cambiosPendientes = false;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error sincronizando anticipos desde Firebase: " + e.getMessage());
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
    public void sincronizarConFirebase() {
        if (cambiosPendientes) {
            executorService.submit(() -> {
                for (Anticipo anticipo : anticiposCache) {
                    db.collection("usuarios").document(email)
                            .collection("anticipos")
                            .document(anticipo.getId())
                            .set(anticipo);
                }
                cambiosPendientes = false;
                System.out.println("Caché de anticipos sincronizada con Firebase.");
            });
        }
    }

    public void limpiarCache() throws IOException {
        if (Files.exists(Paths.get(CACHE_FILE))) {
            Files.delete(Paths.get(CACHE_FILE));
        }
    }

    // ✅ CREAR anticipo (modifica la caché y luego sube a Firebase en segundo plano)
    public boolean crear(Anticipo anticipo) {
        anticiposCache.add(anticipo);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection("usuarios").document(email)
                    .collection("anticipos")
                    .document(anticipo.getId())
                    .set(anticipo);
        });

        return true;
    }

    // ✅ LEER anticipo desde caché
    public Anticipo leer(String id) {
        return anticiposCache.stream()
                .filter(anticipo -> anticipo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR anticipo (modifica en caché y lo sube a Firebase en segundo plano)
    public boolean actualizar(Anticipo anticipo) {
        for (int i = 0; i < anticiposCache.size(); i++) {
            if (anticiposCache.get(i).getId().equals(anticipo.getId())) {
                anticiposCache.set(i, anticipo);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection("usuarios").document(email)
                            .collection("anticipos")
                            .document(anticipo.getId())
                            .set(anticipo);
                });

                return true;
            }
        }
        return false;
    }

    // ✅ BORRAR anticipo (elimina en caché y en Firebase en segundo plano)
    public boolean borrar(Anticipo anticipo) {
        boolean eliminado = anticiposCache.remove(anticipo);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection("usuarios").document(email)
                        .collection("anticipos")
                        .document(anticipo.getId())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODOS los anticipos desde caché
    public List<Anticipo> leerTodos() {
        return new ArrayList<>(anticiposCache);
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.gson.Gson;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Preferencias;
import static es.ujaen.tfg.utils.Utils.PREFERENCIAS_COLECCION;
import static es.ujaen.tfg.utils.Utils.TIEMPO_ACTUALIZACION_BBDD;
import static es.ujaen.tfg.utils.Utils.USUARIOS_COLECCION;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
public final class PreferenciasDAO implements InterfazDAO<Preferencias>{
    private final Firestore db;
    private final String email;
    private Preferencias preferenciasCache;
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Path archivoCache;

    public PreferenciasDAO(String email) throws IOException, ExecutionException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        
        this.archivoCache = Files.createTempFile("preferencias_", ".json");
        this.archivoCache.toFile().deleteOnExit();
        
        if (this.preferenciasCache == null) {
            sincronizarDesdeFirebase();
        }
        
        //iniciarSincronizacionPeriodica();
        //agregarShutdownHook();
    }

    // ✅ Guardar preferencias en la caché
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(archivoCache.toString())) {
            new Gson().toJson(preferenciasCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché: " + e.getMessage());
        }
    }

    // ✅ Sincronizar desde Firebase
    public void sincronizarDesdeFirebase() throws ExecutionException {
        try {
            ApiFuture<DocumentSnapshot> future = db.collection(USUARIOS_COLECCION)
                    .document(email)
                    .collection(PREFERENCIAS_COLECCION)
                    .document("config")
                    .get();

            DocumentSnapshot document = future.get();
            if (document.exists()) {
                preferenciasCache = document.toObject(Preferencias.class);
                guardarEnCache();
                cambiosPendientes = false;
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error sincronizando con Firebase: " + e.getMessage());
        }
    }

    // ✅ Sincronización periódica con Firebase si hay cambios
    private void iniciarSincronizacionPeriodica() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (cambiosPendientes) {
                    sincronizarConFirebase();
                }
            }
        }, 0, TIEMPO_ACTUALIZACION_BBDD); // Cada X tiempo
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

    // ✅ Subir cambios a Firebase si hay modificaciones pendientes
    public void sincronizarConFirebase() {
        if (cambiosPendientes) {
            executorService.submit(() -> {
                db.collection(USUARIOS_COLECCION).document(email)
                        .collection(PREFERENCIAS_COLECCION)
                        .document("config")
                        .set(preferenciasCache);
                cambiosPendientes = false;
            });
        }
    }

    public void limpiarCache() throws IOException {
        Files.deleteIfExists(archivoCache);
    }

    // ✅ Crear preferencias (modifica la caché y sube a Firebase en segundo plano)
    @Override
    public boolean crear(Preferencias preferencias) {
        this.preferenciasCache = preferencias;
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(PREFERENCIAS_COLECCION)
                    .document("config")
                    .set(preferencias);
        });

        return true;
    }

    // ✅ Leer preferencias desde caché
    @Override
    public Preferencias leer(String id) {
        return preferenciasCache;
    }

    // ✅ Actualizar preferencias (modifica en caché y sube a Firebase en segundo plano)
    @Override
    public boolean actualizar(Preferencias preferencias) {
        this.preferenciasCache = preferencias;
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(PREFERENCIAS_COLECCION)
                    .document("config")
                    .set(preferencias);
        });

        return true;
    }

    // ✅ Borrar preferencias (limpia caché y Firebase en segundo plano)
    @Override
    public boolean borrar(Preferencias preferencias) {
        preferenciasCache = new Preferencias();
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(PREFERENCIAS_COLECCION)
                    .document("config")
                    .delete();
        });

        return true;
    }

    // ✅ Leer todas las preferencias (en este caso, solo hay una)
    @Override
    public List<Preferencias> leerTodos() {
        List<Preferencias> preferencias = new ArrayList<>();
        preferencias.add(preferenciasCache);
        return preferencias;
    }
}

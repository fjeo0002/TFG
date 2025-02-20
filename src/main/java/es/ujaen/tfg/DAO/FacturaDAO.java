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
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.utils.LocalDateAdapterGson;
import static es.ujaen.tfg.utils.Utils.FACTURAS_COLECCION;
import static es.ujaen.tfg.utils.Utils.FACTURAS_JSON;
import static es.ujaen.tfg.utils.Utils.TIEMPO_ACTUALIZACION_BBDD;
import static es.ujaen.tfg.utils.Utils.USUARIOS_COLECCION;
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
public final class FacturaDAO implements InterfazDAO<Factura> {

    private final Firestore db;
    private final String email;
    private List<Factura> facturasCache;
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapterGson())
            .create();

    public FacturaDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        if (this.facturasCache == null) {
            sincronizarDesdeFirebase();
        }
        //iniciarSincronizacionPeriodica();
        //agregarShutdownHook();
    }

    // ✅ Guardar la caché localmente
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(FACTURAS_JSON)) {
            gson.toJson(facturasCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché de facturas: " + e.getMessage());
        }
    }

    // ✅ Descargar la colección de facturas desde Firebase al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Factura> facturas = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection(USUARIOS_COLECCION)
                    .document(email)
                    .collection(FACTURAS_COLECCION)
                    .get();

            for (QueryDocumentSnapshot document : future.get().getDocuments()) {
                facturas.add(document.toObject(Factura.class));
            }

            facturasCache = facturas;
            guardarEnCache();
            cambiosPendientes = false;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error sincronizando facturas desde Firebase: " + e.getMessage());
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
                for (Factura factura : facturasCache) {
                    db.collection(USUARIOS_COLECCION).document(email)
                            .collection(FACTURAS_COLECCION)
                            .document(factura.getId()) // 🔹 Asegura un ID único en Firestore
                            .set(factura);
                }
                cambiosPendientes = false;
            });
        }
    }

    public void limpiarCache() throws IOException {
        if (Files.exists(Paths.get(FACTURAS_JSON))) {
            Files.delete(Paths.get(FACTURAS_JSON));
        }
    }

    // ✅ CREAR factura (modifica la caché y luego sube a Firebase en segundo plano)
    @Override
    public boolean crear(Factura factura) {
        facturasCache.add(factura);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(FACTURAS_COLECCION)
                    .document(factura.getId())
                    .set(factura);
        });

        return true;
    }

    // ✅ LEER factura desde caché
    @Override
    public Factura leer(String id) {
        return facturasCache.stream()
                .filter(factura -> factura.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR factura (modifica en caché y lo sube a Firebase en segundo plano)
    @Override
    public boolean actualizar(Factura factura) {
        for (int i = 0; i < facturasCache.size(); i++) {
            if (facturasCache.get(i).getId().equals(factura.getId())) {
                facturasCache.set(i, factura);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection(USUARIOS_COLECCION).document(email)
                            .collection(FACTURAS_COLECCION)
                            .document(factura.getId())
                            .set(factura);
                });

                return true;
            }
        }
        return false;
    }

    // ✅ BORRAR factura (elimina en caché y en Firebase en segundo plano)
    @Override
    public boolean borrar(Factura factura) {
        boolean eliminado = facturasCache.remove(factura);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection(USUARIOS_COLECCION).document(email)
                        .collection(FACTURAS_COLECCION)
                        .document(factura.getId())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODAS las facturas desde caché
    @Override
    public List<Factura> leerTodos() {
        return new ArrayList<>(facturasCache);
    }
}

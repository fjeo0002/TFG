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
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.utils.LocalDateAdapterGson;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.FACTURAS_COLECCION;
import static es.ujaen.tfg.utils.Utils.FACTURAS_JSON;
import static es.ujaen.tfg.utils.Utils.calcularHashArchivo;
import static es.ujaen.tfg.utils.Utils.cargarDatosDesdeArchivo;
import static es.ujaen.tfg.utils.Utils.iniciarSincronizacionPeriodica;
import static es.ujaen.tfg.utils.Utils.sincronizarConFirebase;
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
public class FacturaDAO implements InterfazDAO<Factura> {
    
    private final Firestore db;
    private final String userId;
    private List<Factura> facturasCache;
    private static final String CACHE_FILE = "facturas_.json";
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapterGson())
            .create();

    public FacturaDAO(String userId) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.userId = userId;
        this.facturasCache = cargarDesdeCache();
        sincronizarDesdeFirebase();
        iniciarSincronizacionPeriodica();
        agregarShutdownHook();
    }

    // ✅ Cargar la caché localmente con manejo de fechas
    private List<Factura> cargarDesdeCache() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(CACHE_FILE)));
            return gson.fromJson(json, new TypeToken<List<Factura>>() {}.getType());
        } catch (IOException e) {
            System.err.println("No se pudo cargar la caché de facturas. Se creará una nueva lista.");
            return new ArrayList<>();
        }
    }

    // ✅ Guardar la caché localmente
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(CACHE_FILE)) {
            gson.toJson(facturasCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché de facturas: " + e.getMessage());
        }
    }

    // ✅ Descargar la colección de facturas desde Firebase al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Factura> facturas = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection("usuarios")
                    .document(userId)
                    .collection("facturas")
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
            for (Factura factura : facturasCache) {
                db.collection("usuarios").document(userId)
                        .collection("facturas")
                        .document(factura.getId()) // 🔹 Asegura un ID único en Firestore
                        .set(factura);
            }
            cambiosPendientes = false;
            System.out.println("Caché de facturas sincronizada con Firebase.");
        });
    }

    // ✅ CREAR factura (modifica la caché y luego sube a Firebase en segundo plano)
    public boolean crear(Factura factura) {
        facturasCache.add(factura);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection("usuarios").document(userId)
                    .collection("facturas")
                    .document(factura.getId())
                    .set(factura);
        });

        return true;
    }

    // ✅ LEER factura desde caché
    public Factura leer(String id) {
        return facturasCache.stream()
                .filter(factura -> factura.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR factura (modifica en caché y lo sube a Firebase en segundo plano)
    public boolean actualizar(Factura factura) {
        for (int i = 0; i < facturasCache.size(); i++) {
            if (facturasCache.get(i).getId().equals(factura.getId())) {
                facturasCache.set(i, factura);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection("usuarios").document(userId)
                            .collection("facturas")
                            .document(factura.getId())
                            .set(factura);
                });

                return true;
            }
        }
        return false;
    }
    /*
    public boolean actualizar(Factura factura) {
        LocalDate fecha = factura.getFecha();
        String clienteDNI = factura.getClienteDNI();
        for (int i = 0; i < facturasCache.size(); i++) {
            String clienteDNIFactura = facturasCache.get(i).getClienteDNI();
            LocalDate fechaFactura = facturasCache.get(i).getFecha();
            if (clienteDNI.equals(clienteDNIFactura) && fecha.isEqual(fechaFactura)) {
                facturasCache.set(i, factura);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection("usuarios").document(userId)
                            .collection("facturas")
                            .document(factura.getId())
                            .set(factura);
                });

                return true;
            }
        }
        return false;
    }
    */
    // ✅ BORRAR factura (elimina en caché y en Firebase en segundo plano)
    public boolean borrar(Factura factura) {
        boolean eliminado = facturasCache.remove(factura);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection("usuarios").document(userId)
                        .collection("facturas")
                        .document(factura.getId())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODAS las facturas desde caché
    public List<Factura> leerTodos() {
        return new ArrayList<>(facturasCache);
    }
    
    
/*
    private List<Factura> facturas;
    private final String ultimoHashArchivo;

    public FacturaDAO() throws IOException {
        this.facturas = cargarDatosDesdeArchivo(
                FACTURAS_JSON,
                new TypeToken<List<Factura>>() {
                }.getType()
        );
        /*
        this.facturas = cargarDatosDesdeFirebase(
                FACTURAS_JSON, 
                FACTURAS_COLECCION, 
                Factura.class
        );
         */
        /*
        this.ultimoHashArchivo = calcularHashArchivo(FACTURAS_JSON);
        iniciarSincronizacionPeriodica(
                FACTURAS_JSON,
                FACTURAS_COLECCION,
                ultimoHashArchivo,
                facturas,
                Factura::getId
        );
        agregarShutdownHook();
    }

    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        FACTURAS_JSON,
                        FACTURAS_COLECCION,
                        ultimoHashArchivo,
                        facturas,
                        Factura::getId
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
    public boolean crear(Factura t) {
        if (facturas == null) {
            facturas = new ArrayList<>();
        }
        facturas.add(t);
        Utils.guardarDatosEnArchivo(FACTURAS_JSON, facturas);
        return true;
    }

    @Override
    public Factura leer(String id) {
        for (Factura f : facturas) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Factura f) {
        String letra = f.getLetra();
        LocalDate fecha = f.getFecha();
        String clienteDNI = f.getClienteDNI();
        if (facturas != null) {
            for (int i = 0; i < facturas.size(); i++) {
                String letraFactura = facturas.get(i).getLetra();
                String clienteDNIFactura = facturas.get(i).getClienteDNI();
                LocalDate fechaFactura = facturas.get(i).getFecha();
                if (letra.equals(letraFactura) && clienteDNI.equals(clienteDNIFactura) && fecha.isEqual(fechaFactura)) {
                    facturas.set(i, f);
                    Utils.guardarDatosEnArchivo(FACTURAS_JSON, facturas);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Factura t) {
        boolean removed = false;
        if (facturas != null) {
            removed = facturas.remove(t);  // Eliminar factura de la lista
            if (removed) {
                Utils.guardarDatosEnArchivo(FACTURAS_JSON, facturas);
            }
        }
        return removed;
    }

    @Override
    public List<Factura> leerTodos() {
        if (facturas != null) {
            return facturas;
        }
        return null;
    }
*/
}

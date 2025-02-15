/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.CLIENTES_COLECCION;
import static es.ujaen.tfg.utils.Utils.CLIENTES_JSON;
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
public final class ClienteDAO implements InterfazDAO<Cliente> {

    private final Firestore db;
    private final String userId;
    private List<Cliente> clientesCache; 
    private static final String CACHE_FILE = "clientes_.json";
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ClienteDAO(String userId) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.userId = userId;
        this.clientesCache = cargarDesdeCache();  
        sincronizarDesdeFirebase();  
        iniciarSincronizacionPeriodica();
        agregarShutdownHook();
    }

    // ✅ Manejo mejorado de errores al cargar la caché
    private List<Cliente> cargarDesdeCache() {
        try {
            String json = new String(Files.readAllBytes(Paths.get(CACHE_FILE)));
            return new Gson().fromJson(json, new TypeToken<List<Cliente>>() {}.getType());
        } catch (IOException e) {
            System.err.println("No se pudo cargar la caché. Se creará una nueva lista.");
            return new ArrayList<>();
        }
    }

    // ✅ Guardar la caché localmente con manejo de errores
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(CACHE_FILE)) {
            new Gson().toJson(clientesCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché: " + e.getMessage());
        }
    }

    // ✅ Descarga la subcolección de clientes al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Cliente> clientes = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection("usuarios")
                    .document(userId)
                    .collection("clientes")
                    .get();

            for (QueryDocumentSnapshot document : future.get().getDocuments()) {
                clientes.add(document.toObject(Cliente.class));
            }

            clientesCache = clientes;
            guardarEnCache();
            cambiosPendientes = false;  // 🔹 Marcamos que no hay cambios pendientes
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error sincronizando con Firebase: " + e.getMessage());
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
            for (Cliente cliente : clientesCache) {
                db.collection("usuarios").document(userId)
                        .collection("clientes")
                        .document(cliente.getDNI())
                        .set(cliente);
            }
            cambiosPendientes = false;
            System.out.println("Caché sincronizada con Firebase.");
        });
    }

    // ✅ CREAR cliente (trabaja en caché y luego sube a Firebase en segundo plano)
    public boolean crear(Cliente cliente) {
        clientesCache.add(cliente);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection("usuarios").document(userId)
                    .collection("clientes")
                    .document(cliente.getDNI())
                    .set(cliente);
        });

        return true;
    }

    // ✅ LEER cliente desde caché
    public Cliente leer(String DNI) {
        return clientesCache.stream()
                .filter(cliente -> cliente.getDNI().equals(DNI))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR cliente (modifica en caché y lo sube a Firebase en segundo plano)
    public boolean actualizar(Cliente cliente) {
        for (int i = 0; i < clientesCache.size(); i++) {
            if (clientesCache.get(i).getDNI().equals(cliente.getDNI())) {
                clientesCache.set(i, cliente);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection("usuarios").document(userId)
                            .collection("clientes")
                            .document(cliente.getDNI())
                            .set(cliente);
                });

                return true;
            }
        }
        return false;
    }

    // ✅ BORRAR cliente (elimina en caché y en Firebase en segundo plano)
    public boolean borrar(Cliente cliente) {
        boolean eliminado = clientesCache.remove(cliente);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection("usuarios").document(userId)
                        .collection("clientes")
                        .document(cliente.getDNI())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODOS los clientes desde caché
    public List<Cliente> leerTodos() {
        return new ArrayList<>(clientesCache);
    }

    /* Esto ahora funca con el usuario logueado... vamos a hacer una cache para que vaya más rapido
    private final Firestore db;
    private final String userId;  // Identificador del usuario autenticado

    public ClienteDAO(String userId) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.userId = userId;
    }

    // 📌 Crear un nuevo cliente en la base de datos del usuario autenticado
    @Override
    public boolean crear(Cliente cliente) {
        try {
            db.collection("usuarios").document(userId)
                    .collection("clientes")
                    .document(cliente.getDNI()) // Usamos el DNI como ID del documento
                    .set(cliente)
                    .get();  // Bloqueante para asegurarnos de que se complete la operación

            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al crear cliente: " + e.getMessage());
            return false;
        }
    }

    // 📌 Leer un cliente por su DNI
    @Override
    public Cliente leer(String DNI) {
        try {
            DocumentSnapshot document = db.collection("usuarios").document(userId)
                    .collection("clientes")
                    .document(DNI)
                    .get()
                    .get();  // Bloqueante

            if (document.exists()) {
                return document.toObject(Cliente.class);
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al leer cliente: " + e.getMessage());
        }
        return null;
    }

    // 📌 Actualizar un cliente
    @Override
    public boolean actualizar(Cliente cliente) {
        try {
            db.collection("usuarios").document(userId)
                    .collection("clientes")
                    .document(cliente.getDNI())
                    .set(cliente)
                    .get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al actualizar cliente: " + e.getMessage());
            return false;
        }
    }

    // 📌 Eliminar un cliente
    @Override
    public boolean borrar(Cliente cliente) {
        try {
            db.collection("usuarios").document(userId)
                    .collection("clientes")
                    .document(cliente.getDNI())
                    .delete()
                    .get();

            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al borrar cliente: " + e.getMessage());
            return false;
        }
    }

    // 📌 Obtener la lista de todos los clientes del usuario
    @Override
    public List<Cliente> leerTodos() {
        List<Cliente> clientes = new ArrayList<>();
        try {
            ApiFuture<QuerySnapshot> future = db.collection("usuarios")
                    .document(userId)
                    .collection("clientes")
                    .get();

            List<QueryDocumentSnapshot> documents = future.get().getDocuments();
            for (QueryDocumentSnapshot document : documents) {
                clientes.add(document.toObject(Cliente.class));
            }
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al obtener clientes: " + e.getMessage());
        }
        return clientes;
    }

    /*
    private List<Cliente> clientes;
    private final String ultimoHashArchivo;

    public ClienteDAO() throws IOException {
        this.clientes = cargarDatosDesdeArchivo(
                CLIENTES_JSON,
                new TypeToken<List<Cliente>>() {
                }.getType()
        );
        /*
        this.clientes = cargarDatosDesdeFirebase(
                CLIENTES_JSON, 
                CLIENTES_COLECCION, 
                Cliente.class
        );
     */
 /*
        this.ultimoHashArchivo = calcularHashArchivo(CLIENTES_JSON);
        iniciarSincronizacionPeriodica(
                CLIENTES_JSON,
                CLIENTES_COLECCION,
                ultimoHashArchivo,
                clientes,
                Cliente::getDNI
        );
        agregarShutdownHook();
    }

    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        CLIENTES_JSON,
                        CLIENTES_COLECCION,
                        ultimoHashArchivo,
                        clientes,
                        Cliente::getDNI
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
    public boolean crear(Cliente t) {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        clientes.add(t);
        Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
        return true;
    }
    
    //@Override
    public boolean crear(Cliente t, int index) {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        //clientes.add(t);
        clientes.add(index, t);
        Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
        return true;
    }

    @Override
    public Cliente leer(String DNI) {
        for (Cliente cliente : clientes) {
            if (cliente.getDNI().equals(DNI)) {
                return cliente;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Cliente c) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDNI().equals(c.getDNI())) {
                clientes.set(i, c);
                Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Cliente t) {
        boolean removed = clientes.remove(t);
        if (removed) {
            Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
        }
        return removed;
    }

    @Override
    public List<Cliente> leerTodos() {
        return clientes;
    }
     */
}

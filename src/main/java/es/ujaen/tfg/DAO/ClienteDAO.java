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
import es.ujaen.tfg.modelo.Cliente;
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
    private final String email;
    private List<Cliente> clientesCache;
    private static final String CACHE_FILE = "clientes_.json";
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public ClienteDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        if (this.clientesCache == null) {
            sincronizarDesdeFirebase();
        }
        iniciarSincronizacionPeriodica();
        agregarShutdownHook();
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
                    .document(email)
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
    public void sincronizarConFirebase() {
        if (cambiosPendientes) {
            executorService.submit(() -> {
                for (Cliente cliente : clientesCache) {
                    db.collection("usuarios").document(email)
                            .collection("clientes")
                            .document(cliente.getDNI())
                            .set(cliente);
                }
                cambiosPendientes = false;
                System.out.println("Caché sincronizada con Firebase.");
            });
        }
    }

    public void limpiarCache() throws IOException {
        if (Files.exists(Paths.get(CACHE_FILE))) {
            Files.delete(Paths.get(CACHE_FILE));
        }
    }

    // ✅ CREAR cliente (trabaja en caché y luego sube a Firebase en segundo plano)
    public boolean crear(Cliente cliente) {
        clientesCache.add(cliente);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection("usuarios").document(email)
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
                    db.collection("usuarios").document(email)
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
                db.collection("usuarios").document(email)
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
}

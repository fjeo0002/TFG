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
import static es.ujaen.tfg.utils.Utils.CLIENTES_COLECCION;
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
public final class ClienteDAO implements InterfazDAO<Cliente> {

    private final Firestore db;
    private final String email;
    private List<Cliente> clientesCache;
    private boolean cambiosPendientes = false;
    private Timer timer;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    private final Path archivoCache;

    public ClienteDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        
        this.archivoCache = Files.createTempFile("clientes_", ".json");
        this.archivoCache.toFile().deleteOnExit();
        
        if (this.clientesCache == null) {
            sincronizarDesdeFirebase();
        }
        //iniciarSincronizacionPeriodica();
        //agregarShutdownHook();
    }

    // ✅ Guardar la caché localmente con manejo de errores
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(archivoCache.toString())) {
            new Gson().toJson(clientesCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché: " + e.getMessage());
        }
    }

    // ✅ Descarga la subcolección de clientes al iniciar sesión
    public void sincronizarDesdeFirebase() {
        try {
            List<Cliente> clientes = new ArrayList<>();
            ApiFuture<QuerySnapshot> future = db.collection(USUARIOS_COLECCION)
                    .document(email)
                    .collection(CLIENTES_COLECCION)
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
                for (Cliente cliente : clientesCache) {
                    db.collection(USUARIOS_COLECCION).document(email)
                            .collection(CLIENTES_COLECCION)
                            .document(cliente.getDNI())
                            .set(cliente);
                }
                cambiosPendientes = false;
            });
        }
    }

    public void limpiarCache() throws IOException {
        Files.deleteIfExists(archivoCache);
        /*
        if (Files.exists(Paths.get(CLIENTES_JSON))) {
            Files.delete(Paths.get(CLIENTES_JSON));
        }
*/
    }

    // ✅ CREAR cliente (trabaja en caché y luego sube a Firebase en segundo plano)
    @Override
    public boolean crear(Cliente cliente) {
        clientesCache.add(cliente);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(CLIENTES_COLECCION)
                    .document(cliente.getDNI())
                    .set(cliente);
        });

        return true;
    }

    // ✅ CREAR local con index (modifica la caché y luego sube a Firebase en segundo plano)
    public boolean crear(Cliente local, int index) {
        clientesCache.add(index, local);
        guardarEnCache();
        cambiosPendientes = true;

        executorService.submit(() -> {
            db.collection(USUARIOS_COLECCION).document(email)
                    .collection(CLIENTES_COLECCION)
                    .document(local.getDNI())
                    .set(local);
        });

        return true;
    }

    // ✅ LEER cliente desde caché
    @Override
    public Cliente leer(String DNI) {
        return clientesCache.stream()
                .filter(cliente -> cliente.getDNI().equals(DNI))
                .findFirst()
                .orElse(null);
    }

    // ✅ ACTUALIZAR cliente (modifica en caché y lo sube a Firebase en segundo plano)
    @Override
    public boolean actualizar(Cliente cliente) {
        for (int i = 0; i < clientesCache.size(); i++) {
            if (clientesCache.get(i).getDNI().equals(cliente.getDNI())) {
                clientesCache.set(i, cliente);
                guardarEnCache();
                cambiosPendientes = true;

                executorService.submit(() -> {
                    db.collection(USUARIOS_COLECCION).document(email)
                            .collection(CLIENTES_COLECCION)
                            .document(cliente.getDNI())
                            .set(cliente);
                });

                return true;
            }
        }
        return false;
    }

    // ✅ BORRAR cliente (elimina en caché y en Firebase en segundo plano)
    @Override
    public boolean borrar(Cliente cliente) {
        boolean eliminado = clientesCache.remove(cliente);
        if (eliminado) {
            guardarEnCache();
            cambiosPendientes = true;

            executorService.submit(() -> {
                db.collection(USUARIOS_COLECCION).document(email)
                        .collection(CLIENTES_COLECCION)
                        .document(cliente.getDNI())
                        .delete();
            });
        }
        return eliminado;
    }

    // ✅ LEER TODOS los clientes desde caché
    @Override
    public List<Cliente> leerTodos() {
        return new ArrayList<>(clientesCache);
    }
}

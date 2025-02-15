/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Usuario;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author jota
 */
public final class UsuarioDAO implements InterfazDAO<Usuario> {

    private final Firestore db;
    private final String email;
    private Usuario usuarioCache;
    private static final String CACHE_FILE = "usuario_.json";
    private boolean cambiosPendientes = false;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public UsuarioDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        this.usuarioCache = cargarDesdeCache();
        if (this.usuarioCache == null) {
            sincronizarDesdeFirebase();
        }
        //sincronizarDesdeFirebase();
        agregarShutdownHook();
    }

    // ✅ Cargar usuario desde la caché local
    private Usuario cargarDesdeCache() {
        try {
            if (!Files.exists(Paths.get(CACHE_FILE))) {
                return null;
            }
            String json = new String(Files.readAllBytes(Paths.get(CACHE_FILE)));
            return new Gson().fromJson(json, Usuario.class);
        } catch (IOException e) {
            System.err.println("No se pudo cargar la caché del usuario. Se creará una nueva.");
            return null;
        }
    }

    // ✅ Guardar usuario en la caché local
    private void guardarEnCache() {
        try (FileWriter writer = new FileWriter(CACHE_FILE)) {
            new Gson().toJson(usuarioCache, writer);
        } catch (IOException e) {
            System.err.println("Error guardando caché de usuario: " + e.getMessage());
        }
    }

    // ✅ Sincronizar usuario desde Firestore al iniciar sesión
    public void sincronizarDesdeFirebase() {
        executorService.submit(() -> {
            try {
                DocumentSnapshot document = db.collection("usuarios").document(email).get().get();
                if (document.exists()) {
                    usuarioCache = document.toObject(Usuario.class);
                    guardarEnCache();
                    cambiosPendientes = false;
                    System.out.println("Usuario sincronizado desde Firebase.");
                }
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Error sincronizando usuario con Firebase: " + e.getMessage());
            }
        });
    }

    // ✅ Guardar cambios en Firebase antes de cerrar la aplicación
    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (cambiosPendientes) {
                sincronizarConFirebase();
            }
            executorService.shutdown();
        }));
    }

    // ✅ Sincronizar caché con Firebase en segundo plano
    private void sincronizarConFirebase() {
        executorService.submit(() -> {
            if (usuarioCache != null) {
                db.collection("usuarios").document(email).set(usuarioCache);
                cambiosPendientes = false;
                System.out.println("Caché de usuario sincronizada con Firebase.");
            }
        });
    }

    // ✅ CREAR usuario en caché y Firebase
    @Override
    public boolean crear(Usuario usuario) {
        this.usuarioCache = usuario;
        guardarEnCache();
        cambiosPendientes = true;
        sincronizarConFirebase();
        return true;
    }

    // ✅ LEER usuario desde caché
    @Override
    public Usuario leer(String id) {
        return usuarioCache;
    }

    // ✅ ACTUALIZAR usuario en caché y Firebase
    @Override
    public boolean actualizar(Usuario usuario) {
        this.usuarioCache = new Usuario(usuario);
        guardarEnCache();
        cambiosPendientes = true;
        sincronizarConFirebase();
        return true;
    }

    // ✅ BORRAR usuario de la caché y Firebase
    @Override
    public boolean borrar(Usuario usuario) {
        this.usuarioCache = null;
        try {
            Files.deleteIfExists(Paths.get(CACHE_FILE));
            db.collection("usuarios").document(email).delete();
            return true;
        } catch (IOException e) {
            System.err.println("Error al borrar usuario: " + e.getMessage());
            return false;
        }
    }

    // ✅ LEER TODOS (no aplica, solo hay un usuario logueado)
    @Override
    public List<Usuario> leerTodos() {
        throw new UnsupportedOperationException("Operación no soportada para UsuarioDAO.");
    }

    public boolean verificarCredenciales(String email, String password) {
        try {
            // Obtener el documento del usuario directamente
            DocumentSnapshot document = db.collection("usuarios").document(email).get().get();

            // Obtener la contraseña almacenada y compararla con el hash de la ingresada
            String contrasenaGuardada = document.getString("contrasena");

            if (contrasenaGuardada != null) {
                String passwordHash = Usuario.hashContrasena(password);
                if (contrasenaGuardada.equals(passwordHash)) {
                    return true;
                }
            }
            return false;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al verificar credenciales: " + e.getMessage());
            return false;
        }
    }
}

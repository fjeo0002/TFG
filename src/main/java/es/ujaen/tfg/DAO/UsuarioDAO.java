/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Usuario;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author jota
 */
public final class UsuarioDAO implements InterfazDAO<Usuario> {

    
    private final Firestore db;
    private final String email;

    public UsuarioDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
    }

    // ✅ CREAR usuario en Firebase y luego actualizar caché
    @Override
    public boolean crear(Usuario usuario) {
        try {
            // 🔹 Guardar usuario en Firebase primero
            db.collection("usuarios").document(usuario.getEmail()).set(usuario).get();
            
            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al crear usuario en Firebase: " + e.getMessage());
            return false;
        }
    }
    
    // ✅ LEER usuario directamente desde Firebase
    @Override
    public Usuario leer(String id) {
        try {
            DocumentSnapshot document = db.collection("usuarios").document(id).get().get();

            if (document.exists()) {
                Usuario usuario = document.toObject(Usuario.class);
                return usuario;
            } else {
                return null; // 🔹 Usuario no encontrado
            }

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al leer usuario desde Firebase: " + e.getMessage());
            return null;
        }
    }

    // ✅ ACTUALIZAR usuario en Firebase y luego actualizar caché
    @Override
    public boolean actualizar(Usuario usuario) {
        try {
            db.collection("usuarios").document(email).set(usuario).get();
            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al actualizar usuario en Firebase: " + e.getMessage());
            return false;
        }
    }

    // ✅ BORRAR usuario correctamente al cerrar sesión
    @Override
    public boolean borrar(Usuario usuario) {
        try {
            // 🔹 Borrar de Firebase
            db.collection("usuarios").document(email).delete().get();

            return true;
        } catch (InterruptedException | ExecutionException  e) {
            System.err.println("Error al borrar usuario: " + e.getMessage());
            return false;
        }
    }

    // ✅ VERIFICAR credenciales en Firebase
    public boolean verificarCredenciales(String email, String password) {
        try {
            DocumentSnapshot document = db.collection("usuarios").document(email).get().get();

            if (document.exists()) {
                String contrasenaGuardada = document.getString("contrasena");

                if (contrasenaGuardada != null && contrasenaGuardada.equals(Usuario.hashContrasena(password))) {
                    return true;
                }
            }
            return false;

        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al verificar credenciales: " + e.getMessage());
            return false;
        }
    }

    // ✅ LEER TODOS (no aplica, solo hay un usuario logueado)
    @Override
    public java.util.List<Usuario> leerTodos() {
        throw new UnsupportedOperationException("Operación no soportada para UsuarioDAO.");
    }
    /*
    private final Firestore db;
    private final String email;
    private Usuario usuarioCache;
    private static final String CACHE_FILE = "usuario_.json";
    private boolean cambiosPendientes = false;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    public UsuarioDAO(String email) throws IOException {
        this.db = FirebaseInitializer.getInstance().getDb();
        this.email = email;
        //this.usuarioCache = cargarDesdeCache();
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
    private void agregarShutdownHook() throws IOException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            if (cambiosPendientes) {
                sincronizarConFirebase();
            }
            executorService.shutdown();
        }));
        //Files.delete(Paths.get(CACHE_FILE));
    }

    // ✅ Sincronizar caché con Firebase en segundo plano
    public void sincronizarConFirebase() {
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
            
            if(document == null) return false;

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
    */
}

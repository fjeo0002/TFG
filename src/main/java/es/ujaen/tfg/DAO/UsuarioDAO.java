/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.modelo.Usuario;
import static es.ujaen.tfg.utils.Utils.USUARIOS_COLECCION;
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
            db.collection(USUARIOS_COLECCION).document(usuario.getEmail()).set(usuario).get();
            
            return true;
        } catch (InterruptedException | ExecutionException e) {
            System.err.println("Error al crear usuario en Firebase: " + e.getMessage());
            return false;
        }
    }
    
    // ✅ LEER usuario directamente desde Firebase
    @Override
    public Usuario leer(String email) {
        try {
            DocumentSnapshot document = db.collection(USUARIOS_COLECCION).document(email).get().get();

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
            db.collection(USUARIOS_COLECCION).document(email).set(usuario).get();
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
            db.collection(USUARIOS_COLECCION).document(email).delete().get();

            return true;
        } catch (InterruptedException | ExecutionException  e) {
            System.err.println("Error al borrar usuario: " + e.getMessage());
            return false;
        }
    }

    // ✅ VERIFICAR credenciales en Firebase
    public boolean verificarCredenciales(String email, String password) {
        try {
            DocumentSnapshot document = db.collection(USUARIOS_COLECCION).document(email).get().get();

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
    
}

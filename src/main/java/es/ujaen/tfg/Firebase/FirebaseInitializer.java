/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.Firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import static es.ujaen.tfg.utils.Utils.FIREBASE_JSON;
import java.io.FileInputStream;
import java.io.IOException;

public class FirebaseInitializer {
    // Clase Singleton
    private static FirebaseInitializer instance; // Instancia única
    private final Firestore db;

    // Constructor privado para evitar instanciación externa
    private FirebaseInitializer() throws IOException {
        // Cargar el archivo de credenciales JSON de la cuenta de servicio
        FileInputStream serviceAccount = new FileInputStream(FIREBASE_JSON);

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .build();

        // Inicializar Firebase
        FirebaseApp.initializeApp(options);
        this.db = FirestoreClient.getFirestore();
    }

    // Método estático para obtener la instancia única
    public static synchronized FirebaseInitializer getInstance() throws IOException {
        if (instance == null) {  // Crear instancia si no existe
            instance = new FirebaseInitializer();
        }
        return instance;
    }

    // Obtener la instancia de Firestore
    public Firestore getDb() {
        return db;
    }
}

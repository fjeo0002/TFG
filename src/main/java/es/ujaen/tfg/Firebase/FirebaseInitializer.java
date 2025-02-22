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
import io.grpc.LoadBalancerRegistry;
import io.grpc.internal.PickFirstLoadBalancerProvider;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FirebaseInitializer {

    // Clase Singleton
    private static FirebaseInitializer instance; // Instancia única
    private Firestore db;
    private Path archivoCache;

    // Constructor privado para evitar instanciación externa
    private FirebaseInitializer() throws IOException {
        // Esto es porque hay un fallo: 
        // Al generar el JAR, no se hace bien la conexión.
        // Lo encuentro en un foro de StackOverFlow: 
        // https://stackoverflow.com/questions/59076255/compiled-jar-keeps-throwing-uncaught-exception-in-the-synchronizationcontext
        LoadBalancerRegistry.getDefaultRegistry().register(new PickFirstLoadBalancerProvider());
        // Cargar el archivo de credenciales JSON de la cuenta de servicio
        //FileInputStream serviceAccount = new FileInputStream(FIREBASE_JSON);

        // Obtener el archivo desde el JAR
        InputStream inputStream = getClass().getResourceAsStream(FIREBASE_JSON);
        if (inputStream == null) {
            System.out.println("No se encontró el archivo dentro del JAR.");
            return;
        }

        // Crear un archivo temporal
        File tempFile = File.createTempFile("firebase_", ".json");
        this.archivoCache = tempFile.toPath();
        tempFile.deleteOnExit();  // Eliminar automáticamente el archivo temporal cuando se cierre el programa

        // Copiar el contenido del archivo del JAR al archivo temporal
        try (FileOutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        }

        // Usar FileInputStream con el archivo temporal
        FileInputStream serviceAccount = new FileInputStream(tempFile);

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

    public void limpiarCache() throws IOException {
        Files.deleteIfExists(archivoCache);
    }
}

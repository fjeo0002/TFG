/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Anticipo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class AnticipoDAO implements InterfazDAO<Anticipo> {

    private List<Anticipo> anticipos;  
    private static final String FILE_PATH = "anticipos.json";

    public AnticipoDAO() {
        this.anticipos = cargarDatosDesdeArchivo();  // Inicializar la lista desde el archivo
    }
    
    private List<Anticipo> cargarDatosDesdeArchivo() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {  // Si el archivo no existe
                return new ArrayList<>();  // Retornar una lista vacía
            }
            String jsonData = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            Type listType = new TypeToken<List<Anticipo>>() {}.getType();  // Tipo de la lista de Anticipo
            return new Gson().fromJson(jsonData, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();  // En caso de error, retornar lista vacía
        }
    }
    
    private void guardarDatosEnArchivo() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(anticipos, writer);  // Guardar la lista de anticipos en formato JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public boolean crear(Anticipo t) {
        if (anticipos == null) {  // Verificar si la lista es nula
            anticipos = new ArrayList<>();
        }
        anticipos.add(t);  // Agregar anticipo a la lista
        guardarDatosEnArchivo();  // Guardar los cambios en el archivo
        return true;
    }

    @Override
    public Anticipo leer(String id) {
        for (Anticipo anticipo : anticipos) {
            if (anticipo.getId().equals(id)) {
                return anticipo;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Anticipo anticipo) {
        for (int i = 0; i < anticipos.size(); i++) {
            if (anticipos.get(i).getId().equals(anticipo.getId())) {
                anticipos.set(i, anticipo);
                guardarDatosEnArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Anticipo t) {
        boolean removed = anticipos.remove(t);  // Eliminar anticipo de la lista
        if (removed) {
            guardarDatosEnArchivo();  // Guardar los cambios en el archivo
        }
        return removed;
    }

    @Override
    public List<Anticipo> leerTodos() {
        return anticipos;
    }

    public String convertirAJSON(Anticipo anticipo) {
        if (anticipo == null) {
            return "{}";
        }
        Gson gson = new Gson();
        return gson.toJson(anticipo);  // Convertir el objeto cliente a JSON
    }

    public String convertirListaAJSON() {
        Gson gson = new Gson();
        return gson.toJson(anticipos);  // Convertir la lista de clientes a JSON
    }
}

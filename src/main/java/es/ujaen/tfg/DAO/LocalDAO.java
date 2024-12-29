/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Local;

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
public class LocalDAO implements InterfazDAO<Local> {

    private List<Local> locales;
    private static final String FILE_PATH = "locales.json";

    public LocalDAO() {
        this.locales = cargarDatosDesdeArchivo();
    }

    private List<Local> cargarDatosDesdeArchivo() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {  // Si el archivo no existe
                return new ArrayList<>();  // Retornar una lista vacía
            }
            String jsonData = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            Type listType = new TypeToken<List<Local>>() {}.getType();  // Tipo de la lista de Local
            return new Gson().fromJson(jsonData, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();  // En caso de error, retornar lista vacía
        }
    }

    private void guardarDatosEnArchivo() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(locales, writer);  // Guardar la lista de locales en formato JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean crear(Local local) {
        if(locales == null){
            locales = new ArrayList<>();
        }
        locales.add(local);
        guardarDatosEnArchivo();
        return true;
    }

    @Override
    public Local leer(String codigo) {
        for (Local local : locales) {
            if (local.getCodigo().equals(codigo)) {
                return local;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Local local) {
        for (int i = 0; i < locales.size(); i++) {
            if (locales.get(i).getCodigo().equals(local.getCodigo())) {
                locales.set(i, local);
                guardarDatosEnArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Local local) {
        boolean removed = locales.remove(local);
        if (removed) {
            guardarDatosEnArchivo();
        }
        return removed;
    }

    @Override
    public List<Local> leerTodos() {
        return locales;
    }

    // Convertir un local a JSON (para otros usos)
    public String convertirAJSON(Local local) {
        if (local == null) {
            return "{}";
        }
        Gson gson = new Gson();
        return gson.toJson(local);  // Convertir el objeto local a JSON
    }

    // Convertir la lista de locales a JSON
    public String convertirListaAJSON() {
        Gson gson = new Gson();
        return gson.toJson(locales);  // Convertir la lista de locales a JSON
    }
}

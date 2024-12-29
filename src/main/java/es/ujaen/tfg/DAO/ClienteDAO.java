/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Cliente;

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
public class ClienteDAO implements InterfazDAO<Cliente> {

    private List<Cliente> clientes;
    private static final String FILE_PATH = "clientes.json";

    public ClienteDAO() {
        this.clientes = cargarDatosDesdeArchivo();
    }

    private List<Cliente> cargarDatosDesdeArchivo() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {  // Si el archivo no existe
                return new ArrayList<>();  // Retornar una lista vacía
            }
            String jsonData = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            Type listType = new TypeToken<List<Cliente>>() {}.getType();  // Tipo de la lista de Cliente
            return new Gson().fromJson(jsonData, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();  // En caso de error, retornar lista vacía
        }
    }

    private void guardarDatosEnArchivo() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new Gson();
            gson.toJson(clientes, writer);  // Guardar la lista de clientes en formato JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean crear(Cliente t) {
        if(clientes == null){
            clientes = new ArrayList<>();
        }
        clientes.add(t);
        guardarDatosEnArchivo();
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
    public boolean actualizar(Cliente t) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDNI().equals(t.getDNI())) {
                clientes.set(i, t);
                guardarDatosEnArchivo();
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Cliente t) {
        boolean removed = clientes.remove(t);
        if (removed) {
            guardarDatosEnArchivo();
        }
        return removed;
    }

    @Override
    public List<Cliente> leerTodos() {
        return clientes;
    }

    public Cliente buscarPorNombre(String nombre) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombre)) {
                return cliente;
            }
        }
        return null;
    }

    public Cliente buscarPorAlias(String alias) {
        for (Cliente cliente : clientes) {
            if (cliente.getAlias().equals(alias)) {
                return cliente;
            }
        }
        return null;
    }

    public String convertirAJSON(Cliente cliente) {
        if (cliente == null) {
            return "{}";
        }
        Gson gson = new Gson();
        return gson.toJson(cliente);  // Convertir el objeto cliente a JSON
    }

    public String convertirListaAJSON() {
        Gson gson = new Gson();
        return gson.toJson(clientes);  // Convertir la lista de clientes a JSON
    }
}

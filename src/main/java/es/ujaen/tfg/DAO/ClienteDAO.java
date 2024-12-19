/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import es.ujaen.tfg.modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class ClienteDAO implements InterfazDAO<Cliente> {

    private List<Cliente> clientes = new ArrayList<>(){{
        add(new Cliente("92345678A", 
                "Juan Pérez", 
                "Juanito", 
                "juan.perez@example.com",
                "Calle Falsa 123", 
                "Jaén", 
                "23001", 
                "Cliente habitual",
                "Al día",
                "0,00",
                "A"));
        add(new Cliente("87654321B", 
                "María López", 
                "M_Lopez", 
                "maria.lopez@example.com",
                "Avenida Real 45", 
                "Madrid", 
                "28001",
                "Cliente premium",
                "Debe",
                "-35,00", 
                "B"));
    }};

    public ClienteDAO() {
    }

    @Override
    public boolean crear(Cliente t) {
        clientes.add(t);
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
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Cliente t) {
        return clientes.remove(t);
    }

    @Override
    public List<Cliente> leerTodos() {
        return clientes;
    }
    
    public String convertirAJSON(Cliente cliente) {
        if (cliente == null) return "{}";

        JsonObject json = new JsonObject();
        json.addProperty("DNI", cliente.getDNI());
        json.addProperty("nombre", cliente.getNombre());
        json.addProperty("alias", cliente.getAlias());
        json.addProperty("correo", cliente.getEmail());
        json.addProperty("direccion", cliente.getDireccion());
        json.addProperty("localidad", cliente.getLocalidad());
        json.addProperty("codigoPostal", cliente.getCodigoPostal());
        json.addProperty("descripcion", cliente.getDescripcion());
        json.addProperty("tipo", cliente.getTipo());

        return json.toString();
    }
    
    public String convertirListaAJSON() {
        JsonArray jsonArray = new JsonArray();

        for (Cliente cliente : clientes) {
            JsonObject json = new JsonObject();
            json.addProperty("DNI", cliente.getDNI());
            json.addProperty("nombre", cliente.getNombre());
            json.addProperty("alias", cliente.getAlias());
            json.addProperty("correo", cliente.getEmail());
            json.addProperty("direccion", cliente.getDireccion());
            json.addProperty("ciudad", cliente.getLocalidad());
            json.addProperty("codigoPostal", cliente.getCodigoPostal());
            json.addProperty("descripcion", cliente.getDescripcion());
            json.addProperty("tipo", cliente.getTipo());
            jsonArray.add(json);
        }

        return jsonArray.toString();
    }
}

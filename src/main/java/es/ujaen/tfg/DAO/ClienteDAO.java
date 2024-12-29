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

    private List<Cliente> clientes = new ArrayList<>() {
        {
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
            add(new Cliente("11223344C",
                    "Carlos García",
                    "Carlos_G",
                    "carlos.garcia@example.com",
                    "Calle del Sol 12",
                    "Granada",
                    "18001",
                    "Cliente ocasional",
                    "Anticipa",
                    "15,50",
                    "A"));
            add(new Cliente("22334455D",
                    "Ana Sánchez",
                    "AnaS",
                    "ana.sanchez@example.com",
                    "Calle Nueva 78",
                    "Sevilla",
                    "41001",
                    "Cliente nuevo",
                    "Debe",
                    "-10,00",
                    "B"));
            add(new Cliente("33445566E",
                    "Luis Fernández",
                    "LuisF",
                    "luis.fernandez@example.com",
                    "Plaza Mayor 5",
                    "Córdoba",
                    "14001",
                    "Cliente habitual",
                    "Al día",
                    "0,00",
                    "A"));
            add(new Cliente("44556677F",
                    "Patricia Jiménez",
                    "Patricia_J",
                    "patricia.jimenez@example.com",
                    "Avenida de la Constitución 90",
                    "Jaén",
                    "23002",
                    "Cliente VIP",
                    "Anticipa",
                    "50,00",
                    "A"));
            add(new Cliente("55667788G",
                    "Javier Martínez",
                    "Javi_M",
                    "javier.martinez@example.com",
                    "Calle Real 23",
                    "Almería",
                    "04001",
                    "Cliente regular",
                    "Anticipa",
                    "20,00",
                    "B"));
            add(new Cliente("66778899H",
                    "Marta Rodríguez",
                    "Marta_R",
                    "marta.rodriguez@example.com",
                    "Calle de la Luna 45",
                    "Madrid",
                    "28002",
                    "Cliente de empresa",
                    "Anticipa",
                    "100,00",
                    "A"));
        }
    };

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

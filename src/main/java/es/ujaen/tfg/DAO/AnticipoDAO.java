/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import es.ujaen.tfg.modelo.Anticipo;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jota
 */
public class AnticipoDAO implements InterfazDAO<Anticipo> {

    private List<Anticipo> anticipos = new ArrayList<>();

    public AnticipoDAO() {
    }

    @Override
    public boolean crear(Anticipo anticipo) {
        anticipos.add(anticipo);
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
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Anticipo anticipo) {
        return anticipos.remove(anticipo);
    }

    @Override
    public List<Anticipo> leerTodos() {
        return anticipos;
    }

    public String convertirAJSON(Anticipo anticipo) {
        if (anticipo == null) {
            return "{}";
        }

        JsonObject json = new JsonObject();
        json.addProperty("ID", anticipo.getId());
        json.addProperty("monto", anticipo.getMonto());
        json.addProperty("mesesCubiertos", anticipo.getMesesCubiertos());
        json.addProperty("fecha", anticipo.getFecha().toString());

        return json.toString();
    }

    public String convertirListaAJSON() {
        JsonArray jsonArray = new JsonArray();

        for (Anticipo anticipo : anticipos) {
            JsonObject json = new JsonObject();
            json.addProperty("ID", anticipo.getId());
            json.addProperty("monto", anticipo.getMonto());
            json.addProperty("mesesCubiertos", anticipo.getMesesCubiertos());
            json.addProperty("fecha", anticipo.getFecha().toString());
            jsonArray.add(json);
        }

        return jsonArray.toString();
    }
}

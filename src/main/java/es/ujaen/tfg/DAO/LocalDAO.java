/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import es.ujaen.tfg.modelo.Local;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class LocalDAO implements InterfazDAO<Local>{
    
    private List<Local> locales = new ArrayList<>() {{
        add(new Local("L001", "Artículo 1", "Alias1", "10,50"));
        add(new Local("L002", "Artículo 2", "Alias2", "20,00"));
    }};;

    public LocalDAO() {
    }

    @Override
    public boolean crear(Local local) {
        locales.add(local);
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
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Local local) {
        return locales.remove(local);
    }

    @Override
    public List<Local> leerTodos() {
        return locales;
    }
    
    public String convertirAJSON(Local local) {
        if (local == null) return "{}";

        JsonObject json = new JsonObject();
        json.addProperty("codigo", local.getCodigo());
        json.addProperty("articulo", local.getNombre());
        json.addProperty("alias", local.getAlias());
        json.addProperty("precio", local.getPrecio());

        return json.toString();
    }
    
    public String convertirListaAJSON() {
        JsonArray jsonArray = new JsonArray();

        for (Local local : locales) {
            JsonObject json = new JsonObject();
            json.addProperty("codigo", local.getCodigo());
            json.addProperty("articulo", local.getNombre());
            json.addProperty("alias", local.getAlias());
            json.addProperty("precio", local.getPrecio());
            jsonArray.add(json);
        }

        return jsonArray.toString();
    }
}

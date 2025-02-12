/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.LOCALES_COLECCION;
import static es.ujaen.tfg.utils.Utils.LOCALES_JSON;
import static es.ujaen.tfg.utils.Utils.calcularHashArchivo;
import static es.ujaen.tfg.utils.Utils.cargarDatosDesdeArchivo;
import static es.ujaen.tfg.utils.Utils.iniciarSincronizacionPeriodica;
import static es.ujaen.tfg.utils.Utils.sincronizarConFirebase;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public final class LocalDAO implements InterfazDAO<Local> {

    private List<Local> locales;
    private final String ultimoHashArchivo;

    public LocalDAO() throws IOException {
        this.locales = cargarDatosDesdeArchivo(
                LOCALES_JSON,
                new TypeToken<List<Local>>() {
                }.getType()
        );
        /*
        this.locales = cargarDatosDesdeFirebase(
                LOCALES_JSON, 
                LOCALES_COLECCION, 
                Local.class
        );
         */
        this.ultimoHashArchivo = calcularHashArchivo(LOCALES_JSON);
        iniciarSincronizacionPeriodica(
                LOCALES_JSON,
                LOCALES_COLECCION,
                ultimoHashArchivo,
                locales,
                Local::getCodigo
        );
        agregarShutdownHook();
    }

    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        LOCALES_JSON,
                        LOCALES_COLECCION,
                        ultimoHashArchivo,
                        locales,
                        Local::getCodigo
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
    public boolean crear(Local t) {
        if (locales == null) {
            locales = new ArrayList<>();
        }
        locales.add(t);
        Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
        return true;
    }
    
    //@Override
    public boolean crear(Local local, int index) {
        if (locales == null) {
            locales = new ArrayList<>();
        }
        //locales.add(local);
        locales.add(index, local);
        Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
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
                Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Local local) {
        boolean removed = locales.remove(local);
        if (removed) {
            Utils.guardarDatosEnArchivo(LOCALES_JSON, locales);
        }
        return removed;
    }

    @Override
    public List<Local> leerTodos() {
        return locales;
    }

}

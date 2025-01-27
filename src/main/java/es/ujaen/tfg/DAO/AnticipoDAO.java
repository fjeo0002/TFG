/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.utils.LocalDateAdapterGson;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.ANTICIPOS_COLECCION;
import static es.ujaen.tfg.utils.Utils.ANTICIPOS_JSON;
import static es.ujaen.tfg.utils.Utils.calcularHashArchivo;
import static es.ujaen.tfg.utils.Utils.cargarDatosDesdeArchivo;
import static es.ujaen.tfg.utils.Utils.iniciarSincronizacionPeriodica;
import static es.ujaen.tfg.utils.Utils.sincronizarConFirebase;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public final class AnticipoDAO implements InterfazDAO<Anticipo> {

    private List<Anticipo> anticipos;
    private final String ultimoHashArchivo;

    public AnticipoDAO() throws IOException {
        this.anticipos = cargarDatosDesdeArchivo(
                ANTICIPOS_JSON,
                new TypeToken<List<Anticipo>>() {
                }.getType()
        );
        /*
        this.anticipos = cargarDatosDesdeFirebase(
                ANTICIPOS_JSON, 
                ANTICIPOS_COLECCION, 
                Anticipo.class
        );
         */
        this.ultimoHashArchivo = calcularHashArchivo(ANTICIPOS_JSON);
        iniciarSincronizacionPeriodica(
                ANTICIPOS_JSON,
                ANTICIPOS_COLECCION,
                ultimoHashArchivo,
                anticipos,
                Anticipo::getId
        );
        agregarShutdownHook();
    }

    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        ANTICIPOS_JSON,
                        ANTICIPOS_COLECCION,
                        ultimoHashArchivo,
                        anticipos,
                        Anticipo::getId
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
    public boolean crear(Anticipo t) {
        if (anticipos == null) {  // Verificar si la lista es nula
            anticipos = new ArrayList<>();
        }
        anticipos.add(t);  // Agregar anticipo a la lista
        Utils.guardarDatosEnArchivo(ANTICIPOS_JSON, anticipos);  // Guardar los cambios en el archivo
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
                Utils.guardarDatosEnArchivo(ANTICIPOS_JSON, anticipos);  // Guardar los cambios en el archivo
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Anticipo t) {
        boolean removed = anticipos.remove(t);  // Eliminar anticipo de la lista
        if (removed) {
            Utils.guardarDatosEnArchivo(ANTICIPOS_JSON, anticipos);  // Guardar los cambios en el archivo
        }
        return removed;
    }

    @Override
    public List<Anticipo> leerTodos() {
        return anticipos;
    }

}

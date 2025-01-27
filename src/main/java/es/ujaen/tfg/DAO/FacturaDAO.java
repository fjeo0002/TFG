/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.utils.LocalDateAdapterGson;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.FACTURAS_COLECCION;
import static es.ujaen.tfg.utils.Utils.FACTURAS_JSON;
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
public class FacturaDAO implements InterfazDAO<Factura> {

    private List<Factura> facturas;
    private final String ultimoHashArchivo;

    public FacturaDAO() throws IOException {
        this.facturas = cargarDatosDesdeArchivo(
                FACTURAS_JSON,
                new TypeToken<List<Factura>>() {
                }.getType()
        );
        /*
        this.facturas = cargarDatosDesdeFirebase(
                FACTURAS_JSON, 
                FACTURAS_COLECCION, 
                Factura.class
        );
         */
        this.ultimoHashArchivo = calcularHashArchivo(FACTURAS_JSON);
        iniciarSincronizacionPeriodica(
                FACTURAS_JSON,
                FACTURAS_COLECCION,
                ultimoHashArchivo,
                facturas,
                Factura::getId
        );
        agregarShutdownHook();
    }

    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        FACTURAS_JSON,
                        FACTURAS_COLECCION,
                        ultimoHashArchivo,
                        facturas,
                        Factura::getId
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
    public boolean crear(Factura t) {
        if (facturas == null) {
            facturas = new ArrayList<>();
        }
        facturas.add(t);
        Utils.guardarDatosEnArchivo(FACTURAS_JSON, facturas);
        return true;
    }

    @Override
    public Factura leer(String id) {
        for (Factura f : facturas) {
            if (f.getId().equals(id)) {
                return f;
            }
        }
        return null;
    }

    /*
    public Factura leer(String letra, int numero, LocalDate fecha) {
        if (facturas != null) {
            for (Factura factura : facturas) {
                String letraFactura = factura.getLetra();
                int numeroFactura = factura.getNumero();
                LocalDate fechaFactura = factura.getFecha();
                if (letra.equals(letraFactura) && numero == numeroFactura && fecha.isEqual(fechaFactura)) {
                    return factura;
                }
            }
        }
        return null;
    }
     */
    @Override
    public boolean actualizar(Factura f) {
        String letra = f.getLetra();
        int numero = f.getNumero();
        LocalDate fecha = f.getFecha();
        if (facturas != null) {
            for (int i = 0; i < facturas.size(); i++) {
                String letraFactura = facturas.get(i).getLetra();
                int numeroFactura = facturas.get(i).getNumero();
                LocalDate fechaFactura = facturas.get(i).getFecha();
                if (letra.equals(letraFactura) && numero == numeroFactura && fecha.isEqual(fechaFactura)) {
                    facturas.set(i, f);
                    Utils.guardarDatosEnArchivo(FACTURAS_JSON, facturas);
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Factura t) {
        boolean removed = false;
        if (facturas != null) {
            removed = facturas.remove(t);  // Eliminar factura de la lista
            if (removed) {
                Utils.guardarDatosEnArchivo(FACTURAS_JSON, facturas);
            }
        }
        return removed;
    }

    @Override
    public List<Factura> leerTodos() {
        if (facturas != null) {
            return facturas;
        }
        return null;
    }

}

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
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class FacturaDAO implements InterfazDAO<Factura> {

    private List<Factura> facturas;
    private static final String FILE_PATH = "facturas.json";

    public FacturaDAO() {
        this.facturas = cargarDatosDesdeArchivo();
    }

    private List<Factura> cargarDatosDesdeArchivo() {
        try {
            if (!Files.exists(Paths.get(FILE_PATH))) {  // Si el archivo no existe
                return new ArrayList<>();  // Retornar una lista vacía
            }
            String jsonData = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
            Type listType = new TypeToken<List<Factura>>() {
            }.getType();  // Tipo de la lista de Anticipo
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapterGson()) // Registrar el adaptador
                    .create();

            return gson.fromJson(jsonData, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;  // En caso de error, retornar lista vacía
        }
    }

    private void guardarDatosEnArchivo() {
        try (FileWriter writer = new FileWriter(FILE_PATH)) {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateAdapterGson()) // Registrar el adaptador
                    .create();

            gson.toJson(facturas, writer);  // Guardar la lista de anticipos en formato JSON
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean crear(Factura t) {
        if (facturas == null) {
            facturas = new ArrayList<>();
        }
        facturas.add(t);
        guardarDatosEnArchivo();
        return true;
    }

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
                    guardarDatosEnArchivo();
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
                guardarDatosEnArchivo();  // Guardar los cambios en el archivo
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

    @Override
    public Factura leer(String id) {
        return null;
    }

    public String convertirAJSON(Factura factura) {
        if (factura == null) {
            return "{}";
        }
        Gson gson = new Gson();
        return gson.toJson(factura);  // Convertir el objeto cliente a JSON
    }

    public String convertirListaAJSON() {
        Gson gson = new Gson();
        return gson.toJson(facturas);  // Convertir la lista de clientes a JSON
    }

}

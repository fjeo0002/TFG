/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.CLIENTES_COLECCION;
import static es.ujaen.tfg.utils.Utils.CLIENTES_JSON;
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
public final class ClienteDAO implements InterfazDAO<Cliente> {

    private List<Cliente> clientes;
    private final String ultimoHashArchivo;

    public ClienteDAO() throws IOException {
        this.clientes = cargarDatosDesdeArchivo(
                CLIENTES_JSON,
                new TypeToken<List<Cliente>>() {
                }.getType()
        );
        /*
        this.clientes = cargarDatosDesdeFirebase(
                CLIENTES_JSON, 
                CLIENTES_COLECCION, 
                Cliente.class
        );
         */
        this.ultimoHashArchivo = calcularHashArchivo(CLIENTES_JSON);
        iniciarSincronizacionPeriodica(
                CLIENTES_JSON,
                CLIENTES_COLECCION,
                ultimoHashArchivo,
                clientes,
                Cliente::getDNI
        );
        agregarShutdownHook();
    }

    private void agregarShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            try {
                sincronizarConFirebase(
                        CLIENTES_JSON,
                        CLIENTES_COLECCION,
                        ultimoHashArchivo,
                        clientes,
                        Cliente::getDNI
                );
            } catch (IOException e) {
            }
        }));
    }

    @Override
    public boolean crear(Cliente t) {
        if (clientes == null) {
            clientes = new ArrayList<>();
        }
        clientes.add(t);
        Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
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
    public boolean actualizar(Cliente c) {
        for (int i = 0; i < clientes.size(); i++) {
            if (clientes.get(i).getDNI().equals(c.getDNI())) {
                clientes.set(i, c);
                Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Cliente t) {
        boolean removed = clientes.remove(t);
        if (removed) {
            Utils.guardarDatosEnArchivo(CLIENTES_JSON, clientes);
        }
        return removed;
    }

    @Override
    public List<Cliente> leerTodos() {
        return clientes;
    }

}

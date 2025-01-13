/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class AnticipoControlador implements Observable {

    private List<Observador> observadores;
    private final AnticipoDAO anticipoDAO;

    public AnticipoControlador() {
        this.anticipoDAO = new AnticipoDAO();
        this.observadores = new ArrayList<>();
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }

    public boolean crear(Anticipo anticipo) {
        anticipoDAO.crear(anticipo);
        notificarObservadores();
        return true;

    }

    public Anticipo leer(String ID) {
        return anticipoDAO.leer(ID);
    }

    public boolean actualizar(Anticipo anticipo) {
        anticipoDAO.actualizar(anticipo);
        notificarObservadores();
        return true;

    }

    public boolean borrar(Anticipo anticipo) {
        anticipoDAO.borrar(anticipo);
        notificarObservadores();
        return true;
    }

    public List<Anticipo> leerTodos() {
        return anticipoDAO.leerTodos();
    }

    public boolean anticipoRepetido(Anticipo a) {
        List<Anticipo> anticipos = leerTodos();
        if (anticipos != null) {
            for (Anticipo anticipo : anticipos) {
                if (a.equals(anticipo)) {
                    return true;
                }
            }
            // Verificar solapamientos temporales con anticipos del mismo cliente
            String clienteDNI = a.getClienteDNI();
            List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);
            if (anticiposCliente != null) {
                int mesesCubiertosNuevo = a.getMesesCubiertos();
                LocalDate fechaInicioNuevo = a.getFecha();
                LocalDate fechaFinNuevo = fechaInicioNuevo.plusMonths(mesesCubiertosNuevo);

                for (Anticipo anticipo : anticiposCliente) {
                    int mesesCubiertosExistente = anticipo.getMesesCubiertos();
                    LocalDate fechaInicioExistente = anticipo.getFecha();
                    LocalDate fechaFinExistente = fechaInicioExistente.plusMonths(mesesCubiertosExistente);

                    // Comprobar si los rangos de fechas se solapan
                    if (!(fechaFinNuevo.isBefore(fechaInicioExistente) || fechaInicioNuevo.isAfter(fechaFinExistente))) {
                        return true; // Hay solapamiento temporal
                    }
                }
            }
        }
        return false; // No hay repetición ni solapamiento
    }

    public List<Anticipo> anticiposCliente(String clienteDNI) {
        List<Anticipo> anticipos = leerTodos();
        if (anticipos != null) {
            List<Anticipo> anticiposCliente = new ArrayList<>();
            for (Anticipo factura : anticipos) {
                if (factura.getClienteDNI().equals(clienteDNI)) {
                    anticiposCliente.add(factura);
                }
            }
            return anticiposCliente;
        }
        return null;
    }

    public Anticipo obtenerUltimoAnticipo(Cliente cliente) {
        List<Anticipo> anticipos = leerTodos();
        if (anticipos != null) {
            List<Anticipo> anticiposActivos = new ArrayList<>();
            for (Anticipo anticipo : anticipos) {
                if (anticipo.getClienteDNI().equals(cliente.getDNI()) && anticipo.getSaldo() > 0) {
                    anticiposActivos.add(anticipo);
                }
            }
            if (anticiposActivos.isEmpty()) {
                Anticipo ultimoAnticipo = anticiposActivos.get(0); // Inicializar con el primero
                for (Anticipo anticipo : anticiposActivos) {
                    LocalDate fechaAnticipo = anticipo.getFecha();
                    LocalDate fechaUltimoAnticipo = ultimoAnticipo.getFecha();
                    if (fechaAnticipo.isAfter(fechaUltimoAnticipo)) {
                        ultimoAnticipo = anticipo;
                    }
                }
                return ultimoAnticipo;
            }
        }
        return null;
    }
}

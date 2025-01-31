/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import java.io.IOException;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class AnticipoControlador implements Observable {

    private final List<Observador> observadores;
    private final AnticipoDAO anticipoDAO;

    public AnticipoControlador() throws IOException {
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
        //return anticipoDAO.leerTodos();
        List<Anticipo> anticipos = anticipoDAO.leerTodos();
        if (anticipos != null) {
            return anticipos.stream()
                    .sorted((a1, a2) -> a2.getFecha().compareTo(a1.getFecha()))
                    .toList();
        }
        return null;
    }

    public boolean anticipoRepetido(Anticipo a) {
        // 1º Miramos que no sea uno igual:        
        List<Anticipo> anticipos = leerTodos();
        if (anticipos != null) {
            for (Anticipo anticipo : anticipos) {
                if (a.equals(anticipo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Anticipo anticipoActivo(String clienteDNI) {
        List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);
        if (anticiposCliente != null) {
            if (!anticiposCliente.isEmpty()) {
                for (Anticipo anticipo : anticiposCliente) {
                    if (anticipo.getSaldo() > 0.0) {
                        return anticipo;
                    }
                }
            }
        }
        return null;
    }

    public boolean anticipoSolapado(Anticipo a) {
        // Obtener datos del nuevo anticipo
        int mesesCubiertosNuevo = a.getMesesCubiertos();
        LocalDate fechaInicioNuevo = a.getFecha();
        LocalDate fechaFinNuevo = fechaInicioNuevo.plusMonths(mesesCubiertosNuevo - 1); // Ajustar rango al último mes incluido

        YearMonth anioMesInicioNuevo = YearMonth.from(fechaInicioNuevo);
        YearMonth anioMesFinNuevo = YearMonth.from(fechaFinNuevo);

        // Obtener anticipos existentes del cliente
        String clienteDNI = a.getClienteDNI();
        List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);

        // Comprobar si existe algún solapamiento
        if (anticiposCliente != null && !anticiposCliente.isEmpty()) {
            for (Anticipo anticipo : anticiposCliente) {
                // Obtener datos del anticipo existente
                int mesesCubiertosExistente = anticipo.getMesesCubiertos();
                LocalDate fechaInicioExistente = anticipo.getFecha();
                LocalDate fechaFinExistente = fechaInicioExistente.plusMonths(mesesCubiertosExistente - 1);

                YearMonth anioMesInicioExistente = YearMonth.from(fechaInicioExistente);
                YearMonth anioMesFinExistente = YearMonth.from(fechaFinExistente);

                // Comprobar solapamiento entre rangos
                if (!(anioMesFinNuevo.isBefore(anioMesInicioExistente) || anioMesInicioNuevo.isAfter(anioMesFinExistente))) {
                    return true; // Hay solapamiento
                }
            }
        }
        return false; // No hay solapamiento
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

    public boolean borrarAnticiposCliente(String clienteDNI) {
        List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);
        if (anticiposCliente != null) {
            for (Anticipo anticipo : anticiposCliente) {
                borrar(anticipo);
            }
            return true;
        }
        return false;
    }
    /*
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
     */
}

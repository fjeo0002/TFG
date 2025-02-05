/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.BorrarAnticipoCommand;
import es.ujaen.tfg.orden.Command;
import es.ujaen.tfg.orden.CrearAnticipoCommand;
import es.ujaen.tfg.orden.UndoManager;
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
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final UndoManager undoManager;

    public AnticipoControlador(ClienteDAO clienteDAO, AnticipoDAO anticipoDAO, FacturaDAO facturaDAO) throws IOException {
        this.anticipoDAO = anticipoDAO;
        this.observadores = new ArrayList<>();
        this.clienteDAO = clienteDAO;
        this.facturaDAO = facturaDAO;
        this.undoManager = UndoManager.getInstance();
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

    public boolean crear(Anticipo anticipo, Cliente clienteOriginal, Cliente clienteModificado, List<Factura> facturasACrear) {
        //anticipoDAO.crear(anticipo);
        Command crearAnticipo = new CrearAnticipoCommand(anticipoDAO, anticipo, clienteDAO, clienteOriginal, clienteModificado, facturaDAO, facturasACrear);
        undoManager.execute(crearAnticipo);
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

    public boolean borrar(Anticipo anticipo, Cliente clienteOriginal, Cliente clienteModificado, List<Factura> facturasAnticipadas) {
        //anticipoDAO.borrar(anticipo);
        Command borrarAnticipo = new BorrarAnticipoCommand(anticipoDAO, anticipo, clienteDAO, clienteOriginal, clienteModificado, facturaDAO, facturasAnticipadas);
        undoManager.execute(borrarAnticipo);
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
/*
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
*/
}

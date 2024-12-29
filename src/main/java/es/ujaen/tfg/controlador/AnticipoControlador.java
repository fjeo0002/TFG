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
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

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

    public Anticipo obtenerUltimoAnticipo(Cliente cliente) {
        // Formato de las fechas (dd/MM/yyyy)
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        // Lista de todos los anticipos
        List<Anticipo> anticipos = leerTodos();

        // Filtro: obtener solo los anticipos activos del cliente dado
        List<Anticipo> anticiposActivos = new ArrayList<>();
        for (Anticipo anticipo : anticipos) {
            if (anticipo.getCliente().equals(cliente) && anticipo.getSaldoDouble() > 0) {
                anticiposActivos.add(anticipo);
            }
        }

        // Si no hay anticipos activos, devuelve null
        if (anticiposActivos.isEmpty()) {
            return null;
        }

        // Encontrar el anticipo con la fecha más reciente
        Anticipo ultimoAnticipo = anticiposActivos.get(0); // Inicializar con el primero
        for (Anticipo anticipo : anticiposActivos) {
            try {
                // Convertir las fechas a LocalDate
                LocalDate fechaUltimo = LocalDate.parse(ultimoAnticipo.getFecha(), formatter);
                LocalDate fechaActual = LocalDate.parse(anticipo.getFecha(), formatter);

                // Comparar fechas
                if (fechaActual.isAfter(fechaUltimo)) { // Si la fecha actual es más reciente
                    ultimoAnticipo = anticipo; // Actualizar el último anticipo
                }
            } catch (DateTimeParseException e) {
                // Si hay error en el formato de la fecha, ignorar este anticipo
                System.err.println("Error al parsear la fecha: " + anticipo.getFecha());
            }
        }

        // Devolver el último anticipo encontrado
        return ultimoAnticipo;
    }
}

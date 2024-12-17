/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
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
}

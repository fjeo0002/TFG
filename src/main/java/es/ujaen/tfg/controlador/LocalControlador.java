/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.LocalDAO;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class LocalControlador implements Observable {

    private List<Observador> observadores;
    private final LocalDAO localDAO;

    public LocalControlador() {
        this.localDAO = new LocalDAO();
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

    public boolean crear(Local local) {
        localDAO.crear(local);
        notificarObservadores();
        return true;

    }

    public Local leer(String codigo) {
        return localDAO.leer(codigo);
    }

    public boolean actualizar(Local local) {
        localDAO.actualizar(local);
        notificarObservadores();
        return true;

    }

    public boolean borrar(Local local) {
        localDAO.borrar(local);
        notificarObservadores();
        return true;
    }

    public List<Local> leerTodos() {
        return localDAO.leerTodos();
    }

}

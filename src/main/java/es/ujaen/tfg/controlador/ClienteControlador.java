/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class ClienteControlador implements Observable {

    private List<Observador> observadores;
    private final ClienteDAO clienteDAO;

    public ClienteControlador() {
        this.clienteDAO = new ClienteDAO();
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

    public boolean crear(Cliente cliente) {
        clienteDAO.crear(cliente);
        notificarObservadores();
        return true;
    }

    public Cliente leer(String nombre) {
        return clienteDAO.leer(nombre);
    }

    public boolean actualizar(Cliente cliente) {
        clienteDAO.actualizar(cliente);
        notificarObservadores();
        return true;
    }

    public boolean borrar(Cliente cliente) {
        clienteDAO.borrar(cliente);
        notificarObservadores();
        return true;
    }

    public List<Cliente> leerTodos() {
        return clienteDAO.leerTodos();
    }
}

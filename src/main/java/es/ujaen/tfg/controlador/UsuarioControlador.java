/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.UsuarioDAO;
import es.ujaen.tfg.modelo.Usuario;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.Command;
import es.ujaen.tfg.orden.ModificarUsuarioCommand;
import es.ujaen.tfg.orden.UndoManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class UsuarioControlador implements Observable {

    private final List<Observador> observadores;
    private final UsuarioDAO usuarioDAO;
    private final UndoManager undoManager;

    public UsuarioControlador(UsuarioDAO usuarioDAO) throws IOException {
        this.usuarioDAO = usuarioDAO;
        this.observadores = new ArrayList<>();
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

    public boolean crear(Usuario usuario) {
        usuarioDAO.crear(usuario);
        notificarObservadores();
        return true;
    }

    public Usuario leer(String userId) {
        return usuarioDAO.leer(userId);
    }

    public boolean actualizar(Usuario usuarioOriginal, Usuario usuarioModificado) {
        if (!usuarioOriginal.equals(usuarioModificado)) {
            Command modificarCliente = new ModificarUsuarioCommand(usuarioDAO, usuarioOriginal, usuarioModificado);
            undoManager.execute(modificarCliente);
            notificarObservadores();
            return true;
        }
        return false;
    }

    public boolean borrar(Usuario usuario) {
        usuarioDAO.borrar(usuario);
        notificarObservadores();
        return true;
    }

    public boolean verificarCredenciales(String email, String password) {
        return usuarioDAO.verificarCredenciales(email, password);
    }

}

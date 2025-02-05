/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.LocalDAO;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.BorrarLocalCommand;
import es.ujaen.tfg.orden.Command;
import es.ujaen.tfg.orden.CrearLocalCommand;
import es.ujaen.tfg.orden.ModificarLocalCommand;
import es.ujaen.tfg.orden.UndoManager;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class LocalControlador implements Observable {

    private final List<Observador> observadores;
    private final LocalDAO localDAO;
    private final UndoManager undoManager;

    public LocalControlador(LocalDAO localDAO) throws IOException {
        this.localDAO = localDAO;
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

    public boolean crear(Local local) {
        //localDAO.crear(local);
        Command crearLocal = new CrearLocalCommand(localDAO, local);
        undoManager.execute(crearLocal);
        notificarObservadores();
        return true;
    }

    public Local leer(String codigo) {
        return localDAO.leer(codigo);
    }

    public boolean actualizar(Local localOriginal, Local localModificado) {
        //localDAO.actualizar(local);
        if(!localOriginal.equals(localModificado)){
            Command modificarLocal = new ModificarLocalCommand(localDAO, localOriginal, localModificado);
            undoManager.execute(modificarLocal);
            notificarObservadores();
            return true;
        }
        return false;

    }

    public boolean borrar(Local local) {
        //localDAO.borrar(local);
        Command borrarLocal = new BorrarLocalCommand(localDAO, local);
        undoManager.execute(borrarLocal);
        notificarObservadores();
        return true;
    }

    public List<Local> leerTodos() {
        return localDAO.leerTodos();
    }

    public boolean localRepetido(Local l) {
        List<Local> locales = leerTodos();
        if (locales != null) {
            for (Local local : locales) {
                if (l.equals(local)) {
                    return true;
                }
            }
        }
        return false;
    }
}

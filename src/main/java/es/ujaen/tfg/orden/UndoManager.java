/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author jota
 */
public class UndoManager implements Observable {

    private static UndoManager instance;
    private final Stack<Command> pilaDeshacer;
    private final Stack<Command> pilaRehacer;
    private final List<Observador> observadores;

    private UndoManager() {
        this.pilaDeshacer = new Stack<>();
        this.pilaRehacer = new Stack<>();
        this.observadores = new ArrayList<>();
    }

    public static synchronized UndoManager getInstance() {
        if (instance == null) {  // Crear instancia si no existe
            instance = new UndoManager();
        }
        return instance;
    }

    public void execute(Command command) {
        command.execute();
        pilaDeshacer.push(command);
        pilaRehacer.clear(); // Limpiar el redo stack al ejecutar un nuevo comando
    }

    public void undo() {
        if (!pilaDeshacer.isEmpty()) {
            Command command = pilaDeshacer.pop();
            command.undo();
            pilaRehacer.push(command);
            notificarObservadores();
        }
    }

    public void redo() {
        if (!pilaRehacer.isEmpty()) {
            Command command = pilaRehacer.pop();
            command.execute();
            pilaDeshacer.push(command);
            notificarObservadores();
        }
    }

    public boolean canUndo() {
        return !pilaDeshacer.isEmpty();
    }

    public boolean canRedo() {
        return !pilaRehacer.isEmpty();
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
    
    public void limpiarUndoManager(){
        pilaDeshacer.clear();
        pilaRehacer.clear();
    }

}

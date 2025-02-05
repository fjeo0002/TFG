/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.DAO.LocalDAO;
import es.ujaen.tfg.modelo.Local;

/**
 *
 * @author jota
 */
public class BorrarLocalCommand implements Command {
    private final LocalDAO localDAO;
    private final Local local;
    private final int index;

    public BorrarLocalCommand(LocalDAO localDAO, Local local, int index) {
        this.localDAO = localDAO;
        this.local = local;
        this.index = index;
    }

    @Override
    public void execute() {
        localDAO.borrar(local);
    }

    @Override
    public void undo() {
        localDAO.crear(local, index);
    }
}

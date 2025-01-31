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
public class ModificarLocalCommand implements Command {
    private final LocalDAO localDAO;
    private final Local localModificado;
    private final Local localOriginal;

    public ModificarLocalCommand(LocalDAO localDAO, Local localOriginal, Local localModificado) {
        this.localDAO = localDAO;
        this.localOriginal = localOriginal;
        this.localModificado = localModificado;
    }

    @Override
    public void execute() {
        localDAO.actualizar(localModificado);
    }

    @Override
    public void undo() {
        localDAO.actualizar(localOriginal);
    }
}

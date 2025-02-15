/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.DAO.UsuarioDAO;
import es.ujaen.tfg.modelo.Usuario;

/**
 *
 * @author jota
 */
public class ModificarUsuarioCommand implements Command {
    private final UsuarioDAO usuarioDAO;
    private final Usuario usuarioModificado;
    private final Usuario usuarioOriginal;

    public ModificarUsuarioCommand(UsuarioDAO usuarioDAO, Usuario usuarioOriginal, Usuario usuarioModificado) {
        this.usuarioDAO = usuarioDAO;
        this.usuarioOriginal = usuarioOriginal;
        this.usuarioModificado = usuarioModificado;
    }

    @Override
    public void execute() {
        usuarioDAO.actualizar(usuarioModificado);
    }

    @Override
    public void undo() {
        usuarioDAO.actualizar(usuarioOriginal);
    }
}

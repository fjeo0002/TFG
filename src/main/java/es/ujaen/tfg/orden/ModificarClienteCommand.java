/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.modelo.Cliente;

/**
 *
 * @author jota
 */
public class ModificarClienteCommand implements Command {

    private final ClienteDAO clienteDAO;
    private final Cliente clienteModificado;
    private final Cliente clienteOriginal;

    public ModificarClienteCommand(ClienteDAO clienteDAO, Cliente clienteOriginal, Cliente clienteModificado) {
        this.clienteDAO = clienteDAO;
        this.clienteOriginal = clienteOriginal;
        this.clienteModificado = clienteModificado;
    }

    @Override
    public void execute() {
        clienteDAO.actualizar(clienteModificado);
    }

    @Override
    public void undo() {
        clienteDAO.actualizar(clienteOriginal);
    }
}

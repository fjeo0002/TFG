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
public class BorrarClienteCommand implements Command {
    private final ClienteDAO clienteDAO;
    private final Cliente cliente;

    public BorrarClienteCommand(ClienteDAO clienteDAO, Cliente cliente) {
        this.clienteDAO = clienteDAO;
        this.cliente = cliente;
    }

    @Override
    public void execute() {
        clienteDAO.borrar(cliente);
    }

    @Override
    public void undo() {
        clienteDAO.crear(cliente);
    }
}

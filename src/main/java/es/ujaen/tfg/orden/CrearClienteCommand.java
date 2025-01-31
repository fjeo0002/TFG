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
public class CrearClienteCommand implements Command {

    private final ClienteDAO clienteDAO;
    private final Cliente cliente;

    public CrearClienteCommand(ClienteDAO clienteDAO, Cliente cliente) {
        this.clienteDAO = clienteDAO;
        this.cliente = cliente;
    }

    @Override
    public void execute() {
        clienteDAO.crear(cliente);
    }

    @Override
    public void undo() {
        clienteDAO.borrar(cliente);
    }

}

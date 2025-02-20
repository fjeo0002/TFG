/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import java.util.List;

/**
 *
 * @author jota
 */
public class BorrarClienteCommand implements Command {
    private final ClienteDAO clienteDAO;
    private final Cliente cliente;
    private final int fila;
    private final AnticipoDAO anticipoDAO;
    private final List<Anticipo> anticiposCliente;
    private final FacturaDAO facturaDAO;
    private final List<Factura> facturasCliente;

    public BorrarClienteCommand(ClienteDAO clienteDAO, Cliente cliente, int fila, FacturaDAO facturaDAO, List<Factura> facturasCliente, AnticipoDAO anticipoDAO, List<Anticipo> anticiposCliente) {
        this.clienteDAO = clienteDAO;
        this.cliente = cliente;
        this.fila = fila;
        this.facturaDAO = facturaDAO;
        this.facturasCliente = facturasCliente;
        this.anticipoDAO = anticipoDAO;
        this.anticiposCliente = anticiposCliente;
    }

    @Override
    public void execute() {
        clienteDAO.borrar(cliente);
        for (Anticipo a : anticiposCliente) {
            anticipoDAO.borrar(a);
        }
        for (Factura f : facturasCliente) {
            facturaDAO.borrar(f);
        }
    }

    @Override
    public void undo() {
        clienteDAO.crear(cliente, fila);
        for (Anticipo a : anticiposCliente) {
            anticipoDAO.crear(a);
        }
        for (Factura f : facturasCliente) {
            facturaDAO.crear(f);
        }
    }
}

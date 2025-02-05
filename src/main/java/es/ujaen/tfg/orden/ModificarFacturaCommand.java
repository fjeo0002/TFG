/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;

/**
 *
 * @author jota
 */
public class ModificarFacturaCommand implements Command {

    private final FacturaDAO facturaDAO;
    private final Factura facturaOriginal;
    private final Factura facturaModificada;
    private final ClienteDAO clienteDAO;
    private final Cliente clienteOriginal;
    private final Cliente clienteModificado;

    public ModificarFacturaCommand(
            FacturaDAO facturaDAO,
            Factura facturaOriginal,
            Factura facturaModificada,
            ClienteDAO clienteDAO,
            Cliente clienteOriginal,
            Cliente clienteModificado
    ) {
        this.facturaDAO = facturaDAO;
        this.facturaOriginal = facturaOriginal;
        this.facturaModificada = facturaModificada;
        this.clienteDAO = clienteDAO;
        this.clienteOriginal = clienteOriginal;
        this.clienteModificado = clienteModificado;
    }

    @Override
    public void execute() {
        facturaDAO.actualizar(facturaModificada);
        clienteDAO.actualizar(clienteModificado);
    }

    @Override
    public void undo() {
        facturaDAO.actualizar(facturaOriginal);
        clienteDAO.actualizar(clienteOriginal);
    }
}

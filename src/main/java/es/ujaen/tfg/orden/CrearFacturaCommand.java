/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Factura;

/**
 *
 * @author jota
 */
public class CrearFacturaCommand implements Command {
    private final FacturaDAO facturaDAO;
    private final Factura factura;

    public CrearFacturaCommand(FacturaDAO facturaDAO, Factura factura) {
        this.facturaDAO = facturaDAO;
        this.factura = factura;
    }

    @Override
    public void execute() {
        facturaDAO.crear(factura);
    }

    @Override
    public void undo() {
        facturaDAO.borrar(factura);
    }
}

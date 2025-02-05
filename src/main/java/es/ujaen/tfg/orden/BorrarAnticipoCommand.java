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
public class BorrarAnticipoCommand implements Command {
    private final AnticipoDAO anticipoDAO;
    private final Anticipo anticipo;
    private final ClienteDAO clienteDAO;
    private final Cliente clienteOriginal;
    private final Cliente clienteModificado;
    private final FacturaDAO facturaDAO;
    private final List<Factura> facturasAnticipadas;

    public BorrarAnticipoCommand(
            AnticipoDAO anticipoDAO,
            Anticipo anticipo,
            ClienteDAO clienteDAO,
            Cliente clienteOriginal,
            Cliente clienteModificado,
            FacturaDAO facturaDAO,
            List<Factura> facturasACrear
    ) {
        this.anticipoDAO = anticipoDAO;
        this.anticipo = anticipo;
        this.clienteDAO = clienteDAO;
        this.clienteOriginal = clienteOriginal;
        this.clienteModificado = clienteModificado;
        this.facturaDAO = facturaDAO;
        this.facturasAnticipadas = facturasACrear;
    }

    @Override
    public void execute() {
        anticipoDAO.borrar(anticipo);
        clienteDAO.actualizar(clienteModificado);
        for (Factura f : facturasAnticipadas) {
            facturaDAO.borrar(f);
        }
    }

    @Override
    public void undo() {
        anticipoDAO.crear(anticipo);
        clienteDAO.actualizar(clienteOriginal);
        for (Factura f : facturasAnticipadas) {
            facturaDAO.crear(f);
        }
    }
}

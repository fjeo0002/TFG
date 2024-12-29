/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class FacturaControlador implements Observable {

    private List<Observador> observadores;
    private final FacturaDAO facturaDAO;

    public FacturaControlador() {
        this.facturaDAO = new FacturaDAO();
        this.observadores = new ArrayList<>();
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }

    public boolean crear(Factura factura) {
        facturaDAO.crear(factura);
        notificarObservadores();
        return true;

    }

    public Factura leer(String numero, String anio) {
        return facturaDAO.leer(numero, anio);
    }

    public boolean actualizar(Factura factura) {
        facturaDAO.actualizar(factura);
        notificarObservadores();
        return true;

    }

    public boolean borrar(Factura factura) {
        facturaDAO.borrar(factura);
        notificarObservadores();
        return true;
    }

    public List<Factura> leerTodos() {
        return facturaDAO.leerTodos();
    }

    public List<Factura> facturasCliente(Cliente cliente) {
        List<Factura> facturas = facturaDAO.leerTodos();
        if (facturas != null) {
            List<Factura> facturasCliente = new ArrayList<>();
            for (Factura factura : facturas) {
                if (factura.getCliente().equals(cliente)) {
                    facturasCliente.add(factura);
                }
            }
            return facturasCliente;
        }
        return null;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.DAO;

import es.ujaen.tfg.modelo.Factura;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author jota
 */
public class FacturaDAO implements InterfazDAO<Factura> {

    private List<Factura> facturas = new ArrayList<>();

    public FacturaDAO() {
    }

    @Override
    public boolean crear(Factura t) {
        facturas.add(t);
        return true;
    }

    public Factura leer(String numero, String anio) {
        for (Factura factura : facturas) {
            String[] diaMesAnio = factura.getFecha().split("/");
            String anioFactura = diaMesAnio[2];
            if (factura.getNumero().equals(numero) && anio.equals(anioFactura)) {
                return factura;
            }
        }
        return null;
    }

    @Override
    public boolean actualizar(Factura t) {
        for (int i = 0; i < facturas.size(); i++) {
            String[] diaMesAnio = facturas.get(i).getFecha().split("/");
            String anioFactura = diaMesAnio[2];
            if (facturas.get(i).getNumero().equals(t.getNumero()) && t.getFecha().endsWith(anioFactura)) {
                facturas.set(i, t);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean borrar(Factura t) {
        return facturas.remove(t);
    }

    @Override
    public List<Factura> leerTodos() {
        return facturas;
    }

    @Override
    public Factura leer(String id) {
        return null;
    }

}

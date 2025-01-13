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
import java.time.LocalDate;
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

    public Factura leer(String letra, int numero, LocalDate fecha) {
        return facturaDAO.leer(letra, numero, fecha);
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

    public boolean facturaRepetida(Factura f) {
        // La factura no está repetida en caso de que el cliente no tenga factura en ese mes y anio
        String clienteDNI = f.getClienteDNI();
        int mes = f.getFecha().getMonthValue();
        int anio = f.getFecha().getYear();
        Factura facturaClienteMesAnio = facturaClienteMesAnio(clienteDNI, mes, anio);
        if (facturaClienteMesAnio != null) {
            return true;
        }
        return false;
    }

    public List<Factura> facturasCliente(String clienteDNI) {
        List<Factura> facturas = leerTodos();
        if (facturas != null) {
            List<Factura> facturasCliente = new ArrayList<>();
            for (Factura factura : facturas) {
                if (factura.getClienteDNI().equals(clienteDNI)) {
                    facturasCliente.add(factura);
                }
            }
            return facturasCliente;
        }
        return null;
    }

    public Factura facturaClienteMesAnio(String clienteDNI, int mes, int anio) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            for (Factura factura : facturasCliente) {
                int m = factura.getFecha().getMonthValue();
                int a = factura.getFecha().getYear();
                if (a == anio && m == mes) {
                    return factura;
                }
            }
        }
        return null;
    }

    public List<Factura> facturasLetra(String letra) {
        List<Factura> facturas = facturaDAO.leerTodos();
        if (facturas != null) {
            List<Factura> facturasLetra = new ArrayList<>();
            for (Factura factura : facturas) {
                if (factura.getLetra().equals(letra)) {
                    facturasLetra.add(factura);
                }
            }
            return facturasLetra;
        }
        return null;
    }

    public List<Factura> facturasLetraAnio(String letra, LocalDate fecha) {
        List<Factura> facturasLetra = facturasLetra(letra);
        if (facturasLetra != null) {
            List<Factura> facturasLetraAnio = new ArrayList<>();
            for (Factura factura : facturasLetra) {
                if (factura.getFecha().getYear() == fecha.getYear()) {
                    facturasLetraAnio.add(factura);
                }
            }
            return facturasLetraAnio;
        }
        return null;
    }

    public int siguienteNumeroFacturaLetraAnio(String letra, LocalDate fecha) {
        int ultimoNumeroFactura = 0;
        List<Factura> facturasLetraAnio = facturasLetraAnio(letra, fecha);
        if (facturasLetraAnio != null) {
            for (Factura factura : facturasLetraAnio) {
                if (factura.getNumero() > ultimoNumeroFactura) {
                    ultimoNumeroFactura = factura.getNumero();
                }
            }
        }
        ultimoNumeroFactura++;
        return ultimoNumeroFactura;
    }

    public List<Factura> facturasNoNumeradasCliente(String clienteDNI) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            List<Factura> facturasNoNumeradasCliente = new ArrayList<>();
            for (Factura factura : facturasCliente) {
                if (factura.getNumero() == 0) {
                    facturasNoNumeradasCliente.add(factura);
                }
            }
            return facturasNoNumeradasCliente;
        }
        return null;
    }

}

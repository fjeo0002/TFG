/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import static es.ujaen.tfg.utils.Utils.FORMATO_FECHA;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 *
 * @author jota
 */
public class Factura {

    private String numero;
    private String fecha;
    private Boolean pagado;
    private Boolean facturado;
    private Cliente cliente;
    private String monto;

    public Factura() {
    }

    public Factura(String numero, String fecha, Boolean pagado, Boolean facturado, Cliente cliente, String monto) {
        this.numero = numero;
        this.fecha = fecha;
        this.pagado = pagado;
        this.facturado = facturado;
        this.cliente = cliente;
        this.monto = monto;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public LocalDate getFechaLocalDate() {
        try {
            return LocalDate.parse(fecha, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            return null;
        }
    }

    public void setFecha(LocalDate fecha) {
        if (fecha != null) {
            // Convertir LocalDate a String en el formato deseado
            this.fecha = fecha.format(FORMATO_FECHA);
        } else {
            this.fecha = null;
        }
    }

    public Boolean getPagado() {
        return pagado;
    }

    public void setPagado(Boolean pagado) {
        this.pagado = pagado;
    }

    public Boolean getFacturado() {
        return facturado;
    }

    public void setFacturado(Boolean facturado) {
        this.facturado = facturado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getMonto() {
        return monto;
    }

    public Double getMontoDouble() {
        String montoDouble = this.monto.replace(" €", "").replace(",", ".");
        return Double.valueOf(montoDouble);
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public void setMonto(double monto) {
        this.monto = String.format("%.2f", monto);
    }

    @Override
    public String toString() {
        return "Factura{" + "numero=" + numero + ", fecha=" + fecha + ", pagado=" + pagado + ", facturado=" + facturado + ", cliente=" + cliente.getNombre() + ", monto=" + monto + '}';
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import java.util.Date;

/**
 *
 * @author jota
 */
public class Factura {

    private String numero;
    private Date fecha;
    private Boolean pagado;
    private Boolean facturado;

    private Cliente cliente;

    public Factura() {
    }

    public Factura(String numero, Date fecha, Boolean pagado, Boolean facturado, Cliente cliente) {
        this.numero = numero;
        this.fecha = fecha;
        this.pagado = pagado;
        this.facturado = facturado;
        this.cliente = cliente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    @Override
    public String toString() {
        return "Factura{" + "numero=" + numero + ", fecha=" + fecha + ", pagado=" + pagado + ", facturado=" + facturado + '}';
    }

}

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
public class Anticipo {

    private String id;
    private Double monto;
    private Integer mesesCubiertos;
    private Date fecha;

    private Cliente cliente;

    public Anticipo() {
    }

    public Anticipo(String id, Double monto, Integer mesesCubiertos, Date fecha, Cliente cliente) {
        this.id = id;
        this.monto = monto;
        this.mesesCubiertos = mesesCubiertos;
        this.fecha = fecha;
        this.cliente = cliente;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Integer getMesesCubiertos() {
        return mesesCubiertos;
    }

    public void setMesesCubiertos(Integer mesesCubiertos) {
        this.mesesCubiertos = mesesCubiertos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Anticipo{" + "id=" + id + ", monto=" + monto + ", mesesCubiertos=" + mesesCubiertos + ", fecha=" + fecha + '}';
    }

}

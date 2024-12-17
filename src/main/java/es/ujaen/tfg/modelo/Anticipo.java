/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

/**
 *
 * @author jota
 */
public class Anticipo {

    private String id;
    private String monto;
    private String mesesCubiertos;
    private String fecha;
    private Cliente cliente;

    public Anticipo() {
    }

    public Anticipo(String id, String monto, String mesesCubiertos, String fecha, Cliente cliente) {
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

    public String getMonto() {
        return monto;
    }

    public void setMonto(String monto) {
        this.monto = monto;
    }

    public String getMesesCubiertos() {
        return mesesCubiertos;
    }

    public void setMesesCubiertos(String mesesCubiertos) {
        this.mesesCubiertos = mesesCubiertos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
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
        return "Anticipo{" + "id=" + id + ", monto=" + monto + ", mesesCubiertos=" + mesesCubiertos + ", fecha=" + fecha + ", cliente=" + cliente.getNombre() + '}';
    }
    
    

}

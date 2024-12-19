/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import java.util.Objects;

/**
 *
 * @author jota
 */
public class Anticipo {

    private String id;
    private String monto;
    private String mesesCubiertos;
    private String fecha;
    private String saldo;
    private Cliente cliente;

    public Anticipo() {
    }

    public Anticipo(String id, String monto, String mesesCubiertos, String fecha, String saldo, Cliente cliente) {
        this.id = id;
        this.monto = monto;
        this.mesesCubiertos = mesesCubiertos;
        this.fecha = fecha;
        this.saldo = saldo;
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

    public String getMesesCubiertos() {
        return mesesCubiertos;
    }
    
    public Integer getMesesCubiertosInteger() {
        return Integer.valueOf(mesesCubiertos);
    }

    public void setMesesCubiertos(String mesesCubiertos) {
        this.mesesCubiertos = mesesCubiertos;
    }
    
    public void setMesesCubiertos(int mesesCubiertos) {
        this.mesesCubiertos = String.valueOf(mesesCubiertos);
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

    public String getSaldo() {
        return saldo;
    }
    
    public Double getSaldoDouble() {
        String saldoDouble = this.saldo.replace(" €", "").replace(",", ".");
        return Double.valueOf(saldoDouble);
    }

    public void setSaldo(String saldo) {
        this.saldo = saldo;
    }
    
    public void setSaldo(double saldo) {
        this.saldo = String.format("%.2f", saldo);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Anticipo other = (Anticipo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.monto, other.monto)) {
            return false;
        }
        if (!Objects.equals(this.mesesCubiertos, other.mesesCubiertos)) {
            return false;
        }
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.saldo, other.saldo)) {
            return false;
        }
        return Objects.equals(this.cliente, other.cliente);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.monto);
        hash = 89 * hash + Objects.hashCode(this.mesesCubiertos);
        hash = 89 * hash + Objects.hashCode(this.fecha);
        hash = 89 * hash + Objects.hashCode(this.saldo);
        hash = 89 * hash + Objects.hashCode(this.cliente);
        return hash;
    }

    @Override
    public String toString() {
        return "Anticipo{" + "id=" + id + ", monto=" + monto + ", mesesCubiertos=" + mesesCubiertos + ", fecha=" + fecha + ", saldo=" + saldo + ", cliente=" + cliente.getNombre() + '}';
    }

}

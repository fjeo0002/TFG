/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import static es.ujaen.tfg.utils.Utils.convertirDoubleAString;
import static es.ujaen.tfg.utils.Utils.convertirFechaAString;
import static es.ujaen.tfg.utils.Utils.convertirStringADouble;
import static es.ujaen.tfg.utils.Utils.convertirStringAFecha;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author jota
 */
public class Factura {

    private String letra;
    private int numero;
    private LocalDate fecha;
    private Boolean pagado;
    private Boolean facturado;
    private double monto;
    private String clienteDNI;

    public Factura() {
    }

    public Factura(String letra, int numero, LocalDate fecha, Boolean pagado, Boolean facturado, double monto, String clienteDNI) {
        this.letra = letra;
        this.numero = numero;
        this.fecha = fecha;
        this.pagado = pagado;
        this.facturado = facturado;
        this.clienteDNI = clienteDNI;
        this.monto = monto;
    }

    public Factura(String letra, String numero, String fecha, Boolean pagado, Boolean facturado, String monto, String clienteDNI) {
        this.letra = letra;
        this.numero = Integer.parseInt(numero);
        this.fecha = convertirStringAFecha(fecha);
        this.pagado = pagado;
        this.facturado = facturado;
        this.clienteDNI = clienteDNI;
        this.monto = convertirStringADouble(monto);
    }

    public Factura(Factura f) {
        this.letra = f.letra;
        this.numero = f.numero;
        this.fecha = f.fecha;
        this.pagado = f.pagado;
        this.facturado = f.facturado;
        this.clienteDNI = f.clienteDNI;
        this.monto = f.monto;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }

    public int getNumero() {
        return numero;
    }

    public String getNumeroString() {
        return String.valueOf(numero);
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setNumero(String numero) {
        this.numero = Integer.parseInt(numero);
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaString() {
        return convertirFechaAString(fecha);
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = convertirStringAFecha(fecha);
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

    public String getClienteDNI() {
        return clienteDNI;
    }

    public void setClienteDNI(String clienteDNI) {
        this.clienteDNI = clienteDNI;
    }

    public double getMonto() {
        return monto;
    }

    public String getMontoString() {
        return convertirDoubleAString(monto);
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public void setMonto(String monto) {
        this.monto = convertirStringADouble(monto);
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
        final Factura other = (Factura) obj;
        /*
        if (this.numero != other.numero) {
            return false;
        }
        if (!Objects.equals(this.letra, other.letra)) {
            return false;
        }
        */ //Para mí, una factura es igual en caso de tener fecha (año y mes) y cliente Iguales
        if (!Objects.equals(this.fecha, other.fecha)) {
            return false;
        }
        if (!Objects.equals(this.fecha.getMonth(), other.fecha.getMonth())) {
            return false;
        }
        if (!Objects.equals(this.fecha.getYear(), other.fecha.getYear())) {
            return false;
        }
        return Objects.equals(this.clienteDNI, other.clienteDNI);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.letra);
        hash = 97 * hash + this.numero;
        hash = 97 * hash + Objects.hashCode(this.fecha);
        hash = 97 * hash + Objects.hashCode(this.clienteDNI);
        return hash;
    }

    @Override
    public String toString() {
        return "Factura{" + "letra=" + letra + ", numero=" + numero + ", fecha=" + fecha + ", pagado=" + pagado + ", facturado=" + facturado + ", monto=" + monto + ", clienteDNI=" + clienteDNI + '}';
    }

}

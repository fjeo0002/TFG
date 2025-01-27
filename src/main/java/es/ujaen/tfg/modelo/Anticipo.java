/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import com.google.cloud.firestore.annotation.Exclude;
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
public class Anticipo {

    private String id;
    private double monto;
    private int mesesCubiertos;
    private LocalDate fecha;
    private double saldo;
    private String clienteDNI;

    public Anticipo() {
    }

    public Anticipo(String id, double monto, int mesesCubiertos, LocalDate fecha, double saldo, String clienteDNI) {
        this.id = id;
        this.monto = monto;
        this.mesesCubiertos = mesesCubiertos;
        this.fecha = fecha;
        this.saldo = saldo;
        this.clienteDNI = clienteDNI;
    }

    public Anticipo(String id, String monto, String mesesCubiertos, String fecha, String saldo, String clienteDNI) {
        this.id = id;
        this.monto = convertirStringADouble(monto);
        this.mesesCubiertos = Integer.parseInt(mesesCubiertos);
        this.fecha = convertirStringAFecha(fecha);
        this.saldo = convertirStringADouble(saldo);
        this.clienteDNI = clienteDNI;
    }

    public Anticipo(Anticipo a) {
        this.id = a.id;
        this.monto = a.monto;
        this.mesesCubiertos = a.mesesCubiertos;
        this.fecha = a.fecha;
        this.saldo = a.saldo;
        this.clienteDNI = a.clienteDNI;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMonto() {
        return monto;
    }

    @Exclude
    public String getMontoString() {
        return convertirDoubleAString(monto);
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    @Exclude
    public void setMontoString(String monto) {
        this.monto = convertirStringADouble(monto);
    }

    public int getMesesCubiertos() {
        return mesesCubiertos;
    }

    @Exclude
    public String getMesesCubiertosString() {
        return String.valueOf(mesesCubiertos);
    }

    public void setMesesCubiertos(int mesesCubiertos) {
        this.mesesCubiertos = mesesCubiertos;
    }

    @Exclude
    public void setMesesCubiertosString(String mesesCubiertos) {
        this.mesesCubiertos = Integer.parseInt(mesesCubiertos);
    }
    
    @Exclude
    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaString() {
        return convertirFechaAString(fecha);
    }
    
    @Exclude
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public void setFechaString(String fecha) {
        this.fecha = convertirStringAFecha(fecha);
    }

    public double getSaldo() {
        return saldo;
    }
    
    @Exclude
    public String getSaldoString() {
        return convertirDoubleAString(saldo);
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Exclude
    public void setSaldoString(String saldo) {
        this.saldo = convertirStringADouble(saldo);
    }

    public String getClienteDNI() {
        return clienteDNI;
    }

    public void setClienteDNI(String clienteDNI) {
        this.clienteDNI = clienteDNI;
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
        return Objects.equals(this.clienteDNI, other.clienteDNI);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        hash = 89 * hash + Objects.hashCode(this.monto);
        hash = 89 * hash + Objects.hashCode(this.mesesCubiertos);
        hash = 89 * hash + Objects.hashCode(this.fecha);
        hash = 89 * hash + Objects.hashCode(this.saldo);
        hash = 89 * hash + Objects.hashCode(this.clienteDNI);
        return hash;
    }

    @Override
    public String toString() {
        return "Anticipo{" + "id=" + id + ", monto=" + monto + ", mesesCubiertos=" + mesesCubiertos + ", fecha=" + fecha + ", saldo=" + saldo + ", cliente=" + clienteDNI + '}';
    }

}

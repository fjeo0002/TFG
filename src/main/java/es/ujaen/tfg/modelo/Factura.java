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
import static es.ujaen.tfg.utils.Utils.redondearDosDecimales;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.Objects;

/**
 *
 * @author jota
 */
public class Factura {

    private String id;
    private String letra;
    private int numero;
    private LocalDate fecha;
    private Boolean pagado;
    private Boolean facturado;
    private double monto;
    private String clienteDNI;

    public Factura() {
    }

    public Factura(String id, String letra, int numero, LocalDate fecha, Boolean pagado, Boolean facturado, double monto, String clienteDNI) {
        this.id = id;
        this.letra = letra;
        this.numero = numero;
        this.fecha = fecha;
        this.pagado = pagado;
        this.facturado = facturado;
        this.clienteDNI = clienteDNI;
        this.monto = redondearDosDecimales(monto);
    }

    public Factura(String id, String letra, String numero, String fecha, Boolean pagado, Boolean facturado, String monto, String clienteDNI) {
        this.id = id;
        this.letra = letra;
        this.numero = Integer.parseInt(numero);
        this.fecha = convertirStringAFecha(fecha);
        this.pagado = pagado;
        this.facturado = facturado;
        this.clienteDNI = clienteDNI;
        this.monto = convertirStringADouble(monto);
    }

    public Factura(Factura f) {
        this.id = f.id;
        this.letra = f.letra;
        this.numero = f.numero;
        this.fecha = f.fecha;
        this.pagado = f.pagado;
        this.facturado = f.facturado;
        this.clienteDNI = f.clienteDNI;
        this.monto = f.monto;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    @Exclude
    public String getNumeroString() {
        return String.valueOf(numero);
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    @Exclude
    public void setNumeroString(String numero) {
        this.numero = Integer.parseInt(numero);
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

    @Exclude
    public String getMontoString() {
        return convertirDoubleAString(monto);
    }

    public void setMonto(double monto) {
        this.monto = redondearDosDecimales(monto);
    }

    @Exclude
    public void setMontoString(String monto) {
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
        // No pueden tener Mes/Año, Cliente y Monto iguales
        YearMonth fecha1 = YearMonth.from(this.fecha);
        YearMonth fecha2 = YearMonth.from(other.fecha);
        if (!fecha1.equals(fecha2)) {
            return false;
        }
        if (!Objects.equals(this.monto, other.monto)) {
            return false;
        }
        return Objects.equals(this.clienteDNI, other.clienteDNI);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + Objects.hashCode(this.fecha);
        hash = 89 * hash + (int) (Double.doubleToLongBits(this.monto) ^ (Double.doubleToLongBits(this.monto) >>> 32));
        hash = 89 * hash + Objects.hashCode(this.clienteDNI);
        return hash;
    }

    

    @Override
    public String toString() {
        return "Factura{" + "id=" + id + ", letra=" + letra + ", numero=" + numero + ", fecha=" + fecha + ", pagado=" + pagado + ", facturado=" + facturado + ", monto=" + monto + ", clienteDNI=" + clienteDNI + '}';
    }

}

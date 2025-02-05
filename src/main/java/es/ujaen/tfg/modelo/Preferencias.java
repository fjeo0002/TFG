/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

/**
 *
 * @author jota
 */
public class Preferencias {

    private int iva;
    private int retencion;
    private boolean facturasContiguas;

    public Preferencias() {
        this.iva = 21; // Valor predeterminado
        this.retencion = 19; // Valor predeterminado
        this.facturasContiguas = true; // Valor predeterminado
    }

    public Preferencias(int iva, int retencion, boolean facturasContiguas) {
        this.iva = iva;
        this.retencion = retencion;
        this.facturasContiguas = facturasContiguas;
    }

    public int getIva() {
        return iva;
    }

    public void setIva(int iva) {
        this.iva = iva;
    }

    public int getRetencion() {
        return retencion;
    }

    public void setRetencion(int retencion) {
        this.retencion = retencion;
    }

    public boolean getFacturasContiguas() {
        return facturasContiguas;
    }

    public void setFacturasContiguas(boolean facturasContiguas) {
        this.facturasContiguas = facturasContiguas;
    }
}

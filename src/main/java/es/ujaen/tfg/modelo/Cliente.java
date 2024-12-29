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
public class Cliente {

    private String DNI;
    private String nombre;
    private String alias;
    private String email;
    private String direccion;
    private String localidad;
    private String codigoPostal;
    private String descripcion;
    private String estado;
    private String saldo;
    private String tipo;

    public Cliente() {
    }

    public Cliente(String DNI, String nombre, String alias, String email, String direccion, String localidad, String codigoPostal, String descripcion, String estado, String saldo, String tipo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.alias = alias;
        this.email = email;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.descripcion = descripcion;
        this.estado = estado;
        this.saldo = saldo;
        this.tipo = tipo;
    }
    
    public Cliente(Cliente c) {
        this.DNI = c.DNI;
        this.nombre = c.nombre;
        this.alias = c.alias;
        this.email = c.email;
        this.direccion = c.direccion;
        this.codigoPostal = c.codigoPostal;
        this.localidad = c.localidad;
        this.descripcion = c.descripcion;
        this.estado = c.estado;
        this.saldo = c.saldo;
        this.tipo = c.tipo;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
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
    public String toString() {
        return "Cliente{" + "DNI=" + DNI + ", nombre=" + nombre + ", alias=" + alias + ", correo=" + email + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", ciudad=" + localidad + ", descripcion=" + descripcion + ", estado=" + estado + ", saldo=" + saldo + ", tipo=" + tipo + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.DNI);
        hash = 97 * hash + Objects.hashCode(this.nombre);
        hash = 97 * hash + Objects.hashCode(this.alias);
        hash = 97 * hash + Objects.hashCode(this.email);
        hash = 97 * hash + Objects.hashCode(this.direccion);
        hash = 97 * hash + Objects.hashCode(this.localidad);
        hash = 97 * hash + Objects.hashCode(this.codigoPostal);
        hash = 97 * hash + Objects.hashCode(this.descripcion);
        hash = 97 * hash + Objects.hashCode(this.estado);
        hash = 97 * hash + Objects.hashCode(this.saldo);
        hash = 97 * hash + Objects.hashCode(this.tipo);
        return hash;
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
        final Cliente other = (Cliente) obj;
        if (!Objects.equals(this.DNI, other.DNI)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.direccion, other.direccion)) {
            return false;
        }
        if (!Objects.equals(this.localidad, other.localidad)) {
            return false;
        }
        if (!Objects.equals(this.codigoPostal, other.codigoPostal)) {
            return false;
        }
        if (!Objects.equals(this.descripcion, other.descripcion)) {
            return false;
        }
        if (!Objects.equals(this.estado, other.estado)) {
            return false;
        }
        if (!Objects.equals(this.saldo, other.saldo)) {
            return false;
        }
        return Objects.equals(this.tipo, other.tipo);
    }

}

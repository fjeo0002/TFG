/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

/**
 *
 * @author jota
 */
public class Cliente {

    private String DNI;
    private String nombre;
    private String alias;
    private String correo;
    private String direccion;
    private String codigoPostal;
    private String ciudad;
    private String descripcion;
    private Boolean tipo;

    public Cliente() {
    }

    public Cliente(String DNI, String nombre, String alias, String correo, String direccion, String codigoPostal, String ciudad, String descripcion, Boolean tipo) {
        this.DNI = DNI;
        this.nombre = nombre;
        this.alias = alias;
        this.correo = correo;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.ciudad = ciudad;
        this.descripcion = descripcion;
        this.tipo = tipo;
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

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
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

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getTipo() {
        return tipo;
    }

    public void setTipo(Boolean tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "DNI=" + DNI + ", nombre=" + nombre + ", alias=" + alias + ", correo=" + correo + ", direccion=" + direccion + ", codigoPostal=" + codigoPostal + ", ciudad=" + ciudad + ", descripcion=" + descripcion + ", tipo=" + tipo + '}';
    }
}

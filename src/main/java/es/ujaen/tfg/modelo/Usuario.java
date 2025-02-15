/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Objects;

/**
 *
 * @author jota
 */
public class Usuario {

    private String email;
    private String contrasena;
    private String nombre;
    private String dni;
    private String direccion;
    private String localidad;
    private String codigoPostal;
    private String telefono;

    public Usuario() {
    }

    public Usuario(String email, String contrasena, String nombre, String dni, String direccion, String localidad, String codigoPostal, String telefono) {
        this.email = email;
        //this.contrasena = contrasena;
        setContrasena(contrasena);
        this.nombre = nombre;
        this.dni = dni;
        this.direccion = direccion;
        this.localidad = localidad;
        this.codigoPostal = codigoPostal;
        this.telefono = telefono;
    }

    public Usuario(Usuario u) {
        this.email = u.email;
        this.contrasena = u.contrasena;
        this.nombre = u.nombre;
        this.dni = u.dni;
        this.direccion = u.direccion;
        this.localidad = u.localidad;
        this.codigoPostal = u.codigoPostal;
        this.telefono = u.telefono;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = hashContrasena(contrasena);
    }

    public static String hashContrasena(String contraseña) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(contraseña.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al cifrar la contraseña", e);
        }
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.email);
        hash = 73 * hash + Objects.hashCode(this.contrasena);
        hash = 73 * hash + Objects.hashCode(this.nombre);
        hash = 73 * hash + Objects.hashCode(this.dni);
        hash = 73 * hash + Objects.hashCode(this.direccion);
        hash = 73 * hash + Objects.hashCode(this.localidad);
        hash = 73 * hash + Objects.hashCode(this.codigoPostal);
        hash = 73 * hash + Objects.hashCode(this.telefono);
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
        final Usuario other = (Usuario) obj;
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.contrasena, other.contrasena)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.dni, other.dni)) {
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
        return Objects.equals(this.telefono, other.telefono);
    }

    @Override
    public String toString() {
        return "Usuario{" + "email=" + email + ", contrasena=" + contrasena + ", nombre=" + nombre + ", dni=" + dni + ", direccion=" + direccion + ", localidad=" + localidad + ", codigoPostal=" + codigoPostal + ", telefono=" + telefono + '}';
    }

}

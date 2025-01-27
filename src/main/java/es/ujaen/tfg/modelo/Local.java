/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

import com.google.cloud.firestore.annotation.Exclude;
import static es.ujaen.tfg.utils.Utils.convertirDoubleAString;
import static es.ujaen.tfg.utils.Utils.convertirStringADouble;
import java.util.Objects;

/**
 *
 * @author jota
 */
public class Local {

    private String codigo;
    private String nombre;
    private String alias;
    private double precio;

    public Local() {
    }

    public Local(String codigo, String articulo, String alias, double precio) {
        this.codigo = codigo;
        this.nombre = articulo;
        this.alias = alias;
        this.precio = precio;
    }

    public Local(String codigo, String articulo, String alias, String precio) {
        this.codigo = codigo;
        this.nombre = articulo;
        this.alias = alias;
        this.precio = convertirStringADouble(precio);
    }

    public Local(Local local) {
        this.codigo = local.codigo;
        this.nombre = local.nombre;
        this.alias = local.alias;
        this.precio = local.precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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

    public double getPrecio() {
        return precio;
    }
    
    @Exclude
    public String getPrecioString() {
        return convertirDoubleAString(precio);
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Exclude
    public void setPrecioString(String precioStr) {
        this.precio = convertirStringADouble(precioStr);
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
        final Local other = (Local) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        if (!Objects.equals(this.nombre, other.nombre)) {
            return false;
        }
        if (!Objects.equals(this.alias, other.alias)) {
            return false;
        }
        return Objects.equals(this.precio, other.precio);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codigo);
        hash = 67 * hash + Objects.hashCode(this.nombre);
        hash = 67 * hash + Objects.hashCode(this.alias);
        hash = 67 * hash + Objects.hashCode(this.precio);
        return hash;
    }

    @Override
    public String toString() {
        return "Local{" + "codigo=" + codigo + ", nombre=" + nombre + ", alias=" + alias + ", precio=" + precio + '}';
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.modelo;

/**
 *
 * @author jota
 */
public class Local {

    private String codigo;
    private String articulo;
    private String alias;
    private Double precio;

    public Local() {
    }

    public Local(String codigo, String articulo, String alias, Double precio) {
        this.codigo = codigo;
        this.articulo = articulo;
        this.alias = alias;
        this.precio = precio;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Local{" + "codigo=" + codigo + ", articulo=" + articulo + ", alias=" + alias + ", precio=" + precio + '}';
    }

}

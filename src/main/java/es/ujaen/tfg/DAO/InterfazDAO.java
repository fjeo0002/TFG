/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.ujaen.tfg.DAO;

import java.util.List;

/**
 *
 * @author jota
 */
public interface InterfazDAO<T> {
    boolean crear(T t);
    T leer(String id);
    boolean actualizar(T t);
    boolean borrar(T t);
    List<T> leerTodos();
}

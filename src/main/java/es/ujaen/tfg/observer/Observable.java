/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.ujaen.tfg.observer;

/**
 *
 * @author jota
 */
public interface Observable {
    void agregarObservador(Observador o);
    void eliminarObservador(Observador o);
    void notificarObservadores();
}

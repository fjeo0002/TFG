/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package es.ujaen.tfg.orden;

/**
 *
 * @author jota
 */
public interface Command {
    void execute(); // Ejecutar la acción principal
    void undo();    // Deshacer la acción
    //void redo();    // Rehacer la acción (si es diferente a execute)
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import java.awt.Color;
import java.util.function.Predicate;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;

/**
 *
 * @author jota
 */
public class HerramientasComunesTextField {

    public static void agregarPlaceHolder(JTextField field, String placeHolder) {
        if (field.getText().trim().equals("")) {
            field.setText(placeHolder);
            field.setForeground(new Color(153, 153, 153));
        }
    }

    public static void quitarPlaceHolder(JTextField field, String placeHolder) {
        if (field.getText().trim().equals(placeHolder)) {
            field.setText("");
            field.setForeground(new Color(0, 0, 0));
        }
    }

    public static void agregarSufijo(JTextField field, String sufijo, boolean validar){
        if(!field.getText().trim().endsWith(sufijo) && validar){
            field.setText(field.getText().trim() + sufijo);
        }
    }
    
    public static void quitarSufijo(JTextField field, String sufijo){
        if(field.getText().trim().endsWith(sufijo)){
            field.setText(field.getText().trim().substring(0,field.getText().trim().length()-2));
        }
    }
    
    public static boolean validarCampo(
        JTextField field, 
        JLabel labelAdvertencia, 
        String mensajeAdvertencia, 
        Border bordeOriginal, 
        Predicate<String> validador
    ) {
        
        if (validador.test(field.getText().trim())) {
            // Si el campo es válido
            field.setBorder(bordeOriginal);
            labelAdvertencia.setText("");
            return true;
        } else {
            // Si el campo es inválido
            field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED),
                bordeOriginal
            ));
            labelAdvertencia.setText(mensajeAdvertencia);
            return false;
        }
    }
}

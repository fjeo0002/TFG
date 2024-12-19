/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import java.awt.Color;
import java.time.format.DateTimeParseException;
import java.util.function.Predicate;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import org.threeten.bp.LocalDate;
import org.threeten.bp.format.DateTimeFormatter;

/**
 *
 * @author jota
 */
public class Utils {
    
    public static String sufijoPrecios = " €";

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

    public static boolean agregarSufijo(JTextField field, String sufijo){
        if(!field.getText().trim().endsWith(sufijo)){
            field.setText(field.getText().trim() + sufijo);
            return true;
        }
        return false;
    }
    
    public static void quitarSufijo(JTextField field, String sufijo){
        if(field.getText().trim().endsWith(sufijo)){
            field.setText(field.getText().trim().substring(0,field.getText().trim().length()-2));
        }
    }
    
    public static boolean validarCampoFormulario(
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
    
    public static Object obtenerValorCelda(JTable table, int row, int column) {
        Object value = table.getValueAt(row, column);
        if (value != null) {
            return value;
        }
        return null; // Si está vacío
    }
    
    public static String obtenerIdDeFilaSeleccionada(JTable jTable, DefaultTableModel dtm) {
        int filaSeleccionada = jTable.getSelectedRow(); // Índice de la fila seleccionada
        if (filaSeleccionada != -1) { // Verificar si hay una fila seleccionada
            int modeloFila = jTable.convertRowIndexToModel(filaSeleccionada); // Convertir índice de vista a modelo
            return (String) dtm.getValueAt(modeloFila, 0); // Obtener el valor de la columna ID (índice 0)
        }
        return null; // Si no hay fila seleccionada, retorna null
    }
    
    public static boolean validarFecha(String fecha) {
        // Expresión regular para "dd/MM/yyyy"
        String regex = "^\\d{2}/\\d{2}/\\d{4}$";

        // Verificar si cumple con el formato básico usando la expresión regular
        if (!fecha.matches(regex)) {
            return false; // Formato inválido
        }

        // Intentar parsear la fecha
        try {
            // Formateador para convertir la cadena en una fecha
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            // Parsear la fecha ingresada
            LocalDate fechaIngresada = LocalDate.parse(fecha, formatter);

            // Obtener la fecha actual
            LocalDate fechaActual = LocalDate.now();

            // Validar que no sea posterior a la fecha actual
            return !fechaIngresada.isAfter(fechaActual);

        } catch (DateTimeParseException e) {
            // Si ocurre un error al parsear, el formato es inválido
            return false;
        }
    }
    
    public static boolean validarMesesCubiertos(String meses) {
        try {
            int value = Integer.parseInt(meses.trim());
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarMonto(String monto) {
        String regex = "^\\d+(,\\d{2})?";
        if (!monto.matches(regex)) {
            return false;
        }

        try {
            String numericPart = monto.replace(",", ".");
            double value = Double.parseDouble(numericPart);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    
    public static void mostrarError(JFrame parent, String titulo, String mensaje) {
        // Cambiar la fuente global del JOptionPane
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

        // Mostrar el JOptionPane con la fuente personalizada
        JOptionPane.showMessageDialog(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import java.awt.Color;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
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

/**
 *
 * @author jota
 */
public class Utils {

    public static final String EURO = " €";
    public static final String PORCENTAJE = " %";
    public static final String TIPOA = "A";
    public static final String TIPOB = "B";
    public static final String VACIO = "";
    public static final String COMA = ",";
    public static final String PUNTO = ".";
    public static final char COMA_CHAR = ',';
    public static final String FORMATO_DECIMAL = "0.00";
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String FORMATO_ENTERO = "%02d";
    public static final String FORMATO_FECHA_STRING = "^\\d{2}/\\d{2}/\\d{4}$";

    public static final Color NEGRO = new Color(0, 0, 0);
    public static final Color GRIS = new Color(153, 153, 153);

    public static final String TITULO_VISTA_ANADIR_CLIENTE = "Añadir Nuevo Cliente";
    public static final String TITULO_VISTA_MODIFICAR_CLIENTE = "Modificar Cliente";
    public static final String TITULO_CLIENTE_REPETIDO = "Cliente Registrado";

    public static final String MENSAJE_CLIENTE_REPETIDO = "El cliente ya ha sido registrado";

    public static final String ERROR_DNI_CLIENTE = "* Introduce un DNI válido (8 números y 1 letra mayúscula)";
    public static final String ERROR_NOMBRE_CLIENTE = "* Introduce un nombre válido (sin números ni caracteres especiales)";
    public static final String ERROR_EMAIL_CLIENTE = "* Introduce un correo electrónico válido";
    public static final String ERROR_CODIGOPOSTAL_CLIENTE = "* Introduce un código postal válido (5 dígitos)";

    public static final String PLACEHOLDER_DNI_CLIENTE = "12345678X";
    public static final String PLACEHOLDER_NOMBRE_CLIENTE = "Nombre Apellido1 Apellido2";
    public static final String PLACEHOLDER_ALIAS_CLIENTE = "Introduzca Alias de Cliente (opcional)";
    public static final String PLACEHOLDER_EMAIL_CLIENTE = "nombre.123@gmail.com (opcional)";
    public static final String PLACEHOLDER_DIRECCION_CLIENTE = "C/ Mirabueno, 9, 9ºB";
    public static final String PLACEHOLDER_LOCALIDAD_CLIENTE = "Localidad, Provincia";
    public static final String PLACEHOLDER_CODIGO_POSTAL_CLIENTE = "12345";

    public static final String VALIDACION_DNI_CLIENTE = "\\d{8}[A-Z]";
    public static final String VALIDACION_NOMBRE_CLIENTE = "[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s'-]+";
    public static final String VALIDACION_EMAIL_CLIENTE = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String VALIDACION_CODIGO_POSTAL_CLIENTE = "^\\d{5}$";

    public static final String TITULO_VISTA_ANADIR_LOCAL = "Añadir Nuevo Local";
    public static final String TITULO_VISTA_MODIFICAR_LOCAL = "Modificar Local";
    public static final String TITULO_LOCAL_REPETIDO = "Local Registrado";

    public static final String MENSAJE_LOCAL_REPETIDO = "El local ya ha sido registrado";

    public static final String PLACEHOLDER_NOMBRE_LOCAL = "Introduzca Nombre de Local";
    public static final String PLACEHOLDER_ALIAS_LOCAL = "Introduzca Alias de Local (opcional)";
    public static final String PLACEHOLDER_PRECIO_LOCAL = "0,00 €";

    public static final String ERROR_PRECIO_LOCAL = "* Introduce un número con 2 decimales";

    public static final String TITULO_ANTICIPO_REPETIDO = "Anticipo Existente";

    public static final String MENSAJE_ANTICIPO_REPETIDO = "El anticipo ya ha sido registrado";
    
    public static final String TITULO_FACTURA_REPETIDO = "Factura Existente";
    
    public static final String MENSAJE_FACTURA_REPETIDO = "La factura ya ha sido registrada";


    public static double convertirStringADouble(String doubleStr) {
        if (doubleStr == null || doubleStr.isEmpty()) {
            throw new IllegalArgumentException("El valor proporcionado es nulo o vacío.");
        }
        // Eliminar el símbolo del euro - porcentaje y espacios
        String sanitizedValue = doubleStr.replace(EURO, VACIO).replace(PORCENTAJE, VACIO).trim();
        // Reemplazar la coma por un punto para la conversión
        sanitizedValue = sanitizedValue.replace(COMA, PUNTO);
        // Convertir el String a Double
        return Double.parseDouble(sanitizedValue);
    }

    public static String convertirDoubleAString(double value) {
        // Crear un formato específico para String
        DecimalFormatSymbols symbols = new DecimalFormatSymbols(Locale.getDefault());
        // Agregar el carácter de la ','
        symbols.setDecimalSeparator(COMA_CHAR);
        // Parsear según formato decimal y simbolo
        DecimalFormat df = new DecimalFormat(FORMATO_DECIMAL, symbols);

        return df.format(value);
    }

    public static LocalDate convertirStringAFecha(String fechaStr) {
        if(!fechaStr.matches(FORMATO_FECHA_STRING)){
            return null;
        }
        try {
            return LocalDate.parse(fechaStr, FORMATO_FECHA);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Formato de fecha inválido. Use dd/MM/yyyy.");
        }
    }

    public static String convertirFechaAString(LocalDate fecha) {
        return fecha.format(FORMATO_FECHA);
    }

    public static String convertirEnteroAString(int numero) {
        return String.format(FORMATO_ENTERO, numero); // Formato de dos cifras
    }

    public static int convertirStringAEntero(String numeroStr) {
        try {
            return Integer.parseInt(numeroStr.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Formato de número entero inválido.");
        }
    }

    public enum EstadoSaldo {
        AL_DIA("Al día", 0.0),
        ANTICIPA("Anticipa", 1.0),
        DEBE("Debe", -1.0);

        private final String estado;
        private final double valor;

        EstadoSaldo(String estado, double valor) {
            this.estado = estado;
            this.valor = valor;
        }

        public String getEstado() {
            return estado;
        }

        public double getValor() {
            return valor;
        }

        public String getValorString() {
            return convertirDoubleAString(valor);
        }

    }

    public enum Mes {
        ENERO("Enero", 1),
        FEBRERO("Febrero", 2),
        MARZO("Marzo", 3),
        ABRIL("Abril", 4),
        MAYO("Mayo", 5),
        JUNIO("Junio", 6),
        JULIO("Julio", 7),
        AGOSTO("Agosto", 8),
        SEPTIEMBRE("Septiembre", 9),
        OCTUBRE("Octubre", 10),
        NOVIEMBRE("Noviembre", 11),
        DICIEMBRE("Diciembre", 12);

        private final String nombre;
        private final int numero;

        Mes(String nombre, int numero) {
            this.nombre = nombre;
            this.numero = numero;
        }

        public String getNombre() {
            return nombre;
        }

        public int getNumero() {
            return numero;
        }

        public String getNumeroString() {
            return convertirEnteroAString(numero);
        }

        public static Mes porNumero(int numero) {
            for (Mes mes : Mes.values()) {
                if (mes.getNumero() == numero) {
                    return mes;
                }
            }
            throw new IllegalArgumentException("Número inválido: " + numero);
        }

        public static Mes porNombre(String nombre) {
            for (Mes mes : Mes.values()) {
                if (mes.getNombre().equalsIgnoreCase(nombre)) {
                    return mes;
                }
            }
            throw new IllegalArgumentException("Nombre inválido: " + nombre);
        }
    }

    public static void agregarPlaceHolder(JTextField field, String placeHolder) {
        if (field.getText().trim().equals(VACIO)) {
            field.setText(placeHolder);
            field.setForeground(GRIS);
        }
    }

    public static void quitarPlaceHolder(JTextField field, String placeHolder) {
        if (field.getText().trim().equals(placeHolder)) {
            field.setText(VACIO);
            field.setForeground(NEGRO);
        }
    }

    public static boolean agregarSufijo(JTextField field, String sufijo) {
        if (!field.getText().trim().endsWith(sufijo)) {
            field.setText(field.getText().trim() + sufijo);
            return true;
        }
        return false;
    }

    public static boolean agregarSufijo(JLabel field, String sufijo) {
        if (!field.getText().trim().endsWith(sufijo)) {
            field.setText(field.getText().trim() + sufijo);
            return true;
        }
        return false;
    }

    public static void quitarSufijo(JTextField field, String sufijo) {
        if (field.getText().trim().endsWith(sufijo)) {
            field.setText(field.getText().trim().substring(0, field.getText().trim().length() - 2));
        }
    }

    public static void quitarSufijo(JLabel field, String sufijo) {
        if (field.getText().trim().endsWith(sufijo)) {
            field.setText(field.getText().trim().substring(0, field.getText().trim().length() - 2));
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
            int row = jTable.convertRowIndexToModel(filaSeleccionada); // Convertir índice de vista a modelo
            return (String) dtm.getValueAt(row, 0); // Obtener el numero de la columna ID (índice 0)
        }
        return null; // Si no hay fila seleccionada, retorna null
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
            labelAdvertencia.setText(VACIO);
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

    public static boolean validarFecha(LocalDate fecha) {
        // Obtener la fecha actual
        LocalDate fechaActual = LocalDate.now();

        // Validar que no sea posterior a la fecha actual
        return !fecha.isAfter(fechaActual);
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

    public static boolean validarMesesCubiertos(String meses) {
        try {
            int value = Integer.parseInt(meses.trim());
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean validarSaldo(String saldo) {
        String regex = "^\\d+(,\\d{2})?";
        if (!saldo.matches(regex)) {
            return false;
        }

        try {
            String numericPart = saldo.replace(",", ".");
            double value = Double.parseDouble(numericPart);
            return value >= 0;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.awt.Color;
import java.awt.Font;
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

// ========================================VARIABLES========================================
// ----------------------------------------FIREBASE----------------------------------------
    public static final String CLIENTES_JSON = "clientes.json";
    public static final String LOCALES_JSON = "locales.json";
    public static final String ANTICIPOS_JSON = "anticipos.json";
    public static final String FACTURAS_JSON = "facturas.json";
    public static final String PREFERENCIAS_JSON = "preferencias.json";
    public static final String FIREBASE_JSON = "tfg-uja2425-fjeo0002-firebase-adminsdk-fbsvc-93c6ce5c57.json";

    public static final String CLIENTES_COLECCION = "clientes";
    public static final String LOCALES_COLECCION = "locales";
    public static final String ANTICIPOS_COLECCION = "anticipos";
    public static final String FACTURAS_COLECCION = "facturas";

    public static final long TIEMPO_ACTUALIZACION_BBDD = 1;

    public static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapterGson())
            .create();

// ----------------------------------------UTILS----------------------------------------
    public static final String EURO = " €";
    public static final String PORCENTAJE = " %";
    public static final String TIPOA = "A";
    public static final String TIPOB = "B";
    public static final String VACIO = "";
    public static final String COMA = ",";
    public static final String PUNTO = ".";
    public static final char COMA_CHAR = ',';
    public static final String GUION = "-";

// ----------------------------------------FORMATOS----------------------------------------
    public static final String FORMATO_DECIMAL = "0.00";
    public static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final String FORMATO_ENTERO = "%02d";
    public static final String FORMATO_FECHA_STRING = "^\\d{2}/\\d{2}/\\d{4}$";

// ----------------------------------------SWING----------------------------------------
    public static final Color NEGRO = new Color(0, 0, 0);
    public static final Color GRIS = new Color(153, 153, 153);
    public static final Color BLANCO = new Color(255, 255, 255);
    public static final Color VERDE = new Color(144, 238, 144);
    public static final Color AMARILLO = new Color(255, 255, 102);
    public static final Color ROJO = new Color(241, 107, 107);

    public static final Font FONT = new Font("Segoe UI", 0, 14);

    public static final String SUBTOTAL = "Subtotal";

    public static final String PAGADO = "Pagado";
    public static final String FACTURADO = "Facturado";
    public static final String GUARDAR = "Guardar";

    public static final String ESTADO = "Estado";
    public static final String AL_DIA = "Al día";
    public static final String ANTICIPA = "Anticipa";
    public static final String DEBE = "Debe";

    public static final String TODOS = "Todos";
    public static final String ANTICIPOS_ACTIVOS = "Anticipos Activos";
    public static final String ANTICIPOS_FINALIZADOS = "Anticipos Finalizados";

    public static final String FILTROS_APLICADOS = "* Filtros aplicados";

// ----------------------------------------TITULOS----------------------------------------
    public static final String TITULO_VISTA_ANADIR_CLIENTE = "Añadir Nuevo Cliente";
    public static final String TITULO_VISTA_MODIFICAR_CLIENTE = "Modificar Cliente";
    public static final String TITULO_CLIENTE_REPETIDO = "Cliente Registrado";

    public static final String TITULO_VISTA_ANADIR_LOCAL = "Añadir Nuevo Local";
    public static final String TITULO_VISTA_MODIFICAR_LOCAL = "Modificar Local";
    public static final String TITULO_LOCAL_REPETIDO = "Local Registrado";

    public static final String TITULO_ANTICIPO_REPETIDO = "Anticipo Existente";
    public static final String TITULO_ANTICIPO_ACTIVO = "Anticipo Activo";
    public static final String TITULO_ANTICIPO_SOLAPADO = "Anticipo Solapado";
    public static final String TITULO_ANTICIPO_ANTERIOR_FACTURA = "Anticipo Anterior a Factura";
    public static final String TITULO_ANTICIPO_CONTIGUO_FACTURA = "Anticipo Contiguo a Factura";

    public static final String TITULO_FACTURA_REPETIDO = "Factura Existente";
    public static final String TITULO_FACTURA_CONTIGUA = "Factura No Contigua";
    public static final String TITULO_FACTURA_ANTICIPO_DISTINTOS = "Factura distinta a Anticipo";

    public static final String TITULO_CONTRASENA_CAMBIADA = "Contraseña Modificada";
    public static final String TITULO_CONTRASENA_IGUAL = "Contraseña No Modificada";

    public static final String TITULO_ERROR_FIREBASE = "Error de conexión";

    public static final String TITULO_INICIO_SESION = "Inicio de Sesión";
    public static final String TITULO_ERROR_CREDENCIALES = "Credenciales incorrectas";
    public static final String TITULO_USUARIO_NO_EXISTE = "Usuario inexistente";
    public static final String TITULO_USUARIO_YA_REGISTRADO = "Usuario registrado.";
    public static final String TITULO_USUARIO_REGISTRADO = "Registro completado";
    public static final String TITULO_USUARIO_MODIFICADO = "Usuario modificado.";

    public static final String TITULO_CONFIRMACION_ELIMINACION_USUARIO = "Confirmación de eliminación";
    public static final String TITULO_ELIMINACION_USUARIO = "Cuenta eliminada";

// ----------------------------------------MENSAJES---------------------------------------- 
    public static final String MENSAJE_CLIENTE_REPETIDO = "El cliente ya ha sido registrado";

    public static final String MENSAJE_LOCAL_REPETIDO = "El local ya ha sido registrado";

    public static final String MENSAJE_ANTICIPO_REPETIDO = "El anticipo ya ha sido registrado";
    public static final String MENSAJE_ANTICIPO_ACTIVO = "El cliente ya tiene un anticipo activo";
    public static final String MENSAJE_ANTICIPO_SOLAPADO = "El anticipo no puede solaparse con otros anticipos";
    public static final String MENSAJE_ANTICIPO_ANTERIOR_FACTURA = "El anticipo no puede ser anterior a la última factura del cliente";
    public static final String MENSAJE_ANTICIPO_CONTIGUO_FACTURA = "El anticipo debe ser contiguo a la última factura del cliente";

    public static final String MENSAJE_FACTURA_REPETIDO = "La factura ya ha sido registrada";
    public static final String MENSAJE_FACTURA_CONTIGUA = "La factura a generar debe ser contigua a la última creada del cliente";
    public static final String MENSAJE_FACTURA_ANTICIPO_DISTINTOS = "La factura a generar es distinta al anticipo que estaba creado";

    public static final String MENSAJE_CONTRASENA_CAMBIADA = "Contraseña cambiada con éxito";
    public static final String MENSAJE_CONTRASENA_IGUAL = "La nueva contraseña no puede ser igual a la anterior";

    public static final String MENSAJE_ERROR_FIREBASE = "Error al conectar con Firebase.";

    public static final String MENSAJE_INICIO_SESION = "Inicio de sesión exitoso.";
    public static final String MENSAJE_ERROR_CREDENCIALES = "Las credenciales no son correctas.";
    public static final String MENSAJE_USUARIO_NO_EXISTE = "No se encontraron datos del usuario.";
    public static final String MENSAJE_USUARIO_YA_REGISTRADO = "El usuario ya está registrado.";
    public static final String MENSAJE_USUARIO_REGISTRADO = "Usuario registrado con éxito.";
    public static final String MENSAJE_USUARIO_MODIFICADO = "Usuario modificado con éxito.";

    public static final String MENSAJE_CONFIRMACION_ELIMINACION_USUARIO = "¿Estás seguro de que deseas eliminar tu cuenta? Más adelante podrás recuperar tus datos en caso de volver a registrarse.";
    public static final String MENSAJE_ELIMINACION_USUARIO = "Tu cuenta ha sido eliminada correctamente.";
    
// ----------------------------------------ERRORES----------------------------------------
    public static final String ERROR_DNI_CLIENTE = "* DNI válido: 8 números y 1 mayúscula";
    public static final String ERROR_NOMBRE_CLIENTE = "* Nombre válido: sin números ni caracteres especiales";
    public static final String ERROR_EMAIL_CLIENTE = "* Introduce un correo electrónico válido";
    public static final String ERROR_CODIGOPOSTAL_CLIENTE = "* Código postal válido: 5 dígitos";

    public static final String ERROR_PRECIO_LOCAL = "* Introduce un número con 2 decimales";

    public static final String ERROR_ANIO = "* Introduce un año válido (4 dígitos)";

    public static final String ERROR_TELEFONO = "* Teléfono válido: 9 dígitos";

    public static final String ERROR_CONTRASENA_REPETIDA = "* La contraseñas deben coincidir";

    public static final String ERROR_CONTRASENA = "<html>"
            + "La contraseña debe cumplir con:<br>"
            + "✔ Al menos 8 caracteres<br>"
            + "✔ Al menos una letra mayúscula<br>"
            + "✔ Al menos una letra minúscula<br>"
            + "✔ Al menos un número<br>"
            + "✔ Al menos un símbolo (@$!%*?&)"
            + "</html>";

// ----------------------------------------PLACEHOLDERS----------------------------------------    
    public static final String PLACEHOLDER_DNI_CLIENTE = "12345678X";
    public static final String PLACEHOLDER_NOMBRE_CLIENTE = "Nombre Apellido1 Apellido2";
    public static final String PLACEHOLDER_ALIAS_CLIENTE = "Introduzca Alias de Cliente (opcional)";
    public static final String PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL = "nombre.123@gmail.com (opcional)";
    public static final String PLACEHOLDER_EMAIL_CLIENTE = "nombre.123@gmail.com";
    public static final String PLACEHOLDER_DIRECCION_CLIENTE = "C/ Mirabueno, 9, 9ºB";
    public static final String PLACEHOLDER_LOCALIDAD_CLIENTE = "Localidad, Provincia";
    public static final String PLACEHOLDER_CODIGO_POSTAL_CLIENTE = "12345";

    public static final String PLACEHOLDER_NOMBRE_LOCAL = "Introduzca Nombre de Local";
    public static final String PLACEHOLDER_ALIAS_LOCAL = "Introduzca Alias de Local (opcional)";
    public static final String PLACEHOLDER_PRECIO_LOCAL = "0,00 €";

    public static final String PLACEHOLDER_ANIO = "aaaa";

    public static final String PLACEHOLDER_TELEFONO = "9 dígitos sin espacios";

// ----------------------------------------VALIDACIONES----------------------------------------
    public static final String VALIDACION_DNI_CLIENTE = "\\d{8}[A-Z]";
    public static final String VALIDACION_NOMBRE_CLIENTE = "[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s'-]+";
    public static final String VALIDACION_EMAIL_CLIENTE = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String VALIDACION_CODIGO_POSTAL_CLIENTE = "^\\d{5}$";

    public static final String VALIDACION_ANIO = "^\\d{4}";

    public static final String VALIDACION_CONTRASENA = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";

    public static final String VALIDACION_TELEFONO = "^\\d{9}";

// ========================================ENUMS========================================
// ----------------------------------------MES----------------------------------------
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

// ========================================METODOS========================================
// ----------------------------------------PARSEAR----------------------------------------
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
        if (!fechaStr.matches(FORMATO_FECHA_STRING)) {
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

// ----------------------------------------CAMPOS FORMULARIOS----------------------------------------
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

// ----------------------------------------TABLAS----------------------------------------
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

// ----------------------------------------VALIDACIONES----------------------------------------
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
        if (!monto.matches("^\\d+(,\\d{2})?")) {
            return false;
        }

        try {
            String numericPart = monto.replace(COMA, PUNTO);
            double value = Double.parseDouble(numericPart);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }
// ----------------------------------------JOPTIONS----------------------------------------
    // Mensaje de Información

    public static void mostrarInformacion(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        JOptionPane.showMessageDialog(parent, mensaje, titulo, JOptionPane.INFORMATION_MESSAGE);
    }

    // Mensaje de Advertencia
    public static void mostrarAdvertencia(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        JOptionPane.showMessageDialog(parent, mensaje, titulo, JOptionPane.WARNING_MESSAGE);
    }

    // Mensaje de Error
    public static void mostrarError(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        JOptionPane.showMessageDialog(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    // Mensaje de Pregunta
    public static void mostrarPregunta(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        JOptionPane.showMessageDialog(parent, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    // Confirmación con botones Sí/No
    public static int mostrarConfirmacion(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        return JOptionPane.showConfirmDialog(parent, mensaje, titulo, JOptionPane.YES_NO_OPTION);
    }

    // Confirmación con botones Sí/No/Cancelar
    public static int mostrarConfirmacionExtendida(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        return JOptionPane.showConfirmDialog(parent, mensaje, titulo, JOptionPane.YES_NO_CANCEL_OPTION);
    }

    // Entrada de texto
    public static String mostrarSolicitarTexto(JFrame parent, String titulo, String mensaje) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        return JOptionPane.showInputDialog(parent, mensaje, titulo, JOptionPane.QUESTION_MESSAGE);
    }

    // Entrada con selección en ComboBox
    public static String mostrarSolicitarSeleccion(JFrame parent, String titulo, String mensaje, String[] opciones) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        return (String) JOptionPane.showInputDialog(parent, mensaje, titulo, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }

    // Opción personalizada
    public static int mostrarOpcionPersonalizada(JFrame parent, String titulo, String mensaje, String[] opciones) {
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        return JOptionPane.showOptionDialog(parent, mensaje, titulo, JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
    }
}

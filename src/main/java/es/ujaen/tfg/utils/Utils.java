/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import java.awt.Color;
import java.awt.Font;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
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

// ----------------------------------------ERRORES----------------------------------------
    public static final String ERROR_DNI_CLIENTE = "* Introduce un DNI válido (8 números y 1 letra mayúscula)";
    public static final String ERROR_NOMBRE_CLIENTE = "* Introduce un nombre válido (sin números ni caracteres especiales)";
    public static final String ERROR_EMAIL_CLIENTE = "* Introduce un correo electrónico válido";
    public static final String ERROR_CODIGOPOSTAL_CLIENTE = "* Introduce un código postal válido (5 dígitos)";

    public static final String ERROR_PRECIO_LOCAL = "* Introduce un número con 2 decimales";

    public static final String ERROR_ANIO = "* Introduce un año válido (4 dígitos)";

// ----------------------------------------PLACEHOLDERS----------------------------------------    
    public static final String PLACEHOLDER_DNI_CLIENTE = "12345678X";
    public static final String PLACEHOLDER_NOMBRE_CLIENTE = "Nombre Apellido1 Apellido2";
    public static final String PLACEHOLDER_ALIAS_CLIENTE = "Introduzca Alias de Cliente (opcional)";
    public static final String PLACEHOLDER_EMAIL_CLIENTE = "nombre.123@gmail.com (opcional)";
    public static final String PLACEHOLDER_DIRECCION_CLIENTE = "C/ Mirabueno, 9, 9ºB";
    public static final String PLACEHOLDER_LOCALIDAD_CLIENTE = "Localidad, Provincia";
    public static final String PLACEHOLDER_CODIGO_POSTAL_CLIENTE = "12345";

    public static final String PLACEHOLDER_NOMBRE_LOCAL = "Introduzca Nombre de Local";
    public static final String PLACEHOLDER_ALIAS_LOCAL = "Introduzca Alias de Local (opcional)";
    public static final String PLACEHOLDER_PRECIO_LOCAL = "0,00 €";

    public static final String PLACEHOLDER_ANIO = "aaaa";

// ----------------------------------------VALIDACIONES----------------------------------------
    public static final String VALIDACION_DNI_CLIENTE = "\\d{8}[A-Z]";
    public static final String VALIDACION_NOMBRE_CLIENTE = "[a-zA-ZÁÉÍÓÚáéíóúÑñ\\s'-]+";
    public static final String VALIDACION_EMAIL_CLIENTE = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
    public static final String VALIDACION_CODIGO_POSTAL_CLIENTE = "^\\d{5}$";

    public static final String VALIDACION_ANIO = "^\\d{4}";

// ========================================ENUMS========================================
// ----------------------------------------ESTADOSALDO----------------------------------------
    /*
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
     */
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
// ----------------------------------------FIREBASE----------------------------------------
    public static <T> void sincronizarConFirebase(
            String archivoJson,
            String coleccionFirebase,
            String ultimoHashArchivo,
            List<T> lista,
            Function<T, String> getID
    ) throws IOException {
        Firestore db = FirebaseInitializer.getInstance().getDb();
        String hashActual = calcularHashArchivo(archivoJson);

        if (!hashActual.equals(ultimoHashArchivo)) {
            // Obtener los documentos actuales de Firebase
            List<String> listaFirebase = new ArrayList<>();
            try {
                QuerySnapshot querySnapshot = db.collection(coleccionFirebase).get().get();
                for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                    listaFirebase.add(document.getId());
                }
            } catch (InterruptedException | ExecutionException e) {
            }

            // Sincronizar objetos locales con Firebase
            for (T item : lista) {
                try {
                    db.collection(coleccionFirebase).document(getID.apply(item)).set(item).get();
                } catch (InterruptedException | ExecutionException e) {
                }
            }

            // Eliminar objetos de Firebase que ya no están en el archivo local
            /*
            for (String itemFirebase : listaFirebase) {
                boolean existe = false;
                for (T item : listaLocal) {
                    if (getId.apply(item).equals(itemFirebase)) {
                        existe = true;
                        break;
                    }
                }
                if (!existe) {
                    try {
                        db.collection(coleccionFirebase).document(itemFirebase).delete().get();
                    } catch (InterruptedException | ExecutionException e) {
                    }
                }
            }
             */
            for (String firebaseId : listaFirebase) {
                boolean existsInLocal = lista.stream().anyMatch(item -> getID.apply(item).equals(firebaseId));
                if (!existsInLocal) {
                    try {
                        db.collection(coleccionFirebase).document(firebaseId).delete().get();
                    } catch (InterruptedException | ExecutionException e) {
                    }
                }
            }
            ultimoHashArchivo = hashActual;
        }
    }

    public static <T> void iniciarSincronizacionPeriodica(
            String archivoJson,
            String coleccionFirebase,
            String ultimoHashArchivo,
            List<T> lista,
            Function<T, String> getID
    ) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(() -> {
            try {
                sincronizarConFirebase(archivoJson, coleccionFirebase, ultimoHashArchivo, lista, getID);
            } catch (IOException ex) {
            }
        }, 0, 1, TimeUnit.MINUTES); // Sincronización cada minuto
    }

    public static <T> List<T> cargarDatosDesdeArchivo(String archivoJson, Type listType) {
        try {
            if (!Files.exists(Paths.get(archivoJson))) { // Si el archivo no existe
                return new ArrayList<>(); // Retornar lista vacía
            }
            String jsonData = new String(Files.readAllBytes(Paths.get(archivoJson))); // Leer archivo JSON
            return gson.fromJson(jsonData, listType); // Convertir JSON a lista de objetos
        } catch (IOException e) {
            return new ArrayList<>(); // En caso de error, retornar lista vacía
        }
    }

    public static <T> void guardarDatosEnArchivo(String archivoJson, List<T> lista) {
        try (FileWriter writer = new FileWriter(archivoJson)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
        }
    }

    public static <T> List<T> cargarDatosDesdeFirebase(String archivoJson, String coleccionFirebase, Class<T> clase) throws IOException {
        Firestore db = FirebaseInitializer.getInstance().getDb();
        try {
            QuerySnapshot querySnapshot = db.collection(coleccionFirebase).get().get();
            List<T> lista = new ArrayList<>();
            for (QueryDocumentSnapshot document : querySnapshot.getDocuments()) {
                T objeto = document.toObject(clase); // Mapear documento a clase
                lista.add(objeto);
            }
            Utils.guardarDatosEnArchivo(archivoJson, lista); // Guardar datos en el archivo local
            return lista;
        } catch (InterruptedException | ExecutionException e) {
            return new ArrayList<>();
        }
    }

// ----------------------------------------HASH----------------------------------------
    public static String calcularHashArchivo(String filePath) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(filePath)); // Leer contenido del archivo
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256"); // Algoritmo SHA-256
            byte[] hashBytes = digest.digest(fileBytes); // Calcular el hash
            return Base64.getEncoder().encodeToString(hashBytes); // Convertir a Base64
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al calcular el hash del archivo", e);
        }
    }

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

    /*
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
     */
// ----------------------------------------JOPTIONS----------------------------------------
    public static void mostrarError(JFrame parent, String titulo, String mensaje) {
        // Cambiar la fuente global del JOptionPane
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

        // Mostrar el JOptionPane con la fuente personalizada
        JOptionPane.showMessageDialog(parent, mensaje, titulo, JOptionPane.ERROR_MESSAGE);
    }

    public static int mostrarConfirmacion(JFrame parent, String titulo, String mensaje) {
        // Cambiar la fuente global del JOptionPane
        UIManager.put("OptionPane.messageFont", new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));

        // Mostrar el JOptionPane con la fuente personalizada
        return JOptionPane.showConfirmDialog(parent, mensaje, titulo, JOptionPane.OK_CANCEL_OPTION);
    }
}

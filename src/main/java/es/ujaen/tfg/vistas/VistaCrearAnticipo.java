/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.mxrck.autocompleter.TextAutoCompleter;
import es.ujaen.tfg.controlador.AnticipoControlador;
import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.controlador.FacturaControlador;
import es.ujaen.tfg.controlador.PreferenciasControlador;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.modelo.Usuario;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.UndoManager;
import static es.ujaen.tfg.utils.Utils.ANTICIPA;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ANTICIPO_ACTIVO;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ANTICIPO_ANTERIOR_FACTURA;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ANTICIPO_CONTIGUO_FACTURA;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ANTICIPO_REPETIDO;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ANTICIPO_SOLAPADO;
import static es.ujaen.tfg.utils.Utils.MENSAJE_FACTURA_REPETIDO;
import static es.ujaen.tfg.utils.Utils.TITULO_ANTICIPO_ACTIVO;
import static es.ujaen.tfg.utils.Utils.TITULO_ANTICIPO_ANTERIOR_FACTURA;
import static es.ujaen.tfg.utils.Utils.TITULO_ANTICIPO_CONTIGUO_FACTURA;
import static es.ujaen.tfg.utils.Utils.TITULO_ANTICIPO_REPETIDO;
import static es.ujaen.tfg.utils.Utils.TITULO_ANTICIPO_SOLAPADO;
import static es.ujaen.tfg.utils.Utils.TITULO_FACTURA_REPETIDO;
import static es.ujaen.tfg.utils.Utils.convertirStringAFecha;
import static es.ujaen.tfg.utils.Utils.mostrarError;
import static es.ujaen.tfg.utils.Utils.validarFecha;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author jota
 */
public class VistaCrearAnticipo extends javax.swing.JDialog implements Observador {

    private final JFrame parent;

    private final ClienteControlador clienteControlador;
    private final AnticipoControlador anticipoControlador;
    private final FacturaControlador facturaControlador;
    private final PreferenciasControlador preferenciasControlador;

    private final Usuario usuario;

    //private final UndoManager undoManager;
    private TextAutoCompleter autoCompleterBuscadorClientes;

    private boolean campoBuscadorClientesCorrecto;
    private boolean campoFechaCorrecto;

    /**
     * Creates new form VistaCrearAnticipo
     *
     * @param parent
     * @param modal
     * @param clienteControlador
     * @param anticipoControlador
     * @param facturaControlador
     * @param preferenciasControlador
     * @param usuario
     */
    public VistaCrearAnticipo(java.awt.Frame parent, boolean modal, ClienteControlador clienteControlador, AnticipoControlador anticipoControlador, FacturaControlador facturaControlador, PreferenciasControlador preferenciasControlador, Usuario usuario) {
        super(parent, modal);
        initComponents();
        this.parent = (JFrame) parent;
        setLocationRelativeTo(null);

        this.clienteControlador = clienteControlador;
        this.clienteControlador.agregarObservador(this);

        this.anticipoControlador = anticipoControlador;

        this.facturaControlador = facturaControlador;

        this.preferenciasControlador = preferenciasControlador;

        this.usuario = usuario;

        this.campoBuscadorClientesCorrecto = false;
        this.campoFechaCorrecto = false;

        inicializarListenerFecha();
        cargarAutocompletarBuscadorClientes();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelCabecera = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelCuerpo = new javax.swing.JPanel();
        jLabelBuscarCliente = new javax.swing.JLabel();
        jTextFieldBuscadorClientes = new javax.swing.JTextField();
        jLabelFechaFactura = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabelMontoAnticipo = new javax.swing.JLabel();
        jSpinnerMonto = new javax.swing.JSpinner();
        jLabelMesesCubiertos = new javax.swing.JLabel();
        jSpinnerMesesCubiertos = new javax.swing.JSpinner();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonCrearAnticipo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Anticipo");
        setIconImage(null);
        setResizable(false);

        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Crear Anticipo");
        jPanelCabecera.add(jLabelTitulo);

        jPanelPrincipal.add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new java.awt.GridBagLayout());

        jLabelBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelBuscarCliente.setText("Buscar Cliente");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelBuscarCliente, gridBagConstraints);

        jTextFieldBuscadorClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldBuscadorClientes.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorClientes.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBuscadorClientesFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldBuscadorClientes, gridBagConstraints);

        jLabelFechaFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFechaFactura.setText("Fecha de Anticipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelFechaFactura, gridBagConstraints);

        jDateChooser.setDateFormatString("dd/MM/yyyy");
        jDateChooser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDateChooser.setPreferredSize(new java.awt.Dimension(103, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jDateChooser, gridBagConstraints);

        jLabelMontoAnticipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelMontoAnticipo.setText("Monto del Anticipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelMontoAnticipo, gridBagConstraints);

        jSpinnerMonto.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinnerMonto.setModel(new javax.swing.SpinnerNumberModel(50.0d, 0.01d, null, 1.0d));
        jSpinnerMonto.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerMonto, "0.00 '€'"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jSpinnerMonto, gridBagConstraints);

        jLabelMesesCubiertos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelMesesCubiertos.setText("Meses Cubiertos");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelMesesCubiertos, gridBagConstraints);

        jSpinnerMesesCubiertos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinnerMesesCubiertos.setModel(new javax.swing.SpinnerNumberModel(3, 1, null, 1));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jSpinnerMesesCubiertos, gridBagConstraints);

        jPanelPrincipal.add(jPanelCuerpo, java.awt.BorderLayout.CENTER);

        jPanelPiePagina.setName(""); // NOI18N
        jPanelPiePagina.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonCancelar);

        jButtonCrearAnticipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonCrearAnticipo.setText("Crear Anticipo");
        jButtonCrearAnticipo.setEnabled(false);
        jButtonCrearAnticipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearAnticipoActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonCrearAnticipo);

        jPanelPrincipal.add(jPanelPiePagina, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 374, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 379, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonCrearAnticipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearAnticipoActionPerformed
        // TODO add your handling code here:
        String ID;
        double monto;
        int mesesCubiertos;
        LocalDate fecha;
        double saldo;
        Cliente clienteOriginal;
        Cliente clienteModificado;
        String clienteDNI;

        UUID uuid = UUID.randomUUID();
        ID = uuid.toString().trim();

        monto = ((Number) jSpinnerMonto.getValue()).doubleValue();

        mesesCubiertos = ((Number) jSpinnerMesesCubiertos.getValue()).intValue();

        JTextField dateField = (JTextField) jDateChooser.getDateEditor().getUiComponent();
        String fechaTxt = dateField.getText().trim();
        fecha = convertirStringAFecha(fechaTxt);

        saldo = monto;

        String nombreAliasCliente = jTextFieldBuscadorClientes.getText().trim();
        clienteOriginal = clienteControlador.buscarPorNombre(nombreAliasCliente);
        if (clienteOriginal == null) {
            clienteOriginal = clienteControlador.buscarPorAlias(nombreAliasCliente);
        }
        clienteDNI = clienteOriginal.getDNI();

        Anticipo anticipo = new Anticipo(
                ID,
                monto,
                mesesCubiertos,
                fecha,
                saldo,
                clienteDNI
        );

        boolean procesarAnticipo = procesarAnticipo(anticipo);
        if (!procesarAnticipo) {
            return;
        }

        // Creamos tantas facturas con fechas anticipadas como meses cubiertos tengamos
        // Dejo por ahora el numero de factura inconcluso (0)
        int numero = 0;
        String letra = clienteOriginal.getTipo();
        boolean pagado = true;
        boolean facturado = false;

        double montoMes = monto / mesesCubiertos;

        List<Factura> facturasAnticipadas = new ArrayList<>();
        for (int i = 0; i < mesesCubiertos; i++) {
            ID = facturaControlador.generarIdFactura(letra, numero, fecha, clienteDNI);

            Factura factura = new Factura(ID, letra, numero, fecha, pagado, facturado, montoMes, clienteDNI);
            boolean facturaRepetida = facturaControlador.facturaRepetida(factura);
            if (facturaRepetida) {
                mostrarError(parent, TITULO_FACTURA_REPETIDO, MENSAJE_FACTURA_REPETIDO);
                return;
            }
            fecha = fecha.plusMonths(+1);
            facturasAnticipadas.add(factura);
        }

        // Finalmente, si todo ha salido bien:
        // Actualizamos el saldo del cliente:
        clienteModificado = new Cliente(clienteOriginal);
        clienteModificado.setSaldo(saldo);
        clienteModificado.setEstado(ANTICIPA);
        // Creamos el Anticipo + FacturasAnticipadas + actualizamos saldo de Cliente
        anticipoControlador.crear(anticipo, clienteOriginal, clienteModificado, facturasAnticipadas, usuario);

        // Creamos las Facturas asociadas
        // Ahora las facturas anticipadas deben crearse en correlacion al Anticipo creado en el Command
        /*
        for (Factura factura : facturasACrear) {
            facturaControlador.crear(factura);
        }
         */
        // Ahora el saldo del cliente debe actualizarse en correlacion al Anticipo creado en el Command
        //clienteControlador.numerar(clienteOriginal, clienteModificado);
        dispose();
    }//GEN-LAST:event_jButtonCrearAnticipoActionPerformed

    private void jTextFieldBuscadorClientesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorClientesFocusLost
        // TODO add your handling code here:
        campoBuscadorClientesCorrecto = false;
        if (!jTextFieldBuscadorClientes.getText().trim().isEmpty()) {
            for (Cliente cliente : clienteControlador.leerTodos()) {
                if (jTextFieldBuscadorClientes.getText().trim().equals(cliente.getNombre())
                        || jTextFieldBuscadorClientes.getText().trim().equals(cliente.getAlias())) {
                    campoBuscadorClientesCorrecto = true;
                    break;
                }
            }
        }

        habilitarBotonCrearAnticipo();
    }//GEN-LAST:event_jTextFieldBuscadorClientesFocusLost

    private void inicializarListenerFecha() {
        // Obtener el editor de texto asociado al JDateChooser
        JTextField dateField = (JTextField) jDateChooser.getDateEditor().getUiComponent();
        // Agregar un DocumentListener al campo de la fecha
        dateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                procesarFecha(dateField.getText().trim());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                procesarFecha(dateField.getText().trim());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                procesarFecha(dateField.getText().trim());
            }
        });
    }

    private void procesarFecha(String fechaTxt) {
        // Validar si el texto cumple con el formato "dd/MM/yyyy"
        LocalDate fecha = convertirStringAFecha(fechaTxt);
        if (fecha != null) {
            campoFechaCorrecto = validarFecha(fecha);
        } else {
            campoFechaCorrecto = false;
        }

        // Actualizar el estado del botón
        habilitarBotonCrearAnticipo();
    }

    private boolean procesarAnticipo(Anticipo anticipo) {
        // 1º El anticipo no puede estar ya creado
        boolean anticipoRepetido = anticipoControlador.anticipoRepetido(anticipo);
        if (anticipoRepetido) {
            mostrarError(parent, TITULO_ANTICIPO_REPETIDO, MENSAJE_ANTICIPO_REPETIDO);
            return false;
        }
        // 2º El anticipo no puede crearse si hay uno activo ya
        Anticipo anticipoActivo = anticipoControlador.anticipoActivo(anticipo.getClienteDNI());
        if (anticipoActivo != null) {
            mostrarError(parent, TITULO_ANTICIPO_ACTIVO, MENSAJE_ANTICIPO_ACTIVO);
            return false;
        }
        // 3º El anticipo no puede solaparse con anticipos creados
        boolean anticipoSolapado = anticipoControlador.anticipoSolapado(anticipo);
        if (anticipoSolapado) {
            mostrarError(parent, TITULO_ANTICIPO_SOLAPADO, MENSAJE_ANTICIPO_SOLAPADO);
            return false;
        }
        // 4º El anticipo debe ser más tardío que la fecha de la última Factura
        boolean anticipoTardioAUltimaFactura = facturaControlador.anticipoTardioAUltimaFactura(anticipo);
        if (!anticipoTardioAUltimaFactura) {
            mostrarError(parent, TITULO_ANTICIPO_ANTERIOR_FACTURA, MENSAJE_ANTICIPO_ANTERIOR_FACTURA);
            return false;
        }
        // 5º El anticipo debe ser contiguo a la ultima Factura del Cliente
        // Ahora con las preferencias pueden no serlo
        boolean preferenciaFacturasContiguas = preferenciasControlador.obtenerPreferencias().getFacturasContiguas();
        if (preferenciaFacturasContiguas == true) {
            boolean anticipoContiguoAUltimaFactura = facturaControlador.anticipoContiguoAUltimaFactura(anticipo);
            if (!anticipoContiguoAUltimaFactura) {
                mostrarError(parent, TITULO_ANTICIPO_CONTIGUO_FACTURA, MENSAJE_ANTICIPO_CONTIGUO_FACTURA);
                return false;
            }
        }
        // Ha pasado todos los filtros: Anticipo válido
        return true;
    }

    private void cargarAutocompletarBuscadorClientes() {
        //Iniciamos el Autocompletes con el Buscador de Clientes
        autoCompleterBuscadorClientes = new TextAutoCompleter(jTextFieldBuscadorClientes);

        //Esto hace que también se pueda buscar por en medio del String
        autoCompleterBuscadorClientes.setMode(0);

        List<Cliente> clientes = clienteControlador.leerTodos();

        if (clientes != null) {
            for (Cliente cliente : clientes) {
                autoCompleterBuscadorClientes.addItem(cliente.getNombre());
                autoCompleterBuscadorClientes.addItem(cliente.getAlias());
            }
        }

    }

    private void habilitarBotonCrearAnticipo() {
        if (campoBuscadorClientesCorrecto) {
            if (campoFechaCorrecto) {
                jButtonCrearAnticipo.setEnabled(true);
                return;
            }
        }
        jButtonCrearAnticipo.setEnabled(false);
    }

    @Override
    public void actualizar() {
        actualizarAutocompleter();
    }

    private void actualizarAutocompleter() {
        autoCompleterBuscadorClientes.removeAllItems();
        cargarAutocompletarBuscadorClientes();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonCrearAnticipo;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabelBuscarCliente;
    private javax.swing.JLabel jLabelFechaFactura;
    private javax.swing.JLabel jLabelMesesCubiertos;
    private javax.swing.JLabel jLabelMontoAnticipo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JSpinner jSpinnerMesesCubiertos;
    private javax.swing.JSpinner jSpinnerMonto;
    private javax.swing.JTextField jTextFieldBuscadorClientes;
    // End of variables declaration//GEN-END:variables

}

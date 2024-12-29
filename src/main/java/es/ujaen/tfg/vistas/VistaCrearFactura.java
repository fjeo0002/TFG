/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.mxrck.autocompleter.TextAutoCompleter;
import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.controlador.FacturaControlador;
import es.ujaen.tfg.controlador.LocalControlador;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.observer.Observador;
import static es.ujaen.tfg.utils.Utils.agregarSufijo;
import static es.ujaen.tfg.utils.Utils.quitarSufijo;
import static es.ujaen.tfg.utils.Utils.sufijoPrecios;
import static es.ujaen.tfg.utils.Utils.validarFecha;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author jota
 */
public class VistaCrearFactura extends javax.swing.JFrame implements Observador {

    private Local localBuscado;
    private double total;

    private final ClienteControlador clienteControlador;
    private final LocalControlador localControlador;
    private final FacturaControlador facturaControlador;

    private TextAutoCompleter autoCompleterBuscadorClientes;
    private TextAutoCompleter autoCompleterBuscadorLocales;

    private boolean campoBuscadorClientesCorrecto;
    private boolean campoFechaCorrecto;
    private boolean campoBuscadorLocalesCorrecto;

    private final DefaultTableModel dtm;

    private final JFrame parent;

    /**
     * Creates new form VistaCrearFactura
     *
     * @param parent
     * @param clienteControlador
     * @param localControlador
     * @param facturaControlador
     */
    public VistaCrearFactura(JFrame parent, ClienteControlador clienteControlador, LocalControlador localControlador, FacturaControlador facturaControlador) {
        initComponents();
        setLocationRelativeTo(null);
        this.parent = parent;

        this.clienteControlador = clienteControlador;
        this.clienteControlador.agregarObservador(this);

        this.localControlador = localControlador;
        this.localControlador.agregarObservador(this);

        this.facturaControlador = facturaControlador;
        this.facturaControlador.agregarObservador(this);

        this.campoBuscadorClientesCorrecto = false;
        this.campoFechaCorrecto = false;
        this.campoBuscadorLocalesCorrecto = false;

        this.jRadioButtonFactura.setSelected(true);

        this.dtm = (DefaultTableModel) jTable.getModel();

        this.total = 0.0;

        addTableSelectionListener();
        inicializarListenerFecha();
        cargarAutocompletarBuscadorClientes();
        cargarAutocompletarBuscadorLocales();
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

        buttonGroupFactura = new javax.swing.ButtonGroup();
        jPanelPrincipal = new javax.swing.JPanel();
        jPanelDetallesFactura = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelBuscarCliente = new javax.swing.JLabel();
        jTextFieldBuscadorClientes = new javax.swing.JTextField();
        jLabelFechaFactura = new javax.swing.JLabel();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        jLabelElegirTipoFactura = new javax.swing.JLabel();
        jPanelRadioButtons = new javax.swing.JPanel();
        jRadioButtonFactura = new javax.swing.JRadioButton();
        jRadioButtonFacturaAbono = new javax.swing.JRadioButton();
        jPanelCuerpo = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelSeleccionarLocal = new javax.swing.JLabel();
        jPanelSeleccionarLocal = new javax.swing.JPanel();
        jLabelBuscarLocal = new javax.swing.JLabel();
        jLabelCantidad = new javax.swing.JLabel();
        jLabelPrecioUnitario = new javax.swing.JLabel();
        jLabelIVA = new javax.swing.JLabel();
        jLabelRetencion = new javax.swing.JLabel();
        jTextFieldBuscadorLocales = new javax.swing.JTextField();
        jSpinnerCantidad = new javax.swing.JSpinner();
        jLabelPrecioUnitarioValor = new javax.swing.JLabel();
        jLabelIVAValor = new javax.swing.JLabel();
        jLabelRetencionValor = new javax.swing.JLabel();
        jPanelAgregarEliminarLocal = new javax.swing.JPanel();
        jPanelBotonAgregarLocal = new javax.swing.JPanel();
        jButtonAgregarLocal = new javax.swing.JButton();
        jPanelEliminarLocal = new javax.swing.JPanel();
        jButtonEliminarLocal = new javax.swing.JButton();
        jScrollPaneTabla = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();
        jPanelPiePagina = new javax.swing.JPanel();
        jPanelLabelTotal = new javax.swing.JPanel();
        jLabelTotal = new javax.swing.JLabel();
        jPanelBotones = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonGenerarFactura = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Crear Factura");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelDetallesFactura.setLayout(new java.awt.GridBagLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Crear Factura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jLabelTitulo, gridBagConstraints);

        jLabelBuscarCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelBuscarCliente.setText("Buscar Cliente por Nombre o Alias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jLabelBuscarCliente, gridBagConstraints);

        jTextFieldBuscadorClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldBuscadorClientes.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldBuscadorClientes.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorClientes.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorClientes.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBuscadorClientesFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jTextFieldBuscadorClientes, gridBagConstraints);

        jLabelFechaFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFechaFactura.setText("Fecha de Factura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jLabelFechaFactura, gridBagConstraints);

        jDateChooser.setDateFormatString("dd/MM/yyyy");
        jDateChooser.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jDateChooser.setPreferredSize(new java.awt.Dimension(103, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.ipadx = 75;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jDateChooser, gridBagConstraints);

        jLabelElegirTipoFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelElegirTipoFactura.setText("Elegir Tipo de Factura");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jLabelElegirTipoFactura, gridBagConstraints);

        buttonGroupFactura.add(jRadioButtonFactura);
        jRadioButtonFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButtonFactura.setText("Factura");
        jPanelRadioButtons.add(jRadioButtonFactura);

        buttonGroupFactura.add(jRadioButtonFacturaAbono);
        jRadioButtonFacturaAbono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButtonFacturaAbono.setText("Factura de Abono");
        jPanelRadioButtons.add(jRadioButtonFacturaAbono);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelDetallesFactura.add(jPanelRadioButtons, gridBagConstraints);

        jPanelPrincipal.add(jPanelDetallesFactura, java.awt.BorderLayout.NORTH);

        jPanelCuerpo.setLayout(new javax.swing.BoxLayout(jPanelCuerpo, javax.swing.BoxLayout.Y_AXIS));

        jPanelTitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelSeleccionarLocal.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelSeleccionarLocal.setText("Seleccionar Local");
        jLabelSeleccionarLocal.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        jPanelTitulo.add(jLabelSeleccionarLocal);

        jPanelCuerpo.add(jPanelTitulo);

        jPanelSeleccionarLocal.setLayout(new java.awt.GridBagLayout());

        jLabelBuscarLocal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelBuscarLocal.setText("Buscar Local por Nombre o Alias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 0);
        jPanelSeleccionarLocal.add(jLabelBuscarLocal, gridBagConstraints);

        jLabelCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelCantidad.setText("Cantidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelCantidad, gridBagConstraints);

        jLabelPrecioUnitario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPrecioUnitario.setText("Precio Unitario");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelPrecioUnitario, gridBagConstraints);

        jLabelIVA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelIVA.setText("IVA");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelIVA, gridBagConstraints);

        jLabelRetencion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelRetencion.setText("Retención");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelRetencion, gridBagConstraints);

        jTextFieldBuscadorLocales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldBuscadorLocales.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldBuscadorLocales.setPreferredSize(new java.awt.Dimension(180, 26));
        jTextFieldBuscadorLocales.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldBuscadorLocalesFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.ipadx = 200;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 5, 2, 0);
        jPanelSeleccionarLocal.add(jTextFieldBuscadorLocales, gridBagConstraints);

        jSpinnerCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(1, 1, null, 1));
        jSpinnerCantidad.setPreferredSize(new java.awt.Dimension(75, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jSpinnerCantidad, gridBagConstraints);

        jLabelPrecioUnitarioValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelPrecioUnitarioValor, gridBagConstraints);

        jLabelIVAValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelIVAValor.setText("21 %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelIVAValor, gridBagConstraints);

        jLabelRetencionValor.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelRetencionValor.setText("19 %");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 25, 2, 0);
        jPanelSeleccionarLocal.add(jLabelRetencionValor, gridBagConstraints);

        jPanelCuerpo.add(jPanelSeleccionarLocal);

        jPanelAgregarEliminarLocal.setLayout(new java.awt.GridLayout(1, 2));

        jPanelBotonAgregarLocal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jButtonAgregarLocal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAgregarLocal.setText("Agregar Local");
        jButtonAgregarLocal.setEnabled(false);
        jButtonAgregarLocal.setNextFocusableComponent(jButtonCancelar);
        jButtonAgregarLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAgregarLocalActionPerformed(evt);
            }
        });
        jPanelBotonAgregarLocal.add(jButtonAgregarLocal);

        jPanelAgregarEliminarLocal.add(jPanelBotonAgregarLocal);

        jPanelEliminarLocal.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonEliminarLocal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEliminarLocal.setText("Eliminar Local");
        jButtonEliminarLocal.setEnabled(false);
        jButtonEliminarLocal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarLocalActionPerformed(evt);
            }
        });
        jPanelEliminarLocal.add(jButtonEliminarLocal);

        jPanelAgregarEliminarLocal.add(jPanelEliminarLocal);

        jPanelCuerpo.add(jPanelAgregarEliminarLocal);

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cantidad", "Local", "Precio Unitario", "IVA", "Retención", "Subtotal"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.setShowGrid(false);
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPaneTabla.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setPreferredWidth(5);
            jTable.getColumnModel().getColumn(2).setPreferredWidth(15);
            jTable.getColumnModel().getColumn(3).setPreferredWidth(10);
            jTable.getColumnModel().getColumn(4).setPreferredWidth(10);
        }

        jPanelCuerpo.add(jScrollPaneTabla);

        jPanelPrincipal.add(jPanelCuerpo, java.awt.BorderLayout.CENTER);

        jPanelPiePagina.setName(""); // NOI18N
        jPanelPiePagina.setLayout(new java.awt.GridLayout(1, 2));

        jLabelTotal.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabelTotal.setText("Total: 0,00€");
        jPanelLabelTotal.add(jLabelTotal);

        jPanelPiePagina.add(jPanelLabelTotal);

        jPanelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonCancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonCancelar.setText("Cancelar");
        jButtonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCancelarActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonCancelar);

        jButtonGenerarFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonGenerarFactura.setText("Generar Factura");
        jButtonGenerarFactura.setEnabled(false);
        jButtonGenerarFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonGenerarFacturaActionPerformed(evt);
            }
        });
        jPanelBotones.add(jButtonGenerarFactura);

        jPanelPiePagina.add(jPanelBotones);

        jPanelPrincipal.add(jPanelPiePagina, java.awt.BorderLayout.SOUTH);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 810, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 515, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        parent.setEnabled(true);
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonGenerarFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonGenerarFacturaActionPerformed
        // TODO add your handling code here:
        JTextField dateField = (JTextField) jDateChooser.getDateEditor().getUiComponent();
        String fecha = dateField.getText().trim();

        String nombreAliasCliente = jTextFieldBuscadorClientes.getText().trim();
        Cliente cliente = clienteControlador.buscarPorNombre(nombreAliasCliente);
        if (cliente == null) {
            cliente = clienteControlador.buscarPorAlias(nombreAliasCliente);
        }

        String monto = String.format("%.2f", total);

        String anio = "";
        String[] diaMesAnio = fecha.split("/");
        anio = diaMesAnio[2];

        boolean clienteA = false;
        String numero = "";
        if ("A".equals(cliente.getTipo())) {
            numero += "A/";
            clienteA = true;
        } else {
            numero += "B/";
            clienteA = false;
        }

        List<Factura> todasFacturas = facturaControlador.leerTodos();
        if (todasFacturas == null) {
            numero += "1";
        } else {
            // Mapa para agrupar el último número por tipo y año
            Map<String, Map<String, Integer>> ultimoPorTipoYAno = new HashMap<>();

            for (Factura factura : todasFacturas) {
                String id = factura.getNumero();
                String[] partes = id.split("/"); // Ejemplo: "A/1"
                String tipo = partes[0];
                int num = Integer.parseInt(partes[1]);

                // Obtener la fecha de la factura
                String facturaFecha = factura.getFecha();
                String facturaAnio = facturaFecha.split("/")[2]; // Suponiendo formato "dd/MM/yyyy"

                // Inicializar mapas anidados
                ultimoPorTipoYAno.putIfAbsent(tipo, new HashMap<>());
                Map<String, Integer> facturasPorAnio = ultimoPorTipoYAno.get(tipo);

                // Actualizar el último número para el tipo y año
                facturasPorAnio.put(facturaAnio, Math.max(facturasPorAnio.getOrDefault(facturaAnio, 0), num));
            }

            // Obtener el último número para el tipo y año actuales
            Map<String, Integer> facturasDelTipo = ultimoPorTipoYAno.getOrDefault(clienteA ? "A" : "B", new HashMap<>());
            int ultimoNumero = facturasDelTipo.getOrDefault(anio, 0);

            // Incrementar el número de factura
            int siguienteNumero = ultimoNumero + 1;
            numero += String.valueOf(siguienteNumero);

        }

        Factura factura = new Factura(numero, fecha, true, true, cliente, monto);
        facturaControlador.crear(factura);

        parent.setEnabled(true);
        
        // Limpiar Textos de Cliente y Tabla 
        // Deshabilitar Boton GenerarFactura
        // Reinciar valor de total
        jTextFieldBuscadorClientes.setText("");
        dtm.setRowCount(0);
        jButtonGenerarFactura.setEnabled(false);
        total = 0.0;
        actualizarLabelTotal();
    }//GEN-LAST:event_jButtonGenerarFacturaActionPerformed

    private void jTextFieldBuscadorLocalesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorLocalesFocusLost
        // TODO add your handling code here:
        campoBuscadorLocalesCorrecto = false;
        localBuscado = null;

        if (!jTextFieldBuscadorLocales.getText().trim().isEmpty()) {
            for (Local local : localControlador.leerTodos()) {
                if (jTextFieldBuscadorLocales.getText().trim().equals(local.getNombre())
                        || jTextFieldBuscadorLocales.getText().trim().equals(local.getAlias())) {
                    campoBuscadorLocalesCorrecto = true;
                    localBuscado = new Local(local);
                    break;
                }
            }
        }

        habilitarBotonAgregarLocal();
        actualizarLabelPrecioUnitarioValor();
    }//GEN-LAST:event_jTextFieldBuscadorLocalesFocusLost

    private void jButtonAgregarLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAgregarLocalActionPerformed
        // TODO add your handling code here:
        // Limpiar Local + Precio:
        jTextFieldBuscadorLocales.setText("");
        jLabelPrecioUnitarioValor.setText("");

        //Insertar nueva fila en la Tabla resumen:
        String cantidad = jSpinnerCantidad.getValue().toString();
        String local = localBuscado.getNombre();
        String precioUnitario = localBuscado.getPrecio() + sufijoPrecios;

        quitarSufijo(jLabelIVAValor, " %");
        quitarSufijo(jLabelRetencionValor, " %");
        String IVA = jLabelIVAValor.getText();
        String retencion = jLabelRetencionValor.getText();

        double IVADouble = Double.parseDouble(IVA) / 100.0 + 1.0;
        double retencionDouble = Double.parseDouble(retencion) / 100.0 + 1.0;
        double precioUnitarioDouble = localBuscado.getPrecioDouble();
        double subtotalDouble = ((precioUnitarioDouble * IVADouble) - (precioUnitarioDouble * retencionDouble) + precioUnitarioDouble) * Integer.parseInt(cantidad);
        String subtotal = String.format("%.2f", subtotalDouble) + sufijoPrecios;

        agregarSufijo(jLabelIVAValor, " %");
        agregarSufijo(jLabelRetencionValor, " %");
        IVA = jLabelIVAValor.getText();
        retencion = jLabelRetencionValor.getText();

        Object[] nuevaFila = new Object[]{
            cantidad,
            local,
            precioUnitario,
            IVA,
            retencion,
            subtotal
        };

        dtm.addRow(nuevaFila);

        // Actualizar Total:
        total += subtotalDouble;
        actualizarLabelTotal();
        habilitarBotonGenerarFactura();
    }//GEN-LAST:event_jButtonAgregarLocalActionPerformed

    private void jButtonEliminarLocalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarLocalActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow != -1) {
            String subtotal = (String) dtm.getValueAt(selectedRow, dtm.getColumnCount() - 1);
            double subtotalDouble = Double.parseDouble(subtotal.substring(0, subtotal.length() - 2).replace(",", "."));
            total -= subtotalDouble;
            actualizarLabelTotal();
            dtm.removeRow(selectedRow);
            jButtonEliminarLocal.setEnabled(false);
            habilitarBotonGenerarFactura();
        }
    }//GEN-LAST:event_jButtonEliminarLocalActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        parent.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        parent.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

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

        habilitarBotonAgregarLocal();
    }//GEN-LAST:event_jTextFieldBuscadorClientesFocusLost

    private void addTableSelectionListener() {
        jTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable.getSelectedRow();
                boolean isRowSelected = selectedRow != -1;
                jButtonEliminarLocal.setEnabled(isRowSelected);
            }
        });
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

    private void cargarAutocompletarBuscadorLocales() {
        //Iniciamos el Autocompletes con el Buscador de Clientes
        autoCompleterBuscadorLocales = new TextAutoCompleter(jTextFieldBuscadorLocales);

        //Esto hace que también se pueda buscar por en medio del String
        autoCompleterBuscadorLocales.setMode(0);

        List<Local> locales = localControlador.leerTodos();

        if (locales != null) {
            for (Local local : locales) {
                autoCompleterBuscadorLocales.addItem(local.getNombre());
                autoCompleterBuscadorLocales.addItem(local.getAlias());
            }
        }
    }

    private void inicializarListenerFecha() {
        // Obtener el editor de texto asociado al JDateChooser
        JTextField dateField = (JTextField) jDateChooser.getDateEditor().getUiComponent();

        // Agregar un DocumentListener al campo de la fecha
        dateField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                verificarFecha();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                verificarFecha();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                verificarFecha();
            }
        });
    }

    private void verificarFecha() {
        JTextField dateField = (JTextField) jDateChooser.getDateEditor().getUiComponent();
        String textoFecha = dateField.getText().trim();

        // Validar si el texto cumple con el formato "dd/MM/yyyy"
        campoFechaCorrecto = !textoFecha.isEmpty() && validarFecha(textoFecha);

        // Actualizar el estado del botón
        habilitarBotonAgregarLocal();
    }

    private void habilitarBotonAgregarLocal() {
        if (campoBuscadorClientesCorrecto) {
            if (campoFechaCorrecto) {
                if (campoBuscadorLocalesCorrecto) {
                    jButtonAgregarLocal.setEnabled(true);
                    return;
                }
            }
        }
        jButtonAgregarLocal.setEnabled(false);
    }

    private void habilitarBotonGenerarFactura() {
        if (dtm.getRowCount() != 0) {
            jButtonGenerarFactura.setEnabled(true);
            return;
        }
        jButtonGenerarFactura.setEnabled(false);
    }

    private void actualizarLabelPrecioUnitarioValor() {
        // Comprobamos que haya un local introducido correctamente
        if (campoBuscadorLocalesCorrecto) {
            String txt = localBuscado.getPrecio() + sufijoPrecios;
            jLabelPrecioUnitarioValor.setText(txt);
        } else {
            jLabelPrecioUnitarioValor.setText("");
        }
    }

    private void actualizarLabelTotal() {
        String subtotal = String.format("%.2f", total);
        jLabelTotal.setText("Total: " + subtotal + sufijoPrecios);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupFactura;
    private javax.swing.JButton jButtonAgregarLocal;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JButton jButtonEliminarLocal;
    private javax.swing.JButton jButtonGenerarFactura;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabelBuscarCliente;
    private javax.swing.JLabel jLabelBuscarLocal;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelElegirTipoFactura;
    private javax.swing.JLabel jLabelFechaFactura;
    private javax.swing.JLabel jLabelIVA;
    private javax.swing.JLabel jLabelIVAValor;
    private javax.swing.JLabel jLabelPrecioUnitario;
    private javax.swing.JLabel jLabelPrecioUnitarioValor;
    private javax.swing.JLabel jLabelRetencion;
    private javax.swing.JLabel jLabelRetencionValor;
    private javax.swing.JLabel jLabelSeleccionarLocal;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanelAgregarEliminarLocal;
    private javax.swing.JPanel jPanelBotonAgregarLocal;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelDetallesFactura;
    private javax.swing.JPanel jPanelEliminarLocal;
    private javax.swing.JPanel jPanelLabelTotal;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelRadioButtons;
    private javax.swing.JPanel jPanelSeleccionarLocal;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JRadioButton jRadioButtonFactura;
    private javax.swing.JRadioButton jRadioButtonFacturaAbono;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JSpinner jSpinnerCantidad;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldBuscadorClientes;
    private javax.swing.JTextField jTextFieldBuscadorLocales;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        actualizarAutocompleter();
    }

    private void actualizarAutocompleter() {
        autoCompleterBuscadorClientes.removeAllItems();
        cargarAutocompletarBuscadorClientes();

        autoCompleterBuscadorLocales.removeAllItems();
        cargarAutocompletarBuscadorLocales();
    }
}

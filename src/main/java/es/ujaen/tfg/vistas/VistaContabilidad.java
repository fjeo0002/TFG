/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package es.ujaen.tfg.vistas;

import es.ujaen.tfg.controlador.AnticipoControlador;
import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.controlador.FacturaControlador;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.UndoManager;
import es.ujaen.tfg.utils.ColorCelda;
import static es.ujaen.tfg.utils.Utils.AL_DIA;
import static es.ujaen.tfg.utils.Utils.ANTICIPA;
import static es.ujaen.tfg.utils.Utils.DEBE;
import static es.ujaen.tfg.utils.Utils.EURO;
import static es.ujaen.tfg.utils.Utils.FONT;
import static es.ujaen.tfg.utils.Utils.GUARDAR;
import es.ujaen.tfg.utils.Utils.Mes;
import static es.ujaen.tfg.utils.Utils.PAGADO;
import static es.ujaen.tfg.utils.Utils.obtenerIdDeFilaSeleccionada;
import java.awt.Component;
import java.awt.GridLayout;
import java.time.LocalDate;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jota
 */
public class VistaContabilidad extends javax.swing.JPanel implements Observador {

    private int anio;

    private final ClienteControlador clienteControlador;
    private final FacturaControlador facturaControlador;
    private final AnticipoControlador anticipoControlador;

    private final UndoManager undoManager;

    private final DefaultTableModel dtm;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form VistaContabilidad
     *
     * @param clienteControlador
     * @param facturaControlador
     * @param anticipoControlador
     */
    public VistaContabilidad(ClienteControlador clienteControlador, FacturaControlador facturaControlador, AnticipoControlador anticipoControlador) {
        initComponents();

        this.facturaControlador = facturaControlador;
        this.facturaControlador.agregarObservador(this);

        this.clienteControlador = clienteControlador;
        this.clienteControlador.agregarObservador(this);

        this.anticipoControlador = anticipoControlador;
        this.anticipoControlador.agregarObservador(this);

        this.undoManager = UndoManager.getInstance();
        this.undoManager.agregarObservador(this);

        this.dtm = (DefaultTableModel) jTable.getModel();

        this.jSpinnerAnio.setValue(LocalDate.now().getYear());
        this.anio = LocalDate.now().getYear();

        cargarTablaContabilidad();
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

        jPanelCabecera = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelFiltro = new javax.swing.JPanel();
        jLabelAnio = new javax.swing.JLabel();
        jSpinnerAnio = new javax.swing.JSpinner();
        jPanelCuerpo = new javax.swing.JPanel();
        jScrollPaneTabla = new javax.swing.JScrollPane();
        jTable = new javax.swing.JTable();

        setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setToolTipText("");
        jPanelCabecera.setName(""); // NOI18N
        jPanelCabecera.setPreferredSize(new java.awt.Dimension(676, 74));
        jPanelCabecera.setLayout(new java.awt.GridLayout(2, 1));

        jPanelTitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTitulo.setText("Contabilidad Mensual");
        jPanelTitulo.add(jLabelTitulo);

        jPanelCabecera.add(jPanelTitulo);

        jPanelFiltro.setLayout(new java.awt.GridBagLayout());

        jLabelAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAnio.setText("Año Seleccionado");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelFiltro.add(jLabelAnio, gridBagConstraints);

        jSpinnerAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jSpinnerAnio.setModel(new javax.swing.SpinnerNumberModel());
        jSpinnerAnio.setEditor(new javax.swing.JSpinner.NumberEditor(jSpinnerAnio, "####"));
        jSpinnerAnio.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinnerAnioStateChanged(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelFiltro.add(jSpinnerAnio, gridBagConstraints);

        jPanelCabecera.add(jPanelFiltro);

        add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new javax.swing.BoxLayout(jPanelCuerpo, javax.swing.BoxLayout.Y_AXIS));

        jScrollPaneTabla.setBackground(new java.awt.Color(245, 248, 255));

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "DNI", "Cliente", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableMouseClicked(evt);
            }
        });
        jScrollPaneTabla.setViewportView(jTable);
        if (jTable.getColumnModel().getColumnCount() > 0) {
            jTable.getColumnModel().getColumn(0).setResizable(false);
            jTable.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable.getColumnModel().getColumn(2).setResizable(false);
            jTable.getColumnModel().getColumn(3).setResizable(false);
            jTable.getColumnModel().getColumn(4).setResizable(false);
            jTable.getColumnModel().getColumn(5).setResizable(false);
            jTable.getColumnModel().getColumn(6).setResizable(false);
            jTable.getColumnModel().getColumn(7).setResizable(false);
            jTable.getColumnModel().getColumn(8).setResizable(false);
            jTable.getColumnModel().getColumn(9).setResizable(false);
            jTable.getColumnModel().getColumn(10).setResizable(false);
            jTable.getColumnModel().getColumn(11).setResizable(false);
            jTable.getColumnModel().getColumn(12).setResizable(false);
            jTable.getColumnModel().getColumn(13).setResizable(false);
        }

        jPanelCuerpo.add(jScrollPaneTabla);

        add(jPanelCuerpo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jTableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMouseClicked
        // TODO add your handling code here:
        int column = jTable.columnAtPoint(evt.getPoint());

        // Solo actuar si se clickea en columnas de meses (ignorar "DNI y Cliente")
        if (column > 1) {
            // Obtener la factura de la celda clickeada (basada en la fila y columna)
            String clienteDNI = obtenerIdDeFilaSeleccionada(jTable, dtm);  // Obtener el DNI del cliente

            String mesNombre = jTable.getColumnName(column);  // Obtener el nombre del mes basado en la columna
            Mes mes = Mes.porNombre(mesNombre);

            // Buscar la factura correspondiente a este cliente y mes
            Factura facturaClienteMesAnio = facturaControlador.facturaClienteMesAnio(clienteDNI, mes.getNumero(), anio);
            if (facturaClienteMesAnio != null) {
                // Solo mostrar popUp si facturado
                if (facturaClienteMesAnio.getFacturado() != false) {
                    // Mostrar el popup en la ubicación del clic
                    showPopupPanel(evt.getComponent(), evt.getX(), evt.getY(), facturaClienteMesAnio);
                }
            }
        }
    }//GEN-LAST:event_jTableMouseClicked

    private void jSpinnerAnioStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinnerAnioStateChanged
        // TODO add your handling code here:
        this.anio = ((Number) jSpinnerAnio.getValue()).intValue();
        cargarTablaContabilidad();
    }//GEN-LAST:event_jSpinnerAnioStateChanged

    private void showPopupPanel(Component parent, int x, int y, Factura facturaOriginal) {
        // Crear el popup
        JPopupMenu popupMenu = new JPopupMenu();
        JPanel panel = new JPanel(new GridLayout(1, 1)); // Panel con 2 filas para los checkboxes

        JCheckBox pagadoCheckBox = new JCheckBox(PAGADO);
        //JCheckBox facturadoCheckBox = new JCheckBox(FACTURADO);

        pagadoCheckBox.setFont(FONT);
        //facturadoCheckBox.setFont(FONT);

        // Si la Factura tiene el valor "Pagado" o "Facturado", preseleccionar los checkboxes
        pagadoCheckBox.setSelected(facturaOriginal.getPagado());
        //facturadoCheckBox.setSelected(factura.getFacturado());

        // Agregar checkboxes al panel
        panel.add(pagadoCheckBox);
        //panel.add(facturadoCheckBox);

        // Agregar el panel al popup
        popupMenu.add(panel);

        // Añadir un botón para cerrar y guardar los cambios
        JButton guardarBtn = new JButton(GUARDAR);
        guardarBtn.setFont(FONT);

        guardarBtn.addActionListener(e -> {
            // Lógica para numerar factura de la celda
            boolean pagado = pagadoCheckBox.isSelected();
            //boolean facturado = facturadoCheckBox.isSelected();

            // Actualizar la factura (ahora mismo solo el valor Pagado)
            Factura facturaModificada = new Factura(facturaOriginal);
            facturaModificada.setPagado(pagado);
            //factura.setFacturado(facturado);

            // Actualizar Saldo de cliente al que le modificamos la factura
            String clienteDNI = facturaOriginal.getClienteDNI();
            Cliente clienteOriginal = clienteControlador.leer(clienteDNI);
            
            // EL PROBLEMA ESTÁ EN QUE, ESTOY INTENTANDO COGER LAS FACTURAS QUE NO TIENE PAGADAS ANTES DE ACTUALIZARLAS
            facturaControlador.actualizar(facturaModificada);
            
            double saldoDebe = facturaControlador.saldoFacturasNoPagadasCliente(clienteDNI);
            double saldoAnticipa = facturaControlador.saldoFacturasNoNumeradas(clienteDNI);

            Cliente clienteModificado = new Cliente(clienteOriginal);

            if (saldoDebe != 0.0) {
                // El cliente debe
                clienteModificado.setSaldo(saldoDebe * -1);
                clienteModificado.setEstado(DEBE);
            } else if (saldoAnticipa != 0.0) {
                // El cliente Anticipa
                clienteModificado.setSaldo(saldoAnticipa);
                clienteModificado.setEstado(ANTICIPA);
            } else {
                // El saldo está a 0
                clienteModificado.setSaldo(saldoDebe);
                clienteModificado.setEstado(AL_DIA);
            }

            // Este SÍ es el Command definitivo
            facturaControlador.actualizar(facturaOriginal, facturaModificada, clienteOriginal, clienteModificado);

            // Cerrar el popup
            popupMenu.setVisible(false);
        });

        // Añadir botón al popup
        popupMenu.add(guardarBtn);

        // Mostrar el popup en las coordenadas del clic
        popupMenu.show(parent, x, y);
    }

    @Override
    public void actualizar() {
        cargarTablaContabilidad();
    }

    private void cargarTablaContabilidad() {

        dtm.setRowCount(0); //Limpiar la tabla

        List<Cliente> clientes = clienteControlador.leerTodos();

        if (clientes != null) {
            for (Cliente cliente : clientes) {
                // 1º Crear filas a todos los Clientes (vacias)
                dtm.addRow(new Object[]{
                    cliente.getDNI().trim(), // Columna DNI
                    cliente.getNombre().trim(), // Columna Nombre
                    "", // Columna Enero
                    "", // Columna Febrero
                    "", // Columna Marzo
                    "", // Columna Abril
                    "", // Columna Mayo
                    "", // Columna Junio
                    "", // Columna Julio
                    "", // Columna Agosto
                    "", // Columna Septiembre
                    "", // Columna Octubre
                    "", // Columna Noviembre
                    "" // Columna Diciembre
                });
            }

            // 2º Rellenar uno a uno las facturas de los clientes
            for (int i = 0; i < clientes.size(); i++) {   // Necesito la fila del cliente
                Cliente cliente = clientes.get(i);
                String clienteDNI = cliente.getDNI();
                List<Factura> facturasCliente = facturaControlador.facturasCliente(clienteDNI);    // Cojo todas las facturas del cliente

                if (facturasCliente != null) {
                    for (Factura factura : facturasCliente) {
                        // 3º Hacer coincidir el Año con el spinner
                        int anioFactura = factura.getFecha().getYear();
                        if (this.anio == anioFactura) {
                            // 4º Hacer coincidir el Mes con la columna
                            int numeroMes = factura.getFecha().getMonthValue();
                            Mes mes = Mes.porNumero(numeroMes);
                            String nombreMes = mes.getNombre();
                            int columna = jTable.getColumnModel().getColumnIndex(nombreMes);

                            // 5º Asociar celda con monto de factura
                            dtm.setValueAt(factura.getMontoString() + EURO, i, columna);
                        }
                    }
                }
            }

            // 6º Aplicar el Render de colores a las celdas despues de haber puesto todos los valores (clientes y montos)
            for (int col = 2; col < jTable.getColumnCount(); col++) {  // Empieza desde la columna de los meses
                jTable.getColumnModel().getColumn(col).setCellRenderer(
                        new ColorCelda(facturaControlador, anio));
            }
        }

        // Ocultar la columna del DNI
        jTable.getColumnModel().getColumn(0).setMinWidth(0);
        jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabelAnio;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JSpinner jSpinnerAnio;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

}

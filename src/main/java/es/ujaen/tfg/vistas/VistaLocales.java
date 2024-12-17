/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.mxrck.autocompleter.TextAutoCompleter;
import es.ujaen.tfg.controlador.LocalControlador;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.observer.Observador;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jota
 */
public class VistaLocales extends javax.swing.JPanel implements Observador {

    private final LocalControlador localControlador;
    private TextAutoCompleter autoCompleterBuscadorLocales;

    private VistaAnadirModificarLocal vistaAnadirModificarLocal;
    private final JFrame parent;

    private final DefaultTableModel dtm;
    private final Object[] o;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form VistaClientes
     *
     * @param parent
     * @param localControlador
     */
    public VistaLocales(JFrame parent, LocalControlador localControlador) {
        initComponents();
        this.localControlador = localControlador;
        this.localControlador.agregarObservador(this);
        this.parent = parent;
        this.dtm = (DefaultTableModel) jTable.getModel();
        this.o = new Object[jTable.getColumnCount()];

        addTableSelectionListener();
        cargarTablaLocales();
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

        jPanelCabecera = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelFiltro = new javax.swing.JPanel();
        jTextFieldBuscadorLocales = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jPanelCuerpo = new javax.swing.JPanel();
        jPanelBotonesPrincipales = new javax.swing.JPanel();
        jButtonAnadir = new javax.swing.JButton();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
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
        jLabelTitulo.setText("Catálogo de Locales");
        jPanelTitulo.add(jLabelTitulo);

        jPanelCabecera.add(jPanelTitulo);

        jPanelFiltro.setLayout(new java.awt.GridBagLayout());

        jTextFieldBuscadorLocales.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldBuscadorLocales.setForeground(new java.awt.Color(0, 0, 0));
        jTextFieldBuscadorLocales.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorLocales.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorLocales.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscadorLocalesKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.ipadx = 58;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelFiltro.add(jTextFieldBuscadorLocales, gridBagConstraints);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Busca un Local por Nombre o Alias:");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelFiltro.add(jLabel1, gridBagConstraints);

        jPanelCabecera.add(jPanelFiltro);

        add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new javax.swing.BoxLayout(jPanelCuerpo, javax.swing.BoxLayout.Y_AXIS));

        jPanelBotonesPrincipales.setLayout(new java.awt.GridBagLayout());

        jButtonAnadir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAnadir.setText("Añadir");
        jButtonAnadir.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonAnadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAnadirActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBotonesPrincipales.add(jButtonAnadir, gridBagConstraints);

        jButtonModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonModificar.setText("Modificar");
        jButtonModificar.setEnabled(false);
        jButtonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBotonesPrincipales.add(jButtonModificar, gridBagConstraints);

        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelBotonesPrincipales.add(jButtonEliminar, gridBagConstraints);

        jPanelCuerpo.add(jPanelBotonesPrincipales);

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Alias", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPaneTabla.setViewportView(jTable);

        jPanelCuerpo.add(jScrollPaneTabla);

        add(jPanelCuerpo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAnadirActionPerformed
        // TODO add your handling code here:
        vistaAnadirModificarLocal = new VistaAnadirModificarLocal(parent, true, null, localControlador);
        vistaAnadirModificarLocal.setVisible(true);
    }//GEN-LAST:event_jButtonAnadirActionPerformed

    private void jButtonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable.getSelectedRow();
        Local localOriginal = null;
        if (selectedRow != -1) { // Verificar que haya una fila seleccionada
            localOriginal = localControlador.leerTodos().get(selectedRow);
        }

        vistaAnadirModificarLocal = new VistaAnadirModificarLocal(parent, true, localOriginal, localControlador);
        vistaAnadirModificarLocal.setVisible(true);
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable.getSelectedRow();
        if (selectedRow != -1) { // Verificar que haya una fila seleccionada
            // Eliminar la fila del modelo de la tabla
            dtm.removeRow(selectedRow);

            // Eliminar fila de BBDD: Esto me hace que tenga que tenerlos SIEMPRE ordenados
            Local localEliminado = localControlador.leerTodos().get(selectedRow);

            localControlador.borrar(localEliminado);
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextFieldBuscadorLocalesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorLocalesKeyReleased
        // TODO add your handling code here:
        String texto = jTextFieldBuscadorLocales.getText();

        if (texto.trim().isEmpty()) {
            rowSorter.setRowFilter(null); // No aplicar filtro si el campo está vacío
        } else {
            // El filtro buscará en las columnas 0 (Nombre) y 1 (Alias)
            rowSorter.setRowFilter(RowFilter.regexFilter("(?i)" + texto, 0, 1));
        }
    }//GEN-LAST:event_jTextFieldBuscadorLocalesKeyReleased

    private void addTableSelectionListener() {
        jTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable.getSelectedRow();
                boolean isRowSelected = selectedRow != -1;
                jButtonModificar.setEnabled(isRowSelected);
                jButtonEliminar.setEnabled(isRowSelected);
            }
        });
    }

    private void cargarTablaLocales() {

        dtm.setRowCount(0); //Limpiar la tabla

        List<Local> locales = localControlador.leerTodos();

        for (Local local : locales) {
            o[0] = local.getNombre().trim();
            o[1] = local.getAlias().trim();
            o[2] = local.getPrecio().trim() + " €";

            dtm.addRow(o);
        }

        rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
    }

    private void cargarAutocompletarBuscadorLocales() {
        //Iniciamos el Autocompletes con el Buscador de Clientes
        autoCompleterBuscadorLocales = new TextAutoCompleter(jTextFieldBuscadorLocales);

        //Esto hace que también se pueda buscar por en medio del String
        autoCompleterBuscadorLocales.setMode(0);

        for (Local cliente : localControlador.leerTodos()) {
            autoCompleterBuscadorLocales.addItem(cliente.getNombre());
            autoCompleterBuscadorLocales.addItem(cliente.getAlias());
        }

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAnadir;
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelBotonesPrincipales;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JTable jTable;
    private javax.swing.JTextField jTextFieldBuscadorLocales;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        cargarTablaLocales();
        actualizarAutocompleter();
    }

    private void actualizarAutocompleter() {
        autoCompleterBuscadorLocales.removeAllItems();
        cargarAutocompletarBuscadorLocales();
    }

}

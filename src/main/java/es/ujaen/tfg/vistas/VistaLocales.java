/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.mxrck.autocompleter.TextAutoCompleter;
import es.ujaen.tfg.controlador.LocalControlador;
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.observer.Observador;
import static es.ujaen.tfg.utils.Utils.obtenerIdDeFilaSeleccionada;
import static es.ujaen.tfg.utils.Utils.sufijoPrecios;
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
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form VistaClientes
     *
     * @param parent
     * @param localControlador
     */
    public VistaLocales(JFrame parent, LocalControlador localControlador) {
        initComponents();
        this.parent = parent;

        this.localControlador = localControlador;
        this.localControlador.agregarObservador(this);

        this.dtm = (DefaultTableModel) jTable.getModel();

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
                "Codigo", "Nombre", "Alias", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
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
        String codigo = obtenerIdDeFilaSeleccionada(jTable, dtm);
        if (codigo != null) {
            Local localModificado = localControlador.leer(codigo);
            if (localModificado != null) {
                vistaAnadirModificarLocal = new VistaAnadirModificarLocal(parent, true, localModificado, localControlador);
                vistaAnadirModificarLocal.setVisible(true);
            }
        }
    }//GEN-LAST:event_jButtonModificarActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        String codigo = obtenerIdDeFilaSeleccionada(jTable, dtm);
        if (codigo != null) {
            Local localEliminado = localControlador.leer(codigo);
            if (localEliminado != null) {
                localControlador.borrar(localEliminado);
            }
        }
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void jTextFieldBuscadorLocalesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscadorLocalesKeyReleased
        // TODO add your handling code here:
        actualizarFiltro();
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

        if (locales != null) {
            for (Local local : locales) {
                dtm.addRow(new Object[]{
                    local.getCodigo().trim(), // Columna Codigo
                    local.getNombre().trim(), // Columna Nombre
                    local.getAlias().trim(), // Columna Alias
                    local.getPrecio().trim() + sufijoPrecios // Columna Precio
                });
            }
        }
        //Ocultar la columna del Codigo
        jTable.getColumnModel().getColumn(0).setMinWidth(0);
        jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
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

    private void actualizarFiltro() {
        String texto = jTextFieldBuscadorLocales.getText().trim().toLowerCase();

        // Aplicar RowFilter basado en los objetos Cliente
        rowSorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                String codigo = (String) entry.getValue(0); // Obtener DNI de la fila
                Local localFiltrado = localControlador.leer(codigo); // Recuperar el cliente correspondiente

                // Verificar si cliente cumple el filtro por nombre o alias
                String nombre = localFiltrado.getNombre().toLowerCase();
                String alias = localFiltrado.getAlias().toLowerCase();
                if (!texto.isEmpty() && !(nombre.contains(texto) || alias.contains(texto))) {
                    return false;
                }

                return true; // Si pasa todos los filtros, incluir la fila
            }
        });
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package es.ujaen.tfg.vistas;

import es.ujaen.tfg.controlador.AnticipoControlador;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.observer.Observador;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jota
 */
public class VistaRegistroAnticipos extends javax.swing.JPanel implements Observador{

    private final AnticipoControlador anticipoControlador;
    
    private VistaRegistroAnticiposFiltrarBusqueda vistaRegistroAnticiposFiltrarBusqueda;
    private final JFrame parent;
    
    private final DefaultTableModel dtm;
    private final Object[] o;
    private TableRowSorter<DefaultTableModel> rowSorter;
    /**
     * Creates new form VistaRegistroAnticipos
     * @param parent
     * @param anticipoControlador
     */
    public VistaRegistroAnticipos(JFrame parent, AnticipoControlador anticipoControlador) {
        initComponents();
        this.parent = parent;
        
        this.anticipoControlador = anticipoControlador;
        this.anticipoControlador.agregarObservador(this);
        
        this.dtm = (DefaultTableModel) jTable.getModel();
        this.o = new Object[jTable.getColumnCount()];
        
        addTableSelectionListener();
        cargarTablaAnticipos();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelCabecera = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelMenu = new javax.swing.JPanel();
        jPanelFiltro = new javax.swing.JPanel();
        jLabelFiltrarBusqueda = new javax.swing.JLabel();
        jButtonFiltrarBusqueda = new javax.swing.JButton();
        jPanelBotones = new javax.swing.JPanel();
        jButtonModificar = new javax.swing.JButton();
        jButtonEliminar = new javax.swing.JButton();
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
        jLabelTitulo.setText("Registro de Anticipos");
        jPanelTitulo.add(jLabelTitulo);

        jPanelCabecera.add(jPanelTitulo);

        jPanelMenu.setLayout(new java.awt.GridLayout(1, 2));

        jPanelFiltro.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelFiltrarBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFiltrarBusqueda.setText("Filtrar Búsqueda");
        jPanelFiltro.add(jLabelFiltrarBusqueda);

        jButtonFiltrarBusqueda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonFiltrarBusqueda.setIcon(new javax.swing.ImageIcon("C:\\Users\\jota\\Pictures\\carta.png")); // NOI18N
        jButtonFiltrarBusqueda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFiltrarBusquedaActionPerformed(evt);
            }
        });
        jPanelFiltro.add(jButtonFiltrarBusqueda);

        jPanelMenu.add(jPanelFiltro);

        jPanelBotones.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonModificar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonModificar.setText("Modificar");
        jButtonModificar.setEnabled(false);
        jPanelBotones.add(jButtonModificar);

        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jPanelBotones.add(jButtonEliminar);

        jPanelMenu.add(jPanelBotones);

        jPanelCabecera.add(jPanelMenu);

        add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new javax.swing.BoxLayout(jPanelCuerpo, javax.swing.BoxLayout.Y_AXIS));

        jTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Cliente", "Fecha", "Monto", "Meses Cubiertos", "Saldo Restante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
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

    private void jButtonFiltrarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarBusquedaActionPerformed
        // TODO add your handling code here:
        vistaRegistroAnticiposFiltrarBusqueda = new VistaRegistroAnticiposFiltrarBusqueda(parent, true);
        vistaRegistroAnticiposFiltrarBusqueda.setVisible(true);
    }//GEN-LAST:event_jButtonFiltrarBusquedaActionPerformed

    private void cargarTablaAnticipos() {

        dtm.setRowCount(0); //Limpiar la tabla

        List<Anticipo> anticipos = anticipoControlador.leerTodos();

        for (Anticipo anticipo : anticipos) {
            o[0] = anticipo.getCliente().getNombre().trim();
            o[1] = anticipo.getFecha().trim();
            o[2] = anticipo.getMonto().trim() + " €";
            o[3] = anticipo.getMesesCubiertos().trim();
            o[4] = anticipo.getMonto().trim() + " €";   // ¿Nuevo campo Saldo en Anticipo o en Cliente?

            dtm.addRow(o);
        }

        rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
    }
    
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonFiltrarBusqueda;
    private javax.swing.JButton jButtonModificar;
    private javax.swing.JLabel jLabelFiltrarBusqueda;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelBotones;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelFiltro;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JScrollPane jScrollPaneTabla;
    private javax.swing.JTable jTable;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actualizar() {
        cargarTablaAnticipos();
    }
}

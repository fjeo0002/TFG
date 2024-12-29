/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package es.ujaen.tfg.vistas;

import es.ujaen.tfg.controlador.AnticipoControlador;
import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.observer.Observador;
import static es.ujaen.tfg.utils.Utils.agregarSufijo;
import static es.ujaen.tfg.utils.Utils.mostrarError;
import static es.ujaen.tfg.utils.Utils.obtenerIdDeFilaSeleccionada;
import static es.ujaen.tfg.utils.Utils.quitarSufijo;
import static es.ujaen.tfg.utils.Utils.sufijoPrecios;
import static es.ujaen.tfg.utils.Utils.validarFecha;
import static es.ujaen.tfg.utils.Utils.validarMesesCubiertos;
import static es.ujaen.tfg.utils.Utils.validarMonto;
import static es.ujaen.tfg.utils.Utils.validarSaldo;
import java.awt.Component;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import static es.ujaen.tfg.utils.Utils.convertirTextoANumeroMes;

/**
 *
 * @author jota
 */
public class VistaRegistroAnticipos extends javax.swing.JPanel implements Observador {

    private final ClienteControlador clienteControlador;
    private final AnticipoControlador anticipoControlador;

    private Anticipo anticipoActual;

    private VistaRegistroAnticiposFiltrarBusqueda vistaRegistroAnticiposFiltrarBusqueda;
    private final JFrame parent;

    private final DefaultTableModel dtm;
    private TableRowSorter<DefaultTableModel> rowSorter;

    /**
     * Creates new form VistaRegistroAnticipos
     *
     * @param parent
     * @param clienteControlador
     * @param anticipoControlador
     */
    public VistaRegistroAnticipos(JFrame parent, ClienteControlador clienteControlador, AnticipoControlador anticipoControlador) {
        initComponents();
        this.parent = parent;

        this.clienteControlador = clienteControlador;

        this.anticipoControlador = anticipoControlador;
        this.anticipoControlador.agregarObservador(this);

        this.dtm = (DefaultTableModel) jTable.getModel();

        addTableSelectionListener();
        cargarTablaAnticipos();
        configurarEditoresDeCeldas();
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

        jButtonEliminar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEliminar.setText("Eliminar");
        jButtonEliminar.setEnabled(false);
        jButtonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarActionPerformed(evt);
            }
        });
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
                "ID", "Cliente", "Fecha", "Monto", "Meses Cubiertos", "Saldo Restante"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable.getTableHeader().setReorderingAllowed(false);
        jScrollPaneTabla.setViewportView(jTable);

        jPanelCuerpo.add(jScrollPaneTabla);

        add(jPanelCuerpo, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonFiltrarBusquedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFiltrarBusquedaActionPerformed
        // TODO add your handling code here:
        vistaRegistroAnticiposFiltrarBusqueda = new VistaRegistroAnticiposFiltrarBusqueda(parent, true, clienteControlador);
        vistaRegistroAnticiposFiltrarBusqueda.setVisible(true);

        List<String> filtros = vistaRegistroAnticiposFiltrarBusqueda.obtenerFiltros();
        aplicarFiltros(filtros);
    }//GEN-LAST:event_jButtonFiltrarBusquedaActionPerformed

    private void jButtonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarActionPerformed
        // TODO add your handling code here:
        String idAnticipo = obtenerIdDeFilaSeleccionada(jTable, dtm);
        Cliente clienteActualizar = null;
        if (idAnticipo != null) {
            Anticipo anticipoEliminado = anticipoControlador.leer(idAnticipo);
            if (anticipoEliminado != null) {
                clienteActualizar = anticipoEliminado.getCliente();
                anticipoControlador.borrar(anticipoEliminado);
            }
        }
        
        actualizarSaldoCliente(clienteActualizar);
    }//GEN-LAST:event_jButtonEliminarActionPerformed

    private void cargarTablaAnticipos() {

        dtm.setRowCount(0); //Limpiar la tabla

        // Por ahora lo hago aqui en la Vista... quizás en el DAO podría guardarlos directamente por Fecha
        List<Anticipo> anticipos = anticipoControlador.leerTodos().stream()
                .sorted((a1, a2) -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate fecha1 = LocalDate.parse(a1.getFecha(), formatter);
                    LocalDate fecha2 = LocalDate.parse(a2.getFecha(), formatter);
                    return fecha2.compareTo(fecha1); // Orden descendente
                })
                .toList();

        //List<Anticipo> anticipos = anticipoControlador.leerTodos();
        for (Anticipo anticipo : anticipos) {
            dtm.addRow(new Object[]{
                anticipo.getId(), // Columna ID
                anticipo.getCliente().getNombre().trim(), // Columna Cliente
                anticipo.getFecha().trim(), // Columna Fecha
                anticipo.getMonto().trim() + sufijoPrecios, // Columna Monto
                anticipo.getMesesCubiertos().trim(), // Columna Meses Cubiertos
                anticipo.getSaldo().trim() + sufijoPrecios // Columna Saldo
            });
        }

        //Ocultar la columna del ID
        jTable.getColumnModel().getColumn(0).setMinWidth(0);
        jTable.getColumnModel().getColumn(0).setMaxWidth(0);
        jTable.getColumnModel().getColumn(0).setPreferredWidth(0);

        rowSorter = new TableRowSorter<>(dtm);
        jTable.setRowSorter(rowSorter);
    }

    private void addTableSelectionListener() {
        jTable.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = jTable.getSelectedRow();
                boolean isRowSelected = selectedRow != -1;
                jButtonEliminar.setEnabled(isRowSelected);
            }
        });
    }

    private void configurarEditoresDeCeldas() {
        // Editor para FECHA
        JTextField fechaTextField = new JTextField();
        DefaultCellEditor fechaEditor = new DefaultCellEditor(fechaTextField) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                cargarDatosFilaSeleccionada(row);
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }

            @Override
            public boolean stopCellEditing() {
                String value = (String) getCellEditorValue();
                if (!validarFecha(value)) {
                    mostrarError(parent, "Fecha inválida.", "Debe tener formato 'dd/MM/yyyy' y no puede ser posterior a hoy.");
                    return false;
                }
                anticipoActual.setFecha(value);
                anticipoControlador.actualizar(anticipoActual);
                return super.stopCellEditing();
            }
        };
        jTable.getColumnModel().getColumn(2).setCellEditor(fechaEditor);

        // Editor para MONTO
        JTextField montoTextField = new JTextField();

        montoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                quitarSufijo(montoTextField, sufijoPrecios);
            }
        });

        DefaultCellEditor montoEditor = new DefaultCellEditor(montoTextField) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                cargarDatosFilaSeleccionada(row);
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }

            @Override
            public boolean stopCellEditing() {
                String value = (String) getCellEditorValue();
                if (!validarMonto(value)) {
                    mostrarError(parent, "Monto inválido.", "Debe ser un número distinto de 0,00 € con 2 decimales separados por coma (e.g. 150,00 €).");
                    //montoTextField.requestFocus();
                    return false;
                }

                double nuevoMonto = Double.parseDouble(value.replace(",", "."));

                if (anticipoActual.getSaldoDouble() > nuevoMonto) {
                    mostrarError(parent, "Monto inválido.", "No puede ser menor al Saldo actual.");
                    //montoTextField.requestFocus();
                    return false;
                }

                anticipoActual.setMonto(nuevoMonto); // Actualizar monto solo si es válido
                anticipoControlador.actualizar(anticipoActual);
                agregarSufijo(montoTextField, sufijoPrecios);
                return super.stopCellEditing();
            }
        };
        jTable.getColumnModel().getColumn(3).setCellEditor(montoEditor);

        JTextField mesesTextField = new JTextField();
        DefaultCellEditor mesesEditor = new DefaultCellEditor(mesesTextField) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                cargarDatosFilaSeleccionada(row);
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }

            @Override
            public boolean stopCellEditing() {
                String value = (String) getCellEditorValue();
                if (!validarMesesCubiertos(value)) {
                    mostrarError(parent, "Meses cubiertos inválido.", "Debe ser un número entero mayor que 0.");
                    return false;
                }
                anticipoActual.setMesesCubiertos(value);
                anticipoControlador.actualizar(anticipoActual);
                return super.stopCellEditing();
            }
        };
        jTable.getColumnModel().getColumn(4).setCellEditor(mesesEditor);

        // Editor para SALDO
        JTextField saldoTextField = new JTextField();

        saldoTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                quitarSufijo(saldoTextField, sufijoPrecios);
            }
        });

        DefaultCellEditor saldoEditor = new DefaultCellEditor(saldoTextField) {
            @Override
            public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
                cargarDatosFilaSeleccionada(row);
                return super.getTableCellEditorComponent(table, value, isSelected, row, column);
            }

            @Override
            public boolean stopCellEditing() {
                String value = (String) getCellEditorValue();
                if (!validarSaldo(value)) {
                    mostrarError(parent, "Saldo inválido.", "Debe ser un número con 2 decimales separados por coma (e.g. 150,00 €).");
                    saldoTextField.requestFocus();
                    return false;
                }

                double nuevoSaldo = Double.parseDouble(value.replace(",", "."));

                if (nuevoSaldo > anticipoActual.getMontoDouble()) {
                    mostrarError(parent, "Saldo inválido.", "No puede ser mayor al Monto.");
                    saldoTextField.requestFocus();
                    return false;
                }

                anticipoActual.setSaldo(nuevoSaldo); // Actualizar saldo solo si es válido
                anticipoControlador.actualizar(anticipoActual);

                Cliente clienteActual = anticipoActual.getCliente();
                actualizarSaldoCliente(clienteActual);
                /*
                clienteActual.setSaldo(nuevoSaldo);
                if (nuevoSaldo == 0) {
                    clienteActual.setEstado("Al día");
                }
                */
                clienteControlador.actualizar(clienteActual);

                agregarSufijo(saldoTextField, sufijoPrecios);
                return super.stopCellEditing();
            }
        };
        jTable.getColumnModel().getColumn(5).setCellEditor(saldoEditor);
    }

    private void cargarDatosFilaSeleccionada(int row) {
        int modeloFila = jTable.convertRowIndexToModel(row); // Convertir índice de vista a modelo
        String idAnticipo = (String) dtm.getValueAt(modeloFila, 0); // Columna del ID
        anticipoActual = anticipoControlador.leer(idAnticipo); // Recuperar el objeto Anticipo        
    }

    private void aplicarFiltros(List<String> filtros) {

        String nombreAliasClienteFiltro = filtros.get(0);
        String saldoFiltro = filtros.get(1);
        String anioFiltro = filtros.get(2);
        String mesFiltro = filtros.get(3);

        // Filtrar utilizando un RowFilter personalizado
        rowSorter.setRowFilter(new RowFilter<DefaultTableModel, Integer>() {
            @Override
            public boolean include(Entry<? extends DefaultTableModel, ? extends Integer> entry) {
                String ID = (String) entry.getValue(0); // Obtener ID de la fila
                Anticipo anticipoFiltrado = anticipoControlador.leer(ID); // Obtener el anticipo correspondiente

                // Extraer datos del anticipo
                String nombreCliente = anticipoFiltrado.getCliente().getNombre().toLowerCase();
                String aliasCliente = anticipoFiltrado.getCliente().getAlias().toLowerCase();
                double saldo = anticipoFiltrado.getSaldoDouble();
                String fecha = anticipoFiltrado.getFecha();
                String[] fechaSeparada = fecha.split("/");
                String mes = fechaSeparada[1];
                String anio = fechaSeparada[2];

                // Filtrar por cliente
                if (!nombreAliasClienteFiltro.isEmpty()
                        && !(nombreCliente.contains(nombreAliasClienteFiltro.toLowerCase())
                        || aliasCliente.contains(nombreAliasClienteFiltro.toLowerCase()))) {
                    return false;
                }

                // Filtrar por saldo
                if (!saldoFiltro.equals("Todos")) {
                    if (saldoFiltro.equals("Anticipos Activos") && saldo <= 0) {
                        return false;
                    } else if (saldoFiltro.equals("Anticipos Finalizados") && saldo > 0) {
                        return false;
                    }
                }

                // Filtrar por año
                if (!anioFiltro.isEmpty() && !anio.equals(anioFiltro)) {
                    return false;
                }

                // Filtrar por mes (convertir mes textual a número si es necesario)
                if (!mesFiltro.equals("-")) {
                    String mesTexto = convertirTextoANumeroMes(mesFiltro);
                    if (mesTexto != null && !mes.equals(mesTexto)) {
                        return false;
                    }
                }

                return true; // Si pasa todos los filtros, incluir la fila
            }
        });

    }

    private Anticipo obtenerUltimoAnticipoActivo(Cliente cliente) {
        return anticipoControlador.leerTodos().stream()
                .filter(a -> a.getCliente().equals(cliente) && a.getSaldoDouble() > 0)
                .max((a1, a2) -> a1.getFecha().compareTo(a2.getFecha())) // Comparar por fecha
                .orElse(null); // Devuelve null si no hay anticipos activos
    }

    private void actualizarSaldoCliente(Cliente cliente) {
        Anticipo ultimoAnticipo = obtenerUltimoAnticipoActivo(cliente);

        if (ultimoAnticipo != null) {
            cliente.setSaldo(ultimoAnticipo.getSaldo());
            cliente.setEstado("Anticipa");
        } else {
            cliente.setSaldo("0,00");
            cliente.setEstado("Al día");
        }

        clienteControlador.actualizar(cliente);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminar;
    private javax.swing.JButton jButtonFiltrarBusqueda;
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

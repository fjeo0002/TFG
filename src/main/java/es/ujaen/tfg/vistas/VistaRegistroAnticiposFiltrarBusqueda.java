/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.mxrck.autocompleter.TextAutoCompleter;
import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.observer.Observador;
import static es.ujaen.tfg.utils.Utils.ERROR_ANIO;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_ANIO;
import static es.ujaen.tfg.utils.Utils.VACIO;
import static es.ujaen.tfg.utils.Utils.VALIDACION_ANIO;
import static es.ujaen.tfg.utils.Utils.agregarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.quitarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.validarCampoFormulario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author jota
 */
public class VistaRegistroAnticiposFiltrarBusqueda extends javax.swing.JDialog implements Observador {

    private final ClienteControlador clienteControlador;
    private TextAutoCompleter autoCompleterBuscadorClientes;

    private final Border originalBorder;
    
    private boolean campoAnioCorrecto = true; // Si está vacío, no pasa nada, podemos filtrar por otro/s campo/s
    
    private boolean aceptar = false;

    /**
     * Creates new form VistaRegistroAnticiposFiltrarBusqueda
     *
     * @param parent
     * @param modal
     * @param clienteControlador
     */
    public VistaRegistroAnticiposFiltrarBusqueda(java.awt.Frame parent, boolean modal, ClienteControlador clienteControlador) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        ImageIcon icon = new ImageIcon("iconoFondoTransparente.png"); // Ruta de la imagen
        this.setIconImage(icon.getImage()); // Establecer el icono
        
        this.originalBorder = jTextFieldAnio.getBorder();

        this.clienteControlador = clienteControlador;
        this.clienteControlador.agregarObservador(this);

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
        jLabelFiltrarCliente = new javax.swing.JLabel();
        jTextFieldBuscadorClientes = new javax.swing.JTextField();
        jLabelFiltrarAnio = new javax.swing.JLabel();
        jTextFieldAnio = new javax.swing.JTextField();
        jLabelAdvertenciaAnio = new javax.swing.JLabel();
        jLabelFiltrarSaldo = new javax.swing.JLabel();
        jComboBoxSaldo = new javax.swing.JComboBox<>();
        jLabelFiltrarMes = new javax.swing.JLabel();
        jComboBoxMes = new javax.swing.JComboBox<>();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Filtar Búsqueda");

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Filtrar Búsqueda");
        jPanelCabecera.add(jLabelTitulo);

        jPanelPrincipal.add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new java.awt.GridBagLayout());

        jLabelFiltrarCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFiltrarCliente.setText("Filtrar Cliente por Nombre o Alias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelFiltrarCliente, gridBagConstraints);

        jTextFieldBuscadorClientes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldBuscadorClientes.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldBuscadorClientes.setPreferredSize(new java.awt.Dimension(125, 26));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldBuscadorClientes, gridBagConstraints);

        jLabelFiltrarAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFiltrarAnio.setText("Filtrar Año");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelFiltrarAnio, gridBagConstraints);

        jTextFieldAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldAnio.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldAnio.setText("aaaa");
        jTextFieldAnio.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldAnio.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldAnio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAnioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAnioFocusLost(evt);
            }
        });
        jTextFieldAnio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldAnioKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldAnio, gridBagConstraints);

        jLabelAdvertenciaAnio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaAnio.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaAnio, gridBagConstraints);

        jLabelFiltrarSaldo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFiltrarSaldo.setText("Filtrar Saldo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelFiltrarSaldo, gridBagConstraints);

        jComboBoxSaldo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxSaldo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Todos", "Anticipos Activos", "Anticipos Finalizados" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jComboBoxSaldo, gridBagConstraints);

        jLabelFiltrarMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelFiltrarMes.setText("Filtrar Mes");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelFiltrarMes, gridBagConstraints);

        jComboBoxMes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBoxMes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "-", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre" }));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jComboBoxMes, gridBagConstraints);

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

        jButtonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonAceptar.setText("Aceptar");
        jButtonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAceptarActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonAceptar);

        jPanelPrincipal.add(jPanelPiePagina, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        // TODO add your handling code here:
        aceptar = true;
        dispose();
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jTextFieldAnioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAnioFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldAnio, PLACEHOLDER_ANIO);
    }//GEN-LAST:event_jTextFieldAnioFocusLost

    private void jTextFieldAnioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAnioFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldAnio, PLACEHOLDER_ANIO);
    }//GEN-LAST:event_jTextFieldAnioFocusGained

    private void jTextFieldAnioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldAnioKeyReleased
        // TODO add your handling code here:
        campoAnioCorrecto = validarCampoFormulario(
                jTextFieldAnio,
                jLabelAdvertenciaAnio,
                ERROR_ANIO,
                originalBorder,
                texto -> texto.isEmpty() || texto.matches(VALIDACION_ANIO) || texto.equals(PLACEHOLDER_ANIO)
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldAnioKeyReleased

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

    public List<String> obtenerFiltros() {
        List<String> filtros = new ArrayList<>();
        filtros.add(jTextFieldBuscadorClientes.getText().trim());
        filtros.add((String) jComboBoxSaldo.getSelectedItem());
        filtros.add(jTextFieldAnio.getText().trim().equals(PLACEHOLDER_ANIO) ? VACIO : jTextFieldAnio.getText().trim());
        filtros.add((String) jComboBoxMes.getSelectedItem());
        return filtros;
    }

    private void habilitarBotonAceptar() {
        if (campoAnioCorrecto) {
            jButtonAceptar.setEnabled(true);
            return;
        }
        jButtonAceptar.setEnabled(false);
    }

    @Override
    public void actualizar() {
        actualizarAutocompleter();
    }

    private void actualizarAutocompleter() {
        autoCompleterBuscadorClientes.removeAllItems();
        cargarAutocompletarBuscadorClientes();
    }

    public boolean getAceptar() {
        return aceptar;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JComboBox<String> jComboBoxMes;
    private javax.swing.JComboBox<String> jComboBoxSaldo;
    private javax.swing.JLabel jLabelAdvertenciaAnio;
    private javax.swing.JLabel jLabelFiltrarAnio;
    private javax.swing.JLabel jLabelFiltrarCliente;
    private javax.swing.JLabel jLabelFiltrarMes;
    private javax.swing.JLabel jLabelFiltrarSaldo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JTextField jTextFieldAnio;
    private javax.swing.JTextField jTextFieldBuscadorClientes;
    // End of variables declaration//GEN-END:variables

}

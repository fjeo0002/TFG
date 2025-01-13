/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.formdev.flatlaf.FlatLightLaf;
import es.ujaen.tfg.controlador.AnticipoControlador;
import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.controlador.FacturaControlador;
import es.ujaen.tfg.controlador.LocalControlador;
import es.ujaen.tfg.controlador.PreferenciasControlador;
import java.awt.BorderLayout;
import javax.swing.*;

/**
 *
 * @author PCCA201
 */
public class VistaPrincipal extends javax.swing.JFrame {

    private VistaPreferencias vistaPreferencias;
    private VistaCrearFactura vistaCrearFactura;
    private VistaCrearAnticipo vistaCrearAnticipo;

    private VistaRegistroAnticipos vistaRegistroAnticipos;
    private VistaContabilidad vistaContabilidad;

    private VistaClientes vistaClientes;
    private VistaLocales vistaLocales;

    private final ClienteControlador clienteControlador;
    private final LocalControlador localControlador;
    private final AnticipoControlador anticipoControlador;
    private final FacturaControlador facturaControlador;
    private final PreferenciasControlador preferenciasControlador;

    /**
     * Creates new form VistaCrearModificarCliente2
     */
    public VistaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Maximiza la ventana

        clienteControlador = new ClienteControlador();
        localControlador = new LocalControlador();
        anticipoControlador = new AnticipoControlador();
        facturaControlador = new FacturaControlador();
        preferenciasControlador = new PreferenciasControlador();

        cargarVistaContabilidad();
        cargarVistaRegistroAnticipos();

        cargarVistaClientes();
        cargarVistaLocales();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelPrincipal = new javax.swing.JPanel();
        jPanelCabecera = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelBotonesPrincipales = new javax.swing.JPanel();
        jButtonPreferencias = new javax.swing.JButton();
        jButtonCrearFactura = new javax.swing.JButton();
        jButtonCrearAnticipo = new javax.swing.JButton();
        jButtonDeshacer = new javax.swing.JButton();
        jButtonRehacer = new javax.swing.JButton();
        jTabbedPaneCategorias = new javax.swing.JTabbedPane();
        jPanelContabilidad = new javax.swing.JPanel();
        jPanelAnticipos = new javax.swing.JPanel();
        jPanelPiePagina = new javax.swing.JPanel();
        jPanelClientes = new javax.swing.JPanel();
        jPanelLocales = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de Facturación y Contabilidad");
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        setLocation(new java.awt.Point(0, 0));
        setMinimumSize(new java.awt.Dimension(900, 700));
        setName("VistaPrincipal"); // NOI18N
        setSize(new java.awt.Dimension(0, 0));

        jPanelPrincipal.setName(""); // NOI18N

        jPanelCabecera.setToolTipText("");
        jPanelCabecera.setName(""); // NOI18N
        jPanelCabecera.setPreferredSize(new java.awt.Dimension(676, 74));
        jPanelCabecera.setLayout(new java.awt.GridLayout(2, 1, 10, 10));

        jPanelTitulo.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabelTitulo.setText("Sistema de Facturación y Contabilidad");
        jPanelTitulo.add(jLabelTitulo);

        jPanelCabecera.add(jPanelTitulo);

        java.awt.FlowLayout flowLayout1 = new java.awt.FlowLayout(java.awt.FlowLayout.LEFT);
        flowLayout1.setAlignOnBaseline(true);
        jPanelBotonesPrincipales.setLayout(flowLayout1);

        jButtonPreferencias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonPreferencias.setText("Preferencias");
        jButtonPreferencias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jButtonPreferencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonPreferenciasActionPerformed(evt);
            }
        });
        jPanelBotonesPrincipales.add(jButtonPreferencias);

        jButtonCrearFactura.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonCrearFactura.setText("Crear Factura");
        jButtonCrearFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearFacturaActionPerformed(evt);
            }
        });
        jPanelBotonesPrincipales.add(jButtonCrearFactura);

        jButtonCrearAnticipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonCrearAnticipo.setText("Crear Anticipo");
        jButtonCrearAnticipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCrearAnticipoActionPerformed(evt);
            }
        });
        jPanelBotonesPrincipales.add(jButtonCrearAnticipo);

        jButtonDeshacer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonDeshacer.setText("Deshacer");
        jButtonDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonDeshacerActionPerformed(evt);
            }
        });
        jPanelBotonesPrincipales.add(jButtonDeshacer);

        jButtonRehacer.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonRehacer.setText("Rehacer");
        jPanelBotonesPrincipales.add(jButtonRehacer);

        jPanelCabecera.add(jPanelBotonesPrincipales);

        jTabbedPaneCategorias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        jPanelContabilidad.setBackground(new java.awt.Color(255, 204, 204));

        javax.swing.GroupLayout jPanelContabilidadLayout = new javax.swing.GroupLayout(jPanelContabilidad);
        jPanelContabilidad.setLayout(jPanelContabilidadLayout);
        jPanelContabilidadLayout.setHorizontalGroup(
            jPanelContabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        jPanelContabilidadLayout.setVerticalGroup(
            jPanelContabilidadLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );

        jTabbedPaneCategorias.addTab("Contabilidad", jPanelContabilidad);

        jPanelAnticipos.setBackground(new java.awt.Color(204, 255, 255));

        javax.swing.GroupLayout jPanelAnticiposLayout = new javax.swing.GroupLayout(jPanelAnticipos);
        jPanelAnticipos.setLayout(jPanelAnticiposLayout);
        jPanelAnticiposLayout.setHorizontalGroup(
            jPanelAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1119, Short.MAX_VALUE)
        );
        jPanelAnticiposLayout.setVerticalGroup(
            jPanelAnticiposLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 321, Short.MAX_VALUE)
        );

        jTabbedPaneCategorias.addTab("Anticipos", jPanelAnticipos);

        jPanelPiePagina.setLayout(new java.awt.GridLayout(1, 2));

        jPanelClientes.setBackground(new java.awt.Color(204, 204, 255));

        javax.swing.GroupLayout jPanelClientesLayout = new javax.swing.GroupLayout(jPanelClientes);
        jPanelClientes.setLayout(jPanelClientesLayout);
        jPanelClientesLayout.setHorizontalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );
        jPanelClientesLayout.setVerticalGroup(
            jPanelClientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        jPanelPiePagina.add(jPanelClientes);

        jPanelLocales.setBackground(new java.awt.Color(204, 255, 204));

        javax.swing.GroupLayout jPanelLocalesLayout = new javax.swing.GroupLayout(jPanelLocales);
        jPanelLocales.setLayout(jPanelLocalesLayout);
        jPanelLocalesLayout.setHorizontalGroup(
            jPanelLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 559, Short.MAX_VALUE)
        );
        jPanelLocalesLayout.setVerticalGroup(
            jPanelLocalesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 193, Short.MAX_VALUE)
        );

        jPanelPiePagina.add(jPanelLocales);

        javax.swing.GroupLayout jPanelPrincipalLayout = new javax.swing.GroupLayout(jPanelPrincipal);
        jPanelPrincipal.setLayout(jPanelPrincipalLayout);
        jPanelPrincipalLayout.setHorizontalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPaneCategorias)
            .addComponent(jPanelPiePagina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanelCabecera, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanelPrincipalLayout.setVerticalGroup(
            jPanelPrincipalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelPrincipalLayout.createSequentialGroup()
                .addComponent(jPanelCabecera, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPaneCategorias)
                .addGap(0, 0, 0)
                .addComponent(jPanelPiePagina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonPreferenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonPreferenciasActionPerformed
        // TODO add your handling code here:
        vistaPreferencias = new VistaPreferencias(this, true, preferenciasControlador);
        vistaPreferencias.setVisible(true);
    }//GEN-LAST:event_jButtonPreferenciasActionPerformed

    private void jButtonCrearFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearFacturaActionPerformed
        // TODO add your handling code here:
        vistaCrearFactura = new VistaCrearFactura(this, clienteControlador, localControlador, facturaControlador, preferenciasControlador);
        vistaCrearFactura.setVisible(true);
    }//GEN-LAST:event_jButtonCrearFacturaActionPerformed

    private void jButtonDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonDeshacerActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButtonDeshacerActionPerformed

    private void jButtonCrearAnticipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCrearAnticipoActionPerformed
        // TODO add your handling code here:
        vistaCrearAnticipo = new VistaCrearAnticipo(this, true, clienteControlador, anticipoControlador, facturaControlador);
        vistaCrearAnticipo.setVisible(true);
    }//GEN-LAST:event_jButtonCrearAnticipoActionPerformed

    private void cargarVistaContabilidad() {
        vistaContabilidad = new VistaContabilidad(clienteControlador, facturaControlador);
        vistaContabilidad.setSize(jPanelContabilidad.getWidth(), jPanelContabilidad.getHeight());
        vistaContabilidad.setLocation(0, 0);

        jPanelContabilidad.setLayout(new BorderLayout());
        jPanelContabilidad.removeAll();
        jPanelContabilidad.add(vistaContabilidad, BorderLayout.CENTER);
        jPanelContabilidad.revalidate();
        jPanelContabilidad.repaint();
    }

    private void cargarVistaRegistroAnticipos() {
        vistaRegistroAnticipos = new VistaRegistroAnticipos(this, clienteControlador, anticipoControlador);
        vistaRegistroAnticipos.setSize(jPanelAnticipos.getWidth(), jPanelAnticipos.getHeight());
        vistaRegistroAnticipos.setLocation(0, 0);

        jPanelAnticipos.setLayout(new BorderLayout());
        jPanelAnticipos.removeAll();
        jPanelAnticipos.add(vistaRegistroAnticipos, BorderLayout.CENTER);
        jPanelAnticipos.revalidate();
        jPanelAnticipos.repaint();
    }

    private void cargarVistaClientes() {
        vistaClientes = new VistaClientes(this, clienteControlador);
        vistaClientes.setSize(jPanelClientes.getWidth(), jPanelClientes.getHeight());
        vistaClientes.setLocation(0, 0);

        jPanelClientes.setLayout(new BorderLayout());
        jPanelClientes.removeAll();
        jPanelClientes.add(vistaClientes, BorderLayout.CENTER);
        jPanelClientes.revalidate();
        jPanelClientes.repaint();
    }

    private void cargarVistaLocales() {
        vistaLocales = new VistaLocales(this, localControlador);
        vistaLocales.setSize(jPanelLocales.getWidth(), jPanelLocales.getHeight());
        vistaLocales.setLocation(0, 0);

        jPanelLocales.setLayout(new BorderLayout());
        jPanelLocales.removeAll();
        jPanelLocales.add(vistaLocales, BorderLayout.CENTER);
        jPanelLocales.revalidate();
        jPanelLocales.repaint();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    /*
                    //Modo Noche Nimbus
                    javax.swing.UIManager.put("control", new java.awt.Color(50, 50, 50)); // Fondo de componentes
                    javax.swing.UIManager.put("info", new java.awt.Color(50, 50, 50)); // Fondo de tooltips
                    javax.swing.UIManager.put("nimbusBase", new java.awt.Color(35, 35, 35)); // Base del tema
                    javax.swing.UIManager.put("nimbusFocus", new java.awt.Color(60, 60, 60)); // Color de enfoque
                    javax.swing.UIManager.put("nimbusLightBackground", new java.awt.Color(60, 60, 60)); // Fondo de entrada de texto
                    javax.swing.UIManager.put("nimbusSelectedText", new java.awt.Color(240, 240, 240)); // Texto seleccionado
                    javax.swing.UIManager.put("nimbusSelectionBackground", new java.awt.Color(80, 80, 80)); // Fondo de selección
                    javax.swing.UIManager.put("text", new java.awt.Color(230, 230, 230)); // Texto estándar
                     */
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //Aplicar LookAndFell FlatLaf
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VistaPrincipal().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCrearAnticipo;
    private javax.swing.JButton jButtonCrearFactura;
    private javax.swing.JButton jButtonDeshacer;
    private javax.swing.JButton jButtonPreferencias;
    private javax.swing.JButton jButtonRehacer;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelAnticipos;
    private javax.swing.JPanel jPanelBotonesPrincipales;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelClientes;
    private javax.swing.JPanel jPanelContabilidad;
    private javax.swing.JPanel jPanelLocales;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JTabbedPane jTabbedPaneCategorias;
    // End of variables declaration//GEN-END:variables

}

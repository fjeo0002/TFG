/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package es.ujaen.tfg.vistas;

import es.ujaen.tfg.controlador.LocalControlador;
import es.ujaen.tfg.modelo.Local;
import static es.ujaen.tfg.utils.Utils.agregarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.agregarSufijo;
import static es.ujaen.tfg.utils.Utils.quitarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.quitarSufijo;
import java.awt.Color;
import java.awt.Frame;
import java.util.UUID;
import javax.swing.border.Border;
import static es.ujaen.tfg.utils.Utils.validarCampoFormulario;
import static es.ujaen.tfg.utils.Utils.validarMonto;

/**
 *
 * @author jota
 */
public class VistaAnadirModificarLocal extends javax.swing.JDialog {

    private final boolean esEdicion;
    private Local localOriginal;
    private Local localModificado;

    private final LocalControlador localControlador;

    private final Border originalBorder;

    private boolean campoPrecioBaseCorrecto;

    private final String placeHolderNombre = "Introduzca Nombre de Local";
    private final String placeHolderAlias = "Introduzca Alias de Local (opcional)";
    private final String placeHolderPrecioBase = "0,00 €";
    private final String sufijoPrecioBase = " €";

    /**
     * Creates new form VistaCrearModificarCliente
     *
     * @param parent
     * @param modal
     * @param local: null -> VistaAñadirLocal local -> VistaModificarLocal
     * @param localControlador
     */
    public VistaAnadirModificarLocal(Frame parent, boolean modal, Local local, LocalControlador localControlador) {
        super(parent, modal);
        initComponents();
        setLocationRelativeTo(null);
        
        this.originalBorder = jTextFieldNombre.getBorder();

        this.localControlador = localControlador;

        if (local == null) {
            jLabelTitulo.setText("Añadir Nuevo Local");
            setTitle("Añadir Nuevo Local");

            jButtonAceptar.setEnabled(false);

            esEdicion = false;
            campoPrecioBaseCorrecto = false;
            localOriginal = null;
            localModificado = null;
        } else {
            jLabelTitulo.setText("Modificar Local");
            setTitle("Modificar Local");

            jTextFieldNombre.setText(local.getNombre().trim());
            jTextFieldAlias.setText(local.getAlias().trim());
            jTextFieldPrecioBase.setText(local.getPrecio().trim() + sufijoPrecioBase);

            jTextFieldNombre.setForeground(new Color(0, 0, 0));
            jTextFieldAlias.setForeground(new Color(0, 0, 0));
            jTextFieldPrecioBase.setForeground(new Color(0, 0, 0));

            jButtonAceptar.setEnabled(true);

            esEdicion = true;
            campoPrecioBaseCorrecto = true;
            localOriginal = new Local(local);
            localModificado = new Local(local);
        }
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
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelAlias = new javax.swing.JLabel();
        jTextFieldAlias = new javax.swing.JTextField();
        jLabelPrecioBase = new javax.swing.JLabel();
        jTextFieldPrecioBase = new javax.swing.JTextField();
        jLabelAdvertenciaPrecioBase = new javax.swing.JLabel();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("----- Local");
        jPanelCabecera.add(jLabelTitulo);

        jPanelPrincipal.add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new java.awt.GridBagLayout());

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelNombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelNombre, gridBagConstraints);

        jTextFieldNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNombre.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldNombre.setText("Introduzca Nombre de Local");
        jTextFieldNombre.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldNombre.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldNombreFocusLost(evt);
            }
        });
        jTextFieldNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldNombreKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldNombre, gridBagConstraints);

        jLabelAlias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAlias.setText("Alias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAlias, gridBagConstraints);

        jTextFieldAlias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldAlias.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldAlias.setText("Introduzca Alias de Local (opcional)");
        jTextFieldAlias.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldAlias.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldAlias.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldAliasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldAliasFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldAlias, gridBagConstraints);

        jLabelPrecioBase.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelPrecioBase.setText("Precio Base");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelPrecioBase, gridBagConstraints);

        jTextFieldPrecioBase.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldPrecioBase.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldPrecioBase.setText("0,00 €");
        jTextFieldPrecioBase.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldPrecioBase.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldPrecioBase.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPrecioBaseFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldPrecioBaseFocusLost(evt);
            }
        });
        jTextFieldPrecioBase.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioBaseKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldPrecioBase, gridBagConstraints);

        jLabelAdvertenciaPrecioBase.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaPrecioBase.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaPrecioBase, gridBagConstraints);

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
        jButtonAceptar.setEnabled(false);
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
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 425, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 308, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        // TODO add your handling code here:
        String aliasFinal = jTextFieldAlias.getText().trim();
        if (aliasFinal.equals(placeHolderAlias)) {
            aliasFinal = ""; // Sustituir el placeholder por cadena vacía
        }

        if (!esEdicion) {
            UUID uuid = UUID.randomUUID();
            String codigo = uuid.toString().trim();

            quitarSufijo(jTextFieldPrecioBase, sufijoPrecioBase);

            localOriginal = new Local(
                    codigo,
                    jTextFieldNombre.getText().trim(),
                    aliasFinal,
                    jTextFieldPrecioBase.getText().trim()
            );

            localControlador.crear(localOriginal);
        } else {
            localModificado.setNombre(jTextFieldNombre.getText().trim());
            localModificado.setAlias(aliasFinal);
            quitarSufijo(jTextFieldPrecioBase, sufijoPrecioBase);
            localModificado.setPrecio(jTextFieldPrecioBase.getText().trim());

            if (!localOriginal.equals(localControlador)) {
                localControlador.actualizar(localModificado);
            }
        }
        dispose();
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jTextFieldPrecioBaseKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioBaseKeyReleased
        // TODO add your handling code here:
        campoPrecioBaseCorrecto = validarCampoFormulario(
                jTextFieldPrecioBase,
                jLabelAdvertenciaPrecioBase,
                "* Introduce un número con 2 decimales",
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches("(?!0,00)(?!0)([1-9]\\d{0,9}|0)(,\\d{2})?")
        // Permitir números con exactamente 2 decimales y enteros != de 0,00 y 0
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldPrecioBaseKeyReleased

    private void jTextFieldPrecioBaseFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrecioBaseFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldPrecioBase, placeHolderPrecioBase);
        quitarSufijo(jTextFieldPrecioBase, sufijoPrecioBase);
    }//GEN-LAST:event_jTextFieldPrecioBaseFocusGained

    private void jTextFieldPrecioBaseFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrecioBaseFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldPrecioBase, placeHolderPrecioBase);
        if(validarMonto(jTextFieldPrecioBase.getText().trim())){
            agregarSufijo(jTextFieldPrecioBase, sufijoPrecioBase);
        }
    }//GEN-LAST:event_jTextFieldPrecioBaseFocusLost

    private void jTextFieldAliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAliasFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldAlias, placeHolderAlias);
    }//GEN-LAST:event_jTextFieldAliasFocusGained

    private void jTextFieldAliasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAliasFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldAlias, placeHolderAlias);
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldAliasFocusLost

    private void jTextFieldNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldNombre, placeHolderNombre);
    }//GEN-LAST:event_jTextFieldNombreFocusGained

    private void jTextFieldNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldNombre, placeHolderNombre);
    }//GEN-LAST:event_jTextFieldNombreFocusLost

    private void jTextFieldNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyReleased
        // TODO add your handling code here:
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldNombreKeyReleased

    private void habilitarBotonAceptar() {
        if (!jTextFieldNombre.getText().trim().equals(placeHolderNombre) && !jTextFieldNombre.getText().trim().isEmpty()) {
            if (campoPrecioBaseCorrecto && !jTextFieldPrecioBase.getText().trim().isEmpty()) {
                jButtonAceptar.setEnabled(true);
                return;
            }
        }
        jButtonAceptar.setEnabled(false);

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelAdvertenciaPrecioBase;
    private javax.swing.JLabel jLabelAlias;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelPrecioBase;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JTextField jTextFieldAlias;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldPrecioBase;
    // End of variables declaration//GEN-END:variables
}

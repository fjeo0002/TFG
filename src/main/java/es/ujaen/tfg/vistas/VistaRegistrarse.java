/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import static es.ujaen.tfg.utils.Utils.ERROR_CODIGOPOSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_CODIGO_POSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_DIRECCION_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_LOCALIDAD_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_CODIGO_POSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.agregarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.quitarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.validarCampoFormulario;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

/**
 *
 * @author jota
 */
public class VistaRegistrarse extends javax.swing.JFrame {

    private VistaInicioSesión vistaInicioSesión;

    private final Border originalBorder;

    private boolean campoDNICorrecto;
    private boolean campoNombreCorrecto;
    private boolean campoEmailCorrecto;
    private boolean campoCodigoPostalCorrecto;
    
    private boolean isPasswordVisible = false;

    /**
     * Creates new form VistaRegistrarse
     */
    public VistaRegistrarse() {
        initComponents();
        setLocationRelativeTo(null);
        
        ImageIcon icon = new ImageIcon("iconoFondoTransparente.png"); // Ruta de la imagen
        this.setIconImage(icon.getImage()); // Establecer el icono

        this.originalBorder = jTextFieldDNI.getBorder();

        this.campoDNICorrecto = false;
        this.campoNombreCorrecto = false;
        this.campoEmailCorrecto = false;
        this.campoCodigoPostalCorrecto = false;
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
        jLabelDNI = new javax.swing.JLabel();
        jTextFieldDNI = new javax.swing.JTextField();
        jLabelAdvertenciaDNI = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelAdvertenciaNombre = new javax.swing.JLabel();
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelAdvertenciaEmail = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jLabelLocalidad = new javax.swing.JLabel();
        jTextFieldLocalidad = new javax.swing.JTextField();
        jLabelCodigoPostal = new javax.swing.JLabel();
        jTextFieldCodigoPostal = new javax.swing.JTextField();
        jLabelAdvertenciaCodigoPostal = new javax.swing.JLabel();
        jLabelContrasena = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabelInicioSesion = new javax.swing.JLabel();
        jButtonMostrarContrasena = new javax.swing.JButton();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonRegistrarse = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Registro");

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Registrarse");
        jPanelCabecera.add(jLabelTitulo);

        jPanelPrincipal.add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new java.awt.GridBagLayout());

        jLabelDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDNI.setText("DNI - CIF");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelDNI, gridBagConstraints);

        jTextFieldDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldDNI.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDNI.setText("12345678X");
        jTextFieldDNI.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldDNI.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldDNI.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDNIFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDNIFocusLost(evt);
            }
        });
        jTextFieldDNI.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDNIKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldDNI, gridBagConstraints);

        jLabelAdvertenciaDNI.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaDNI.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaDNI, gridBagConstraints);

        jLabelNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelNombre.setText("Nombre");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 25.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelNombre, gridBagConstraints);

        jTextFieldNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldNombre.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldNombre.setText("Nombre Apellido1 Apellido2");
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
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldNombre, gridBagConstraints);

        jLabelAdvertenciaNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaNombre.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaNombre, gridBagConstraints);

        jLabelEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelEmail.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelEmail, gridBagConstraints);

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldEmail.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldEmail.setText("nombre.123@gmail.com");
        jTextFieldEmail.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldEmail.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldEmail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEmailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldEmailFocusLost(evt);
            }
        });
        jTextFieldEmail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldEmailKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldEmail, gridBagConstraints);

        jLabelAdvertenciaEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaEmail.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaEmail, gridBagConstraints);

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDireccion.setText("Dirección");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelDireccion, gridBagConstraints);

        jTextFieldDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldDireccion.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldDireccion.setText("C/ Mirabueno, 9, 9ºB");
        jTextFieldDireccion.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldDireccion.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldDireccion.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDireccionFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldDireccionFocusLost(evt);
            }
        });
        jTextFieldDireccion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldDireccionKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldDireccion, gridBagConstraints);

        jLabelLocalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelLocalidad.setText("Localidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelLocalidad, gridBagConstraints);

        jTextFieldLocalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldLocalidad.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldLocalidad.setText("Localidad, Provincia");
        jTextFieldLocalidad.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldLocalidad.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldLocalidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldLocalidadFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldLocalidadFocusLost(evt);
            }
        });
        jTextFieldLocalidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldLocalidadKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldLocalidad, gridBagConstraints);

        jLabelCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelCodigoPostal.setText("Código Postal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelCodigoPostal, gridBagConstraints);

        jTextFieldCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldCodigoPostal.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldCodigoPostal.setText("12345");
        jTextFieldCodigoPostal.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldCodigoPostal.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldCodigoPostal.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoPostalFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldCodigoPostalFocusLost(evt);
            }
        });
        jTextFieldCodigoPostal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldCodigoPostalKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldCodigoPostal, gridBagConstraints);

        jLabelAdvertenciaCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaCodigoPostal.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaCodigoPostal, gridBagConstraints);

        jLabelContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelContrasena.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 16;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelContrasena, gridBagConstraints);

        jPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jPasswordField, gridBagConstraints);

        jLabelInicioSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelInicioSesion.setForeground(java.awt.SystemColor.textHighlight);
        jLabelInicioSesion.setText("<html><u>Ya estás registrado...</u></html>");
        jLabelInicioSesion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelInicioSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelInicioSesionMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelInicioSesion, gridBagConstraints);

        jButtonMostrarContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonMostrarContrasena.setIcon(new FlatSVGIcon("svg/ojo_cerrado.svg"));
        jButtonMostrarContrasena.setText("Mostrar Contraseña");
        jButtonMostrarContrasena.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonMostrarContrasenaActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jButtonMostrarContrasena, gridBagConstraints);

        jPanelPrincipal.add(jPanelCuerpo, java.awt.BorderLayout.CENTER);

        jPanelPiePagina.setName(""); // NOI18N
        jPanelPiePagina.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonRegistrarse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonRegistrarse.setText("Registrarse");
        jButtonRegistrarse.setEnabled(false);
        jButtonRegistrarse.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonRegistrarseActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonRegistrarse);

        jPanelPrincipal.add(jPanelPiePagina, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 705, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonRegistrarseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonRegistrarseActionPerformed
        // TODO add your handling code here:

        dispose();
    }//GEN-LAST:event_jButtonRegistrarseActionPerformed

    private void jTextFieldCodigoPostalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPostalKeyReleased
        // TODO add your handling code here:
        campoCodigoPostalCorrecto = validarCampoFormulario(
                jTextFieldCodigoPostal,
                jLabelAdvertenciaCodigoPostal,
                ERROR_CODIGOPOSTAL_CLIENTE,
                originalBorder,
                texto -> texto.matches(VALIDACION_CODIGO_POSTAL_CLIENTE)
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldCodigoPostalKeyReleased

    private void jTextFieldCodigoPostalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPostalFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldCodigoPostal, PLACEHOLDER_CODIGO_POSTAL_CLIENTE);
    }//GEN-LAST:event_jTextFieldCodigoPostalFocusLost

    private void jTextFieldCodigoPostalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPostalFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldCodigoPostal, PLACEHOLDER_CODIGO_POSTAL_CLIENTE);
    }//GEN-LAST:event_jTextFieldCodigoPostalFocusGained

    private void jTextFieldLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLocalidadKeyReleased
        // TODO add your handling code here:
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldLocalidadKeyReleased

    private void jTextFieldLocalidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLocalidadFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldLocalidad, PLACEHOLDER_LOCALIDAD_CLIENTE);
    }//GEN-LAST:event_jTextFieldLocalidadFocusLost

    private void jTextFieldLocalidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLocalidadFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldLocalidad, PLACEHOLDER_LOCALIDAD_CLIENTE);
    }//GEN-LAST:event_jTextFieldLocalidadFocusGained

    private void jTextFieldDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDireccionKeyReleased
        // TODO add your handling code here:
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldDireccionKeyReleased

    private void jTextFieldDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccionFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldDireccion, PLACEHOLDER_DIRECCION_CLIENTE);
    }//GEN-LAST:event_jTextFieldDireccionFocusLost

    private void jTextFieldDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccionFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldDireccion, PLACEHOLDER_DIRECCION_CLIENTE);
    }//GEN-LAST:event_jTextFieldDireccionFocusGained

    private void jTextFieldEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailKeyReleased
        // TODO add your handling code here:
        campoEmailCorrecto = validarCampoFormulario(
                jTextFieldEmail,
                jLabelAdvertenciaEmail,
                ERROR_EMAIL_CLIENTE,
                originalBorder,
                texto -> texto.isEmpty() || texto.matches(VALIDACION_EMAIL_CLIENTE) || texto.equals(PLACEHOLDER_EMAIL_CLIENTE)
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldEmailKeyReleased

    private void jTextFieldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE);
    }//GEN-LAST:event_jTextFieldEmailFocusLost

    private void jTextFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE);
    }//GEN-LAST:event_jTextFieldEmailFocusGained

    private void jTextFieldNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyReleased
        // TODO add your handling code here:
        campoNombreCorrecto = validarCampoFormulario(
                jTextFieldNombre,
                jLabelAdvertenciaNombre,
                ERROR_NOMBRE_CLIENTE,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_NOMBRE_CLIENTE)
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldNombreKeyReleased

    private void jTextFieldNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldNombre, PLACEHOLDER_NOMBRE_CLIENTE);
    }//GEN-LAST:event_jTextFieldNombreFocusLost

    private void jTextFieldNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldNombre, PLACEHOLDER_NOMBRE_CLIENTE);
    }//GEN-LAST:event_jTextFieldNombreFocusGained

    private void jTextFieldDNIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDNIKeyReleased
        // TODO add your handling code here:
        campoDNICorrecto = validarCampoFormulario(
                jTextFieldDNI,
                jLabelAdvertenciaDNI,
                ERROR_DNI_CLIENTE,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_DNI_CLIENTE)
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldDNIKeyReleased

    private void jTextFieldDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDNIFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldDNI, PLACEHOLDER_DNI_CLIENTE);
    }//GEN-LAST:event_jTextFieldDNIFocusLost

    private void jTextFieldDNIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDNIFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldDNI, PLACEHOLDER_DNI_CLIENTE);
    }//GEN-LAST:event_jTextFieldDNIFocusGained

    private void jLabelInicioSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelInicioSesionMouseClicked
        // TODO add your handling code here:
        vistaInicioSesión.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelInicioSesionMouseClicked

    private void jButtonMostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarContrasenaActionPerformed
        // TODO add your handling code here:
        if (isPasswordVisible) {
            jPasswordField.setEchoChar('•'); // Ocultar contraseña
            jButtonMostrarContrasena.setIcon(new FlatSVGIcon("svg/ojo_cerrado.svg"));
        } else {
            jPasswordField.setEchoChar((char) 0); // Mostrar contraseña
            jButtonMostrarContrasena.setIcon(new FlatSVGIcon("svg/ojo.svg"));
        }
        isPasswordVisible = !isPasswordVisible;
    }//GEN-LAST:event_jButtonMostrarContrasenaActionPerformed

    private void habilitarBotonAceptar() {
        if (campoDNICorrecto) {
            if (campoNombreCorrecto) {
                if (campoEmailCorrecto) {
                    if (!jTextFieldDireccion.getText().trim().equals(PLACEHOLDER_DIRECCION_CLIENTE) && !jTextFieldDireccion.getText().trim().isEmpty()) {
                        if (!jTextFieldLocalidad.getText().trim().equals(PLACEHOLDER_LOCALIDAD_CLIENTE) && !jTextFieldLocalidad.getText().trim().isEmpty()) {
                            if (campoCodigoPostalCorrecto) {
                                jButtonRegistrarse.setEnabled(true);
                                return;
                            }
                        }
                    }
                }
            }
        }
        jButtonRegistrarse.setEnabled(false);
    }

    public VistaInicioSesión getVistaInicioSesión() {
        return vistaInicioSesión;
    }

    public void setVistaInicioSesión(VistaInicioSesión vistaInicioSesión) {
        this.vistaInicioSesión = vistaInicioSesión;
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonMostrarContrasena;
    private javax.swing.JButton jButtonRegistrarse;
    private javax.swing.JLabel jLabelAdvertenciaCodigoPostal;
    private javax.swing.JLabel jLabelAdvertenciaDNI;
    private javax.swing.JLabel jLabelAdvertenciaEmail;
    private javax.swing.JLabel jLabelAdvertenciaNombre;
    private javax.swing.JLabel jLabelCodigoPostal;
    private javax.swing.JLabel jLabelContrasena;
    private javax.swing.JLabel jLabelDNI;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelInicioSesion;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTextField jTextFieldCodigoPostal;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLocalidad;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables
}

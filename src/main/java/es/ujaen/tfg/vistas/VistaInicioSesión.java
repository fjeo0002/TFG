/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import es.ujaen.tfg.DAO.UsuarioDAO;
import es.ujaen.tfg.Firebase.FirebaseInitializer;
import es.ujaen.tfg.controlador.UsuarioControlador;
import es.ujaen.tfg.modelo.Usuario;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.ERROR_CONTRASENA;
import static es.ujaen.tfg.utils.Utils.ERROR_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ERROR_CREDENCIALES;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ERROR_FIREBASE;
import static es.ujaen.tfg.utils.Utils.MENSAJE_INICIO_SESION;
import static es.ujaen.tfg.utils.Utils.MENSAJE_USUARIO_NO_EXISTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.TITULO_ERROR_CREDENCIALES;
import static es.ujaen.tfg.utils.Utils.TITULO_ERROR_FIREBASE;
import static es.ujaen.tfg.utils.Utils.TITULO_INICIO_SESION;
import static es.ujaen.tfg.utils.Utils.TITULO_USUARIO_NO_EXISTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_CONTRASENA;
import static es.ujaen.tfg.utils.Utils.VALIDACION_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.agregarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.quitarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.validarCampoFormulario;
import java.awt.Color;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author jota
 */
public class VistaInicioSesión extends javax.swing.JFrame {

    private VistaPrincipal vistaPrincipal;
    private VistaRegistrarse vistaRegistrarse;
    private VistaRecuperarContrasena vistaRecuperarContrasena;

    private UsuarioControlador usuarioControlador;
    private UsuarioDAO usuarioDAO;

    private boolean campoEmailCorrecto;
    private boolean campoContrasenaCorrecto;

    private final Border originalBorder;

    private boolean contrasenaVisible;

    /**
     * Creates new form VistaInicioSesión
     */
    public VistaInicioSesión() throws IOException {
        initComponents();
        setLocationRelativeTo(null);

        FirebaseInitializer.getInstance();

        this.originalBorder = jTextFieldEmail.getBorder();

        this.campoEmailCorrecto = false;
        this.campoContrasenaCorrecto = false;

        this.contrasenaVisible = false;
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
        jLabelEmail = new javax.swing.JLabel();
        jTextFieldEmail = new javax.swing.JTextField();
        jLabelAdvertenciaEmail = new javax.swing.JLabel();
        jLabelContrasena = new javax.swing.JLabel();
        jPasswordField = new javax.swing.JPasswordField();
        jLabelOlvidoContrasena = new javax.swing.JLabel();
        jLabelRegistrarse = new javax.swing.JLabel();
        jButtonMostrarContrasena = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonIniciarSesion = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Inicio de Sesión");
        setResizable(false);

        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Inicio de Sesión");
        jPanelCabecera.add(jLabelTitulo);

        jPanelPrincipal.add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelCuerpo.setLayout(new java.awt.GridBagLayout());

        jLabelEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelEmail.setIcon(new FlatSVGIcon("svg/email.svg"));
        jLabelEmail.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
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
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldEmail, gridBagConstraints);

        jLabelAdvertenciaEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaEmail.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaEmail, gridBagConstraints);

        jLabelContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelContrasena.setIcon(new FlatSVGIcon("svg/contrasena.svg"));
        jLabelContrasena.setText("Contraseña");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelContrasena, gridBagConstraints);

        jPasswordField.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jPasswordField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jPasswordFieldKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jPasswordField, gridBagConstraints);

        jLabelOlvidoContrasena.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelOlvidoContrasena.setForeground(java.awt.SystemColor.textHighlight);
        jLabelOlvidoContrasena.setText("<html><u>¿Olvidaste tu contraseña?</u></html>");
        jLabelOlvidoContrasena.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelOlvidoContrasena.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelOlvidoContrasenaMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelOlvidoContrasena, gridBagConstraints);

        jLabelRegistrarse.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelRegistrarse.setForeground(java.awt.SystemColor.textHighlight);
        jLabelRegistrarse.setText("<html><u>Registrarse</u></html>");
        jLabelRegistrarse.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabelRegistrarse.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelRegistrarseMouseClicked(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.EAST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelRegistrarse, gridBagConstraints);

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
        gridBagConstraints.gridy = 5;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jButtonMostrarContrasena, gridBagConstraints);
        jPanelCuerpo.add(jTabbedPane1, new java.awt.GridBagConstraints());

        jPanelPrincipal.add(jPanelCuerpo, java.awt.BorderLayout.CENTER);

        jPanelPiePagina.setName(""); // NOI18N
        jPanelPiePagina.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonIniciarSesion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonIniciarSesion.setIcon(new FlatSVGIcon("svg/iniciar_sesion.svg"));
        jButtonIniciarSesion.setText("Iniciar Sesión");
        jButtonIniciarSesion.setEnabled(false);
        jButtonIniciarSesion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIniciarSesionActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonIniciarSesion);

        jPanelPrincipal.add(jPanelPiePagina, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 433, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 343, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonIniciarSesionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIniciarSesionActionPerformed
        // TODO add your handling code here:
        // Obtener datos del formulario
        String email = jTextFieldEmail.getText().trim();
        String password = new String(jPasswordField.getPassword()).trim();

        Usuario usuario = null;
        try {
            usuarioDAO = new UsuarioDAO(email);
            usuarioControlador = new UsuarioControlador(usuarioDAO);
            usuario = usuarioControlador.leer(email);
        } catch (IOException e) {
            Utils.mostrarError(this, TITULO_ERROR_FIREBASE, MENSAJE_ERROR_FIREBASE);
            return; // Detener ejecución si hay error de conexión
        }

        if (usuario != null) {
            if (usuarioControlador.verificarCredenciales(email, password)) {
                Utils.mostrarInformacion(this, TITULO_INICIO_SESION, MENSAJE_INICIO_SESION);
                try {
                    abrirVistaPrincipal(email);
                } catch (IOException | ExecutionException ex) {
                    Logger.getLogger(VistaInicioSesión.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                Utils.mostrarError(this, TITULO_ERROR_CREDENCIALES, MENSAJE_ERROR_CREDENCIALES);
            }
        } else {
            Utils.mostrarError(this, TITULO_USUARIO_NO_EXISTE, MENSAJE_USUARIO_NO_EXISTE);
        }
    }//GEN-LAST:event_jButtonIniciarSesionActionPerformed

    private void abrirVistaPrincipal(String email) throws IOException, ExecutionException {
        vistaPrincipal = new VistaPrincipal(email);
        vistaPrincipal.setVisible(true);
        this.dispose(); // Cierra la ventana de inicio de sesión
    }

    private void jTextFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE);
    }//GEN-LAST:event_jTextFieldEmailFocusGained

    private void jTextFieldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE);
    }//GEN-LAST:event_jTextFieldEmailFocusLost

    private void jTextFieldEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailKeyReleased
        // TODO add your handling code here:
        campoEmailCorrecto = validarCampoFormulario(jTextFieldEmail,
                jLabelAdvertenciaEmail,
                ERROR_EMAIL_CLIENTE,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_EMAIL_CLIENTE)
        );
        habilitarBotonIniciarSesion();
    }//GEN-LAST:event_jTextFieldEmailKeyReleased

    private void jLabelOlvidoContrasenaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelOlvidoContrasenaMouseClicked
        try {
            // TODO add your handling code here:
            vistaRecuperarContrasena = new VistaRecuperarContrasena();
        } catch (IOException ex) {
            Logger.getLogger(VistaInicioSesión.class.getName()).log(Level.SEVERE, null, ex);
        }
        vistaRecuperarContrasena.setVistaInicioSesión(this);
        vistaRecuperarContrasena.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelOlvidoContrasenaMouseClicked

    private void jLabelRegistrarseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelRegistrarseMouseClicked
        try {
            // TODO add your handling code here:
            vistaRegistrarse = new VistaRegistrarse();
        } catch (IOException ex) {
            Logger.getLogger(VistaInicioSesión.class.getName()).log(Level.SEVERE, null, ex);
        }
        vistaRegistrarse.setVistaInicioSesión(this);
        vistaRegistrarse.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabelRegistrarseMouseClicked

    private void jButtonMostrarContrasenaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonMostrarContrasenaActionPerformed
        // TODO add your handling code here:
        if (contrasenaVisible) {
            jPasswordField.setEchoChar('•'); // Ocultar contraseña
            jButtonMostrarContrasena.setIcon(new FlatSVGIcon("svg/ojo_cerrado.svg"));
        } else {
            jPasswordField.setEchoChar((char) 0); // Mostrar contraseña
            jButtonMostrarContrasena.setIcon(new FlatSVGIcon("svg/ojo.svg"));
        }
        contrasenaVisible = !contrasenaVisible;
    }//GEN-LAST:event_jButtonMostrarContrasenaActionPerformed

    private void jPasswordFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jPasswordFieldKeyReleased
        // TODO add your handling code here:
        String pss = new String(jPasswordField.getPassword());
        JTextField jTextFieldPassword = new JTextField(pss);
        JLabel jLabel = new JLabel();

        campoContrasenaCorrecto = validarCampoFormulario(
                jTextFieldPassword,
                jLabel,
                ERROR_CONTRASENA,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_CONTRASENA)
        );
        habilitarBotonIniciarSesion();
    }//GEN-LAST:event_jPasswordFieldKeyReleased

    private void habilitarBotonIniciarSesion() {
        if (campoEmailCorrecto) {
            if (campoContrasenaCorrecto) {
                jButtonIniciarSesion.setEnabled(true);
                return;
            }
        }
        jButtonIniciarSesion.setEnabled(false);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws InstantiationException, ClassNotFoundException, IllegalAccessException {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VistaInicioSesión.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VistaInicioSesión.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VistaInicioSesión.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VistaInicioSesión.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        try {
            /*
            // Configuración de tonalidades azules en todos los componentes
            UIManager.put("Component.accentColor", new Color(30, 144, 255)); // Azul DodgerBlue
            UIManager.put("Component.focusColor", new Color(0, 120, 215)); // Azul Windows 10
            UIManager.put("Component.selectionBackground", new Color(0, 102, 204)); // Azul más oscuro para selección

            // Configuración de botones
            UIManager.put("Button.background", new Color(64, 156, 255));
            UIManager.put("Button.foreground", Color.WHITE);
            UIManager.put("Button.hoverBackground", new Color(0, 120, 215));
            UIManager.put("Button.focusedBackground", new Color(0, 102, 204));
            UIManager.put("Button.pressedBackground", new Color(0, 90, 180));

            // Campos de texto
            UIManager.put("TextComponent.background", new Color(225, 245, 254)); // Azul muy claro
            UIManager.put("TextComponent.foreground", new Color(13, 71, 161)); // Azul oscuro
            UIManager.put("TextComponent.selectionBackground", new Color(30, 136, 229));
            UIManager.put("TextComponent.selectionForeground", Color.WHITE);
            UIManager.put("TextComponent.border", BorderFactory.createLineBorder(new Color(30, 136, 229), 1));

            // Fondos de paneles y ventanas
            UIManager.put("Panel.background", new Color(240, 248, 255)); // Azul muy claro
            UIManager.put("Frame.background", new Color(240, 248, 255));
            UIManager.put("Dialog.background", new Color(240, 248, 255));

            // Tablas
            UIManager.put("Table.background", new Color(225, 245, 254));
            UIManager.put("Table.foreground", new Color(13, 71, 161));
            UIManager.put("Table.selectionBackground", new Color(30, 136, 229));
            UIManager.put("Table.selectionForeground", Color.WHITE);
            UIManager.put("Table.gridColor", new Color(144, 202, 249));

            // Menús
            UIManager.put("MenuBar.background", new Color(187, 222, 251));
            UIManager.put("Menu.foreground", new Color(13, 71, 161));
            UIManager.put("Menu.selectionBackground", new Color(30, 136, 229));

            // Scrollbars
            UIManager.put("ScrollBar.thumb", new Color(100, 181, 246));
            UIManager.put("ScrollBar.thumbHover", new Color(33, 150, 243));

            // JTabbedPane (pestañas)
            UIManager.put("TabbedPane.background", new Color(240, 248, 255)); // Fondo claro
            UIManager.put("TabbedPane.selectedBackground", new Color(30, 136, 229)); // Azul al seleccionar
            UIManager.put("TabbedPane.unselectedBackground", new Color(187, 222, 251)); // Azul suave
            UIManager.put("TabbedPane.foreground", new Color(13, 71, 161)); // Azul oscuro para texto

            // Barra de título (cerrar, minimizar, maximizar)
            UIManager.put("TitlePane.background", new Color(30, 136, 229)); // Azul de fondo
            UIManager.put("TitlePane.foreground", Color.WHITE); // Texto en blanco
            UIManager.put("TitlePane.buttonBackground", new Color(0, 120, 215)); // Botones en azul oscuro
            UIManager.put("TitlePane.buttonForeground", Color.WHITE); // Texto de los botones en blanco
            UIManager.put("TitlePane.buttonHoverBackground", new Color(0, 90, 180)); // Efecto hover
            */
            // Aplicar LookAndFeel
            UIManager.setLookAndFeel(new FlatLightLaf());

        } catch (UnsupportedLookAndFeelException e) {
        }

        /*
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.fast.FastLookAndFeel");
        } catch (UnsupportedLookAndFeelException e) {
        }
         */
 /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                new VistaInicioSesión().setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(VistaInicioSesión.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonIniciarSesion;
    private javax.swing.JButton jButtonMostrarContrasena;
    private javax.swing.JLabel jLabelAdvertenciaEmail;
    private javax.swing.JLabel jLabelContrasena;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelOlvidoContrasena;
    private javax.swing.JLabel jLabelRegistrarse;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPasswordField jPasswordField;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextFieldEmail;
    // End of variables declaration//GEN-END:variables
}

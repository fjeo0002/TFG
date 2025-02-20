/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package es.ujaen.tfg.vistas;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import es.ujaen.tfg.controlador.UsuarioControlador;
import es.ujaen.tfg.modelo.Usuario;
import es.ujaen.tfg.utils.Utils;
import static es.ujaen.tfg.utils.Utils.ERROR_CODIGOPOSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_TELEFONO;
import static es.ujaen.tfg.utils.Utils.MENSAJE_CONFIRMACION_ELIMINACION_USUARIO;
import static es.ujaen.tfg.utils.Utils.MENSAJE_ELIMINACION_USUARIO;
import static es.ujaen.tfg.utils.Utils.MENSAJE_USUARIO_MODIFICADO;
import static es.ujaen.tfg.utils.Utils.NEGRO;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_CODIGO_POSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_DIRECCION_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_LOCALIDAD_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_TELEFONO;
import static es.ujaen.tfg.utils.Utils.TITULO_CONFIRMACION_ELIMINACION_USUARIO;
import static es.ujaen.tfg.utils.Utils.TITULO_ELIMINACION_USUARIO;
import static es.ujaen.tfg.utils.Utils.TITULO_USUARIO_MODIFICADO;
import static es.ujaen.tfg.utils.Utils.VALIDACION_CODIGO_POSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_TELEFONO;
import static es.ujaen.tfg.utils.Utils.agregarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.quitarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.validarCampoFormulario;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author jota
 */
public class VistaUsuario extends javax.swing.JFrame {

    private VistaInicioSesión vistaInicioSesión;

    private Usuario usuarioModificado;
    private Usuario usuarioOriginal;

    private final UsuarioControlador usuarioControlador;

    private final Border originalBorder;

    private boolean campoDNICorrecto;
    private boolean campoNombreCorrecto;
    private boolean campoEmailCorrecto;
    private boolean campoCodigoPostalCorrecto;
    private boolean campoTelefonoCorrecto;

    private final JFrame parent;

    /**
     * Creates new form VistaRegistrarse
     *
     * @param parent
     * @param usuario
     * @param usuarioControlador
     */
    public VistaUsuario(JFrame parent, Usuario usuario, UsuarioControlador usuarioControlador) {
        initComponents();
        setLocationRelativeTo(null);

        ImageIcon icon = new ImageIcon("iconoFondoTransparente.png"); // Ruta de la imagen
        this.setIconImage(icon.getImage()); // Establecer el icono

        this.jPanelPrincipal.setBorder(new EmptyBorder(10, 10, 10, 10));

        this.parent = parent;

        this.originalBorder = jTextFieldDNI.getBorder();

        this.usuarioControlador = usuarioControlador;

        cargarVistaUsuario(usuario);

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
        jPanelCuerpo = new javax.swing.JPanel();
        jLabelDNI = new javax.swing.JLabel();
        jTextFieldDNI = new javax.swing.JTextField();
        jLabelAdvertenciaDNI = new javax.swing.JLabel();
        jLabelNombre = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jLabelAdvertenciaNombre = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jTextFieldTelefono = new javax.swing.JTextField();
        jLabelAdvertenciaTelefono = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jTextFieldDireccion = new javax.swing.JTextField();
        jLabelLocalidad = new javax.swing.JLabel();
        jTextFieldLocalidad = new javax.swing.JTextField();
        jLabelCodigoPostal = new javax.swing.JLabel();
        jTextFieldCodigoPostal = new javax.swing.JTextField();
        jLabelAdvertenciaCodigoPostal = new javax.swing.JLabel();
        jPanelCabecera = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonEliminarUsuario = new javax.swing.JButton();
        jButtonModificarUsuario = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Registro");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        java.awt.GridBagLayout jPanelCuerpoLayout = new java.awt.GridBagLayout();
        jPanelCuerpoLayout.columnWeights = new double[] {150.0, 200.0};
        jPanelCuerpo.setLayout(jPanelCuerpoLayout);

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

        jLabelTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTelefono.setText("Teléfono");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelTelefono, gridBagConstraints);

        jTextFieldTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldTelefono.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldTelefono.setText("9 dígitos sin espacios");
        jTextFieldTelefono.setMinimumSize(new java.awt.Dimension(125, 26));
        jTextFieldTelefono.setPreferredSize(new java.awt.Dimension(125, 26));
        jTextFieldTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTelefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                jTextFieldTelefonoFocusLost(evt);
            }
        });
        jTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyReleased(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldTelefono, gridBagConstraints);

        jLabelAdvertenciaTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaTelefono.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaTelefono, gridBagConstraints);

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDireccion.setText("Dirección");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldDireccion, gridBagConstraints);

        jLabelLocalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelLocalidad.setText("Localidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldLocalidad, gridBagConstraints);

        jLabelCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelCodigoPostal.setText("Código Postal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
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
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldCodigoPostal, gridBagConstraints);

        jLabelAdvertenciaCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaCodigoPostal.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaCodigoPostal, gridBagConstraints);

        jPanelPrincipal.add(jPanelCuerpo, java.awt.BorderLayout.CENTER);

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("Modificar Usuario");
        jPanelCabecera.add(jLabelTitulo);

        jPanelPrincipal.add(jPanelCabecera, java.awt.BorderLayout.PAGE_START);

        jPanelPiePagina.setName(""); // NOI18N
        jPanelPiePagina.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.RIGHT));

        jButtonEliminarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonEliminarUsuario.setIcon(new FlatSVGIcon("svg/eliminar_usuario.svg"));
        jButtonEliminarUsuario.setText("Eliminar Usuario");
        jButtonEliminarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonEliminarUsuarioActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonEliminarUsuario);

        jButtonModificarUsuario.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jButtonModificarUsuario.setIcon(new FlatSVGIcon("svg/modificar_cliente.svg"));
        jButtonModificarUsuario.setText("Modificar Usuario");
        jButtonModificarUsuario.setEnabled(false);
        jButtonModificarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonModificarUsuarioActionPerformed(evt);
            }
        });
        jPanelPiePagina.add(jButtonModificarUsuario);

        jPanelPrincipal.add(jPanelPiePagina, java.awt.BorderLayout.PAGE_END);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanelPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 391, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cargarVistaUsuario(Usuario usuario) {

        this.jTextFieldDNI.setText(usuario.getDNI().trim());
        this.jTextFieldNombre.setText(usuario.getNombre().trim());
        this.jTextFieldDireccion.setText(usuario.getDireccion().trim());
        this.jTextFieldLocalidad.setText(usuario.getLocalidad().trim());
        this.jTextFieldCodigoPostal.setText(usuario.getCodigoPostal().trim());
        this.jTextFieldTelefono.setText(usuario.getTelefono().trim());

        this.jTextFieldDNI.setForeground(NEGRO);
        this.jTextFieldNombre.setForeground(NEGRO);
        this.jTextFieldDireccion.setForeground(NEGRO);
        this.jTextFieldLocalidad.setForeground(NEGRO);
        this.jTextFieldCodigoPostal.setForeground(NEGRO);
        this.jTextFieldTelefono.setForeground(NEGRO);

        this.jButtonModificarUsuario.setEnabled(true);

        this.campoDNICorrecto = true;
        this.campoNombreCorrecto = true;
        this.campoEmailCorrecto = true;
        this.campoCodigoPostalCorrecto = true;
        this.campoTelefonoCorrecto = true;
        this.usuarioModificado = new Usuario(usuario);
        this.usuarioOriginal = new Usuario(usuario);

    }

    private void jButtonModificarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonModificarUsuarioActionPerformed
        // TODO add your handling code here:
        // Obtener datos del formulario
        String email = usuarioOriginal.getEmail();
        String password = usuarioOriginal.getContrasena();
        String nombre = jTextFieldNombre.getText().trim();
        String dni = jTextFieldDNI.getText().trim();
        String direccion = jTextFieldDireccion.getText().trim();
        String localidad = jTextFieldLocalidad.getText().trim();
        String codigoPostal = jTextFieldCodigoPostal.getText().trim();
        String telefono = jTextFieldTelefono.getText().trim();

        // Crear objeto UsuarioModificado
        usuarioModificado = new Usuario(email, password, nombre, dni, direccion, localidad, codigoPostal, telefono);
        // La contraseña que tenemos ahora mismo es la hasheada, pero con el constructor la está hasheando 2 veces
        //Ponemos la contraseña sin hashearla 2 veces
        usuarioModificado.setContrasena(password);

        // Guardar usuario en Firestore y en caché con UsuarioControlador
        usuarioControlador.actualizar(usuarioOriginal, usuarioModificado);

        Utils.mostrarInformacion(this, TITULO_USUARIO_MODIFICADO, MENSAJE_USUARIO_MODIFICADO);
        // Abrir ventana de inicio de Sesion
        dispose();
        parent.setEnabled(true);
        //this.dispose();

    }//GEN-LAST:event_jButtonModificarUsuarioActionPerformed

    private void jTextFieldDNIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDNIFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldDNI, PLACEHOLDER_DNI_CLIENTE);
    }//GEN-LAST:event_jTextFieldDNIFocusGained

    private void jTextFieldDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDNIFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldDNI, PLACEHOLDER_DNI_CLIENTE);
    }//GEN-LAST:event_jTextFieldDNIFocusLost

    private void jTextFieldDNIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDNIKeyReleased
        // TODO add your handling code here:
        campoDNICorrecto = validarCampoFormulario(
                jTextFieldDNI,
                jLabelAdvertenciaDNI,
                ERROR_DNI_CLIENTE,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_DNI_CLIENTE)
        );
        habilitarBotonRegistrarse();
    }//GEN-LAST:event_jTextFieldDNIKeyReleased

    private void jTextFieldNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldNombre, PLACEHOLDER_NOMBRE_CLIENTE);

    }//GEN-LAST:event_jTextFieldNombreFocusGained

    private void jTextFieldNombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldNombre, PLACEHOLDER_NOMBRE_CLIENTE);

    }//GEN-LAST:event_jTextFieldNombreFocusLost

    private void jTextFieldNombreKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyReleased
        // TODO add your handling code here:
        campoNombreCorrecto = validarCampoFormulario(
                jTextFieldNombre,
                jLabelAdvertenciaNombre,
                ERROR_NOMBRE_CLIENTE,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_NOMBRE_CLIENTE)
        );
        habilitarBotonRegistrarse();
    }//GEN-LAST:event_jTextFieldNombreKeyReleased

    private void jTextFieldDireccionFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccionFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldDireccion, PLACEHOLDER_DIRECCION_CLIENTE);

    }//GEN-LAST:event_jTextFieldDireccionFocusGained

    private void jTextFieldDireccionFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccionFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldDireccion, PLACEHOLDER_DIRECCION_CLIENTE);

    }//GEN-LAST:event_jTextFieldDireccionFocusLost

    private void jTextFieldDireccionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDireccionKeyReleased
        // TODO add your handling code here:
        habilitarBotonRegistrarse();
    }//GEN-LAST:event_jTextFieldDireccionKeyReleased

    private void jTextFieldLocalidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLocalidadFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldLocalidad, PLACEHOLDER_LOCALIDAD_CLIENTE);

    }//GEN-LAST:event_jTextFieldLocalidadFocusGained

    private void jTextFieldLocalidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldLocalidadFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldLocalidad, PLACEHOLDER_LOCALIDAD_CLIENTE);

    }//GEN-LAST:event_jTextFieldLocalidadFocusLost

    private void jTextFieldLocalidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldLocalidadKeyReleased
        // TODO add your handling code here:
        habilitarBotonRegistrarse();
    }//GEN-LAST:event_jTextFieldLocalidadKeyReleased

    private void jTextFieldCodigoPostalFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPostalFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldCodigoPostal, PLACEHOLDER_CODIGO_POSTAL_CLIENTE);

    }//GEN-LAST:event_jTextFieldCodigoPostalFocusGained

    private void jTextFieldCodigoPostalFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPostalFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldCodigoPostal, PLACEHOLDER_CODIGO_POSTAL_CLIENTE);

    }//GEN-LAST:event_jTextFieldCodigoPostalFocusLost

    private void jTextFieldCodigoPostalKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigoPostalKeyReleased
        // TODO add your handling code here:
        campoCodigoPostalCorrecto = validarCampoFormulario(
                jTextFieldCodigoPostal,
                jLabelAdvertenciaCodigoPostal,
                ERROR_CODIGOPOSTAL_CLIENTE,
                originalBorder,
                texto -> texto.matches(VALIDACION_CODIGO_POSTAL_CLIENTE)
        );
        habilitarBotonRegistrarse();
    }//GEN-LAST:event_jTextFieldCodigoPostalKeyReleased

    private void jTextFieldTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldTelefono, PLACEHOLDER_TELEFONO);

    }//GEN-LAST:event_jTextFieldTelefonoFocusGained

    private void jTextFieldTelefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldTelefono, PLACEHOLDER_TELEFONO);
    }//GEN-LAST:event_jTextFieldTelefonoFocusLost

    private void jTextFieldTelefonoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoKeyReleased
        // TODO add your handling code here:
        campoTelefonoCorrecto = validarCampoFormulario(
                jTextFieldTelefono,
                jLabelAdvertenciaTelefono,
                ERROR_TELEFONO,
                originalBorder,
                texto -> texto.matches(VALIDACION_TELEFONO)
        );
        habilitarBotonRegistrarse();
    }//GEN-LAST:event_jTextFieldTelefonoKeyReleased

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        // TODO add your handling code here:
        parent.setEnabled(true);
    }//GEN-LAST:event_formWindowClosing

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
        parent.setEnabled(false);
    }//GEN-LAST:event_formWindowOpened

    private void jButtonEliminarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonEliminarUsuarioActionPerformed
        // TODO add your handling code here:
        // Mensaje de confirmación
        int opcion = Utils.mostrarConfirmacion(this, TITULO_CONFIRMACION_ELIMINACION_USUARIO, MENSAJE_CONFIRMACION_ELIMINACION_USUARIO);

        // Si el usuario confirma (Opción "Sí")
        if (opcion == JOptionPane.YES_OPTION) {
            usuarioControlador.borrar(usuarioOriginal);

            Utils.mostrarInformacion(this, TITULO_ELIMINACION_USUARIO, MENSAJE_ELIMINACION_USUARIO);

            try {
                vistaInicioSesión = new VistaInicioSesión();
            } catch (IOException ex) {
                Logger.getLogger(VistaUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

            parent.setEnabled(true);
            parent.dispose();

            vistaInicioSesión.setVisible(true);
            this.dispose();
        }
    }//GEN-LAST:event_jButtonEliminarUsuarioActionPerformed

    private void habilitarBotonRegistrarse() {
        if (campoDNICorrecto) {
            if (campoNombreCorrecto) {
                if (campoEmailCorrecto) {
                    if (!jTextFieldDireccion.getText().trim().equals(PLACEHOLDER_DIRECCION_CLIENTE) && !jTextFieldDireccion.getText().trim().isEmpty()) {
                        if (!jTextFieldLocalidad.getText().trim().equals(PLACEHOLDER_LOCALIDAD_CLIENTE) && !jTextFieldLocalidad.getText().trim().isEmpty()) {
                            if (campoCodigoPostalCorrecto) {
                                if (campoTelefonoCorrecto) {
                                    jButtonModificarUsuario.setEnabled(true);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        jButtonModificarUsuario.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonEliminarUsuario;
    private javax.swing.JButton jButtonModificarUsuario;
    private javax.swing.JLabel jLabelAdvertenciaCodigoPostal;
    private javax.swing.JLabel jLabelAdvertenciaDNI;
    private javax.swing.JLabel jLabelAdvertenciaNombre;
    private javax.swing.JLabel jLabelAdvertenciaTelefono;
    private javax.swing.JLabel jLabelCodigoPostal;
    private javax.swing.JLabel jLabelDNI;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JTextField jTextFieldCodigoPostal;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldLocalidad;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldTelefono;
    // End of variables declaration//GEN-END:variables
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package es.ujaen.tfg.vistas;

import es.ujaen.tfg.controlador.ClienteControlador;
import es.ujaen.tfg.modelo.Cliente;
import static es.ujaen.tfg.utils.Utils.AL_DIA;
import static es.ujaen.tfg.utils.Utils.ERROR_CODIGOPOSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.ERROR_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.MENSAJE_CLIENTE_REPETIDO;
import static es.ujaen.tfg.utils.Utils.NEGRO;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_ALIAS_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_CODIGO_POSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_DIRECCION_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_DNI_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_LOCALIDAD_CLIENTE;
import static es.ujaen.tfg.utils.Utils.PLACEHOLDER_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.TIPOA;
import static es.ujaen.tfg.utils.Utils.TIPOB;
import static es.ujaen.tfg.utils.Utils.TITULO_CLIENTE_REPETIDO;
import static es.ujaen.tfg.utils.Utils.TITULO_VISTA_ANADIR_CLIENTE;
import static es.ujaen.tfg.utils.Utils.TITULO_VISTA_MODIFICAR_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VACIO;
import static es.ujaen.tfg.utils.Utils.VALIDACION_CODIGO_POSTAL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_DNI_CIF_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_EMAIL_CLIENTE;
import static es.ujaen.tfg.utils.Utils.VALIDACION_NOMBRE_CLIENTE;
import static es.ujaen.tfg.utils.Utils.agregarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.mostrarError;
import static es.ujaen.tfg.utils.Utils.quitarPlaceHolder;
import static es.ujaen.tfg.utils.Utils.validarCampoFormulario;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.Border;

/**
 *
 * @author jota
 */
public class VistaAnadirModificarCliente extends javax.swing.JDialog {

    private final JFrame parent;

    private boolean esEdicion;

    private Cliente clienteModificado;
    private Cliente clienteOriginal;

    private final ClienteControlador clienteControlador;

    private final Border originalBorder;

    private boolean campoDNICorrecto;
    private boolean campoNombreCorrecto;
    private boolean campoEmailCorrecto;
    private boolean campoCodigoPostalCorrecto;

    /**
     * Creates new form VistaCrearModificarCliente
     *
     * @param parent
     * @param modal
     * @param cliente: null -> VistaAñadirCliente cliente ->
     * VistaModificarCliente
     * @param clienteControlador
     */
    public VistaAnadirModificarCliente(java.awt.Frame parent, boolean modal, Cliente cliente, ClienteControlador clienteControlador) {
        super(parent, modal);
        initComponents();
        this.parent = (JFrame) parent;
        setLocationRelativeTo(null);
        
        this.originalBorder = jTextFieldDNI.getBorder();

        this.clienteControlador = clienteControlador;

        if (cliente == null) {
            cargarVistaAnadirCliente();
        } else {
            cargarVistaModificarCliente(cliente);
        }

    }

    private void cargarVistaAnadirCliente() {
        this.jLabelTitulo.setText(TITULO_VISTA_ANADIR_CLIENTE);
        this.setTitle(TITULO_VISTA_ANADIR_CLIENTE);

        this.jButtonAceptar.setEnabled(false);
        this.jTextFieldDNI.setEditable(true);

        this.esEdicion = false;
        this.campoDNICorrecto = false;
        this.campoNombreCorrecto = false;
        this.campoEmailCorrecto = true;          //Por si no pones Email, q puedas habilitar el boton de Aceptar al crear
        this.campoCodigoPostalCorrecto = false;
        this.clienteModificado = null;
        this.clienteOriginal = null;
    }

    private void cargarVistaModificarCliente(Cliente cliente) {
        this.jLabelTitulo.setText(TITULO_VISTA_MODIFICAR_CLIENTE);
        this.setTitle(TITULO_VISTA_MODIFICAR_CLIENTE);

        this.jTextFieldDNI.setText(cliente.getDNI().trim());
        this.jTextFieldNombre.setText(cliente.getNombre().trim());
        this.jTextFieldAlias.setText(cliente.getAlias().trim());
        this.jTextFieldEmail.setText(cliente.getEmail().trim());
        this.jTextFieldDireccion.setText(cliente.getDireccion().trim());
        this.jTextFieldLocalidad.setText(cliente.getLocalidad().trim());
        this.jTextFieldCodigoPostal.setText(cliente.getCodigoPostal().trim());

        if (TIPOA.equals(cliente.getTipo())) {
            this.jRadioButtonTipoA.setSelected(true);
        } else {
            this.jRadioButtonTipoB.setSelected(true);
        }

        this.jTextFieldDNI.setForeground(NEGRO);
        this.jTextFieldNombre.setForeground(NEGRO);
        this.jTextFieldAlias.setForeground(NEGRO);
        this.jTextFieldEmail.setForeground(NEGRO);
        this.jTextFieldDireccion.setForeground(NEGRO);
        this.jTextFieldLocalidad.setForeground(NEGRO);
        this.jTextFieldCodigoPostal.setForeground(NEGRO);

        this.jButtonAceptar.setEnabled(true);
        this.jTextFieldDNI.setEditable(false);

        this.esEdicion = true;
        this.campoDNICorrecto = true;
        this.campoNombreCorrecto = true;
        this.campoEmailCorrecto = true;
        this.campoCodigoPostalCorrecto = true;
        this.clienteModificado = new Cliente(cliente);
        this.clienteOriginal = new Cliente(cliente);
        
        // Puede que no tenga ni alias ni email
        if(cliente.getAlias().trim().isEmpty()){
            agregarPlaceHolder(jTextFieldAlias, PLACEHOLDER_ALIAS_CLIENTE);
        }
        if(cliente.getEmail().trim().isEmpty()){
            agregarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL);
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

        buttonGroupTipo = new javax.swing.ButtonGroup();
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
        jLabelAlias = new javax.swing.JLabel();
        jTextFieldAlias = new javax.swing.JTextField();
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
        jLabelTipo = new javax.swing.JLabel();
        jPanelRadioButtons = new javax.swing.JPanel();
        jRadioButtonTipoA = new javax.swing.JRadioButton();
        jRadioButtonTipoB = new javax.swing.JRadioButton();
        jPanelPiePagina = new javax.swing.JPanel();
        jButtonCancelar = new javax.swing.JButton();
        jButtonAceptar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setIconImage(null);
        setResizable(false);

        jPanelPrincipal.setBorder(javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10));
        jPanelPrincipal.setLayout(new java.awt.BorderLayout());

        jPanelCabecera.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabelTitulo.setText("----- Cliente");
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

        jLabelAlias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAlias.setText("Alias");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAlias, gridBagConstraints);

        jTextFieldAlias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldAlias.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldAlias.setText("Introduzca Alias de Cliente (opcional)");
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
        gridBagConstraints.gridy = 7;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldAlias, gridBagConstraints);

        jLabelEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelEmail.setText("E-mail");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelEmail, gridBagConstraints);

        jTextFieldEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextFieldEmail.setForeground(new java.awt.Color(153, 153, 153));
        jTextFieldEmail.setText("nombre.123@gmail.com (opcional)");
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
        gridBagConstraints.gridy = 9;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldEmail, gridBagConstraints);

        jLabelAdvertenciaEmail.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaEmail.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaEmail, gridBagConstraints);

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelDireccion.setText("Dirección");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
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
        gridBagConstraints.gridy = 12;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldDireccion, gridBagConstraints);

        jLabelLocalidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelLocalidad.setText("Localidad");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 13;
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
        gridBagConstraints.gridy = 14;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldLocalidad, gridBagConstraints);

        jLabelCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelCodigoPostal.setText("Código Postal");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 15;
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
        gridBagConstraints.gridy = 16;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jTextFieldCodigoPostal, gridBagConstraints);

        jLabelAdvertenciaCodigoPostal.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelAdvertenciaCodigoPostal.setForeground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 17;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelAdvertenciaCodigoPostal, gridBagConstraints);

        jLabelTipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabelTipo.setText("Tipo");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 18;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jLabelTipo, gridBagConstraints);

        buttonGroupTipo.add(jRadioButtonTipoA);
        jRadioButtonTipoA.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButtonTipoA.setText("A");
        jRadioButtonTipoA.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonTipoAMouseClicked(evt);
            }
        });
        jPanelRadioButtons.add(jRadioButtonTipoA);

        buttonGroupTipo.add(jRadioButtonTipoB);
        jRadioButtonTipoB.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jRadioButtonTipoB.setText("B");
        jRadioButtonTipoB.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jRadioButtonTipoBMouseClicked(evt);
            }
        });
        jPanelRadioButtons.add(jRadioButtonTipoB);

        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 19;
        gridBagConstraints.anchor = java.awt.GridBagConstraints.WEST;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        jPanelCuerpo.add(jPanelRadioButtons, gridBagConstraints);

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
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 757, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCancelarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_jButtonCancelarActionPerformed

    private void jButtonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAceptarActionPerformed
        // TODO add your handling code here:
        String DNI, nombre, alias, email, direccion, localidad, codigoPostal, estado, saldo, tipo;

        DNI = jTextFieldDNI.getText().trim();
        nombre = jTextFieldNombre.getText().trim();
        alias = jTextFieldAlias.getText().trim();
        if (alias.equals(PLACEHOLDER_ALIAS_CLIENTE)) {
            alias = VACIO; // Sustituir el placeholder por cadena vacía
        }
        email = jTextFieldEmail.getText().trim();
        if (email.equals(PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL)) {
            email = VACIO;
        }
        direccion = jTextFieldDireccion.getText().trim();
        localidad = jTextFieldLocalidad.getText().trim();
        codigoPostal = jTextFieldCodigoPostal.getText().trim();
        estado = AL_DIA;
        saldo = "0,00";
        tipo = jRadioButtonTipoA.isSelected() ? TIPOA : TIPOB;

        if (!esEdicion) {
            clienteOriginal = new Cliente(
                    DNI,
                    nombre,
                    alias,
                    email,
                    direccion,
                    localidad,
                    codigoPostal,
                    estado,
                    saldo,
                    tipo
            );

            boolean clienteRepetido = clienteControlador.clienteRepetido(clienteOriginal);
            if (clienteRepetido) {
                mostrarError(parent, TITULO_CLIENTE_REPETIDO, MENSAJE_CLIENTE_REPETIDO);
                return;
            }
            clienteControlador.crear(clienteOriginal);

        } else {
            clienteModificado.setNombre(nombre);
            clienteModificado.setAlias(alias);
            clienteModificado.setEmail(email);
            clienteModificado.setDireccion(direccion);
            clienteModificado.setLocalidad(localidad);
            clienteModificado.setCodigoPostal(codigoPostal);
            clienteModificado.setTipo(tipo);

            //if (!clienteOriginal.equals(clienteModificado)) {
                clienteControlador.actualizar(clienteOriginal, clienteModificado);
            //}

        }

        dispose();
    }//GEN-LAST:event_jButtonAceptarActionPerformed

    private void jTextFieldDNIFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDNIFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldDNI, PLACEHOLDER_DNI_CLIENTE);
    }//GEN-LAST:event_jTextFieldDNIFocusLost

    private void jTextFieldDNIFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDNIFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldDNI, PLACEHOLDER_DNI_CLIENTE);
    }//GEN-LAST:event_jTextFieldDNIFocusGained

    private void jTextFieldDNIKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDNIKeyReleased
        // TODO add your handling code here:
        campoDNICorrecto = validarCampoFormulario(
                jTextFieldDNI,
                jLabelAdvertenciaDNI,
                ERROR_DNI_CLIENTE,
                originalBorder,
                texto -> !texto.isEmpty() && texto.matches(VALIDACION_DNI_CIF_CLIENTE)
        );
        habilitarBotonAceptar();
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
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldNombreKeyReleased

    private void jTextFieldAliasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAliasFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldAlias, PLACEHOLDER_ALIAS_CLIENTE);
    }//GEN-LAST:event_jTextFieldAliasFocusGained

    private void jTextFieldAliasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldAliasFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldAlias, PLACEHOLDER_ALIAS_CLIENTE);
    }//GEN-LAST:event_jTextFieldAliasFocusLost

    private void jTextFieldEmailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusGained
        // TODO add your handling code here:
        quitarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL);
    }//GEN-LAST:event_jTextFieldEmailFocusGained

    private void jTextFieldEmailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmailFocusLost
        // TODO add your handling code here:
        agregarPlaceHolder(jTextFieldEmail, PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL);
    }//GEN-LAST:event_jTextFieldEmailFocusLost

    private void jTextFieldEmailKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmailKeyReleased
        // TODO add your handling code here:
        campoEmailCorrecto = validarCampoFormulario(jTextFieldEmail,
                jLabelAdvertenciaEmail,
                ERROR_EMAIL_CLIENTE,
                originalBorder,
                texto -> texto.isEmpty() || texto.matches(VALIDACION_EMAIL_CLIENTE) || texto.equals(PLACEHOLDER_EMAIL_CLIENTE_OPCIONAL)
        );
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldEmailKeyReleased

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
        habilitarBotonAceptar();
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
        habilitarBotonAceptar();
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
        habilitarBotonAceptar();
    }//GEN-LAST:event_jTextFieldCodigoPostalKeyReleased

    private void jRadioButtonTipoAMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonTipoAMouseClicked
        // TODO add your handling code here:
        habilitarBotonAceptar();
    }//GEN-LAST:event_jRadioButtonTipoAMouseClicked

    private void jRadioButtonTipoBMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jRadioButtonTipoBMouseClicked
        // TODO add your handling code here:
        habilitarBotonAceptar();
    }//GEN-LAST:event_jRadioButtonTipoBMouseClicked

    private void habilitarBotonAceptar() {
        if (campoDNICorrecto) {
            if (campoNombreCorrecto) {
                if (campoEmailCorrecto) {
                    if (!jTextFieldDireccion.getText().trim().equals(PLACEHOLDER_DIRECCION_CLIENTE) && !jTextFieldDireccion.getText().trim().isEmpty()) {
                        if (!jTextFieldLocalidad.getText().trim().equals(PLACEHOLDER_LOCALIDAD_CLIENTE) && !jTextFieldLocalidad.getText().trim().isEmpty()) {
                            if (campoCodigoPostalCorrecto) {
                                if (jRadioButtonTipoA.isSelected() || jRadioButtonTipoB.isSelected()) {
                                    jButtonAceptar.setEnabled(true);
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        }
        jButtonAceptar.setEnabled(false);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupTipo;
    private javax.swing.JButton jButtonAceptar;
    private javax.swing.JButton jButtonCancelar;
    private javax.swing.JLabel jLabelAdvertenciaCodigoPostal;
    private javax.swing.JLabel jLabelAdvertenciaDNI;
    private javax.swing.JLabel jLabelAdvertenciaEmail;
    private javax.swing.JLabel jLabelAdvertenciaNombre;
    private javax.swing.JLabel jLabelAlias;
    private javax.swing.JLabel jLabelCodigoPostal;
    private javax.swing.JLabel jLabelDNI;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelEmail;
    private javax.swing.JLabel jLabelLocalidad;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelTipo;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JPanel jPanelCabecera;
    private javax.swing.JPanel jPanelCuerpo;
    private javax.swing.JPanel jPanelPiePagina;
    private javax.swing.JPanel jPanelPrincipal;
    private javax.swing.JPanel jPanelRadioButtons;
    private javax.swing.JRadioButton jRadioButtonTipoA;
    private javax.swing.JRadioButton jRadioButtonTipoB;
    private javax.swing.JTextField jTextFieldAlias;
    private javax.swing.JTextField jTextFieldCodigoPostal;
    private javax.swing.JTextField jTextFieldDNI;
    private javax.swing.JTextField jTextFieldDireccion;
    private javax.swing.JTextField jTextFieldEmail;
    private javax.swing.JTextField jTextFieldLocalidad;
    private javax.swing.JTextField jTextFieldNombre;
    // End of variables declaration//GEN-END:variables

}

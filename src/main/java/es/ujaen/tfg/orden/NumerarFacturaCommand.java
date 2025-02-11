/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.orden;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.utils.Utils;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jota
 */
public class NumerarFacturaCommand implements Command {

    private final FacturaDAO facturaDAO;
    private final Factura facturaOriginal;
    private final Factura facturaModificada;
    private final ClienteDAO clienteDAO;
    private final Cliente clienteOriginal;
    private final Cliente clienteModificado;
    private final AnticipoDAO anticipoDAO;
    private final Anticipo anticipoOriginal;
    private final Anticipo anticipoModificado;
    private final StringWriter writer;
    private File pdfFile;
    private static String ultimaRutaGuardado = null; // Recordar última carpeta

    public NumerarFacturaCommand(
            FacturaDAO facturaDAO,
            Factura facturaOriginal,
            Factura facturaModificada,
            ClienteDAO clienteDAO,
            Cliente clienteOriginal,
            Cliente clienteModificado,
            AnticipoDAO anticipoDAO,
            Anticipo anticipoOriginal,
            Anticipo anticipoModificado,
            StringWriter writer
    ) {
        this.facturaDAO = facturaDAO;
        this.facturaOriginal = facturaOriginal;
        this.facturaModificada = facturaModificada;
        this.clienteDAO = clienteDAO;
        this.clienteOriginal = clienteOriginal;
        this.clienteModificado = clienteModificado;
        this.anticipoDAO = anticipoDAO;
        this.anticipoOriginal = anticipoOriginal;
        this.anticipoModificado = anticipoModificado;
        this.writer = writer;
    }

    @Override
    public void execute() {
        
        facturaDAO.actualizar(facturaModificada);
        clienteDAO.actualizar(clienteModificado);
        anticipoDAO.actualizar(anticipoModificado);
        try {
            escribirPDF(facturaModificada, writer);
        } catch (IOException ex) {
            Logger.getLogger(CrearFacturaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo() {
        
        facturaDAO.actualizar(facturaOriginal);
        clienteDAO.actualizar(clienteOriginal);
        anticipoDAO.actualizar(anticipoOriginal);
        try {
            if (pdfFile != null) {
                borrarPDF(pdfFile);
            }
        } catch (IOException ex) {
            Logger.getLogger(CrearFacturaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribirPDF(Factura f, StringWriter writer) throws IOException {
        // Crear el HTML dinámico temporal
        Path htmlPath = Paths.get("factura-" + f.getId() + ".html");
        Files.write(htmlPath, writer.toString().getBytes(StandardCharsets.UTF_8));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Factura PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        // Si es la primera vez, abrir en Documentos. Si no, usar la última carpeta usada.
        if (ultimaRutaGuardado != null) {
            fileChooser.setCurrentDirectory(new File(ultimaRutaGuardado));
        } else {
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents";
            fileChooser.setCurrentDirectory(new File(rutaDocumentos));
        }

        fileChooser.setSelectedFile(new File("Factura-" + f.getId() + ".pdf"));

        File fileToSave = null;
        int userSelection = fileChooser.showSaveDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            fileToSave = fileChooser.getSelectedFile();

            // Asegurar que tenga extensión .pdf
            if (!fileToSave.getAbsolutePath().endsWith(".pdf")) {
                fileToSave = new File(fileToSave.getAbsolutePath() + ".pdf");
            }

            // Guardar la última ruta usada
            ultimaRutaGuardado = fileToSave.getParent();
        } else {
            // Si el usuario cierra la ventana, se guarda en la carpeta predeterminada
            String defaultPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Facturas";
            new File(defaultPath).mkdirs();  // Crear la carpeta si no existe
            fileToSave = new File(defaultPath + File.separator + "Factura-" + f.getId() + ".pdf");

            JOptionPane.showMessageDialog(null,
                    "No seleccionaste una ubicación. La factura se ha guardado en: \n" + fileToSave.getAbsolutePath(),
                    "Factura Guardada", JOptionPane.INFORMATION_MESSAGE);
        }

        pdfFile = fileToSave;

        // Convertir el HTML a PDF
        try (OutputStream os = new FileOutputStream(pdfFile)) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withUri(htmlPath.toUri().toString());
            builder.toStream(os);
            builder.run();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Borrar el HTML temporal
        Files.delete(htmlPath);
    }

    public void borrarPDF(File pdfFile) throws IOException {
        if (pdfFile != null && pdfFile.exists()) {
            Files.delete(pdfFile.toPath());
        }
    }

}

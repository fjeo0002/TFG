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
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jota
 */
public class CrearAnticipoCommand implements Command {

    private final AnticipoDAO anticipoDAO;
    private final Anticipo anticipo;
    private final ClienteDAO clienteDAO;
    private final Cliente clienteOriginal;
    private final Cliente clienteModificado;
    private final FacturaDAO facturaDAO;
    private final List<Factura> facturasACrear;
    private final StringWriter writer;
    private File pdfFile;
    private static String ultimaRutaGuardado = null; // Recordar última carpeta

    public CrearAnticipoCommand(
            AnticipoDAO anticipoDAO,
            Anticipo anticipo,
            ClienteDAO clienteDAO,
            Cliente clienteOriginal,
            Cliente clienteModificado,
            FacturaDAO facturaDAO,
            List<Factura> facturasACrear,
            StringWriter writer
    ) {
        this.anticipoDAO = anticipoDAO;
        this.anticipo = anticipo;
        this.clienteDAO = clienteDAO;
        this.clienteOriginal = clienteOriginal;
        this.clienteModificado = clienteModificado;
        this.facturaDAO = facturaDAO;
        this.facturasACrear = facturasACrear;
        this.writer = writer;
    }

    @Override
    public void execute() {
        anticipoDAO.crear(anticipo);
        clienteDAO.actualizar(clienteModificado);
        for (Factura f : facturasACrear) {
            facturaDAO.crear(f);
        }
        try {
            escribirPDF(anticipo, writer);
        } catch (IOException ex) {
            Logger.getLogger(CrearFacturaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void undo() {
        anticipoDAO.borrar(anticipo);
        clienteDAO.actualizar(clienteOriginal);
        for (Factura f : facturasACrear) {
            facturaDAO.borrar(f);
        }
        try {
            if (pdfFile != null) {
                borrarPDF(pdfFile);
            }
        } catch (IOException ex) {
            Logger.getLogger(CrearFacturaCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void escribirPDF(Anticipo a, StringWriter writer) throws IOException {
        // Crear el HTML dinámico temporal
        Path htmlPath = Paths.get("anticipo-" + a.getFecha() + "-" + a.getClienteDNI() + ".html");
        Files.write(htmlPath, writer.toString().getBytes(StandardCharsets.UTF_8));

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar Anticipo PDF");
        fileChooser.setFileFilter(new FileNameExtensionFilter("PDF Files", "pdf"));

        // Si es la primera vez, abrir en Documentos. Si no, usar la última carpeta usada.
        if (ultimaRutaGuardado != null) {
            fileChooser.setCurrentDirectory(new File(ultimaRutaGuardado));
        } else {
            String rutaDocumentos = System.getProperty("user.home") + File.separator + "Documents";
            fileChooser.setCurrentDirectory(new File(rutaDocumentos));
        }

        fileChooser.setSelectedFile(new File("Anticipo-" + a.getFecha() + "-" + a.getClienteDNI() + ".pdf"));

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
            String defaultPath = System.getProperty("user.home") + File.separator + "Documents" + File.separator + "Anticipos";
            new File(defaultPath).mkdirs();  // Crear la carpeta si no existe
            fileToSave = new File(defaultPath + File.separator + "Anticipo-" + a.getFecha() + "-" + a.getClienteDNI() + ".pdf");

            JOptionPane.showMessageDialog(null, 
                "No seleccionaste una ubicación. El anticipo se ha guardado en: \n" + fileToSave.getAbsolutePath(), 
                "Anticipo Guardado", JOptionPane.INFORMATION_MESSAGE);
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

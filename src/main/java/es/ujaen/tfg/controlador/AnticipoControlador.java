/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import com.github.mustachejava.DefaultMustacheFactory;
import com.github.mustachejava.Mustache;
import com.github.mustachejava.MustacheFactory;
import es.ujaen.tfg.DAO.AnticipoDAO;
import es.ujaen.tfg.DAO.ClienteDAO;
import es.ujaen.tfg.DAO.FacturaDAO;
import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.modelo.Usuario;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.BorrarAnticipoCommand;
import es.ujaen.tfg.orden.Command;
import es.ujaen.tfg.orden.CrearAnticipoCommand;
import es.ujaen.tfg.orden.UndoManager;
import es.ujaen.tfg.utils.Utils;
import es.ujaen.tfg.utils.Utils.Mes;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jota
 */
public class AnticipoControlador implements Observable {

    private final List<Observador> observadores;
    private final AnticipoDAO anticipoDAO;
    private final ClienteDAO clienteDAO;
    private final FacturaDAO facturaDAO;
    private final UndoManager undoManager;

    public AnticipoControlador(ClienteDAO clienteDAO, AnticipoDAO anticipoDAO, FacturaDAO facturaDAO) throws IOException {
        this.anticipoDAO = anticipoDAO;
        this.observadores = new ArrayList<>();
        this.clienteDAO = clienteDAO;
        this.facturaDAO = facturaDAO;
        this.undoManager = UndoManager.getInstance();
    }

    @Override
    public void agregarObservador(Observador o) {
        observadores.add(o);
    }

    @Override
    public void eliminarObservador(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notificarObservadores() {
        for (Observador o : observadores) {
            o.actualizar();
        }
    }

    public boolean crear(Anticipo anticipo, Cliente clienteOriginal, Cliente clienteModificado, List<Factura> facturasACrear, Usuario usuario) {
        //anticipoDAO.crear(anticipo);
        StringWriter writer = generarAnticipoPDF(anticipo, usuario);
        
        Command crearAnticipo = new CrearAnticipoCommand(anticipoDAO, anticipo, 
                clienteDAO, clienteOriginal, clienteModificado, 
                facturaDAO, facturasACrear, writer);
        undoManager.execute(crearAnticipo);
        notificarObservadores();
        return true;

    }

    public Anticipo leer(String ID) {
        return anticipoDAO.leer(ID);
    }

    public boolean actualizar(Anticipo anticipo) {
        anticipoDAO.actualizar(anticipo);
        notificarObservadores();
        return true;

    }

    public boolean borrar(Anticipo anticipo, Cliente clienteOriginal, Cliente clienteModificado, List<Factura> facturasAnticipadas) {
        //anticipoDAO.borrar(anticipo);
        Command borrarAnticipo = new BorrarAnticipoCommand(anticipoDAO, anticipo, clienteDAO, clienteOriginal, clienteModificado, facturaDAO, facturasAnticipadas);
        undoManager.execute(borrarAnticipo);
        notificarObservadores();
        return true;
    }

    public List<Anticipo> leerTodos() {
        //return anticipoDAO.leerTodos();
        List<Anticipo> anticipos = anticipoDAO.leerTodos();
        if (anticipos != null) {
            return anticipos.stream()
                    .sorted((a1, a2) -> a2.getFecha().compareTo(a1.getFecha()))
                    .toList();
        }
        return null;
    }

    public StringWriter generarAnticipoPDF(Anticipo a, Usuario u) {
        StringWriter writer = new StringWriter();
        try {
            // Cargar la plantilla HTML
            String plantilla = Files.readString(Paths.get("anticipo_template.html"), java.nio.charset.StandardCharsets.UTF_8);

            // Datos del anticipo
            Map<String, Object> datos = new HashMap<>();
            datos.put("titulo", "ANTICIPO");

            // Datos de la Empresa
            Map<String, String> empresa = new HashMap<>();
            empresa.put("nombre", u.getNombre());
            empresa.put("direccion", u.getDireccion());
            empresa.put("codigoPostal", u.getCodigoPostal());
            empresa.put("localidad", u.getLocalidad());
            empresa.put("telefono", u.getTelefono());
            empresa.put("email", u.getEmail());
            empresa.put("dni", u.getDNI());
            datos.put("empresa", empresa);

            // Datos del Cliente
            String DNI = a.getClienteDNI();
            Cliente c = clienteDAO.leer(DNI);
            Map<String, String> cliente = new HashMap<>();
            cliente.put("nombre", c.getNombre());
            cliente.put("direccion", c.getDireccion());
            cliente.put("codigoPostal", c.getCodigoPostal());
            cliente.put("localidad", c.getLocalidad());
            cliente.put("cif", c.getDNI());
            datos.put("cliente", cliente);

            // Datos del Anticipo
            
            int dia = a.getFecha().getDayOfMonth();
            Mes mes = Mes.porNumero(a.getFecha().getMonthValue());
            int anio = a.getFecha().getYear();
            
            Map<String, Object> anticipo = new HashMap<>();
            anticipo.put("fecha", a.getFechaString());
            anticipo.put("dia", Utils.convertirEnteroAString(dia));
            anticipo.put("mes", mes.getNombre());
            anticipo.put("anio", anio);
            anticipo.put("monto", Utils.convertirDoubleAString(a.getMonto()));
            anticipo.put("mesesCubiertos", a.getMesesCubiertos());
            datos.put("anticipo", anticipo);

            // Procesar la plantilla con Mustache
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile(new StringReader(plantilla), "anticipo");
            mustache.execute(writer, datos).flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

        return writer;
    }

    public boolean anticipoRepetido(Anticipo a) {
        List<Anticipo> anticipos = leerTodos();
        if (anticipos != null) {
            for (Anticipo anticipo : anticipos) {
                if (a.equals(anticipo)) {
                    return true;
                }
            }
        }
        return false;
    }

    public Anticipo anticipoActivo(String clienteDNI) {
        List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);
        if (anticiposCliente != null) {
            if (!anticiposCliente.isEmpty()) {
                for (Anticipo anticipo : anticiposCliente) {
                    if (anticipo.getSaldo() > 0.0) {
                        return anticipo;
                    }
                }
            }
        }
        return null;
    }

    public boolean anticipoSolapado(Anticipo a) {
        // Obtener datos del nuevo anticipo
        int mesesCubiertosNuevo = a.getMesesCubiertos();
        LocalDate fechaInicioNuevo = a.getFecha();
        LocalDate fechaFinNuevo = fechaInicioNuevo.plusMonths(mesesCubiertosNuevo - 1); // Ajustar rango al último mes incluido

        YearMonth anioMesInicioNuevo = YearMonth.from(fechaInicioNuevo);
        YearMonth anioMesFinNuevo = YearMonth.from(fechaFinNuevo);

        // Obtener anticipos existentes del cliente
        String clienteDNI = a.getClienteDNI();
        List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);

        // Comprobar si existe algún solapamiento
        if (anticiposCliente != null && !anticiposCliente.isEmpty()) {
            for (Anticipo anticipo : anticiposCliente) {
                // Obtener datos del anticipo existente
                int mesesCubiertosExistente = anticipo.getMesesCubiertos();
                LocalDate fechaInicioExistente = anticipo.getFecha();
                LocalDate fechaFinExistente = fechaInicioExistente.plusMonths(mesesCubiertosExistente - 1);

                YearMonth anioMesInicioExistente = YearMonth.from(fechaInicioExistente);
                YearMonth anioMesFinExistente = YearMonth.from(fechaFinExistente);

                // Comprobar solapamiento entre rangos
                if (!(anioMesFinNuevo.isBefore(anioMesInicioExistente) || anioMesInicioNuevo.isAfter(anioMesFinExistente))) {
                    return true; // Hay solapamiento
                }
            }
        }
        return false; // No hay solapamiento
    }

    public List<Anticipo> anticiposCliente(String clienteDNI) {
        List<Anticipo> anticipos = leerTodos();
        if (anticipos != null) {
            List<Anticipo> anticiposCliente = new ArrayList<>();
            for (Anticipo factura : anticipos) {
                if (factura.getClienteDNI().equals(clienteDNI)) {
                    anticiposCliente.add(factura);
                }
            }
            return anticiposCliente;
        }
        return null;
    }
    /*
    public boolean borrarAnticiposCliente(String clienteDNI) {
        List<Anticipo> anticiposCliente = anticiposCliente(clienteDNI);
        if (anticiposCliente != null) {
            for (Anticipo anticipo : anticiposCliente) {
                borrar(anticipo);
            }
            return true;
        }
        return false;
    }
     */
}

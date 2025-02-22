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
import es.ujaen.tfg.modelo.Local;
import es.ujaen.tfg.modelo.Usuario;
import es.ujaen.tfg.observer.Observable;
import es.ujaen.tfg.observer.Observador;
import es.ujaen.tfg.orden.Command;
import es.ujaen.tfg.orden.CrearFacturaCommand;
import es.ujaen.tfg.orden.ModificarFacturaCommand;
import es.ujaen.tfg.orden.NumerarFacturaCommand;
import es.ujaen.tfg.orden.SobreescribirFacturaCommand;
import es.ujaen.tfg.orden.UndoManager;
import es.ujaen.tfg.utils.Utils;
import es.ujaen.tfg.utils.Utils.Mes;
import static es.ujaen.tfg.utils.Utils.TIPOA;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author jota
 */
public class FacturaControlador implements Observable {

    private final List<Observador> observadores;
    private final FacturaDAO facturaDAO;
    private final ClienteDAO clienteDAO;
    private final AnticipoDAO anticipoDAO;
    private final UndoManager undoManager;

    public FacturaControlador(ClienteDAO clienteDAO, AnticipoDAO anticipoDAO, FacturaDAO facturaDAO) throws IOException {
        this.clienteDAO = clienteDAO;
        this.facturaDAO = facturaDAO;
        this.anticipoDAO = anticipoDAO;
        this.observadores = new ArrayList<>();
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

    public boolean crear(Factura factura, boolean facturaAbono, List<Local> locales, List<Integer> cantidades, int IVA, int retencion, Usuario usuario) {
        //facturaDAO.crear(factura);
        // 1º Habrá que generar el PDF para conseguir la ruta
        StringWriter writer = generarFacturaPDF(factura, facturaAbono, locales, cantidades, IVA, retencion, usuario);
        // 2º Modificar el Command para que, si se crea, CREAR el PDF y si se Deshace, borrarlo
        Command crearFactura = new CrearFacturaCommand(facturaDAO, factura, writer);
        undoManager.execute(crearFactura);
        notificarObservadores();
        return true;
    }

    public Factura leer(String id) {
        return facturaDAO.leer(id);
    }

    public boolean numerar(Factura facturaOriginal, Factura facturaModificada,
            Cliente clienteOriginal, Cliente clienteModificado,
            Anticipo anticipoOriginal, Anticipo anticipoModificado,
            boolean facturaAbono, List<Local> locales, List<Integer> cantidades, int IVA, int retencion,
            Usuario usuario
    ) {
        StringWriter writer = generarFacturaPDF(facturaModificada, facturaAbono, locales, cantidades, IVA, retencion, usuario);
        //facturaDAO.numerar(factura);
        Command numerarFactura = new NumerarFacturaCommand(facturaDAO, facturaOriginal, facturaModificada,
                clienteDAO, clienteOriginal, clienteModificado,
                anticipoDAO, anticipoOriginal, anticipoModificado,
                writer
        );
        undoManager.execute(numerarFactura);
        notificarObservadores();
        return true;

    }

    public boolean actualizar(Factura facturaOriginal, Factura facturaModificada, Cliente clienteOriginal, Cliente clienteModificado) {
        //facturaDAO.numerar(factura);
        Command actualizarFactura = new ModificarFacturaCommand(
                facturaDAO, facturaOriginal, facturaModificada,
                clienteDAO, clienteOriginal, clienteModificado
        );
        undoManager.execute(actualizarFactura);
        notificarObservadores();
        return true;

    }

    public boolean actualizar(Factura factura) {
        facturaDAO.actualizar(factura);
        notificarObservadores();
        return true;

    }

    public boolean sobreescribir(Factura facturaOriginal, Factura facturaModificada,
            boolean facturaAbono, List<Local> locales, List<Integer> cantidades, int IVA, int retencion,
            Usuario usuario
    ) {
        StringWriter writer = generarFacturaPDF(facturaModificada, facturaAbono, locales, cantidades, IVA, retencion, usuario);
        Command sobreescribirFactura = new SobreescribirFacturaCommand(
                facturaDAO, facturaOriginal, facturaModificada,
                writer
        );
        undoManager.execute(sobreescribirFactura);
        notificarObservadores();
        return true;
    }

    public boolean borrar(Factura factura) {
        facturaDAO.borrar(factura);
        notificarObservadores();
        return true;
    }

    public List<Factura> leerTodos() {
        return facturaDAO.leerTodos();
    }

    public StringWriter generarFacturaPDF(Factura f, boolean facturaAbono, List<Local> locales, List<Integer> cantidades, int IVA, int retencion, Usuario u) {
        StringWriter writer = new StringWriter();
        try {
            // Cargar la plantilla HTML
            // Cargar la plantilla HTML
            String plantilla = "";//Files.readString(Paths.get("/archivo/anticipo_template.html"), java.nio.charset.StandardCharsets.UTF_8);

            try {
                InputStream inputStream = getClass().getResourceAsStream("/archivo/factura_template.html");
                if (inputStream == null) {
                    throw new IOException("No se encontró el archivo de plantilla dentro del JAR: " + "/archivo/factura_template.html");
                }

                // Convertir el InputStream a String usando un Buffer
                plantilla = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

            } catch (IOException e) {
                System.err.println("Error leyendo la plantilla: " + e.getMessage());
                return null;
            }
            // Datos de la factura
            Map<String, Object> datos = new HashMap<>();

            if (facturaAbono) {
                datos.put("titulo", "FACTURA DE ABONO");
            } else {
                datos.put("titulo", "FACTURA");
            }
            // Datos de Empresa
            Map<String, String> empresa = new HashMap<>();
            empresa.put("nombre", u.getNombre());
            empresa.put("direccion", u.getDireccion());
            empresa.put("codigoPostal", u.getCodigoPostal());
            empresa.put("localidad", u.getLocalidad());
            empresa.put("telefono", u.getTelefono());
            empresa.put("email", u.getEmail());
            empresa.put("dni", u.getDNI());
            datos.put("empresa", empresa);

            String DNI = f.getClienteDNI();
            Cliente c = clienteDAO.leer(DNI);
            // Datos del Cliente
            Map<String, String> cliente = new HashMap<>();
            cliente.put("nombre", c.getNombre());
            cliente.put("direccion", c.getDireccion());
            cliente.put("codigoPostal", c.getCodigoPostal());
            cliente.put("localidad", c.getLocalidad());
            cliente.put("cif", c.getDNI());
            datos.put("cliente", cliente);

            // Datos de la Factura
            Map<String, Object> factura = new HashMap<>();
            factura.put("numero", c.getTipo() + "/" + f.getNumero());
            factura.put("fecha", f.getFechaString());
            factura.put("fechaValor", f.getFechaString());

            // Lista de Artículos
            List<Map<String, Object>> articulos = new ArrayList<>();

            double IVADouble = IVA / 100.0 + 1.0;
            double retencionDouble = retencion / 100.0 + 1.0;

            double baseImponible = 0;
            double importeIVA = 0;
            double importeRetencion = 0;
            double total = 0;

            for (int i = 0; i < locales.size(); i++) {
                Local l = locales.get(i);
                int cantidad = cantidades.get(i);
                double precio = l.getPrecio();
                double subtotal = 0.0;
                double sumarIVA = precio * IVADouble;
                double restarRetencion = precio * retencionDouble;

                // Calculamos Subtotal según el tipo de Cliente
                if (TIPOA.equals(c.getTipo())) {
                    subtotal = (sumarIVA - restarRetencion + precio) * cantidad;
                } else {
                    subtotal = l.getPrecio() * cantidad;
                }

                total += subtotal;
                baseImponible += precio;
                
                if (TIPOA.equals(c.getTipo())) {
                    importeIVA = sumarIVA;
                    importeRetencion = restarRetencion;
                } else {
                    IVA = 0;
                    retencion = 0;
                    importeIVA = 0;
                    importeRetencion = 0;
                }

                articulos.add(Map.of("cantidad", cantidad,
                        "descripcion", l.getNombre(),
                        "precio", l.getPrecioString(),
                        "iva", IVA,
                        "retencion", retencion,
                        "subtotal", Utils.convertirDoubleAString(subtotal))
                );
            }

            factura.put("articulos", articulos);

            // Totales
            factura.put("baseImponible", Utils.convertirDoubleAString(baseImponible));
            factura.put("importeIVA", Utils.convertirDoubleAString(importeIVA));
            factura.put("importeRetencion", Utils.convertirDoubleAString(importeRetencion));

            if (facturaAbono) {
                factura.put("total", Utils.convertirDoubleAString(-total));
            } else {
                factura.put("total", Utils.convertirDoubleAString(total));
            }

            datos.put("factura", factura);

            // Procesar la plantilla con Mustache
            MustacheFactory mf = new DefaultMustacheFactory();
            Mustache mustache = mf.compile(new StringReader(plantilla), "factura");
            //StringWriter writer = new StringWriter();
            mustache.execute(writer, datos).flush();

        } catch (IOException e) {
        }

        return writer;
    }

    public String generarIdFactura(String letra, int numero, LocalDate fecha, String clienteDNI) {
        // Crear una clave única combinando los campos 'letra', 'numero', 'fecha' y 'DNI'
        int numeroMes = fecha.getMonthValue();
        Mes mes = Mes.porNumero(numeroMes);
        String nombreMes = mes.getNombre();

        int anio = fecha.getYear();

        //String txt = letra + "-" + anio + "-" + nombreMes + "-" + numero + "-" + clienteDNI;
        String txt = letra + "-" + anio + "-" + nombreMes + "-" + clienteDNI;
        return txt;
    }

    public boolean facturaRepetida(Factura f) {
        // La factura no está repetida en caso de que el cliente no tenga factura en ese mes y anio
        String clienteDNI = f.getClienteDNI();
        int mes = f.getFecha().getMonthValue();
        int anio = f.getFecha().getYear();
        Factura facturaClienteMesAnio = facturaClienteMesAnio(clienteDNI, mes, anio);

        if (facturaClienteMesAnio != null) {
            // ¡Ojo! si la factura está "Repetida" por ser un Anticipo (Numero = 0) !No Cuenta!
            if (facturaClienteMesAnio.getNumero() != 0) {
                return true;
            }
        }
        return false;
    }

    public List<Factura> facturasCliente(String clienteDNI) {
        List<Factura> facturas = leerTodos();
        if (facturas != null) {
            List<Factura> facturasCliente = new ArrayList<>();
            for (Factura factura : facturas) {
                if (factura.getClienteDNI().equals(clienteDNI)) {
                    facturasCliente.add(factura);
                }
            }
            return facturasCliente;
        }
        return null;
    }

    public Factura facturaClienteMesAnio(String clienteDNI, int mes, int anio) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            for (Factura factura : facturasCliente) {
                int m = factura.getFecha().getMonthValue();
                int a = factura.getFecha().getYear();
                if (a == anio && m == mes) {
                    return factura;
                }
            }
        }
        return null;
    }

    public List<Factura> facturasLetra(String letra) {
        List<Factura> facturas = leerTodos();
        if (facturas != null) {
            List<Factura> facturasLetra = new ArrayList<>();
            for (Factura factura : facturas) {
                if (factura.getLetra().equals(letra)) {
                    facturasLetra.add(factura);
                }
            }
            return facturasLetra;
        }
        return null;
    }

    public List<Factura> facturasLetraAnio(String letra, LocalDate fecha) {
        List<Factura> facturasLetra = facturasLetra(letra);
        if (facturasLetra != null) {
            List<Factura> facturasLetraAnio = new ArrayList<>();
            for (Factura factura : facturasLetra) {
                if (factura.getFecha().getYear() == fecha.getYear()) {
                    facturasLetraAnio.add(factura);
                }
            }
            return facturasLetraAnio;
        }
        return null;
    }

    public int siguienteNumeroFacturaLetraAnio(String letra, LocalDate fecha) {
        int ultimoNumeroFactura = 0;
        List<Factura> facturasLetraAnio = facturasLetraAnio(letra, fecha);
        if (facturasLetraAnio != null) {
            if (!facturasLetraAnio.isEmpty()) {
                for (Factura factura : facturasLetraAnio) {
                    if (factura.getNumero() > ultimoNumeroFactura) {
                        ultimoNumeroFactura = factura.getNumero();
                    }
                }
            }
        }
        ultimoNumeroFactura++;
        return ultimoNumeroFactura;
    }

    public List<Factura> facturasNoNumeradasCliente(String clienteDNI) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            if (!facturasCliente.isEmpty()) {
                List<Factura> facturasNoNumeradasCliente = new ArrayList<>();
                for (Factura factura : facturasCliente) {
                    if (factura.getNumero() == 0) {
                        facturasNoNumeradasCliente.add(factura);
                    }
                }
                return facturasNoNumeradasCliente;
            }
        }
        return null;
    }

    public double saldoFacturasNoNumeradas(String clienteDNI) {
        List<Factura> facturasNoNumeradasCliente = facturasNoNumeradasCliente(clienteDNI);
        if (facturasNoNumeradasCliente != null) {
            if (!facturasNoNumeradasCliente.isEmpty()) {
                double saldo = 0.0;
                for (Factura f : facturasNoNumeradasCliente) {
                    saldo += f.getMonto();
                }
                return saldo;
            }
        }
        return 0.0;
    }

    public List<Factura> facturasNoPagadasCliente(String clienteDNI) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            if (!facturasCliente.isEmpty()) {
                List<Factura> facturasNoPagadasCliente = new ArrayList<>();
                for (Factura f : facturasCliente) {
                    if (f.getPagado() == false) {
                        facturasNoPagadasCliente.add(f);
                    }
                }
                if (!facturasNoPagadasCliente.isEmpty()) {
                    return facturasNoPagadasCliente;
                }
            }
        }
        return null;
    }

    public double saldoFacturasNoPagadasCliente(String clienteDNI) {
        List<Factura> facturasNoPagadasCliente = facturasNoPagadasCliente(clienteDNI);
        if (facturasNoPagadasCliente != null) {
            if (!facturasNoPagadasCliente.isEmpty()) {
                double saldo = 0.0;
                for (Factura f : facturasNoPagadasCliente) {
                    saldo += f.getMonto();
                }
                return saldo;
            }
        }
        return 0.0;
    }

    public boolean borrarFacturasNoNumeradasCliente(String clienteDNI) {
        List<Factura> facturasNoNumeradasCliente = facturasNoNumeradasCliente(clienteDNI);
        if (facturasNoNumeradasCliente != null) {
            if (!facturasNoNumeradasCliente.isEmpty()) {
                for (Factura factura : facturasNoNumeradasCliente) {
                    borrar(factura);
                }
                return true;
            }
        }
        return false;
    }

    public boolean borrarFacturasCliente(String clienteDNI) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            if (!facturasCliente.isEmpty()) {
                for (Factura factura : facturasCliente) {
                    borrar(factura);
                }
                return true;
            }
        }
        return false;
    }

    public Factura ultimaFacturaCliente(String clienteDNI) {
        List<Factura> facturasCliente = facturasCliente(clienteDNI);
        if (facturasCliente != null) {
            if (!facturasCliente.isEmpty()) {
                int size = facturasCliente.size() - 1;
                Factura ultimaFactura = facturasCliente.get(size); // Empiezo por la última
                for (Factura factura : facturasCliente) {
                    LocalDate fechaUltimaFactura = ultimaFactura.getFecha();
                    LocalDate fechaFactura = factura.getFecha();
                    if (fechaFactura.isAfter(fechaUltimaFactura)) {
                        ultimaFactura = factura;
                    }
                }
                return ultimaFactura;
            }
        }
        return null;
    }

    public Factura primeraFacturaAnticipoActivoCliente(String clienteDNI) {
        List<Factura> facturasNoNumeradasCliente = facturasNoNumeradasCliente(clienteDNI);
        if (facturasNoNumeradasCliente != null) {
            if (!facturasNoNumeradasCliente.isEmpty()) {
                Factura factura = facturasNoNumeradasCliente.remove(0);
                return factura;
            }
        }
        return null;
    }

    public boolean facturaContigua(Factura factura) {
        String clienteDNI = factura.getClienteDNI();
        Factura ultimaFacturaCliente = ultimaFacturaCliente(clienteDNI);
        if (ultimaFacturaCliente != null) {
            boolean facturado = ultimaFacturaCliente.getFacturado();
            // El cliente no tiene anticipos activos y se mira la ultimaFacturaCliente
            if (facturado) {
                LocalDate fechaUltimaFacturaCliente = ultimaFacturaCliente.getFecha();
                LocalDate fechaFactura = factura.getFecha();
                // ¿Son consecutivos por meses?
                long diferenciaMeses = ChronoUnit.MONTHS.between(
                        fechaUltimaFacturaCliente.withDayOfMonth(1),
                        fechaFactura.withDayOfMonth(1)
                );
                if (diferenciaMeses == 1) {
                    return true;
                } else {
                    return false;
                }
            }
        }
        // Entonces, no hay ninguna otra factura creada para el cliente, y siempre puede crearse
        return true;
    }

    public boolean anticipoTardioAUltimaFactura(Anticipo a) {
        Factura ultimaFacturaCliente = ultimaFacturaCliente(a.getClienteDNI());
        if (ultimaFacturaCliente != null) {
            LocalDate fechaUltimaFacturaCliente = ultimaFacturaCliente.getFecha();
            LocalDate fecha = a.getFecha();
            // Solo tenemos en cuenta los años y meses
            YearMonth fechaFactura = YearMonth.from(fechaUltimaFacturaCliente);
            YearMonth fechaAnticipo = YearMonth.from(fecha);

            if (fechaFactura.isAfter(fechaAnticipo)) {
                return false;
            }
        }
        return true;
    }

    public boolean anticipoContiguoAUltimaFactura(Anticipo a) {
        String clienteDNI = a.getClienteDNI();
        Factura ultimaFacturaCliente = ultimaFacturaCliente(clienteDNI);
        if (ultimaFacturaCliente != null) {
            LocalDate fechaUltimaFacturaCliente = ultimaFacturaCliente.getFecha();
            LocalDate fechaAnticipo = a.getFecha();
            // ¿Son consecutivos por meses?
            long diferenciaMeses = ChronoUnit.MONTHS.between(fechaUltimaFacturaCliente.withDayOfMonth(1), fechaAnticipo.withDayOfMonth(1));

            if (diferenciaMeses == 1) {
                return true;
            } else {
                return false;
            }

        }
        // Entonces, no hay ninguna otra factura creada para el cliente, y siempre puede crearse
        return true;
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import es.ujaen.tfg.modelo.Anticipo;
import es.ujaen.tfg.modelo.Cliente;
import es.ujaen.tfg.modelo.Factura;
import es.ujaen.tfg.modelo.Local;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author jota
 */

public class Constantes {
    public static final Local local1 = new Local("1", "Calle Campanas", "Panadería", 300.00);
    public static final Local local2 = new Local("2", "Calle Extremadura", "Estudio", 100.00);
    public static final Local local3 = new Local("3", "Plaza Cruz Rueda", "Cochera", 125.00);
    public static final Local local4 = new Local("4", "Calle Conde", "Trastero", 50.00);
    public static final Local local5 = new Local("5", "Paseo de España", "Peluquería", 450.00);
    public static final Local local6 = new Local("6", "Articulo 9.13.8", "Gastos Comunidad", 8.00);

    public static final List<Local> localescliente1 = new ArrayList<>();
    public static final List<Local> localescliente2 = new ArrayList<>();
    public static final List<Local> localescliente3 = new ArrayList<>();
    public static final List<Local> localescliente4 = new ArrayList<>();

    public static final Cliente cliente1;
    public static final Cliente cliente2;
    public static final Cliente cliente3;
    public static final Cliente cliente4;

    public static final Anticipo anticipo;

    public static final Factura factura1;
    public static final Factura factura2;
    public static final Factura factura3;
    public static final Factura factura4;
    public static final Factura factura5;
    public static final Factura factura6;
    public static final Factura factura7;
    public static final Factura factura8;

    static {
        // Inicializar listas de locales
        localescliente1.add(local1);

        localescliente2.add(local2);
        localescliente2.add(local6);

        localescliente3.add(local3);
        localescliente3.add(local4);
        localescliente3.add(local6);

        localescliente4.add(local5);

        // Inicializar clientes
        cliente1 = new Cliente(
            "11111111A",
            "Manolo",
            "Panadero",
            "ramon1@example.com",
            "C/ Salsipuedes, 14, 3ºA",
            "23003",
            "Jaén",
            "Pago en mano",
            true,
            localescliente1
        );

        cliente2 = new Cliente(
            "22222222B",
            "Antonio",
            "Profesor",
            "antonio2@example.com",
            "C/ Salsipuedes, 14, 3ºA",
            "23009",
            "Jaén",
            "Cobro día 10",
            true,
            localescliente2
        );

        cliente3 = new Cliente(
            "33333333C",
            "Alejandro",
            "Vecino",
            "alexelleon@example.com",
            "C/ Salsipuedes, 14, 3ºA",
            "23002",
            "Jaén",
            "Hace Anticipo",
            true,
            localescliente3
        );

        cliente4 = new Cliente(
            "44444444D",
            "Eva",
            "Peluquera",
            "evapeluquera@example.com",
            "C/ Gran Vía, 152, 7ºC",
            "28080",
            "Madrid",
            "Moroso",
            true,
            localescliente4
        );

        // Inicializar anticipo
        anticipo = new Anticipo("1", 183.00 * 3, 3, new Date(2024, 11, 1), cliente3);

        // Inicializar facturas
        factura1 = new Factura("1", new Date(2024, 10, 1), true, true, cliente1);
        factura2 = new Factura("2", new Date(2024, 10, 1), true, true, cliente2);
        factura3 = new Factura("3", new Date(2024, 10, 1), true, true, cliente3);
        factura4 = new Factura("4", new Date(2024, 10, 1), true, false, cliente4);
        factura5 = new Factura("5", new Date(2024, 11, 1), true, true, cliente1);
        factura6 = new Factura("6", new Date(2024, 11, 1), true, true, cliente2);
        factura7 = new Factura("7", new Date(2024, 11, 1), true, true, cliente3);
        factura8 = new Factura("8", new Date(2024, 11, 1), true, false, cliente4);
    }
}

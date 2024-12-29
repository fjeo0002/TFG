/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import es.ujaen.tfg.controlador.FacturaControlador;
import es.ujaen.tfg.modelo.Factura;
import java.awt.Color;
import java.awt.Component;
import java.util.Map;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jota
 */
public class ColorCelda extends JTable {

    private final FacturaControlador facturaControlador;
    private final Map<Integer, Map<Integer, String>> celdaFacturaMap;

    public ColorCelda(FacturaControlador facturaControlador, Map<Integer, Map<Integer, String>> celdaFacturaMap) {
        this.facturaControlador = facturaControlador;
        this.celdaFacturaMap = celdaFacturaMap;
    }

    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int rowIndex, int columnIndex) {
        Component component = super.prepareRenderer(renderer, rowIndex, columnIndex);
        // Recuperar el número de factura asociado a la celda
        if (celdaFacturaMap.containsKey(rowIndex) && celdaFacturaMap.get(rowIndex).containsKey(columnIndex)) {
            //String numeroFactura = celdaFacturaMap.get(rowIndex).get(columnIndex);
            String claveFactura = celdaFacturaMap.get(rowIndex).get(columnIndex);
            // Leer la factura desde el controlador
            if (claveFactura != null) {
                String[] partesClave = claveFactura.split("_");
                String numeroFactura = partesClave[1];
                String anioFactura = partesClave[0];
                        
                Factura factura = facturaControlador.leer(numeroFactura, anioFactura);
                
                if (factura != null) {
                    // Determinar el color según los estados de Facturado y Pagado
                    if (factura.getFacturado() && factura.getPagado()) {
                        component.setBackground(new Color(144, 238, 144)); // Verde claro
                        component.setForeground(Color.BLACK); // Negro por defecto
                    } else if (factura.getFacturado() && !factura.getPagado()) {
                        component.setBackground(new Color(241, 107, 107)); // Rojo claro
                        component.setForeground(Color.BLACK); // Negro por defecto
                    } else if (!factura.getFacturado() && factura.getPagado()) {
                        component.setBackground(new Color(255, 255, 102)); // Amarillo claro
                        component.setForeground(Color.BLACK); // Negro por defecto
                    } else {
                        component.setBackground(Color.WHITE); // Blanco por defecto
                        component.setForeground(Color.BLACK); // Negro por defecto
                    }
                }
            }
        } else {
            component.setBackground(Color.WHITE); // Blanco por defecto
            component.setForeground(Color.BLACK); // Negro por defecto
        }

        return component;

    }
}

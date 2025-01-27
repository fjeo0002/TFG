/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import es.ujaen.tfg.controlador.FacturaControlador;
import es.ujaen.tfg.modelo.Factura;
import static es.ujaen.tfg.utils.Utils.AMARILLO;
import static es.ujaen.tfg.utils.Utils.BLANCO;
import es.ujaen.tfg.utils.Utils.Mes;
import static es.ujaen.tfg.utils.Utils.NEGRO;
import static es.ujaen.tfg.utils.Utils.ROJO;
import static es.ujaen.tfg.utils.Utils.VERDE;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jota
 */
public class ColorCelda implements TableCellRenderer {

    private final FacturaControlador facturaControlador;
    private final int anio;

    public ColorCelda(FacturaControlador facturaControlador, int anio) {
        this.facturaControlador = facturaControlador;
        this.anio = anio;
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int col) {
        Component component = new DefaultTableCellRenderer().getTableCellRendererComponent(table, value, isSelected, hasFocus, row, col);
        // Recuperar el número de factura asociado a la celda
        if (col > 1) {
            // Obtener el DNI del cliente en la fila actual
            String clienteDNI = (String) table.getValueAt(row, 0);  // Columna 0 contiene el DNI del cliente

            // Obtener el nombre del mes de la columna
            String mesNombre = table.getColumnName(col);  // Nombre del mes en el encabezado de la columna
            Mes mes = Mes.porNombre(mesNombre);  // Convertir el nombre del mes a un objeto Mes para obtener el numero de mes

            // Obtener la factura correspondiente al cliente, mes y año
            Factura factura = facturaControlador.facturaClienteMesAnio(clienteDNI, mes.getNumero(), anio);

            if (factura != null) {
                // Determinar el color según los estados de Facturado y Pagado
                if (factura.getFacturado() && factura.getPagado()) {
                    component.setBackground(VERDE); // Verde claro
                    component.setForeground(NEGRO); // Negro por defecto
                } else if (factura.getFacturado() && !factura.getPagado()) {
                    component.setBackground(ROJO); // Rojo claro
                    component.setForeground(NEGRO); // Negro por defecto
                } else if (!factura.getFacturado() && factura.getPagado()) {
                    component.setBackground(AMARILLO); // Amarillo claro
                    component.setForeground(NEGRO); // Negro por defecto
                } else {
                    component.setBackground(BLANCO); // Blanco por defecto
                    component.setForeground(NEGRO); // Negro por defecto
                }
            } else {
                component.setBackground(BLANCO); // Blanco por defecto
                component.setForeground(NEGRO); // Negro por defecto
            }
        } else {
            component.setBackground(BLANCO); // Blanco por defecto
            component.setForeground(NEGRO); // Negro por defecto
        }

        return component;

    }
}

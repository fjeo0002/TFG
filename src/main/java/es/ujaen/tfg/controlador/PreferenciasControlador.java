/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import es.ujaen.tfg.DAO.PreferenciasDAO;
import es.ujaen.tfg.modelo.Preferencias;

/**
 *
 * @author jota
 */
public class PreferenciasControlador {

    private Preferencias preferencias;
    private final PreferenciasDAO preferenciasDAO;

    public PreferenciasControlador(PreferenciasDAO preferenciasDAO) {
        this.preferenciasDAO = preferenciasDAO;
        this.preferencias = preferenciasDAO.leer("config");
        if(preferencias == null){
            this.preferencias = crearPreferenciasPorDefecto();
        }
    }

    /**
     * Guarda las preferencias en el archivo JSON.
     *
     * @param nuevasPreferencias
     */
    public void guardarPreferencias(Preferencias nuevasPreferencias) {
        this.preferencias = nuevasPreferencias;
        preferenciasDAO.actualizar(nuevasPreferencias);
    }

    /**
     * Obtiene las preferencias actuales.
     *
     * @return Preferencias
     */
    public Preferencias obtenerPreferencias() {
        return this.preferencias;
    }

    /**
     * Crea valores predeterminados para las preferencias.
     */
    private Preferencias crearPreferenciasPorDefecto() {
        Preferencias defaultPreferences = new Preferencias();
        guardarPreferencias(defaultPreferences);
        return defaultPreferences;
    }
}

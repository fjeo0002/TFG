/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.controlador;

import com.google.gson.Gson;
import es.ujaen.tfg.modelo.Preferencias;
import static es.ujaen.tfg.utils.Utils.PREFERENCIAS_JSON;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author jota
 */
public class PreferenciasControlador {
    private Preferencias preferencias;

    public PreferenciasControlador() {
        this.preferencias = cargarPreferenciasDesdeArchivo();
    }

    /**
     * Carga las preferencias desde el archivo JSON o crea valores predeterminados.
     */
    private Preferencias cargarPreferenciasDesdeArchivo() {
        try {
            if (!Files.exists(Paths.get(PREFERENCIAS_JSON))) {  // Si no existe el archivo, creamos valores predeterminados
                return crearPreferenciasPorDefecto();
            }
            String jsonData = new String(Files.readAllBytes(Paths.get(PREFERENCIAS_JSON)));
            return new Gson().fromJson(jsonData, Preferencias.class);
        } catch (IOException e) {
            return crearPreferenciasPorDefecto();
        }
    }

    /**
     * Guarda las preferencias en el archivo JSON.
     * @param nuevasPreferencias
     */
    public void guardarPreferencias(Preferencias nuevasPreferencias) {
        this.preferencias = nuevasPreferencias;
        try (FileWriter writer = new FileWriter(PREFERENCIAS_JSON)) {
            new Gson().toJson(preferencias, writer);
        } catch (IOException e) {
        }
    }

    /**
     * Obtiene las preferencias actuales.
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

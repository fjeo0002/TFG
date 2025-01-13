/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.ujaen.tfg.utils;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializer;
import static es.ujaen.tfg.utils.Utils.FORMATO_FECHA;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
/**
 *
 * @author jota
 */
public class LocalDateAdapterGson implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate>{
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, com.google.gson.JsonSerializationContext context) {
        return new JsonPrimitive(src.format(FORMATO_FECHA)); // Serializa LocalDate como String en formato dd/MM/yyyy
    }

    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, com.google.gson.JsonDeserializationContext context)
            throws JsonParseException {
        try {
            return LocalDate.parse(json.getAsString(), FORMATO_FECHA); // Deserializa String a LocalDate con dd/MM/yyyy
        } catch (DateTimeParseException e) {
            throw new JsonParseException("Error al parsear fecha: " + json.getAsString(), e);
        }
    }
}

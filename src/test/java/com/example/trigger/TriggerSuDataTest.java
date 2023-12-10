package com.example.trigger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;


public class TriggerSuDataTest {

    @Test
    void testToJSONObject() {

        LocalDate data = LocalDate.of(2023, 12, 8);
        LocalTime tempo = LocalTime.of(12, 30);

        TriggerSuData trigger = new TriggerSuData(tempo, data);

        JSONObject jsonObject = trigger.toJSONObject();


        assertAll(
                () -> assertEquals("TriggerSuData", jsonObject.getString("tipo")),
                () -> assertEquals(tempo.toString(), jsonObject.getString("tempo")),
                () -> assertTrue(jsonObject.get("data") instanceof String)
        );

    }

    @Test
    void testToString() {

        LocalTime tempo = LocalTime.now();
        LocalDate data = LocalDate.now();

        TriggerSuData triggerSuData = new TriggerSuData(tempo, data);

        // Verifica che il metodo toString restituisca il valore atteso
        String expectedToString = "Attiva il: " + data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        assertEquals(expectedToString, triggerSuData.toString());

    }

}

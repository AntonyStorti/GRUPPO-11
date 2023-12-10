package com.example.trigger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class TriggerMensileTest {

    @Test
    void testVerificaCondizione() {

        LocalDate oggi = LocalDate.now();

        // Crea un oggetto TriggerMensile per oggi e un'ora futura
        TriggerMensile trigger = new TriggerMensile(LocalTime.now().plusHours(1), oggi);

        // Il risultato dovrebbe essere falso perché l'ora futura non è ancora arrivata
        assertFalse(trigger.verificaCondizione());

        // Crea un oggetto TriggerMensile per oggi e l'ora corrente
        trigger = new TriggerMensile(LocalTime.now(), oggi);

        // Il risultato dovrebbe essere vero perché l'ora corrente soddisfa la condizione
        assertTrue(trigger.verificaCondizione());

    }

    @Test
    void testToJSONObject() {

        LocalDate oggi = LocalDate.now();

        TriggerMensile trigger = new TriggerMensile(LocalTime.of(12, 30), oggi);

        JSONObject jsonObject = trigger.toJSONObject();

        // Verifica che l'oggetto JSON contenga i valori attesi
        assertEquals("TriggerMensile", jsonObject.getString("tipo"));
        assertEquals("12:30", jsonObject.getString("tempo"));
        assertEquals(oggi.toString(), jsonObject.getString("giornomese"));

    }

}

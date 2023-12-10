package com.example.trigger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


public class TriggerSettimanaleTest {

    @Test
    void testVerificaCondizione() {

        DayOfWeek giornoAttuale = LocalDate.now().getDayOfWeek();

        // Crea un oggetto TriggerSettimanale per il giorno attuale e un'ora futura
        TriggerSettimanale trigger = new TriggerSettimanale(LocalTime.now().plusHours(1), giornoAttuale);

        // Il risultato dovrebbe essere falso perché l'ora futura non è ancora arrivata
        assertFalse(trigger.verificaCondizione());

        // Crea un oggetto TriggerSettimanale per il giorno attuale e l'ora corrente
        trigger = new TriggerSettimanale(LocalTime.now(), giornoAttuale);

        // Il risultato dovrebbe essere vero perché l'ora corrente soddisfa la condizione
        assertTrue(trigger.verificaCondizione());

    }

    @Test
    void testToJSONObject() {

        DayOfWeek giornoAttuale = LocalDate.now().getDayOfWeek();

        TriggerSettimanale trigger = new TriggerSettimanale(LocalTime.of(12, 30), giornoAttuale);

        JSONObject jsonObject = trigger.toJSONObject();

        // Verifica che l'oggetto JSON contenga i valori attesi
        assertEquals("TriggerSettimanale", jsonObject.getString("tipo"));
        assertEquals("12:30", jsonObject.getString("tempo"));
        assertEquals(giornoAttuale.toString(), jsonObject.getString("giornosettimana"));

    }

}

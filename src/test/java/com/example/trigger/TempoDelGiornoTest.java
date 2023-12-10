package com.example.trigger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class TempoDelGiornoTest {
    @Test
    void verificaCondizione_DovrebbeRestituireTrueQuandoIlTempoCorrisponde() {

        LocalTime orarioCorrente = LocalTime.of(12, 34);
        System.out.println("Orario corrente: " + orarioCorrente);
        TempoDelGiorno tempoDelGiorno = new TempoDelGiorno(orarioCorrente);

        boolean risultato = tempoDelGiorno.verificaCondizione();


        assertTrue(risultato);
    }



    @Test
    void verificaCondizione_DovrebbeRestituireFalseQuandoIlTempoNonCorrisponde() {

        LocalTime orarioCorrente = LocalTime.of(9, 0);
        LocalTime orarioTrigger = LocalTime.of(12, 34);
        TempoDelGiorno tempoDelGiorno = new TempoDelGiorno(orarioTrigger);


        boolean risultato = tempoDelGiorno.verificaCondizione();


        assertFalse(risultato);
    }

    @Test
    void toJSONObject_DovrebbeRestituireJsonObjectConValoriCorretti() {

        LocalTime orarioTrigger = LocalTime.of(15, 45);
        TempoDelGiorno tempoDelGiorno = new TempoDelGiorno(orarioTrigger);

        JSONObject jsonObject = tempoDelGiorno.toJSONObject();

        assertEquals("TempoDelGiorno", jsonObject.getString("tipo"));
        assertEquals("15:45", jsonObject.getString("tempo"));
    }

    @Test
    void toString_DovrebbeRestituireRappresentazioneStringaCorretta() {

        LocalTime orarioTrigger = LocalTime.of(18, 0);
        TempoDelGiorno tempoDelGiorno = new TempoDelGiorno(orarioTrigger);


        String risultato = tempoDelGiorno.toString();


        assertEquals("Attiva alle ore 18:00", risultato);
    }
}


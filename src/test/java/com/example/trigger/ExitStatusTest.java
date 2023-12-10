package com.example.trigger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class ExitStatusTest {

    @Test
    void verificaCondizione_ExitStatusUguale_RitornaTrue() {

        String percorso = "cmd /c echo hello";
        Integer exitStatus = 0;

        ExitStatus exitStatusTrigger = new ExitStatus(percorso, exitStatus);

        // Verifica che la condizione sia vera
        assertTrue(exitStatusTrigger.verificaCondizione());
    }


    @Test
    void verificaCondizione_ExitStatusDiverso_RitornaFalse() {

        String percorso = "dir /non-esiste";
        Integer exitStatus = 0;

        ExitStatus exitStatusTrigger = new ExitStatus(percorso, exitStatus);

        // Aggiungi output di debug
        System.out.println("Percorso: " + percorso);

        // Verifica che la condizione sia falsa
        assertFalse(exitStatusTrigger.verificaCondizione());

    }


    @Test
    void toJSONObject_DeveRestituireJSONObjectCorretto() {

        String percorso = "ls /";
        Integer exitStatus = 0;

        ExitStatus exitStatusTrigger = new ExitStatus(percorso, exitStatus);

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("tipo", "ExitStatus");
        expectedJson.put("percorso", percorso);
        expectedJson.put("exitStatus", exitStatus);

        // Verificare che il JSONObject restituito sia uguale a quello atteso
        assertEquals(expectedJson.toString(), exitStatusTrigger.toJSONObject().toString());
    }
}

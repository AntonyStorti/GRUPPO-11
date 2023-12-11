package com.example.trigger;

import static org.junit.jupiter.api.Assertions.*;

import com.example.ifttt.Contatore;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;



public class ConfrontoContatoriTest {

    @Test
    void verificaCondizione_ConfrontoMaggioreDi_DeveRitornareTrue() {
        // Creare due oggetti Contatore
        Contatore contatore1 = new Contatore("Contatore1", 10);
        Contatore contatore2 = new Contatore("Contatore2", 5);

        // Creare un'istanza di ConfrontoContatori con confronto ">"
        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, ">", contatore2);

        // Verificare che la condizione sia vera
        assertTrue(confrontoContatori.verificaCondizione());
    }

    @Test
    void verificaCondizione_ConfrontoMinoreDi_DeveRitornareTrue() {
        // Creare due oggetti Contatore
        Contatore contatore1 = new Contatore("Contatore1", 8);
        Contatore contatore2 = new Contatore("Contatore2", 12);

        // Creare un'istanza di ConfrontoContatori con confronto "<"
        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, "<", contatore2);

        // Verificare che la condizione sia vera
        assertTrue(confrontoContatori.verificaCondizione());
    }

    @Test
    void verificaCondizione_ConfrontoUgualeA_DeveRitornareTrue() {
        // Creare due oggetti Contatore con lo stesso valore
        Contatore contatore1 = new Contatore("Contatore1", 7);
        Contatore contatore2 = new Contatore("Contatore2", 7);

        // Creare un'istanza di ConfrontoContatori con confronto "="
        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, "=", contatore2);

        // Verificare che la condizione sia vera
        assertTrue(confrontoContatori.verificaCondizione());
    }


    @Test
    void toJSONObject_DeveRestituireJSONObjectCorretto() {
        // Creare due oggetti Contatore
        Contatore contatore1 = new Contatore("Contatore1", 5);
        Contatore contatore2 = new Contatore("Contatore2", 10);

        // Creare un'istanza di ConfrontoContatori con valori specifici
        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, ">=", contatore2);

        // Creare il JSONObject atteso
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("tipo", "ConfrontoContatori");
        expectedJson.put("contatore1", contatore1.toJSONObject());
        expectedJson.put("confronto", ">=");
        expectedJson.put("contatore2", contatore2.toJSONObject());

        // Verificare che il JSONObject restituito sia uguale a quello atteso
        assertEquals(expectedJson.toString(), confrontoContatori.toJSONObject().toString());
    }
}

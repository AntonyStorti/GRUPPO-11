package com.example.trigger;

import com.example.ifttt.Contatore;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class ConfrontoContatoriTest {


    @Test
    void verificaCondizione_ConfrontoMaggioreDi_DeveRitornareTrue() {

        Contatore contatore1 = new Contatore("Contatore1", 10);
        Contatore contatore2 = new Contatore("Contatore2", 5);

        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, ">", contatore2);

        assertTrue(confrontoContatori.verificaCondizione());

    }

    @Test
    void verificaCondizione_ConfrontoMinoreDi_DeveRitornareTrue() {

        Contatore contatore1 = new Contatore("Contatore1", 8);
        Contatore contatore2 = new Contatore("Contatore2", 12);

        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, "<", contatore2);

        assertTrue(confrontoContatori.verificaCondizione());
    }

    @Test
    void verificaCondizione_ConfrontoUgualeA_DeveRitornareTrue() {

        Contatore contatore1 = new Contatore("Contatore1", 7);
        Contatore contatore2 = new Contatore("Contatore2", 7);

        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, "=", contatore2);

        assertTrue(confrontoContatori.verificaCondizione());
    }


    @Test
    void toJSONObject_DeveRestituireJSONObjectCorretto() {

        Contatore contatore1 = new Contatore("Contatore1", 5);
        Contatore contatore2 = new Contatore("Contatore2", 10);


        ConfrontoContatori confrontoContatori = new ConfrontoContatori(contatore1, ">=", contatore2);

        JSONObject expectedJson = new JSONObject();
        expectedJson.put("tipo", "ConfrontoContatori");
        expectedJson.put("contatore1", contatore1.toJSONObject());
        expectedJson.put("confronto", ">=");
        expectedJson.put("contatore2", contatore2.toJSONObject());

        // Verificare che il JSONObject restituito sia uguale a quello atteso
        assertEquals(expectedJson.toString(), confrontoContatori.toJSONObject().toString());
    }
}

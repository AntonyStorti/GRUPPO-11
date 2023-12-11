package com.example.trigger;

import static org.junit.jupiter.api.Assertions.*;

import com.example.ifttt.Contatore;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ContatoreInteroTest {

    @Test
    void verificaCondizione_ConfrontoMaggioreDi_DeveRitornareTrue() {
        // Creare un oggetto Contatore
        Contatore contatore = new Contatore("NomeContatore", 10);

        // Creare un'istanza di ContatoreIntero con confronto ">"
        ContatoreIntero contatoreIntero = new ContatoreIntero(contatore, 5, ">");

        // Verificare che la condizione sia vera
        assertTrue(contatoreIntero.verificaCondizione());
    }

    @Test
    void verificaCondizione_ConfrontoMinoreDi_DeveRitornareTrue() {
        // Creare un oggetto Contatore
        Contatore contatore = new Contatore("NomeContatore", 5);

        // Creare un'istanza di ContatoreIntero con confronto "<"
        ContatoreIntero contatoreIntero = new ContatoreIntero(contatore, 10, "<");

        // Verificare che la condizione sia vera
        assertTrue(contatoreIntero.verificaCondizione());
    }

    @Test
    void verificaCondizione_ConfrontoUgualeA_DeveRitornareTrue() {
        // Creare un oggetto Contatore
        Contatore contatore = new Contatore("NomeContatore", 7);

        // Creare un'istanza di ContatoreIntero con confronto "="
        ContatoreIntero contatoreIntero = new ContatoreIntero(contatore, 7, "=");

        // Verificare che la condizione sia vera
        assertTrue(contatoreIntero.verificaCondizione());
    }



    @Test
    void toJSONObject_DeveRestituireJSONObjectCorretto() {
        // Creare un oggetto Contatore
        Contatore contatore = new Contatore("NomeContatore", 7);

        // Creare un'istanza di ContatoreIntero con valori specifici
        ContatoreIntero contatoreIntero = new ContatoreIntero(contatore, 7, ">=");

        // Creare il JSONObject atteso
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("tipo", "ContatoreIntero");
        expectedJson.put("contatore", contatore.toJSONObject());
        expectedJson.put("valore", 7);
        expectedJson.put("confronto", ">=");

        // Verificare che il JSONObject restituito sia uguale a quello atteso
        assertEquals(expectedJson.toString(), contatoreIntero.toJSONObject().toString());
    }
}

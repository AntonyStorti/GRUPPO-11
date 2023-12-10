package com.example.azioni;

import com.example.ifttt.Contatore;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SommaValoreContatoreTest {

    @Test
    public void testEseguiAzione() {
        // Creazione di un contatore di test
        Contatore contatore = new Contatore("Contatore1", 5);

        // Creazione di un'istanza di SommaValoreContatore con sommaValore pari a 10
        SommaValoreContatore sommaValoreContatore = new SommaValoreContatore(contatore, 10);

        // Verifica che l'esecuzione dell'azione modifichi correttamente il contatore
        sommaValoreContatore.eseguiAzione();

        // Verifica che il valore del contatore sia stato aggiornato correttamente
        assertEquals(15, contatore.getValore());
    }

    @Test
    public void testToJSONObject() {
        // Creazione di un contatore di test
        Contatore contatore = new Contatore("Contatore1", 5);

        // Creazione di un'istanza di SommaValoreContatore con sommaValore pari a 10
        SommaValoreContatore sommaValoreContatore = new SommaValoreContatore(contatore, 10);

        // Creazione di un oggetto JSONObject dalla SommaValoreContatore
        JSONObject jsonObject = sommaValoreContatore.toJSONObject();

        // Verifica che il JSONObject contenga correttamente le informazioni della SommaValoreContatore
        assertEquals("SommaValoreContatore", jsonObject.getString("tipo"));
        assertEquals(contatore.toJSONObject().toString(), jsonObject.getJSONObject("contatore").toString());
        assertEquals(10, jsonObject.getInt("valore"));
    }

    @Test
    public void testDeserialize() {
        // Creazione di un JSONObject di test
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", "SommaValoreContatore");
        jsonObject.put("contatore", new Contatore("Contatore1", 5).toJSONObject());
        jsonObject.put("valore", 10);

        // Deserializzazione del JSONObject in un'istanza di SommaValoreContatore
        SommaValoreContatore sommaValoreContatore = SommaValoreContatore.deserialize(jsonObject);

        // Verifica che l'istanza di SommaValoreContatore sia stata creata correttamente
        assertNotNull(sommaValoreContatore);
        assertEquals("Contatore1", sommaValoreContatore.getContatore().getNome());
        assertEquals(10, sommaValoreContatore.getSommaValore());
    }
}

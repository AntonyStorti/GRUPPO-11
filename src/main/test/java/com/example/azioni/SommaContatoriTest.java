package com.example.azioni;


import com.example.ifttt.Contatore;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SommaContatoriTest {

    @Test
    public void testEseguiAzione() {
        // Creazione di due contatori di test
        Contatore contatore1 = new Contatore("Contatore1", 5);
        Contatore contatore2 = new Contatore("Contatore2", 10);

        // Creazione di un'istanza di SommaContatori
        SommaContatori sommaContatori = new SommaContatori(contatore1, contatore2);

        // Verifica che l'esecuzione dell'azione modifichi correttamente i contatori
        sommaContatori.eseguiAzione();

        // Verifica che i valori dei contatori siano stati aggiornati correttamente
        assertEquals(15, contatore1.getValore());
        assertEquals(10, contatore2.getValore());
    }

    @Test
    public void testToJSONObject() {
        // Creazione di due contatori di test
        Contatore contatore1 = new Contatore("Contatore1", 5);
        Contatore contatore2 = new Contatore("Contatore2", 10);

        // Creazione di un'istanza di SommaContatori
        SommaContatori sommaContatori = new SommaContatori(contatore1, contatore2);

        // Creazione di un oggetto JSONObject dalla SommaContatori
        JSONObject jsonObject = sommaContatori.toJSONObject();

        // Verifica che il JSONObject contenga correttamente le informazioni della SommaContatori
        assertEquals("SommaContatori", jsonObject.getString("tipo"));
        assertEquals(contatore1.toJSONObject().toString(), jsonObject.getJSONObject("contatore1").toString());
        assertEquals(contatore2.toJSONObject().toString(), jsonObject.getJSONObject("contatore2").toString());
    }

    @Test
    public void testDeserialize() {
        // Creazione di un JSONObject di test
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", "SommaContatori");
        jsonObject.put("contatore1", new Contatore("Contatore1", 5).toJSONObject());
        jsonObject.put("contatore2", new Contatore("Contatore2", 10).toJSONObject());

        // Deserializzazione del JSONObject in un'istanza di SommaContatori
        SommaContatori sommaContatori = SommaContatori.deserialize(jsonObject);

        // Verifica che l'istanza di SommaContatori sia stata creata correttamente
        assertNotNull(sommaContatori);
        assertEquals("Contatore1", sommaContatori.getContatore().getNome());
        assertEquals("Contatore2", sommaContatori.getContatore2().getNome());
    }
}

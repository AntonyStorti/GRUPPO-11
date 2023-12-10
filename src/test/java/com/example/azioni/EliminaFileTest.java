package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;


public class EliminaFileTest {

    @Test
    void testDeserialize() {
        // Crea un oggetto JSON simulato per rappresentare un'istanza di EliminaFile
        JSONObject jsonAzione = new JSONObject();
        jsonAzione.put("tipo", "EliminaFile");
        jsonAzione.put("percorso", "/path/to/file.txt");

        // Deserializza l'oggetto JSON
        Azione azione = EliminaFile.deserialize(jsonAzione);

        // Verifica che l'oggetto deserializzato sia un'istanza di EliminaFile
        assertTrue(azione instanceof EliminaFile);

        // Verifica che il percorso sia corretto
        assertEquals("/path/to/file.txt", ((EliminaFile) azione).percorso);
    }

    @Test
    void testEseguiAzione() throws IOException {

        // Crea un file temporaneo per il test
        Path tempFile = Files.createTempFile("test-file", ".txt");

        EliminaFile eliminaFile = new EliminaFile(tempFile.toString());

        // Verifica che il file esista prima dell'esecuzione del test
        assertTrue(Files.exists(tempFile));

        // Esegui l'azione di eliminazione
        eliminaFile.eseguiAzione();

        // Verifica che il file sia stato eliminato
        assertFalse(Files.exists(tempFile));

    }

    @Test
    void testToString() {

        EliminaFile eliminaFile = new EliminaFile("/path/to/file.txt");

        // Verifica che il metodo toString restituisca la stringa attesa
        assertEquals("Elimina un File", eliminaFile.toString());

    }

    @Test
    void testToJSONObject() {

        EliminaFile eliminaFile = new EliminaFile("/path/to/file.txt");

        // Ottieni l'oggetto JSON
        JSONObject jsonAzione = eliminaFile.toJSONObject();

        // Verifica che l'oggetto JSON contenga i valori attesi
        assertEquals("EliminaFile", jsonAzione.getString("tipo"));
        assertEquals("/path/to/file.txt", jsonAzione.getString("percorso"));
    }

    @Test
    void testGetTipo() {

        EliminaFile eliminaFile = new EliminaFile("/path/to/file.txt");

        // Verifica che il metodo getTipo restituisca il valore atteso
        assertEquals("EliminaFile", eliminaFile.getTipo());
    }

}

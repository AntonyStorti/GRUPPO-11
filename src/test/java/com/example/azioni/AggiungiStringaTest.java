package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AggiungiStringaTest {

    private Azione azione;
    private String testFilePath = "test_file.txt";

    @BeforeEach
    void setUp() {
        azione = new AggiungiStringa(testFilePath, "Testo da aggiungere");
    }

    @Test
    void testEseguiAzione() {

        azione.eseguiAzione();

        // Verifica se la stringa è stata effettivamente aggiunta al file
        assertTrue(fileContainsString(testFilePath, "Testo da aggiungere"));

    }

    @Test
    void testToJSONObject() {

        JSONObject jsonObject = azione.toJSONObject();

        // Verifica che il JSONObject contenga i valori corretti
        assertEquals("AggiungiStringa", jsonObject.getString("tipo"));
        assertEquals(testFilePath, jsonObject.getString("percorso"));
        assertEquals("Testo da aggiungere", jsonObject.getString("testodaaggiungere"));
    }


    private boolean fileContainsString(String filePath, String targetString) {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(targetString)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Metodo per rimuovere il file di test dopo l'esecuzione dello stesso!
    // Chiamato automaticamente da JUnit dopo ogni test
    @Test
    void cleanup() {
        try {
            Files.deleteIfExists(Paths.get(testFilePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

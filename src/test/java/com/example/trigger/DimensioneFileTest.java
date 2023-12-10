package com.example.trigger;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class DimensioneFileTest {

    private static final String PERCORSO_FILE_VUOTO = "C:\\Users\\miria\\OneDrive\\Documenti\\FileVuoto.txt";
    private static final String PERCORSO_FILE_MAGGIORE_DI_1KB = "C:\\Users\\miria\\OneDrive\\Documenti\\FilePieno.txt";

    @Test
    void verificaCondizione_DimensioneFileSuperiore_RitornaTrue() throws IOException {
        // Creare un file vuoto
        Path fileVuoto = Path.of(PERCORSO_FILE_VUOTO);
        Files.deleteIfExists(fileVuoto);
        Files.createFile(fileVuoto);

        // Creare un'istanza di DimensioneFile con dimensione desiderata in KB
        DimensioneFile dimensioneFile = new DimensioneFile(fileVuoto.toString(), 1, "KB");

        // Verificare che la condizione sia vera
        assertTrue(dimensioneFile.verificaCondizione());
    }

    @Test
    void verificaCondizione_DimensioneFileInferiore_RitornaFalse() throws IOException {
        // Creare un file con dimensioni superiori a 1 KB
        Path fileMaggioreDi1KB = Path.of(PERCORSO_FILE_MAGGIORE_DI_1KB);
        Files.deleteIfExists(fileMaggioreDi1KB);
        Files.write(fileMaggioreDi1KB, "Contenuto del file di test".getBytes());

        // Creare un'istanza di DimensioneFile con dimensione desiderata in KB
        DimensioneFile dimensioneFile = new DimensioneFile(fileMaggioreDi1KB.toString(), 1, "KB");

        // Verificare che la condizione sia falsa
        assertFalse(dimensioneFile.verificaCondizione());
    }

    @Test
    void verificaCondizione_FileInesistente_RitornaFalse() {
        // Creare un'istanza di DimensioneFile con un percorso non valido
        DimensioneFile dimensioneFile = new DimensioneFile("C:\\Users\\miria\\OneDrive\\Documenti\\Nuovo.txt", 1, "KB");

        // Verificare che la condizione sia falsa poiché il file non esiste
        assertFalse(dimensioneFile.verificaCondizione());
    }

    @Test
    void toJSONObject_DeveRestituireJSONObjectCorretto() {
        // Creare un'istanza di DimensioneFile con valori specifici
        DimensioneFile dimensioneFile = new DimensioneFile(PERCORSO_FILE_MAGGIORE_DI_1KB, 5, "MB");

        // Creare il JSONObject atteso
        JSONObject expectedJson = new JSONObject();
        expectedJson.put("tipo", "DimensioneFile");
        expectedJson.put("percorso", PERCORSO_FILE_MAGGIORE_DI_1KB);
        expectedJson.put("dimensione", 5);
        expectedJson.put("unita", "MB");

        // Verificare che il JSONObject restituito sia uguale a quello atteso
        assertEquals(expectedJson.toString(), dimensioneFile.toJSONObject().toString());
    }
}

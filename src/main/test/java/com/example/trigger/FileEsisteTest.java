package com.example.trigger;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileEsisteTest {

    @Test
    void testVerificaCondizione_FileEsiste() throws IOException {
        // Crea un file temporaneo per il test
        Path percorsoFileTemporaneo = Files.createTempFile("testfile", ".txt");

        // Crea un'istanza di FileEsiste
        FileEsiste fileEsiste = new FileEsiste(percorsoFileTemporaneo.getParent().toString(), percorsoFileTemporaneo.getFileName().toString());

        // Verifica che il file esista
        assertTrue(fileEsiste.verificaCondizione());
    }

    @Test
    void testVerificaCondizione_FileNonEsiste() {
        // Crea un'istanza di FileEsiste con un percorso che non contiene un file
        FileEsiste fileEsiste = new FileEsiste("percorso-non-esistente", "file-non-esistente.txt");

        // Verifica che il file non esista
        assertFalse(fileEsiste.verificaCondizione());
    }
}

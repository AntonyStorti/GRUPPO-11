package com.example.azioni;

import com.example.ifttt.Azione;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CopiaSpostaTest {

    private static final String PERCORSO_ORIGINE_1 = "C:\\Users\\miria\\test-file-1.txt";
    private static final String PERCORSO_ORIGINE_2 = "C:\\Users\\miria\\test-file-2.txt";
    private static final String PERCORSO_DESTINAZIONE = "C:\\Users\\miria\\Destinazione";

    private Azione azione1;
    private Azione azione2;

    @BeforeEach
    void setUp() {
        azione1 = new CopiaSposta(PERCORSO_ORIGINE_1, PERCORSO_DESTINAZIONE, "Copia nella destinazione");
        azione2 = new CopiaSposta(PERCORSO_ORIGINE_2, PERCORSO_DESTINAZIONE, "Copia nella destinazione");
    }

    @Test
    void testEseguiAzioneCopia() {
        // Assicurati che i file di destinazione non esistano prima dell'esecuzione del test
        Path destinazione = Path.of(PERCORSO_DESTINAZIONE);
        assertTrue(!Files.exists(destinazione.resolve(Path.of("test-file-1.txt"))));
        assertTrue(!Files.exists(destinazione.resolve(Path.of("test-file-2.txt"))));

        // Esegui le azioni di copia
        azione1.eseguiAzione();
        azione2.eseguiAzione();

        // Verifica che i file siano stati copiati con successo nella destinazione
        assertTrue(Files.exists(destinazione.resolve(Path.of("test-file-1.txt"))));
        assertTrue(Files.exists(destinazione.resolve(Path.of("test-file-2.txt"))));
    }

    @Test
    void testEseguiAzioneSposta() {
        // Assicurati che i file di destinazione non esistano prima dell'esecuzione del test
        Path destinazione = Path.of(PERCORSO_DESTINAZIONE);
        assertTrue(!Files.exists(destinazione.resolve(Path.of("test-file-1.txt"))));
        assertTrue(!Files.exists(destinazione.resolve(Path.of("test-file-2.txt"))));

        // Esegui le azioni di spostamento
        azione1.eseguiAzione();
        azione2.eseguiAzione();

        // Verifica che i file siano stati spostati con successo nella destinazione
        assertTrue(Files.exists(destinazione.resolve(Path.of("test-file-1.txt"))));
        assertTrue(Files.exists(destinazione.resolve(Path.of("test-file-2.txt"))));


    }
}

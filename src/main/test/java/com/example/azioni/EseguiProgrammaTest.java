package com.example.azioni;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class EseguiProgrammaTest {

    @Test
    void testEseguiAzione() {
        // Mock del percorso dell'applicazione
        String percorsoAppMock = "C:\\Program Files\\MockApp.exe";

        // Creazione di un'istanza di EseguiProgramma con il percorso mockato
        EseguiProgramma eseguiProgramma = new EseguiProgramma(percorsoAppMock);

        // Esegui l'azione
        assertDoesNotThrow(() -> eseguiProgramma.eseguiAzione());


    }

    @Test
    void testEseguiAzioneConPercorsoNullo() {
        // Creazione di un'istanza di EseguiProgramma con percorso nullo
        EseguiProgramma eseguiProgramma = new EseguiProgramma(null);

        // Non dovrebbe lanciare eccezioni anche se il percorso è nullo
        assertDoesNotThrow(() -> eseguiProgramma.eseguiAzione());
    }



}

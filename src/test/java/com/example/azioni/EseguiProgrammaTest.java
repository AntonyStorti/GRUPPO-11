package com.example.azioni;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class EseguiProgrammaTest {

    @Test
    void testEseguiAzione() {

        String percorsoAppMock = "C:\\Program Files\\MockApp.exe";

        EseguiProgramma eseguiProgramma = new EseguiProgramma(percorsoAppMock);

        assertDoesNotThrow(() -> eseguiProgramma.eseguiAzione());

    }


    @Test
    void testEseguiAzioneConPercorsoNullo() {

        EseguiProgramma eseguiProgramma = new EseguiProgramma(null);
        // Non dovrebbe lanciare eccezioni anche se il percorso è nullo
        assertDoesNotThrow(() -> eseguiProgramma.eseguiAzione());

    }



}

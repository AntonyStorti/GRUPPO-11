package com.example.ifttt;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegolaTest {

    private Trigger trigger;
    private Azione azione;

    @BeforeEach
    void setUp() {
        trigger = new MockTempoDelGiorno(true, LocalTime.now());
        azione = new MockFinestraDialogo("Test messaggio");

    }

    @Test
    void testValutaEsecuzioneTriggerVerificatoStatoAttivo() {
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }

    @Test
    void testValutaEsecuzioneTriggerNonVerificato() {
        ((MockTempoDelGiorno) trigger).setVerificaCondizione(false);
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertFalse(esito);
        assertFalse(((MockFinestraDialogo) azione).isEseguita());
    }

    @Test
    void testValutaEsecuzioneRegolaDisattivata() {
        Regola regola = new Regola("Test Regola", trigger, azione, false, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertFalse(esito);
        assertFalse(((MockFinestraDialogo) azione).isEseguita());
    }

    @Test
    void testEsecuzioneConOrariSpecifici() {
        // Simula un trigger che verifica solo alle 10:00 del mattino
        trigger = new MockTempoDelGiorno(true, LocalTime.of(10, 0));
        azione = new MockFinestraDialogo("Test messaggio");
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }

    @Test
    void testValutaEsecuzioneTriggerFileEsiste() {
        // Utilizza il trigger mock per simulare il comportamento desiderato nel test
        trigger = new MockFileEsiste("/percorso/file", "file.txt");
        azione = new MockFinestraDialogo("Test messaggio");
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }
    @Test
    void testValutaEsecuzioneTriggerMensile() {
        // Utilizza il trigger mock per simulare il comportamento desiderato nel test
        trigger = new MockTriggerMensile(LocalTime.of(12, 0), LocalDate.now());
        azione = new MockFinestraDialogo("Test messaggio");
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }
    @Test
    void testValutaEsecuzioneTriggerSettimanale() {
        // Utilizza il trigger mock per simulare il comportamento desiderato nel test
        trigger = new MockTriggerSettimanale(LocalTime.of(12, 0), DayOfWeek.MONDAY);
        azione = new MockFinestraDialogo("Test messaggio");
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }
    @Test
    void testValutaEsecuzioneTriggerSuData() {
        // Utilizza il trigger mock per simulare il comportamento desiderato nel test
        trigger = new MockTriggerSuData(LocalTime.of(12, 0), LocalDate.now());
        azione = new MockFinestraDialogo("Test messaggio");
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }
    @Test
    void testValutaEsecuzioneTriggerDimensioneFile() {
        // Utilizza il trigger mock per simulare il comportamento desiderato nel test
        trigger = new MockDimensioneFile("/path/to/file", 100, "KB");
        azione = new MockFinestraDialogo("Test messaggio");
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        boolean esito = regola.valutaEsecuzione();

        assertTrue(esito);
        assertTrue(((MockFinestraDialogo) azione).isEseguita());
    }
    @Test
    void testRegolaConRiproduciAudio() {
        // Crea una regola con il trigger e l'azione specificati
        Regola regola = new Regola("Test Regola", trigger, azione, true, false, false);

        // Verifica che la regola sia valutata correttamente
        boolean esito = regola.valutaEsecuzione();

        // Verifica che l'azione RiproduciAudio sia eseguita correttamente
        assertTrue(esito);

    }

}

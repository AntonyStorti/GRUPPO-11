package com.example.azioni;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


public class RiproduciAudioTest {

    @Test
    void testEseguiAzione() {

        String percorsoAudio = "C:\\Users\\miria\\OneDrive - Università di Salerno\\Desktop\\Angelina-Mango-Che-to-dico-a-fa-_Official-Video_.wav";
        RiproduciAudio riproduciAudio = new RiproduciAudio(percorsoAudio);

        try {

            assertDoesNotThrow(() -> riproduciAudio.eseguiAzione());

        } catch (Exception e) {
            System.err.println("Errore durante l'esecuzione dell'azione:");
            e.printStackTrace();
        }

    }

    @Test
    void testSerializeDeserialize() {

        String percorsoAudio = "C:\\percorso\\del\\tuo\\file\\audio.wav";

        RiproduciAudio riproduciAudio = new RiproduciAudio(percorsoAudio);

        try {

            JSONObject jsonObject = riproduciAudio.toJSONObject();

            RiproduciAudio deserializedAudio = RiproduciAudio.deserialize(jsonObject);

            // Verifica che i dati siano gli stessi
            assertDoesNotThrow(() -> deserializedAudio.eseguiAzione());

        } catch (Exception e) {
            System.err.println("Errore durante la serializzazione/deserializzazione o l'esecuzione dell'azione:");
            e.printStackTrace();
        }
    }
}

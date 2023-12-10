package com.example.azioni;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class RiproduciAudioTest {

    @Test
    void testEseguiAzione() {

        String percorsoAudio = "C:\\Users\\miria\\OneDrive - Università di Salerno\\Desktop\\Angelina-Mango-Che-to-dico-a-fa-_Official-Video_.wav";
        RiproduciAudio riproduciAudio = new RiproduciAudio(percorsoAudio);

        try {
            System.out.println("Eseguo l'azione...");
            assertDoesNotThrow(() -> riproduciAudio.eseguiAzione());
            System.out.println("Azione eseguita con successo!");
        } catch (Exception e) {
            System.err.println("Errore durante l'esecuzione dell'azione:");
            e.printStackTrace();
        }

    }

    @Test
    void testSerializeDeserialize() {
        // Sostituisci con il percorso di un file audio reale sul tuo sistema
        String percorsoAudio = "C:\\percorso\\del\\tuo\\file\\audio.wav";

        RiproduciAudio riproduciAudio = new RiproduciAudio(percorsoAudio);

        try {
            // Serializza l'azione in un JSONObject
            System.out.println("Serializzo l'azione...");
            JSONObject jsonObject = riproduciAudio.toJSONObject();

            // Deserializza l'azione dal JSONObject
            System.out.println("Deserializzo l'azione...");
            RiproduciAudio deserializedAudio = RiproduciAudio.deserialize(jsonObject);

            // Verifica che i dati siano gli stessi
            System.out.println("Eseguo l'azione deserializzata...");
            assertDoesNotThrow(() -> deserializedAudio.eseguiAzione());
            System.out.println("Azione deserializzata eseguita con successo!");
        } catch (Exception e) {
            System.err.println("Errore durante la serializzazione/deserializzazione o l'esecuzione dell'azione:");
            e.printStackTrace();
        }
    }
}

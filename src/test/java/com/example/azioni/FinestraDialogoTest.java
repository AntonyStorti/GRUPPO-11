package com.example.azioni;

import javafx.application.Platform;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class FinestraDialogoTest {

    @Test
    public void testEseguiAzione() {
        Platform.runLater(() -> {
            FinestraDialogo finestraDialogo = new FinestraDialogo("Test Message");
            finestraDialogo.eseguiAzione();
        });

    }

    @Test
    public void testToJSONObject() {

        FinestraDialogo finestraDialogo = new FinestraDialogo("Test Message");

        JSONObject jsonObject = finestraDialogo.toJSONObject();

        assertEquals("FinestraDialogo", jsonObject.getString("tipo"));
        assertEquals("Test Message", jsonObject.getString("testoUtente"));
    }

    @Test
    public void testDeserialize() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", "FinestraDialogo");
        jsonObject.put("testoUtente", "Test Message");

        FinestraDialogo finestraDialogo = FinestraDialogo.deserialize(jsonObject);

        // Verifica che l'oggetto deserializzato sia corretto
        assertEquals("Test Message", finestraDialogo.getTestoUtente());
    }
}

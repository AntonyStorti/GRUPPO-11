package com.example.azioni;
import com.example.azioni.FinestraDialogo;
import com.example.ifttt.Azione;
import javafx.application.Platform;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
        // Crea un'istanza della classe da testare
        FinestraDialogo finestraDialogo = new FinestraDialogo("Test Message");

        // Ottieni il JSONObject
        JSONObject jsonObject = finestraDialogo.toJSONObject();

        // Verifica che il JSONObject sia corretto
        assertEquals("FinestraDialogo", jsonObject.getString("tipo"));
        assertEquals("Test Message", jsonObject.getString("testoUtente"));
    }

    @Test
    public void testDeserialize() {
        // Crea un JSONObject di esempio
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", "FinestraDialogo");
        jsonObject.put("testoUtente", "Test Message");

        // Deserializza il JSONObject
        FinestraDialogo finestraDialogo = FinestraDialogo.deserialize(jsonObject);

        // Verifica che l'oggetto deserializzato sia corretto
        assertEquals("Test Message", finestraDialogo.getTestoUtente());
    }
}

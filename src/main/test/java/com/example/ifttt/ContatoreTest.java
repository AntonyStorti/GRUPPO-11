package com.example.ifttt;

import com.example.ifttt.Contatore;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ContatoreTest {

    @Test
    public void testToJSONObject() {
        Contatore contatore = new Contatore("TestContatore", 42);
        JSONObject jsonObject = contatore.toJSONObject();

        assertEquals("TestContatore", jsonObject.getString("nome"));
        assertEquals(42, jsonObject.getInt("valorec"));
    }

    @Test
    public void testDeserializeContatore() {
        JSONObject json = new JSONObject()
                .put("nome", "TestContatore")
                .put("valorec", 42);

        Contatore contatore = Contatore.deserializeContatore(json);

        assertEquals("TestContatore", contatore.getNome());
        assertEquals(42, contatore.getValore());
    }

    @Test
    public void testToString() {
        Contatore contatore = new Contatore("TestContatore", 42);
        String expectedString = "Contatore: TestContatore";

        assertEquals(expectedString, contatore.toString());
    }
}

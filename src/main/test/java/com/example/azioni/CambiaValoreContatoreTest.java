package com.example.azioni;

import com.example.controllerGUI.HelloController;
import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;


class CambiaValoreContatoreTest {

    @Test
    void eseguiAzione_DovrebbeCambiareValoreContatoreEAggiornareListaContatori() {

        Contatore contatore = new Contatore("TestContatore", 10);
        CambiaValoreContatore cambiaValoreContatore = new CambiaValoreContatore(contatore, 20);

        // Mock del HelloController
        HelloController helloController = mock(HelloController.class);
        cambiaValoreContatore.helloController = helloController;

        // Mock della lista di contatori
        GestoreContatori.listaContatori.add(contatore);

        // Stub della chiamata a salvaContatoriSuFile
        doNothing().when(helloController).salvaContatoriSuFile();


        cambiaValoreContatore.eseguiAzione();

        // Assert
        assertEquals(20, contatore.getValore());

        // Verifica che la lista dei contatori sia stata aggiornata
        Contatore contatoreAggiornato = GestoreContatori.listaContatori.get(0);
        assertEquals(20, contatoreAggiornato.getValore());

        // Verifica che salvaContatoriSuFile sia stato chiamato
        verify(helloController).salvaContatoriSuFile();
    }

    @Test
    void toJSONObject_DovrebbeRestituireJSONObjectCorretto() {

        Contatore contatore = new Contatore("TestContatore", 10);
        CambiaValoreContatore cambiaValoreContatore = new CambiaValoreContatore(contatore, 20);

        JSONObject jsonObject = cambiaValoreContatore.toJSONObject();


        assertEquals("CambiaValoreContatore", jsonObject.getString("tipo"));
        assertEquals("TestContatore", jsonObject.getJSONObject("contatore").getString("nome"));
        assertEquals(20, jsonObject.getInt("valore"));
    }

    @Test
    void deserialize_DovrebbeCreareCambiaValoreContatoreCorrettoDaJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", "CambiaValoreContatore");
        jsonObject.put("contatore", new Contatore("TestContatore", 10).toJSONObject());
        jsonObject.put("valore", 20);


        CambiaValoreContatore cambiaValoreContatore = CambiaValoreContatore.deserialize(jsonObject);


        assertEquals("TestContatore", cambiaValoreContatore.getContatore().getNome());
        assertEquals(20, cambiaValoreContatore.getNuovoValore());
    }
}

package com.example.ifttt;


import org.json.JSONObject;

import java.io.Serializable;

public class MockRiproduciAudio implements Azione, Serializable {

    private boolean eseguita = false;

    // Costruttore
    public MockRiproduciAudio(String traccia) {
        // Non è necessario implementare il costruttore nel mock
    }

    // Sovrascrivi il metodo eseguiAzione per controllare il comportamento nel test
    @Override
    public void eseguiAzione() {
        // Simula il comportamento desiderato nel test
        eseguita = true;
    }

    // Sovrascrivi il metodo toJSONObject per soddisfare l'interfaccia Azione
    @Override
    public JSONObject toJSONObject() {
        // Puoi implementare la conversione dell'oggetto a JSONObject nel tuo modo
        // In questo mock, potresti semplicemente ritornare un oggetto vuoto
        return new JSONObject();
    }



    public boolean isEseguita() {
        return eseguita;
    }



}

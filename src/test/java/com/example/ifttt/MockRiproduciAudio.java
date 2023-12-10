package com.example.ifttt;


import org.json.JSONObject;

import java.io.Serializable;

public class MockRiproduciAudio implements Azione, Serializable {

    // Aggiungi eventuali campi necessari per il test
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

    // Puoi aggiungere altri metodi o campi necessari per il test

    // Aggiungi un metodo per verificare se l'azione è stata eseguita nel test
    public boolean isEseguita() {
        return eseguita;
    }

    // Puoi implementare altri metodi necessari per il test

    // Gli altri metodi come toString, deserialize e getTipo non sono necessari per il test del trigger

}

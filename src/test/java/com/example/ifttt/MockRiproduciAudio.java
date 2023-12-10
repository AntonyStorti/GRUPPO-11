package com.example.ifttt;

import org.json.JSONObject;

import java.io.Serializable;


public class MockRiproduciAudio implements Azione, Serializable {

    private boolean eseguita = false;


    public MockRiproduciAudio(String traccia) {
        // Non è necessario implementare il costruttore nel mock
    }


    @Override
    public void eseguiAzione() {
        // Simula il comportamento desiderato nel test
        eseguita = true;
    }


    @Override
    public JSONObject toJSONObject() {
        return new JSONObject();
    }


}

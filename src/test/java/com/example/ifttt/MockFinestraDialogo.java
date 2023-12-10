package com.example.ifttt;

import org.json.JSONObject;


public class MockFinestraDialogo implements Azione {

    private String testoUtente;
    private boolean eseguita;

    public MockFinestraDialogo(String testoUtente) {
        this.testoUtente = testoUtente;
        this.eseguita = false;
    }

    @Override
    public void eseguiAzione() {
        // Aggiorna il flag per indicare che l'azione è stata eseguita
        eseguita = true;
    }

    public boolean isEseguita() {
        return eseguita;
    }



    @Override
    public JSONObject toJSONObject() {
        // Implementazione del metodo astratto toJSONObject()
        return new JSONObject();
    }
}


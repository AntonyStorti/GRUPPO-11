package com.example.ifttt;

import org.json.JSONObject;

import java.time.LocalTime;

public class MockTempoDelGiorno implements Trigger {

    private boolean verificaCondizione;
    private LocalTime oraCorrente;

    public MockTempoDelGiorno(boolean verificaCondizione, LocalTime oraCorrente) {
        this.verificaCondizione = verificaCondizione;
        this.oraCorrente = oraCorrente;
    }

    @Override
    public boolean verificaCondizione() {
        return verificaCondizione;
    }

    public void setVerificaCondizione(boolean verificaCondizione) {
        this.verificaCondizione = verificaCondizione;
    }

    public void setOraCorrente(LocalTime oraCorrente) {
        this.oraCorrente = oraCorrente;
    }


    @Override
    public JSONObject toJSONObject() {
        // Implementazione del metodo astratto toJSONObject()
        return new JSONObject(); // Oppure, restituisci un oggetto JSON valido in base ai tuoi requisiti
    }
}

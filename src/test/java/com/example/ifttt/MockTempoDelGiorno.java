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


    @Override
    public JSONObject toJSONObject() {
        return new JSONObject();
    }

}

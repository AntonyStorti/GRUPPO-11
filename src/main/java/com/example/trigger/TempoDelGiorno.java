package com.example.trigger;

import org.json.JSONObject;

import java.time.LocalTime;



public class TempoDelGiorno extends TriggerTemporali {



    //-----COSTRUTTORE-----//
    public TempoDelGiorno(LocalTime tempo) {
        super(tempo);
    }
    //---------------------//



    @Override
    public boolean verificaCondizione() {

        LocalTime oraCorrente = LocalTime.now();

        return oraCorrente.getHour() == tempo.getHour() && oraCorrente.getMinute() == tempo.getMinute();


    }

    @Override
    public String toString() {
        return "Attiva alle ore " + tempo;
    }

    @Override
    public String getTipo() {
        return "TempoDelGiorno";
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("tempo", tempo.toString());
        return jsonObject;
    }
}

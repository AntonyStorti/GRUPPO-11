package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.io.Serializable;
import java.time.LocalTime;



public abstract class TriggerTemporali implements Trigger, Serializable {


    LocalTime tempo;


    //-----COSTRUTTORE------//
    public TriggerTemporali(LocalTime tempo) {
        this.tempo = tempo;
    }
    //---------------------//

    @Override
    public abstract JSONObject toJSONObject();

    public abstract String getTipo();

}

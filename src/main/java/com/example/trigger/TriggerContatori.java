package com.example.trigger;

import com.example.ifttt.Contatore;
import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class TriggerContatori implements Trigger, Serializable {

    public Contatore contatore;

    public TriggerContatori(Contatore contatore) {
        this.contatore = contatore;

    }

    @Override
    public abstract JSONObject toJSONObject();

    public abstract String getTipo();

}

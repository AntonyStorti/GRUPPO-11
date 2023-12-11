package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class TriggerSuFile implements Trigger, Serializable {

    String percorso;


    public TriggerSuFile(String percorso) {
        this.percorso = percorso;
    }

    @Override
    public abstract JSONObject toJSONObject();

    public abstract String getTipo();
}

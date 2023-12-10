package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

public class TriggerComposto implements Trigger {

    Trigger primoTrigger;
    Trigger secondoTrigger;
    Trigger terzoTriger;
    String condizione1;
    String condizione2;



    public TriggerComposto(Trigger primoTrigger, Trigger secondoTrigger, Trigger terzoTriger, String condizione1, String condizione2) {
        this.primoTrigger = primoTrigger;
        this.secondoTrigger = secondoTrigger;
        this.terzoTriger = terzoTriger;
        this.condizione1 = condizione1;
        this.condizione2 = condizione2;
    }



    @Override
    public boolean verificaCondizione() {
        return false;
    }


    @Override
    public JSONObject toJSONObject() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }


}

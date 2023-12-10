package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

public class TriggerComposto implements Trigger {

    Trigger primoTrigger;
    Trigger secondoTrigger;
    Trigger terzoTriger;
    String condizione1;
    String condizione2;
    boolean not1, not2, not3;



    public TriggerComposto(Trigger primoTrigger, Trigger secondoTrigger, Trigger terzoTriger, String condizione1, String condizione2, boolean not1, boolean not2, boolean not3) {
        this.primoTrigger = primoTrigger;
        this.secondoTrigger = secondoTrigger;
        this.terzoTriger = terzoTriger;
        this.condizione1 = condizione1;
        this.condizione2 = condizione2;
        this.not1 = not1;
        this.not2 = not2;
        this.not3 = not3;
    }

    public TriggerComposto(Trigger primoTrigger, Trigger secondoTrigger, Trigger terzoTriger){
        this.primoTrigger = primoTrigger;
        this.secondoTrigger = secondoTrigger;
        this.terzoTriger = terzoTriger;
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
        return "Se: " + primoTrigger + " & " + secondoTrigger + " & " + terzoTriger;
    }


}

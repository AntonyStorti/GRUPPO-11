package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.io.Serializable;


public class TriggerComposto implements Trigger, Serializable {

    Trigger primoTrigger;
    Trigger secondoTrigger;
    Trigger terzoTrigger;
    String condizione1;
    String condizione2;
    boolean not1, not2, not3;



    public TriggerComposto(Trigger primoTrigger, Trigger secondoTrigger, Trigger terzoTrigger, String condizione1, String condizione2, boolean not1, boolean not2, boolean not3) {
        this.primoTrigger = primoTrigger;
        this.secondoTrigger = secondoTrigger;
        this.terzoTrigger = terzoTrigger;
        this.condizione1 = condizione1;
        this.condizione2 = condizione2;
        this.not1 = not1;
        this.not2 = not2;
        this.not3 = not3;
    }

    public TriggerComposto(Trigger primoTrigger, boolean not1){
        this.primoTrigger = primoTrigger;
        this.not1 = not1;
    }

    public TriggerComposto(Trigger primoTrigger, Trigger secondoTrigger, String condizione1, boolean not1, boolean not2) {
        this.primoTrigger = primoTrigger;
        this.secondoTrigger = secondoTrigger;
        this.condizione1 = condizione1;
        this.not1 = not1;
        this.not2 = not2;
    }



    @Override
    public boolean verificaCondizione() {
        if (primoTrigger != null && secondoTrigger != null && terzoTrigger != null) {
            boolean risultatoPrimo = not1 ? !primoTrigger.verificaCondizione() : primoTrigger.verificaCondizione();
            boolean risultatoSecondo = not2 ? !secondoTrigger.verificaCondizione() : secondoTrigger.verificaCondizione();
            boolean risultatoTerzo = not3 ? !terzoTrigger.verificaCondizione() : terzoTrigger.verificaCondizione();
            if (condizione1 == "AND" && condizione2 == "AND")
                return (risultatoPrimo && risultatoSecondo && risultatoTerzo);
            else if (condizione1 == "AND" && condizione2 == "OR")
                return (risultatoPrimo && risultatoSecondo || risultatoTerzo);
            else if (condizione1 == "OR" && condizione2 =="AND")
                return (risultatoPrimo || risultatoSecondo && risultatoTerzo);
            else
                return (risultatoPrimo || risultatoSecondo || risultatoTerzo);
        } else if (primoTrigger != null && secondoTrigger != null) {
            boolean risultatoPrimo = not1 ? !primoTrigger.verificaCondizione() : primoTrigger.verificaCondizione();
            boolean risultatoSecondo = not2 ? !secondoTrigger.verificaCondizione() : secondoTrigger.verificaCondizione();
            if (condizione1 == "AND")
                return (risultatoPrimo && risultatoSecondo);
            else
                return (risultatoPrimo || risultatoSecondo);
        } else {
            boolean risultatoPrimo = not1 ? !primoTrigger.verificaCondizione() : primoTrigger.verificaCondizione();
            return risultatoPrimo;
        }
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        if (primoTrigger != null && secondoTrigger != null && terzoTrigger != null) {
            jsonObject.put("numeroTrigger", 3);
            jsonObject.put("not1", not1);
            jsonObject.put("primoTrigger", primoTrigger.toJSONObject());
            jsonObject.put("condizione1", condizione1);
            jsonObject.put("not2", not2);
            jsonObject.put("secondoTrigger", secondoTrigger.toJSONObject());
            jsonObject.put("condizione2", condizione2);
            jsonObject.put("not3", not3);
            jsonObject.put("terzoTrigger", terzoTrigger.toJSONObject());
        }
        if (primoTrigger != null && secondoTrigger != null) {
            jsonObject.put("numeroTrigger", 2);
            jsonObject.put("not1", not1);
            jsonObject.put("primoTrigger", primoTrigger.toJSONObject());
            jsonObject.put("condizione1", condizione1);
            jsonObject.put("not2", not2);
            jsonObject.put("secondoTrigger", secondoTrigger.toJSONObject());
        }
        else {
            jsonObject.put("numeroTrigger", 1);
            jsonObject.put("not1", not1);
            jsonObject.put("primoTrigger", primoTrigger.toJSONObject());
        }
        return jsonObject;
    }

    public static Trigger deserialize(JSONObject jsonTrigger) {
        int numTrigger = jsonTrigger.getInt("numeroTrigger");
        if (numTrigger == 3) {
            boolean not1 = jsonTrigger.getBoolean("not1");
            JSONObject obj1 = jsonTrigger.getJSONObject("primoTrigger");
            String tipoTrigger1 = obj1.getString("tipo");
            Trigger t1 = null;
            if ("TempoDelGiorno".equals(tipoTrigger1)) {
                t1 = TempoDelGiorno.deserialize(jsonTrigger);
            }
            if ("TriggerSettimanale".equals(tipoTrigger1)) {
               t1 = TriggerSettimanale.deserialize(jsonTrigger);
            }
            if ("TriggerMensile".equals(tipoTrigger1)) {
                t1 = TriggerMensile.deserialize(jsonTrigger);
            }
            if ("TriggerSuData".equals(tipoTrigger1)) {
                t1 = TriggerSuData.deserialize(jsonTrigger);
            }
            if ("FileEsiste".equals(tipoTrigger1)) {
                t1 = FileEsiste.deserialize(jsonTrigger);
            }
            if ("DimensioneFile".equals(tipoTrigger1)) {
                t1 = DimensioneFile.deserialize(jsonTrigger);
            }
            if ("ExitStatus".equals(tipoTrigger1)) {
                t1 = ExitStatus.deserialize(jsonTrigger);
            }
            if ("ContatoreIntero".equals(tipoTrigger1))
            {
                t1 = ContatoreIntero.deserialize(jsonTrigger);
            }
            if ("ConfrontoContatori".equals(tipoTrigger1))
            {
                t1 = ConfrontoContatori.deserialize(jsonTrigger);
            }
            if ("TriggerComposto".equals(tipoTrigger1))
            {
               t1 = TriggerComposto.deserialize(jsonTrigger);
            }
            String cond1 = jsonTrigger.getString("condizione1");
            boolean not2 = jsonTrigger.getBoolean("not2");
            JSONObject obj2 = jsonTrigger.getJSONObject("secondoTrigger");
            String tipoTrigger2 = obj2.getString("tipo");
            Trigger t2 = null;
            if ("TempoDelGiorno".equals(tipoTrigger2)) {
                t2 = TempoDelGiorno.deserialize(jsonTrigger);
            }
            if ("TriggerSettimanale".equals(tipoTrigger2)) {
                t2 = TriggerSettimanale.deserialize(jsonTrigger);
            }
            if ("TriggerMensile".equals(tipoTrigger2)) {
                t2 = TriggerMensile.deserialize(jsonTrigger);
            }
            if ("TriggerSuData".equals(tipoTrigger2)) {
                t2 = TriggerSuData.deserialize(jsonTrigger);
            }
            if ("FileEsiste".equals(tipoTrigger2)) {
                t2 = FileEsiste.deserialize(jsonTrigger);
            }
            if ("DimensioneFile".equals(tipoTrigger2)) {
                t2 = DimensioneFile.deserialize(jsonTrigger);
            }
            if ("ExitStatus".equals(tipoTrigger2)) {
                t2 = ExitStatus.deserialize(jsonTrigger);
            }
            if ("ContatoreIntero".equals(tipoTrigger2))
            {
                t2 = ContatoreIntero.deserialize(jsonTrigger);
            }
            if ("ConfrontoContatori".equals(tipoTrigger2))
            {
                t2 = ConfrontoContatori.deserialize(jsonTrigger);
            }
            if ("TriggerComposto".equals(tipoTrigger2))
            {
                t2 = TriggerComposto.deserialize(jsonTrigger);
            }
            String cond2 = jsonTrigger.getString("condizione2");
            boolean not3 = jsonTrigger.getBoolean("not3");
            JSONObject obj3 = jsonTrigger.getJSONObject("terzoTrigger");
            String tipoTrigger3 = obj3.getString("tipo");
            Trigger t3 = null;
            if ("TempoDelGiorno".equals(tipoTrigger3)) {
                t3 = TempoDelGiorno.deserialize(jsonTrigger);
            }
            if ("TriggerSettimanale".equals(tipoTrigger3)) {
                t3 = TriggerSettimanale.deserialize(jsonTrigger);
            }
            if ("TriggerMensile".equals(tipoTrigger3)) {
                t3 = TriggerMensile.deserialize(jsonTrigger);
            }
            if ("TriggerSuData".equals(tipoTrigger3)) {
                t3 = TriggerSuData.deserialize(jsonTrigger);
            }
            if ("FileEsiste".equals(tipoTrigger3)) {
                t3 = FileEsiste.deserialize(jsonTrigger);
            }
            if ("DimensioneFile".equals(tipoTrigger3)) {
                t3 = DimensioneFile.deserialize(jsonTrigger);
            }
            if ("ExitStatus".equals(tipoTrigger3)) {
                t3 = ExitStatus.deserialize(jsonTrigger);
            }
            if ("ContatoreIntero".equals(tipoTrigger3))
            {
                t3 = ContatoreIntero.deserialize(jsonTrigger);
            }
            if ("ConfrontoContatori".equals(tipoTrigger3))
            {
                t3 = ConfrontoContatori.deserialize(jsonTrigger);
            }
            if ("TriggerComposto".equals(tipoTrigger3))
            {
                t3 = TriggerComposto.deserialize(jsonTrigger);
            }

            return new TriggerComposto(t1, t2, t3, cond1, cond2, not1, not2, not3);

        }
        if (numTrigger == 2) {
            boolean not1 = jsonTrigger.getBoolean("not1");
            JSONObject obj1 = jsonTrigger.getJSONObject("primoTrigger");
            String tipoTrigger1 = obj1.getString("tipo");
            Trigger t1 = null;
            if ("TempoDelGiorno".equals(tipoTrigger1)) {
                t1 = TempoDelGiorno.deserialize(jsonTrigger);
            }
            if ("TriggerSettimanale".equals(tipoTrigger1)) {
                t1 = TriggerSettimanale.deserialize(jsonTrigger);
            }
            if ("TriggerMensile".equals(tipoTrigger1)) {
                t1 = TriggerMensile.deserialize(jsonTrigger);
            }
            if ("TriggerSuData".equals(tipoTrigger1)) {
                t1 = TriggerSuData.deserialize(jsonTrigger);
            }
            if ("FileEsiste".equals(tipoTrigger1)) {
                t1 = FileEsiste.deserialize(jsonTrigger);
            }
            if ("DimensioneFile".equals(tipoTrigger1)) {
                t1 = DimensioneFile.deserialize(jsonTrigger);
            }
            if ("ExitStatus".equals(tipoTrigger1)) {
                t1 = ExitStatus.deserialize(jsonTrigger);
            }
            if ("ContatoreIntero".equals(tipoTrigger1)) {
                t1 = ContatoreIntero.deserialize(jsonTrigger);
            }
            if ("ConfrontoContatori".equals(tipoTrigger1)) {
                t1 = ConfrontoContatori.deserialize(jsonTrigger);
            }
            if ("TriggerComposto".equals(tipoTrigger1)) {
                t1 = TriggerComposto.deserialize(jsonTrigger);
            }
            String cond1 = jsonTrigger.getString("condizione1");
            boolean not2 = jsonTrigger.getBoolean("not2");
            JSONObject obj2 = jsonTrigger.getJSONObject("secondoTrigger");
            String tipoTrigger2 = obj2.getString("tipo");
            Trigger t2 = null;
            if ("TempoDelGiorno".equals(tipoTrigger2)) {
                t2 = TempoDelGiorno.deserialize(jsonTrigger);
            }
            if ("TriggerSettimanale".equals(tipoTrigger2)) {
                t2 = TriggerSettimanale.deserialize(jsonTrigger);
            }
            if ("TriggerMensile".equals(tipoTrigger2)) {
                t2 = TriggerMensile.deserialize(jsonTrigger);
            }
            if ("TriggerSuData".equals(tipoTrigger2)) {
                t2 = TriggerSuData.deserialize(jsonTrigger);
            }
            if ("FileEsiste".equals(tipoTrigger2)) {
                t2 = FileEsiste.deserialize(jsonTrigger);
            }
            if ("DimensioneFile".equals(tipoTrigger2)) {
                t2 = DimensioneFile.deserialize(jsonTrigger);
            }
            if ("ExitStatus".equals(tipoTrigger2)) {
                t2 = ExitStatus.deserialize(jsonTrigger);
            }
            if ("ContatoreIntero".equals(tipoTrigger2)) {
                t2 = ContatoreIntero.deserialize(jsonTrigger);
            }
            if ("ConfrontoContatori".equals(tipoTrigger2)) {
                t2 = ConfrontoContatori.deserialize(jsonTrigger);
            }
            if ("TriggerComposto".equals(tipoTrigger2)) {
                t2 = TriggerComposto.deserialize(jsonTrigger);
            }

            return new TriggerComposto(t1, t2, cond1, not1, not2);
        }
        boolean not1 = jsonTrigger.getBoolean("not1");
        JSONObject obj1 = jsonTrigger.getJSONObject("primoTrigger");
        String tipoTrigger1 = obj1.getString("tipo");
        Trigger t1 = null;
        if ("TempoDelGiorno".equals(tipoTrigger1)) {
            t1 = TempoDelGiorno.deserialize(jsonTrigger);
        }
        if ("TriggerSettimanale".equals(tipoTrigger1)) {
            t1 = TriggerSettimanale.deserialize(jsonTrigger);
        }
        if ("TriggerMensile".equals(tipoTrigger1)) {
            t1 = TriggerMensile.deserialize(jsonTrigger);
        }
        if ("TriggerSuData".equals(tipoTrigger1)) {
            t1 = TriggerSuData.deserialize(jsonTrigger);
        }
        if ("FileEsiste".equals(tipoTrigger1)) {
            t1 = FileEsiste.deserialize(jsonTrigger);
        }
        if ("DimensioneFile".equals(tipoTrigger1)) {
            t1 = DimensioneFile.deserialize(jsonTrigger);
        }
        if ("ExitStatus".equals(tipoTrigger1)) {
            t1 = ExitStatus.deserialize(jsonTrigger);
        }
        if ("ContatoreIntero".equals(tipoTrigger1)) {
            t1 = ContatoreIntero.deserialize(jsonTrigger);
        }
        if ("ConfrontoContatori".equals(tipoTrigger1)) {
            t1 = ConfrontoContatori.deserialize(jsonTrigger);
        }
        if ("TriggerComposto".equals(tipoTrigger1)) {
            t1 = TriggerComposto.deserialize(jsonTrigger);
        }

        return new TriggerComposto(t1, not1);

    }

    public String getTipo() {
        return "TriggerComposto";
    }

    @Override
    public String toString() {
        return "Se: " + primoTrigger + " & " + secondoTrigger + " & " + terzoTrigger;
    }


}

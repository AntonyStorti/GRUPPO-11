package com.example.trigger;

import com.example.ifttt.Contatore;
import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.util.Objects;


public class ConfrontoContatori extends TriggerContatori {

    String confronto;
    Contatore contatore2;


    public ConfrontoContatori(Contatore contatore1, String confronto, Contatore contatore2) {
        super(contatore1);
        this.confronto = confronto;
        this.contatore2 = contatore2;
    }


    @Override
    public boolean verificaCondizione() {

        if (confronto != null) {

            if (confronto.equals(">"))
                return contatore.getValore() > contatore2.getValore();

            if (confronto.equals("<"))
                return contatore.getValore() < contatore2.getValore();

            if (confronto.equals("="))
                return Objects.equals(contatore.getValore(), contatore2.getValore());

            if (confronto.equals(">="))
                return contatore.getValore() >= contatore2.getValore();

            if (confronto.equals("<="))
                return contatore.getValore() <= contatore2.getValore();

        }

        return false;
    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("contatore1", contatore.toJSONObject());
        jsonObject.put("confronto", confronto);
        jsonObject.put("contatore2", contatore2.toJSONObject());

        return jsonObject;

    }

    public static Trigger deserialize(JSONObject jsonTrigger) {

        JSONObject c1 = jsonTrigger.getJSONObject("contatore1");
        Contatore contatore1 = Contatore.deserializeContatore(c1);
        String confronto = jsonTrigger.getString("confronto");
        JSONObject c2 = jsonTrigger.getJSONObject("contatore2");
        Contatore contatore2 = Contatore.deserializeContatore(c2);
        return new ConfrontoContatori(contatore1, confronto, contatore2);

    }

    @Override
    public String getTipo() {
        return "ConfrontoContatori";
    }

    @Override
    public String toString() {
        return "Se il Contatore " + contatore.getNome() + " è " + confronto + " " + contatore2.getNome();
    }

}

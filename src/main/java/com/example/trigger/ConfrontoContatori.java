package com.example.trigger;

import com.example.ifttt.Contatore;
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
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("contatore1", contatore.toJSONObject());
        jsonObject.put("confronto", confronto);
        jsonObject.put("contatore2", contatore2.toJSONObject());
        return jsonObject;
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

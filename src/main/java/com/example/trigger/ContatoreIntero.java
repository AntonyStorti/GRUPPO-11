package com.example.trigger;

import com.example.ifttt.Contatore;
import org.json.JSONObject;

public class ContatoreIntero extends TriggerContatori{

    Integer valore;
    String confronto;

    public ContatoreIntero(Contatore contatore, Integer valore, String confronto) {
        super(contatore);
        this.valore = valore;
        this.confronto = confronto;
    }

    @Override
    public boolean verificaCondizione() {

        if (confronto != null) {

            if (confronto.equals(">"))
                return contatore.getValore() > valore;

            if (confronto.equals("<"))
                return contatore.getValore() < valore;

            if (confronto.equals("="))
                return contatore.getValore() == valore;

            if (confronto.equals(">="))
                return contatore.getValore() >= valore;

            if (confronto.equals("<="))
                return contatore.getValore() <= valore;

        }

        return false;
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("contatore", contatore.toJSONObject());
        jsonObject.put("valore", valore);
        jsonObject.put("confronto", confronto);
        return jsonObject;
    }

    @Override
    public String getTipo() {
        return "ContatoreIntero";
    }


    @Override
    public String toString() {
        return "Se il Contatore " + contatore.getNome() + " è " + confronto + " " + valore;
    }

}

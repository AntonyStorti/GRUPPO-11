package com.example.trigger;

import com.example.ifttt.Contatore;
import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.util.Objects;


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
                return Objects.equals(contatore.getValore(), valore);

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
        jsonObject.put("tipo", getTipo());
        jsonObject.put("contatore", contatore.toJSONObject());
        jsonObject.put("valore", valore);
        jsonObject.put("confronto", confronto);

        return jsonObject;

    }

    public static Trigger deserialize(JSONObject jsonTrigger) {

        JSONObject c = jsonTrigger.getJSONObject("contatore");
        Contatore contatore = Contatore.deserializeContatore(c);
        Integer valore = jsonTrigger.getInt("valore");
        String confronto = jsonTrigger.getString("confronto");
        return new ContatoreIntero(contatore, valore, confronto);

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

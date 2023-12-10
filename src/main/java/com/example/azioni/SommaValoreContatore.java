package com.example.azioni;

import com.example.controllerGUI.HelloController;
import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import org.json.JSONObject;


public class SommaValoreContatore extends AzioniSuContatori {

    Integer sommaValore;


    public SommaValoreContatore(Contatore contatore, Integer sommaValore) {
        super(contatore);
        this.sommaValore = sommaValore;
    }

    HelloController helloController = new HelloController();


    @Override
    public void eseguiAzione() {

        GestoreContatori.listaContatori.remove(contatore);

        Integer c = contatore.getValore();
        contatore.setValore(c + sommaValore);

        GestoreContatori.listaContatori.add(contatore);

        helloController.salvaContatoriSuFile();

    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("contatore", contatore.toJSONObject());
        jsonObject.put("valore", sommaValore);

        return jsonObject;

    }

    public static SommaValoreContatore deserialize(JSONObject jsonObject) {

        JSONObject c = jsonObject.getJSONObject("contatore");
        Contatore contatore = Contatore.deserializeContatore(c);
        Integer sommaValore = jsonObject.getInt("valore");

        return new SommaValoreContatore(contatore, sommaValore);

    }


    public Integer getSommaValore() {
        return sommaValore;
    }

    @Override
    public String getTipo() {
        return "SommaValoreContatore";
    }

    @Override
    public String toString() {
        return "Somma Valore al Contatore " + contatore.getNome();
    }

}

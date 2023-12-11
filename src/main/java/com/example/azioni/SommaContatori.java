package com.example.azioni;

import com.example.controllerGUI.HelloController;
import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import org.json.JSONObject;


public class SommaContatori extends AzioniSuContatori {

    Contatore contatore2;


    public SommaContatori(Contatore contatore, Contatore contatore2) {
        super(contatore);
        this.contatore2 = contatore2;
    }


    HelloController helloController = new HelloController();


    @Override
    public void eseguiAzione() {

        GestoreContatori.listaContatori.remove(contatore);

        Integer c1 = contatore.getValore();
        Integer c2 = contatore2.getValore();
        contatore.setValore(c1 + c2);

        GestoreContatori.listaContatori.add(contatore);

        helloController.salvaContatoriSuFile();

    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("contatore1", contatore.toJSONObject());
        jsonObject.put("contatore2", contatore2.toJSONObject());

        return jsonObject;

    }

    public static SommaContatori deserialize(JSONObject jsonObject) {

        JSONObject c1 = jsonObject.getJSONObject("contatore");
        Contatore contatore1 = Contatore.deserializeContatore(c1);
        JSONObject c2 = jsonObject.getJSONObject("contatore");
        Contatore contatore2 = Contatore.deserializeContatore(c2);

        return new SommaContatori(contatore1, contatore2);

    }

    @Override
    public String getTipo() {
        return "SommaContatori";
    }

    @Override
    public String toString() {
        return "Somma al Contatore " + contatore.getNome() + " il Contatore " + contatore2.getNome();
    }

}

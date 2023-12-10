package com.example.azioni;

import com.example.controllerGUI.HelloController;
import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import org.json.JSONObject;

public class SommaValoreContatore extends AzioniSuContatori {

    Integer sommaValore;

    HelloController helloController = new HelloController();

    public SommaValoreContatore(Contatore contatore, Integer sommaValore) {
        super(contatore);
        this.sommaValore = sommaValore;
    }

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
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("contatore", contatore.toJSONObject());
        jsonObject.put("valore", sommaValore);
        return jsonObject;
    }

    public static SommaValoreContatore deserialize(JSONObject jsonObject) {
        // Leggi i dati dal JSONObject e crea un'istanza di SommaValoreContatore
        JSONObject c = jsonObject.getJSONObject("contatore");
        Contatore contatore = Contatore.deserializeContatore(c);
        Integer sommaValore = jsonObject.getInt("valore");
        return new SommaValoreContatore(contatore, sommaValore);
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

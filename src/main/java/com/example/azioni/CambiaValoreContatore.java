package com.example.azioni;

import com.example.controllerGUI.HelloController;
import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import org.json.JSONObject;



public class CambiaValoreContatore extends AzioniSuContatori {


    Integer nuovoValore;


    public CambiaValoreContatore(Contatore contatore, Integer nuovoValore) {
        super(contatore);
        this.nuovoValore = nuovoValore;
    }

    HelloController helloController = new HelloController();



    @Override
    public void eseguiAzione() {

        contatore.setValore(nuovoValore);

        int i;

        for (Contatore c: GestoreContatori.listaContatori) {
            if (c.getNome().equals(contatore.getNome())) {
                i = GestoreContatori.listaContatori.indexOf(c);
                GestoreContatori.listaContatori.set(i, contatore);
            }
        }

        helloController.salvaContatoriSuFile();
    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("contatore", contatore.toJSONObject());
        jsonObject.put("valore", nuovoValore);

        return jsonObject;

    }

    public static CambiaValoreContatore deserialize(JSONObject jsonObject) {

        JSONObject c = jsonObject.getJSONObject("contatore");
        Contatore contatore = Contatore.deserializeContatore(c);
        Integer nuovoValore = jsonObject.getInt("valore");

        return new CambiaValoreContatore(contatore, nuovoValore);

    }

    @Override
    public String getTipo() {
        return "CambiaValoreContatore";
    }

    @Override
    public String toString() {
        return "Cambia Valore al Contatore " + contatore.getNome();
    }

}

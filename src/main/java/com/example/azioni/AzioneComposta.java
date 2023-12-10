package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;

public class AzioneComposta implements Azione {

    private Azione primaAzione;
    private Azione secondaAzione;
    private Azione terzaAzione;


    public AzioneComposta(Azione primaAzione, Azione secondaAzione, Azione terzaAzione) {
        this.primaAzione = primaAzione;
        this.secondaAzione = secondaAzione;
        this.terzaAzione = terzaAzione;
    }



    @Override
    public void eseguiAzione() {

        primaAzione.eseguiAzione();
        secondaAzione.eseguiAzione();
        terzaAzione.eseguiAzione();

    }


    @Override
    public JSONObject toJSONObject() {
        return null;
    }


    @Override
    public String toString() {
        return "AzioneComposta: " + primaAzione + " & " + secondaAzione + " & " + terzaAzione;
    }

}

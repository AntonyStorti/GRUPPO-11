package com.example.azioni;

import com.example.ifttt.Azione;
import com.example.ifttt.Contatore;
import org.json.JSONObject;

import java.io.Serializable;

public abstract class AzioniSuContatori implements Azione, Serializable {

    Contatore contatore;

    public AzioniSuContatori(Contatore contatore) {
        this.contatore = contatore;
    }

    public abstract JSONObject toJSONObject();

    public abstract String getTipo();
}

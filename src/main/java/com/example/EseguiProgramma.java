package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class EseguiProgramma implements Azione, Serializable {

    private String percorsoAPP;
    public EseguiProgramma(String percorsoAPP) {

        this.percorsoAPP = percorsoAPP;

    }

    @Override
    public void eseguiAzione() {

        if (percorsoAPP != null) {

            try {

                // Ottieni il file eseguibile dal percorso
                File eseguibile = new File(percorsoAPP);

                if (eseguibile.exists()) {
                    // Apri il programma con l'applicazione predefinita del sistema
                    Desktop.getDesktop().open(eseguibile);

                }

        } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }


    }

    @Override
    public String toString() {
        return "Esegui un programma esterno";
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di azione
        jsonObject.put("percorsoAPP", percorsoAPP);
        return jsonObject;
    }
    // Metodo statico di fabbrica per deserializzare RiproduciAudio
    public static EseguiProgramma deserialize(JSONObject jsonObject) {
        // Leggi i dati dal JSONObject e crea un'istanza di RiproduciAudio
        String percorsoAPP = jsonObject.getString("percorsoAPP");
        return new EseguiProgramma(percorsoAPP);
    }
    // Metodo astratto per ottenere il tipo dell'azione
    public String getTipo() {
        return "EseguiProgramma";
    }

}

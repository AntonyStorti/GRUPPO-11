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

                    // Esegui il programma
                    Desktop.getDesktop().open(eseguibile);

                }

        } catch (IOException e) {

                throw new RuntimeException(e);

            }


        }


    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("percorsoAPP", percorsoAPP);

        return jsonObject;

    }

    public static EseguiProgramma deserialize(JSONObject jsonObject) {

        String percorsoAPP = jsonObject.getString("percorsoAPP");

        return new EseguiProgramma(percorsoAPP);

    }

    public String getTipo() {
        return "EseguiProgramma";
    }

    @Override
    public String toString() {
        return "Esegui un programma esterno";
    }


}

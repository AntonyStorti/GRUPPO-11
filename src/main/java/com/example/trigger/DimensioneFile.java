package com.example.trigger;

import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


public class DimensioneFile extends TriggerSuFile {


    int dimensione;
    String unita;


    public DimensioneFile(String percorso, int dimensione, String unita) {
        super(percorso);
        this.dimensione = dimensione;
        this.unita = unita;
    }



    @Override
    public boolean verificaCondizione() {

        try {

            // Ottieni le dimensioni del file in byte:
            double dimensioniInByte = Files.size(Path.of(percorso));

            if (unita != null) {
                if (unita.equals("MB")) {

                    double dimensioniInMegabyte = dimensioniInByte / (1024 * 1024);
                    return dimensioniInMegabyte > dimensione;


            }

            if (unita.equals("KB")) {

                double dimensioniInKilobyte = dimensioniInByte / 1024;
                return dimensioniInKilobyte > dimensione;

            }
        }
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }


    @Override
    public String toString() {

        if (unita != null) {
            if (unita.equals("MB")) {
                return "Se il File è > di " + dimensione + "MB";
            } else if (unita.equals("KB")) {
                return "Se il File è > di " + dimensione + "KB";
            }
        }

        return "Invalid unit specified";

    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("percorso", percorso);
        jsonObject.put("dimensione", dimensione);
        jsonObject.put("unita", unita);

        return jsonObject;

    }


    @Override
    public String getTipo() {
        return "DimensioneFile";
    }

}

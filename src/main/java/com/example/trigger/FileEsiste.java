package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class FileEsiste extends TriggerSuFile {

    String nomeFile;


    public FileEsiste(String percorso, String nomeFile) {
        super(percorso);
        this.nomeFile = nomeFile;
    }


    @Override
    public boolean verificaCondizione() {

        // Ricostruisci il percorso completo del file:
        Path percorsoCompleto = Paths.get(percorso, nomeFile);

        return Files.exists(percorsoCompleto);

    }


    @Override
    public String toString() {
        return "Se esiste: " + nomeFile;
    }



    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("percorso", percorso);
        jsonObject.put("nomefile", nomeFile);

        return jsonObject;

    }

    public static Trigger deserialize(JSONObject jsonTrigger) {

        String percorso = jsonTrigger.getString("percorso");
        String nomefile = jsonTrigger.getString("nomefile");
        return new FileEsiste(percorso, nomefile);

    }


    @Override
    public String getTipo() {
        return "FileEsiste";
    }
}

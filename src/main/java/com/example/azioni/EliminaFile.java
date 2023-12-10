package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class EliminaFile extends AzioniSuFile {


    public EliminaFile(String percorso) {
        super(percorso);
    }


    @Override
    public void eseguiAzione() {
        try {

            Path path = Paths.get(percorso);

            // Elimina il file
            Files.delete(path);

        } catch (IOException e) {

            System.err.println("Si è verificato un errore durante l'eliminazione del file.");
            e.printStackTrace();

        }
    }

    @Override
    public String toString() {
        return "Elimina un File";
    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("percorso", percorso);

        return jsonObject;

    }

    public static Azione deserialize(JSONObject jsonAzione) {

        String percorso = jsonAzione.getString("percorso");

        return new EliminaFile(percorso);

    }


    @Override
    public String getTipo() {
        return "EliminaFile";
    }

}

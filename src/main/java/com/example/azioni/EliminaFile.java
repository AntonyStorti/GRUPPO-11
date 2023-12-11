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

    public static Azione deserialize(JSONObject jsonAzione) {
        String percorso = jsonAzione.getString("percorso");
        return new EliminaFile(percorso);
    }


    @Override
    public void eseguiAzione() {
        try {
            // Converte il percorso in un oggetto Path
            Path path = Paths.get(percorso);

            // Elimina il file
            Files.delete(path);

            System.out.println("File eliminato con successo.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante l'eliminazione del file.");
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
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di azione
        jsonObject.put("percorso", percorso);
        return jsonObject;
    }


    @Override
    public String getTipo() {
        return "EliminaFile";
    }
}

package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class CopiaSposta extends AzioniSuFile {

    String destinazione;
    String scelta;

    private String sceltaCopia = "Copia nella destinazione";
    private String sceltaSposta = "Sposta nella destinazione";



    public CopiaSposta(String percorso, String destinazione, String scelta) {
        super(percorso);
        this.destinazione = destinazione;
        this.scelta = scelta;
    }

    public static Azione deserialize(JSONObject jsonAzione) {
        String percorso = jsonAzione.getString("percorso");
        String destinazione = jsonAzione.getString("destinazione");
        String scelta = jsonAzione.getString("scelta");
        return new CopiaSposta(percorso, destinazione, scelta);
    }


    @Override
    public void eseguiAzione() {


        Path origine = Path.of(percorso);
        Path dest = Path.of(destinazione);


        if (scelta.equals(sceltaSposta)){

            try {

                // Sposta il file
                Files.move(origine, dest.resolve(origine.getFileName()));

                System.out.println("File spostato con successo.");

            } catch (IOException e) {

                System.out.println("Si è verificato un errore durante lo spostamento del file.");
                e.printStackTrace();

            }

        }



        if (scelta.equals(sceltaCopia)){

            try {
                // Copia il file
                Files.copy(origine, dest.resolve(origine.getFileName()));

                System.out.println("File copiato con successo.");
            } catch (IOException e) {
                System.out.println("Si è verificato un errore durante la copia del file.");
                e.printStackTrace();
            }

        }


    }



    @Override
    public String toString() {

        if (scelta.equals(sceltaCopia))
            return "Copia File";
        else
            return "Sposta File";

    }



    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di azione
        jsonObject.put("percorso", percorso);
        jsonObject.put("destinazione", destinazione);
        jsonObject.put("scelta", scelta);
        return jsonObject;
    }


    @Override
    public String getTipo() {
        return "CopiaSposta";
    }
}

package com.example.azioni;
import com.example.ifttt.Azione;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;

public class AggiungiStringa extends AzioniSuFile {

    private String testoDaAggiungere;

    public AggiungiStringa(String percorso, String testoDaAggiungere) {
        super(percorso);
        this.testoDaAggiungere = testoDaAggiungere;
    }

    public static Azione deserialize(JSONObject jsonAzione) {
        String percorso = jsonAzione.getString("percorso");
        String testodaaggiungere = jsonAzione.getString("testodaaggiungere");
        return new AggiungiStringa(percorso, testodaaggiungere);
    }


    @Override
    public void eseguiAzione() {

        try {
            // Apre il file in modalità append utilizzando solo FileWriter
            FileWriter fileWriter = new FileWriter(percorso, true);

            // Aggiunge la stringa al file
            fileWriter.write(testoDaAggiungere);
            fileWriter.write(System.lineSeparator()); // Aggiunge una nuova riga se necessario

            // Chiude il FileWriter
            fileWriter.close();

            System.out.println("Stringa aggiunta con successo.");
        } catch (IOException e) {
            System.out.println("Si è verificato un errore durante l'aggiunta della stringa al file.");
            e.printStackTrace();
        }

    }


    @Override
    public String toString() {
        return "Aggiungi al file: " + testoDaAggiungere;
    }


    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di azione
        jsonObject.put("percorso", percorso);
        jsonObject.put("testodaaggiungere", testoDaAggiungere);
        return jsonObject;
    }


    @Override
    public String getTipo() {
        return "AggiungiStringa";
    }
}

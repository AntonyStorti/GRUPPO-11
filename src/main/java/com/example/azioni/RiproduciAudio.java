package com.example.azioni;

import com.example.ifttt.Azione;
import org.json.JSONObject;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class RiproduciAudio implements Azione, Serializable {


    private String traccia;

    public RiproduciAudio(String traccia) {
        this.traccia = traccia;
    }



    @Override
    public void eseguiAzione() {

        if (traccia != null) {

            try {
                // Ottieni il file audio dal percorso
                File fileAudio = new File(traccia);

                // Crea un AudioInputStream dal file audio
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileAudio);

                // Ottieni il formato dell'audio
                AudioFormat audioFormat = audioInputStream.getFormat();

                // Crea una sorgente dati per l'audio
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
                SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                // Leggi e riproduci l'audio
                byte[] buffer = new byte[4096];
                int bytesRead;

                while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
                    sourceDataLine.write(buffer, 0, bytesRead);
                }

                // Chiudi la sorgente dati
                sourceDataLine.drain();
                sourceDataLine.close();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                e.printStackTrace();
            }

        }


    }

    @Override
    public String toString() {
        return "Riproduci File Audio";
    }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di azione
        jsonObject.put("traccia", traccia);
        return jsonObject;
    }
    // Metodo statico di fabbrica per deserializzare RiproduciAudio
    public static RiproduciAudio deserialize(JSONObject jsonObject) {
        // Leggi i dati dal JSONObject e crea un'istanza di RiproduciAudio
        String traccia = jsonObject.getString("traccia");
        return new RiproduciAudio(traccia);
    }
    // Metodo astratto per ottenere il tipo dell'azione
    public String getTipo() {
        return "RiproduciAudio";
    }
}

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

                File fileAudio = new File(traccia);

                // Crea un AudioInputStream dal file audio
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(fileAudio);

                // Ottieni il formato dell'audio
                AudioFormat audioFormat = audioInputStream.getFormat();

                // Crea una sorgente dati per leggere l'audio:
                DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
                SourceDataLine sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLineInfo);
                sourceDataLine.open(audioFormat);
                sourceDataLine.start();

                // Riproduci l'audio:
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
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("traccia", traccia);

        return jsonObject;

    }

    public static RiproduciAudio deserialize(JSONObject jsonObject) {

        String traccia = jsonObject.getString("traccia");

        return new RiproduciAudio(traccia);

    }


    public String getTipo() {
        return "RiproduciAudio";
    }

    @Override
    public String toString() {
        return "Riproduci File Audio";
    }

}

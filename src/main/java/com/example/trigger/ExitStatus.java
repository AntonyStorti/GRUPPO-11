package com.example.trigger;

import com.example.ifttt.Trigger;
import org.json.JSONObject;

import java.io.IOException;

public class ExitStatus implements Trigger {

    String percorso;
    Integer exitStatus;



    public ExitStatus(String percorso, Integer exitStatus) {
        this.percorso = percorso;
        this.exitStatus = exitStatus;
    }



    @Override
    public boolean verificaCondizione() {


        ProcessBuilder processBuilder = new ProcessBuilder(percorso);

        try {

            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            // Stampare lo stato di uscita
            System.out.println("Stato di uscita: " + exitCode);


            if (exitStatus.equals(exitCode))
                return true;
            else
                return false;


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;

    }



    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());  // Supponiamo che getTipo() restituisca una stringa che identifica il tipo di trigger
        jsonObject.put("percorso", percorso);
        jsonObject.put("exitStatus", exitStatus);
        return jsonObject;
    }


    public String getTipo() {
        return "ExitStatus";
    }

    @Override
    public String toString() {
        return "Se il programma ha 'Exit Status': " + exitStatus;
    }

}

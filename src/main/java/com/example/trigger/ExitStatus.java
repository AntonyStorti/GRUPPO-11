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


    }



    @Override
    public JSONObject toJSONObject() {
        return null;
    }

}

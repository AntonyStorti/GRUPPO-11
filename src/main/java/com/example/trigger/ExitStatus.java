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


            return exitStatus.equals(exitCode);


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        return false;

    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("percorso", percorso);
        jsonObject.put("exitStatus", exitStatus);

        return jsonObject;

    }

    public static Trigger deserialize(JSONObject jsonTrigger) {

        String percorso = jsonTrigger.getString("percorso");
        Integer exitStatus = jsonTrigger.getInt("exitStatus");
        return new ExitStatus(percorso, exitStatus);

    }


    public String getTipo() {
        return "ExitStatus";
    }

}

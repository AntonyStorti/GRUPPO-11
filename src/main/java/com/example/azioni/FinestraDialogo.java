package com.example.azioni;

import com.example.ifttt.Azione;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import org.json.JSONObject;

import java.io.Serializable;


public class FinestraDialogo implements Azione, Serializable {


    String testoUtente;


    public FinestraDialogo(String testoUtente) {
        this.testoUtente = testoUtente;
    }



    @Override
    public void eseguiAzione() {

        Platform.runLater(() -> {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Messaggio");
            alert.setHeaderText(null);
            alert.setContentText(testoUtente);

            alert.showAndWait();

        });

    }

    @Override
    public String toString() {
        return "Visualizza un messaggio a video";
    }


    @Override
    public JSONObject toJSONObject() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("tipo", getTipo());
        jsonObject.put("testoUtente", testoUtente);

        return jsonObject;

    }

    public static FinestraDialogo deserialize(JSONObject jsonObject) {

        String testoUtente = jsonObject.getString("testoUtente");

        return new FinestraDialogo(testoUtente);

    }

    public String getTipo() {
        return "FinestraDialogo";
    }

    public String getTestoUtente() {
        return testoUtente;
    }

}

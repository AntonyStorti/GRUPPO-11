package com.example.ifttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class HelloController {

    @FXML
    private Button creaRegolaButton;

    @FXML
    private void initialize() {
        creaRegolaButton.setOnAction(event -> apriFinestraCreazioneRegola());
    }

    @FXML
    private void apriFinestraCreazioneRegola() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("OperaSullaRegola.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("Crea la tua Regola...");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
        }
    }

    public void eliminaRegola(ActionEvent actionEvent) {
    }


}
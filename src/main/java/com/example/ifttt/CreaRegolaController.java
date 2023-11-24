package com.example.ifttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;



public class CreaRegolaController {


    @FXML
    private ChoiceBox<String> sceltaTrigger;

    @FXML
    private void initialize() {
        sceltaTrigger.setOnAction(event -> apriFinestraSceltaOra());
    }


    public void apriFinestraSceltaOra() {

        String selectedItem = sceltaTrigger.getSelectionModel().getSelectedItem();
        String sceltaOra = "Seleziona un orario";

        if (selectedItem.equals(sceltaOra)){

            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("SceltaOra.fxml"));
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setTitle("Seleziona un orario...");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
            }

        }




    }



}



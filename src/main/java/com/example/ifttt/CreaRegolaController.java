package com.example.ifttt;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.application.Application;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;
import java.io.File;
import java.util.EventObject;




public class CreaRegolaController {


    @FXML
    private ChoiceBox<String> sceltaTrigger;
    @FXML
    private ChoiceBox<String> sceltaAzione;

    @FXML
    private void initialize() {
        sceltaTrigger.setOnAction(event -> apriFinestraSceltaOra());
        sceltaAzione.setOnAction(event -> apriFinestraSceltaAudio());
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

    private void apriFinestraSceltaAudio() {

        String selectedItem = sceltaAzione.getSelectionModel().getSelectedItem();
        String sceltaAudio = "Riproduci un audio";

        if (selectedItem.equals(sceltaAudio)){

            try {

                // Creare un oggetto FileChooser
                FileChooser fileChooser = new FileChooser();
                fileChooser.setTitle("Seleziona un file audio...");
                fileChooser.getExtensionFilters().addAll(
                        new FileChooser.ExtensionFilter("File audio", "*.mp3", "*.wav", "*.ogg"),
                        new FileChooser.ExtensionFilter("Tutti i file", "*.*")
                );

                Stage stage = (Stage) sceltaAzione.getScene().getWindow();

                File selectedFile = fileChooser.showOpenDialog(stage);

            } catch (Exception e) {
                e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
            }

        }

    }

}



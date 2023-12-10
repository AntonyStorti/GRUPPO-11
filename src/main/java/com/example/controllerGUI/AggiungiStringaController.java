package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class AggiungiStringaController {

    @FXML
    private Button fileButton;
    @FXML
    private TextField testoUtente;
    @FXML
    private Button inviaButton;

    private String percorsoFile;
    private String stringa;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private void initialize() {
        fileButton.setOnAction(event -> scegliFile());
        inviaButton.setOnAction(event -> inviaTutto());
    }


    public void scegliFile() {
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleziona un file di testo (.txt)");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("File di testo", "*.txt")
            );

            Stage stage = (Stage) fileButton.getScene().getWindow();
            File fileSelezionato = fileChooser.showOpenDialog(stage);


            if (fileSelezionato != null) {
                percorsoFile = fileSelezionato.getAbsolutePath();
                sharedDataModel.setPercorsoFile(percorsoFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public void inviaTutto() {

        stringa = testoUtente.getText();
        sharedDataModel.setStringa(stringa);

        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

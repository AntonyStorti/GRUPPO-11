package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class ExitStatusController {


    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    public Button scegli;
    @FXML
    public CheckBox negativo;
    @FXML
    public CheckBox positivo;
    @FXML
    public Button inviaButton;



    @FXML
    private void initialize(){

        scegli.setOnAction(event -> scegliAPP());
        inviaButton.setOnAction(event -> invia());

    }



    public void scegliAPP() {

        try {
            // Creare un oggetto FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleziona il programma da eseguire...");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("File eseguibile", "*.exe", "*.app")
            );

            Stage stage = (Stage) scegli.getScene().getWindow();
            File fileSelezionato = fileChooser.showOpenDialog(stage);


            // Verifica se è stato selezionato un file
            if (fileSelezionato != null) {
                // Salva il percorso del file selezionato
                String percorsoAPP = fileSelezionato.getAbsolutePath();
                sharedDataModel.setPercorsoAPP(percorsoAPP);
            }
        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
        }

    }

    public void invia() {

        if (negativo.isSelected()){

            Integer negativoStatus = 0;
            sharedDataModel.setExitStatus(negativoStatus);

        }

        if (positivo.isSelected()){

            Integer positivoStatus = 1;
            sharedDataModel.setExitStatus(positivoStatus);

        }


        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();


    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

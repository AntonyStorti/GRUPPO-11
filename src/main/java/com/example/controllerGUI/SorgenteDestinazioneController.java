package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;


public class SorgenteDestinazioneController {

    @FXML
    private Button scegliFileButton;
    @FXML
    private Button cartellaDestinazioneButton;
    @FXML
    private Button inviaButton;
    @FXML
    private ChoiceBox<String> scegliAzione;

    private String percorsoFile;
    private String destinazione;
    private String scelta;
    private SharedMemory sharedDataModel = new SharedMemory();


    @FXML
    private void initialize(){

        scegliFileButton.setOnAction(event -> selezionaFile());
        cartellaDestinazioneButton.setOnAction(event -> scegliDestinazione());
        inviaButton.setOnAction(event -> invia());

    }



    public void selezionaFile() {

        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleziona un file (*.txt)");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("File di testo", "*.txt")
            );

            Stage stage = (Stage) scegliFileButton.getScene().getWindow();
            File fileSelezionato = fileChooser.showOpenDialog(stage);

            if (fileSelezionato != null) {
                percorsoFile = fileSelezionato.getAbsolutePath();
                sharedDataModel.setSorgente(percorsoFile);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void scegliDestinazione() {


        try {

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Seleziona una cartella");

            Stage stage = (Stage) cartellaDestinazioneButton.getScene().getWindow();
            File cartellaSelezionata = directoryChooser.showDialog(stage);

            if (cartellaSelezionata != null) {
                destinazione = cartellaSelezionata.getAbsolutePath();
                sharedDataModel.setDestinazione(destinazione);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void invia() {

        scelta = scegliAzione.getSelectionModel().getSelectedItem();
        sharedDataModel.setScelta(scelta);
        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

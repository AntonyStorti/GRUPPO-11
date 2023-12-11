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
            // Creare un oggetto FileChooser
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleziona un file (*.txt)");
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("File di testo", "*.txt")
            );

            Stage stage = (Stage) scegliFileButton.getScene().getWindow();
            File fileSelezionato = fileChooser.showOpenDialog(stage);


            // Verifica se è stato selezionato un file
            if (fileSelezionato != null) {
                // Salva il percorso del file selezionato
                percorsoFile = fileSelezionato.getAbsolutePath();
                sharedDataModel.setSorgente(percorsoFile);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
        }

    }

    public void scegliDestinazione() {


        try {
            // Creare un oggetto DirectoryChooser
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Seleziona una cartella");

            // Mostra la finestra di dialogo per la selezione della cartella
            Stage stage = (Stage) cartellaDestinazioneButton.getScene().getWindow();
            File cartellaSelezionata = directoryChooser.showDialog(stage);

            // Verifica se è stata selezionata una cartella
            if (cartellaSelezionata != null) {
                // Salva il percorso della cartella selezionata
                destinazione = cartellaSelezionata.getAbsolutePath();
                sharedDataModel.setDestinazione(destinazione);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
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

package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class FileEsisteController {

    @FXML
    private Button scegliButton;
    @FXML
    private Button inviaButton;
    @FXML
    private TextField nomeFile;

    private String nFile;
    private String directory;
    private SharedMemory sharedDataModel = new SharedMemory();



    @FXML
    private void initialize(){

        scegliButton.setOnAction(event -> scegliCartella());
        inviaButton.setOnAction(event -> invia());

    }



    public void scegliCartella() {

        try {
            // Creare un oggetto DirectoryChooser
            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Seleziona una cartella");

            // Mostra la finestra di dialogo per la selezione della cartella
            Stage stage = (Stage) scegliButton.getScene().getWindow();
            File cartellaSelezionata = directoryChooser.showDialog(stage);

            // Verifica se è stata selezionata una cartella
            if (cartellaSelezionata != null) {
                // Salva il percorso della cartella selezionata
                directory = cartellaSelezionata.getAbsolutePath();
                sharedDataModel.setCartella(directory);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
        }


    }



    public void invia() {

        nFile = nomeFile.getText();
        System.out.println(nFile);
        sharedDataModel.setNomeFileE(nFile);
        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }


}

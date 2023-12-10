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

            DirectoryChooser directoryChooser = new DirectoryChooser();
            directoryChooser.setTitle("Seleziona una cartella");

            Stage stage = (Stage) scegliButton.getScene().getWindow();
            File cartellaSelezionata = directoryChooser.showDialog(stage);

            if (cartellaSelezionata != null) {
                directory = cartellaSelezionata.getAbsolutePath();
                sharedDataModel.setCartella(directory);
            }

        } catch (Exception e) {
            e.printStackTrace();
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

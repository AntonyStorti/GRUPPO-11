package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class DimensioneFileController {

    @FXML
    private Button inviaButton;
    @FXML
    private Button scegliFileButton;
    @FXML
    private TextField dimensioneFile;
    @FXML
    private ChoiceBox<String> misura;


    private String file;
    private int dim;
    private String unita;
    private SharedMemory sharedDataModel = new SharedMemory();


    @FXML
    private void initialize(){

        scegliFileButton.setOnAction(event -> scegliFile());
        inviaButton.setOnAction(event -> invia());

    }


    public void scegliFile() {


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
                file = fileSelezionato.getAbsolutePath();
                sharedDataModel.setNomeFile(file);
            }

        } catch (Exception e) {
            e.printStackTrace(); // Gestisci l'eccezione in modo adeguato
        }


    }

    public void invia() {

        dim = Integer.parseInt(dimensioneFile.getText());
        unita = misura.getValue();
        sharedDataModel.setDimensione(dim);
        sharedDataModel.setUnita(unita);
        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }
}

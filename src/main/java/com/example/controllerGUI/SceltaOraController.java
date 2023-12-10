package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.time.LocalTime;


public class SceltaOraController {

    @FXML
    private ChoiceBox<String> oreBox;
    @FXML
    private ChoiceBox<String> minutiBox;
    @FXML
    private Button conferma;

    private LocalTime orarioScelto;
    private SharedMemory sharedDataModel = new SharedMemory();


    @FXML
    private void initialize() {
        conferma.setOnAction(event -> inviaOrario());
    }


    public void inviaOrario() {

        String ora = oreBox.getValue();
        String minuti = minutiBox.getValue();

        String orarioCompleto = ora + ":" + minuti;
        orarioScelto = LocalTime.parse(orarioCompleto);

        sharedDataModel.setOrarioScelto(orarioScelto);

        Stage stage = (Stage) conferma.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

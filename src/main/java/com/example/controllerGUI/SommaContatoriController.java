package com.example.controllerGUI;

import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import com.example.ifttt.SharedMemory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class SommaContatoriController {
    @FXML
    public ChoiceBox<Contatore> primoContatore;
    @FXML
    public ChoiceBox<Contatore> secondoContatore;
    @FXML
    public Button sommaContatoriButton;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private void initialize() {
        sommaContatoriButton.setOnAction(event -> sommaContatori());
        primoContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));
        secondoContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));
    }

    public void sommaContatori() {
        Contatore primoC = primoContatore.getValue();
        Contatore secondoC = secondoContatore.getValue();

        sharedDataModel.setContatoreSomma1(primoC);
        sharedDataModel.setContatoreSomma2(secondoC);

        Stage stage = (Stage) sommaContatoriButton.getScene().getWindow();
        stage.close();
    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }
}

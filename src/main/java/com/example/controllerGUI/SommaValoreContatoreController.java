package com.example.controllerGUI;

import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import com.example.ifttt.SharedMemory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class SommaValoreContatoreController {

    @FXML
    public TextField aggiungiValore;
    @FXML
    public Button inviaButton;
    @FXML
    public ChoiceBox<Contatore> sceltaContatore;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private void initialize() {

        inviaButton.setOnAction(event -> sommaValore());

        sceltaContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));

        aggiungiValore.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                aggiungiValore.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    public void sommaValore() {

        Contatore contatore = sceltaContatore.getValue();
        Integer valoreDaSommare = Integer.valueOf(aggiungiValore.getText());

        sharedDataModel.setContatorePiuIntero(contatore);
        sharedDataModel.setValoreDaSommare(valoreDaSommare);

        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

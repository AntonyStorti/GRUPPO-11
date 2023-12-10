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


public class CambiaValoreContatoreController {
    @FXML
    public TextField modificaValore;
    @FXML
    public Button inviaButton;
    @FXML
    public ChoiceBox<Contatore> sceltaContatore;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private void initialize() {

        inviaButton.setOnAction(event -> cambiaValore());

        sceltaContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));

        modificaValore.textProperty().addListener((observable, oldValue, newValue) -> {

            if (!newValue.matches("\\d*")) {
                modificaValore.setText(newValue.replaceAll("[^\\d]", ""));

            }
        });

    }


    public void cambiaValore() {
        Contatore contatore = sceltaContatore.getValue();
        Integer nuovoValoreCont = Integer.valueOf(modificaValore.getText());

        sharedDataModel.setContatoreDaAggiornare(contatore);
        sharedDataModel.setNuovoValore(nuovoValoreCont);

        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();
    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

package com.example.controllerGUI;

import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import com.example.ifttt.SharedMemory;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

public class ConfrontaContatoriController {
    @FXML
    public ChoiceBox<Contatore> primoContatore;
    @FXML
    public ChoiceBox<String> operazione;
    @FXML
    public ChoiceBox<Contatore> secondoContatore;
    @FXML
    public Button confrontaContatoriButton;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private void initialize() {

        confrontaContatoriButton.setOnAction(event -> confrontaContatori());
        primoContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));
        secondoContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));


    }

    public void confrontaContatori() {
        System.out.println(GestoreContatori.listaContatori);
        Contatore contatore1 = primoContatore.getValue();
        String opera = (String) operazione.getValue();
        Contatore contatore2 = secondoContatore.getValue();

        sharedDataModel.setContatore1(contatore1);
        sharedDataModel.setOperazione2(opera);
        sharedDataModel.setContatore2(contatore2);


        Stage stage = (Stage) confrontaContatoriButton.getScene().getWindow();
        stage.close();
    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }
}

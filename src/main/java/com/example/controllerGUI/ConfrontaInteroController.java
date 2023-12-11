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

public class ConfrontaInteroController {


    @FXML
    public TextField intero;
    @FXML
    public Button inviaButton;
    @FXML
    public ChoiceBox<String> operazione;
    @FXML
    public ChoiceBox<Contatore> sceltaContatore;

    private SharedMemory sharedDataModel = new SharedMemory();


    @FXML
    private void initialize() {

        inviaButton.setOnAction(event -> creaTrigger());
        sceltaContatore.setItems(FXCollections.observableArrayList(GestoreContatori.listaContatori));


    }


    public void creaTrigger() {

        System.out.println(GestoreContatori.listaContatori);
        Contatore contatore = sceltaContatore.getValue();
        Integer valore = Integer.valueOf(intero.getText());
        String opera = (String) operazione.getValue();


        sharedDataModel.setContatore(contatore);
        sharedDataModel.setIntero(valore);
        sharedDataModel.setOperazione(opera);


        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

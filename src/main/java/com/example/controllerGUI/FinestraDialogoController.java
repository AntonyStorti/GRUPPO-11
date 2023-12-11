package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;



public class FinestraDialogoController {

    private String messUtente;
    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private TextArea testo;
    @FXML
    private Button bottoneInvio;



    @FXML
    private void initialize(){
        bottoneInvio.setOnAction(event -> inviaTesto());
    }


    @FXML
    public void inviaTesto(){

        String messaggioAllert = testo.getText();

        messUtente = messaggioAllert;
        sharedDataModel.setTestoUtente(messUtente);
        Stage stage = (Stage) bottoneInvio.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

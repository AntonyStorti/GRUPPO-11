package com.example.ifttt;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;



public class FinestraDialogoController {

    public static String messUtente;

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

        Stage stage = (Stage) bottoneInvio.getScene().getWindow();
        stage.close();

    }


}

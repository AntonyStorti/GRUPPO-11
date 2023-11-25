package com.example.ifttt;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.Serializable;
import java.io.*;


public class FinestraDialogoController implements Serializable {


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

        String t = testo.getText();

        FinestraDialogo messaggioAllert = new FinestraDialogo(t);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("messaggioAllert.ser"))) {
            out.writeObject(messaggioAllert);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) bottoneInvio.getScene().getWindow();
        stage.close();

    }


}

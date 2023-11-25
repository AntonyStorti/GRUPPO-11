package com.example.ifttt;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javafx.stage.*;
import java.time.LocalTime;

public class SceltaOraController {

    @FXML
    private ChoiceBox<String> oreBox;
    @FXML
    private ChoiceBox<String> minutiBox;
    @FXML
    private Button conferma;


    @FXML
    private void initialize() {
        conferma.setOnAction(event -> inviaOrario());
    }




    public void inviaOrario() {

        String ora = oreBox.getValue();
        String minuti = minutiBox.getValue();

        //Traforma il LocalTime:
        String orarioCompleto = ora + ":" + minuti;
        LocalTime orario = LocalTime.parse(orarioCompleto);


        //Crea TriggerDelGiorno:
        TempoDelGiorno trigger1 = new TempoDelGiorno(orario);


        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("trigger1.ser"))) {
            out.writeObject(trigger1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Stage stage = (Stage) conferma.getScene().getWindow();
        stage.close();

    }
}

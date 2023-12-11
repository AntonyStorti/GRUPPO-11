package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;

public class DataCompletaController {

    @FXML
    private DatePicker dataCompleta;
    @FXML
    private Button inviaButton;

    private LocalDate dCompleta;
    private SharedMemory sharedDataModel = new SharedMemory();


    @FXML
    private void initialize(){

        dataCompleta.setValue(LocalDate.now());

        // Imposta la factory di celle personalizzata per consentire solo la selezione di giorni futuri
        dataCompleta.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disabilita i giorni precedenti al mese corrente
                setDisable(date.isBefore(LocalDate.now()));

                }
        });

        inviaButton.setOnAction(event -> inviaButton());

    }


    public void inviaDataCompleta() {
        dCompleta = dataCompleta.getValue();
    }

    public void inviaButton() {
        dCompleta = dataCompleta.getValue();
        sharedDataModel.setDataCompleta(dCompleta);
        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();
    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }
}

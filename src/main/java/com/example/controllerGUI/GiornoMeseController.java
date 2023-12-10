package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.stage.Stage;

import java.time.LocalDate;

public class GiornoMeseController {

    @FXML
    private DatePicker giornoMese;
    @FXML
    private Button inviaButton;

    private LocalDate gMese;

    private SharedMemory sharedDataModel = new SharedMemory();


    @FXML
    private void initialize(){

        giornoMese.setValue(LocalDate.now());

        // Imposta la factory di celle personalizzata per consentire solo la selezione di giorni futuri
        giornoMese.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);

                // Disabilita i giorni precedenti al mese corrente
                setDisable(date.isBefore(LocalDate.now()));

                // Disabilita i giorni successivi al mese corrente
                if (date.isAfter(LocalDate.now().withDayOfMonth(LocalDate.now().lengthOfMonth()))) {
                    setDisable(true);
                }
            }
        });

        inviaButton.setOnAction(event -> inviaButton());

    }


    public void inviaGiornoMese() {
        gMese = giornoMese.getValue();
    }

    public void inviaButton() {
        gMese = giornoMese.getValue();
        sharedDataModel.setGiornoMese(gMese);
        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();
    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }
}

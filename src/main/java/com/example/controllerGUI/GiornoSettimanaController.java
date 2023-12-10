package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

import java.time.DayOfWeek;


public class GiornoSettimanaController {

    private DayOfWeek giorno;

    private SharedMemory sharedDataModel = new SharedMemory();

    @FXML
    private Button inviaButton;
    @FXML
    private ChoiceBox<String> giornoBox;



    @FXML
    private void initialize() {
        inviaButton.setOnAction(event -> inviaGiorno());
    }


    public void inviaGiorno() {


        String g = giornoBox.getValue();

        giorno = convertiStringaInDayOfWeek(g);

        sharedDataModel.setGiornoSettimana(giorno);

        Stage stage = (Stage) inviaButton.getScene().getWindow();
        stage.close();

    }

    private DayOfWeek convertiStringaInDayOfWeek(String g) {
        switch (g) {
            case "Lunedì":
                return DayOfWeek.MONDAY;
            case "Martedì":
                return DayOfWeek.TUESDAY;
            case "Mercoledì":
                return DayOfWeek.WEDNESDAY;
            case "Giovedì":
                return DayOfWeek.THURSDAY;
            case "Venerdì":
                return DayOfWeek.FRIDAY;
            case "Sabato":
                return DayOfWeek.SATURDAY;
            case "Domenica":
                return DayOfWeek.SUNDAY;
            default:
                throw new IllegalArgumentException("Stringa giorno non valida!");
        }
    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

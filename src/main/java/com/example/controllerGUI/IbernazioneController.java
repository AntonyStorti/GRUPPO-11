package com.example.controllerGUI;

import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.time.Duration;

public class IbernazioneController {
    @FXML
    private TextField GiorniText;
    @FXML
    private ChoiceBox OreBox;
    @FXML
    private ChoiceBox MinutiBox;
    @FXML
    private Button SalvaButton;

    public Duration periodoIbernazione;
    private SharedMemory sharedDataModel = new SharedMemory();



    @FXML
    private void initialize() {
        GiorniText.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                // Se l'input non è un numero intero, sostituisci il testo con l'input valido precedentemente
                GiorniText.setText(oldValue);
            }
        });
        SalvaButton.setOnAction(event -> salvaImpostazioni());
    }
    public IbernazioneController() {}
    public IbernazioneController(Duration periodoIbernazione) {
        this.periodoIbernazione = periodoIbernazione;
    }




    @FXML
    private void salvaImpostazioni() {

        String g = GiorniText.getText();
        long giorni = Long.parseLong(g);
        String o = (String) OreBox.getValue();
        long ore = Long.parseLong(o);
        String m = (String) MinutiBox.getValue();
        long minuti = Long.parseLong(m);

        periodoIbernazione = Duration.ofDays(giorni).plusHours(ore).plusMinutes(minuti);

        sharedDataModel.setPeriodoIbernazione(periodoIbernazione);

        Stage stage = (Stage) SalvaButton.getScene().getWindow();
        stage.close();
    }



    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }



    @Override
    public String toString() {
        return String.valueOf(periodoIbernazione.toDays()) + "g " + String.valueOf(periodoIbernazione.toHoursPart()) + "h " + String.valueOf(periodoIbernazione.toMinutesPart()) + "m";
    }
}
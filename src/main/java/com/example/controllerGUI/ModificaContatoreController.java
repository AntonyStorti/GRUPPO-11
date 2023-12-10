package com.example.controllerGUI;

import com.example.ifttt.Contatore;
import com.example.ifttt.GestoreContatori;
import com.example.ifttt.SharedMemory;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class ModificaContatoreController {

    @FXML
    public TextField nuovoValoreC;
    @FXML
    public Button nuovoValoreCButton;

    private SharedMemory sharedDataModel = new SharedMemory();

    private HelloController helloController = new HelloController();


    @FXML
    private void initialize() {

        nuovoValoreCButton.setOnAction(event -> inviaNuovoValoreC());

        nuovoValoreC.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                nuovoValoreC.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

    }

    public void inviaNuovoValoreC() {

        Integer nuovoValoreCont = Integer.valueOf(nuovoValoreC.getText());
        TableView<Contatore> tabellaContatori = sharedDataModel.getTabellaContatori();
        Contatore c = tabellaContatori.getSelectionModel().getSelectedItem();

        int i = tabellaContatori.getSelectionModel().getSelectedIndex();

        if (c != null) {

            Contatore nuovoC = new Contatore(c.getNome(), nuovoValoreCont);
            tabellaContatori.getItems().set(i, nuovoC);

            GestoreContatori.listaContatori.set(i, nuovoC);

            helloController.salvaContatoriSuFile();
        }

        Stage stage = (Stage) nuovoValoreCButton.getScene().getWindow();
        stage.close();

    }

    public void setSharedDataModel(SharedMemory sharedDataModel) {
        this.sharedDataModel = sharedDataModel;
    }

}

package com.example.controllerGUI;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

public class RegoleRipetuteController {

    @FXML
    public ChoiceBox Trigger1;
    @FXML
    public ChoiceBox Trigger2;
    @FXML
    public ChoiceBox Trigger3;
    @FXML
    public ChoiceBox Azione1;
    @FXML
    public ChoiceBox Azione2;
    @FXML
    public ChoiceBox Azione3;
    @FXML
    public CheckBox nega1;
    @FXML
    public ChoiceBox LogicaT1;
    @FXML
    public CheckBox nega2;
    @FXML
    public ChoiceBox LogicaA1;
    @FXML
    public CheckBox nega3;
    @FXML
    public ChoiceBox LogicaA2;
    @FXML
    public ChoiceBox LogicaT2;
    @FXML
    public Button creaButton;




    @FXML
    private void initialize(){

        creaButton.setOnAction(event -> creaRegola());

    }

    public void creaRegola() {
    }

}

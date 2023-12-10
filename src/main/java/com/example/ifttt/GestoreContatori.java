package com.example.ifttt;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class GestoreContatori {

    public static ObservableList<Contatore> listaContatori = FXCollections.observableArrayList();

    public GestoreContatori (ObservableList<Contatore> listaContatori) {

        this.listaContatori = listaContatori;
    }
}

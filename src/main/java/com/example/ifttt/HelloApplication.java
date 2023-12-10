package com.example.ifttt;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloApplication extends Application {
    private static Thread threadRulesChecker;
    private static ObservableList<Regola> lista = GestoreRegole.listaRegole;


    @Override
    public void start(Stage stage) throws IOException {

        Image icon = new Image(getClass().getResourceAsStream("/com/example/ifttt/logo.png"));
        stage.getIcons().add(icon);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/com/example/ifttt/HomePage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 750);
        stage.setTitle("Applicazione IFTTT [Gruppo 11 (I-Z)]");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        //Initialization and start of the thread for automatic condition checking
        GestoreRegole gr = new GestoreRegole(lista);

        threadRulesChecker = new Thread(gr);
        threadRulesChecker.setName("Thread Gestore Regole");
        threadRulesChecker.setDaemon(true);
        threadRulesChecker.start();
        launch();
    }
}

module com.example.ifttt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;
    requires org.json;

    opens com.example.ifttt to javafx.fxml;
    exports com.example.ifttt;
    exports com.example.controllerGUI;
    opens com.example.controllerGUI to javafx.fxml;
    exports com.example.trigger;
    opens com.example.trigger to javafx.fxml;
    exports com.example.azioni;
    opens com.example.azioni to javafx.fxml;

}
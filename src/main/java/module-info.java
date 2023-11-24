module com.example.ifttt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.desktop;


    opens com.example.ifttt to javafx.fxml;
    exports com.example.ifttt;
}
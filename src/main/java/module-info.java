module com.example.ifttt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.ifttt to javafx.fxml;
    exports com.example.ifttt;
}
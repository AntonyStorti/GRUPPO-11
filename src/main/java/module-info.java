module com.example.ifttt {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ifttt to javafx.fxml;
    exports com.example.ifttt;
}
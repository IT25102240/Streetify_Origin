module com.example.streetifybackend {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.streetifybackend to javafx.fxml;
    exports com.example.streetifybackend;
}
module com.example.finalproject {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;


    opens com.example.finalproject to javafx.fxml;
    opens com.example.finalproject.controller to javafx.fxml;
    opens com.example.finalproject.model to javafx.fxml;
    
    exports com.example.finalproject;
    exports com.example.finalproject.controller;
    exports com.example.finalproject.model;
}
package com.example.finalproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {
    
    @Override
    public void start(Stage stage) throws IOException {
        // Start with the Manager Login page for easier debugging of manager functionality
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource("/com/example/finalproject/Login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Grandview Theater - Login");
        stage.setScene(scene);
        stage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

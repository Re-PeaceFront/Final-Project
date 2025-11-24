package com.example.finalproject.model;

import com.example.finalproject.Launcher;
import com.example.finalproject.controller.moviePageController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Thesite extends Launcher {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Thesite.class.getResource("com/example/finalproject/moviepage-view.fxml"));
        // load() returns the root node defined in the FXML
        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root);
        stage.setTitle("Movie"); // Set a title
        stage.setScene(scene);
        stage.show();
    }
}

package com.example.finalproject.model;

import com.example.finalproject.Launcher;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The Thesite class is the main JavaFX entry point for the Movie Application UI.
 * <p>
 * It extends {@link Launcher} and is responsible for loading the FXML layout
 * for the movie page and displaying it inside the primary stage.
 * </p>
 *
 * Author: Aya
 */
public class Thesite extends Launcher {

    /**
     * Starts the JavaFX application and sets up the main window.
     *
     * This method loads the FXML file "moviepage-view.fxml", creates a Scene
     * with a fixed size of 800x600, and displays it on the provided Stage.
     *
     * @param stage the primary stage for this application
     * @throws IOException if the FXML file cannot be loaded
     */
    @Override
    public void start(Stage stage) throws IOException {
        // Load the FXML file for the movie page layout
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/finalproject/moviepage-view.fxml")
        );

        // Parse the FXML and create the root node
        Parent root = loader.load();

        // Create the scene with defined width and height
        Scene scene = new Scene(root, 800, 600);

        // Configure and show the main window
        stage.setTitle("Movie App");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method that launches the JavaFX application.
     *
     * @param args command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}

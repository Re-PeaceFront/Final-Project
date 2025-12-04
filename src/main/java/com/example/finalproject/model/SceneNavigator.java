package com.example.finalproject.model;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Utility class to handle navigation between different FXML views.
 * This class abstracts the JavaFX boilerplate for loading scenes.
 * Author Aya
 */
public class SceneNavigator {

    /**
     * Loads a new FXML view and sets it as the scene for the current window.
     * @param event The event (ActionEvent or MouseEvent) that triggered the transition.
     * @param fxmlPath The resource path to the new FXML file .
     * @param title The new title for the window.
     */
    public static void navigate(Event event, String fxmlPath, String title) {
        try {
            // Use the utility class itself to get the resource URL
            URL fxmlUrl = SceneNavigator.class.getResource(fxmlPath);

            if (fxmlUrl == null) {
                showAlert("Resource Error", "FXML file not found at: " + fxmlPath);
                throw new IOException("FXML resource not found at: " + fxmlPath);
            }

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Scene newScene = new Scene(fxmlLoader.load());

            // Get the current Stage from the source of the event
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // Set the new scene and display it
            currentStage.setScene(newScene);
            currentStage.setTitle(title);
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Failed to load scene: " + fxmlPath);
            showAlert("Navigation Error", "Failed to load the view. Check the console.");
            e.printStackTrace();
        } catch (ClassCastException e) {
            // Catches error if event.getSource() is not a Node (e.g., if the event originated from a non-visual element)
            showAlert("Navigation Error", "Cannot navigate: Event source is not a visual element.");
            e.printStackTrace();
        }
    }

    /**
     * Displays an error alert dialog.
     */
    public static void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
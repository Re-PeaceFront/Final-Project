package com.example.finalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLXML;

/**
 * Controller for the SignUp page.
 * Handles user input and sign-up button action.
 * Author: Aya
 */
public class ClientController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField passwordField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField dateField;

    /**
     * Triggered when the SignUp button is clicked.
     * Validates user input .
     */
    public void onSignupButtonClick( ActionEvent event) throws IOException {

        String name = nameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String date = dateField.getText();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || date.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }


        clearFields();

        try {
        // 1. Specify the path to your movie view FXML
        URL fxmlUrl = getClass().getResource("/com/example/finalproject/moviepage-view.fxml");

        if (fxmlUrl == null) {
            throw new IOException("FXML file not found: moviepage-view.fxml. Check your path.");
        }

        FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

        // 2. Load the movie page scene
        Scene movieScene = new Scene(fxmlLoader.load());

        // 3. Get the current stage (the window) from the button that was clicked
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // 4. Set the new scene and show it
        currentStage.setScene(movieScene);
        currentStage.setTitle("Grandview Movie Theater");
        currentStage.show();

        // We skip the success alert and field clearing here
        // because the view is changing immediately upon successful validation.

    } catch (IOException e) {
        showAlert("Error", "Could not load the Movie Page view.");
        System.err.println("Failed to load moviepage-view.fxml.");
        e.printStackTrace();
    }

}

    /**
     * Displays an alert dialog.
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    /**
     * Clears all input fields after successful signup.
     */
    private void clearFields() {
        nameField.clear();
        passwordField.clear();
        emailField.clear();
        dateField.clear();
    }

    public void onLoginButtonClick(ActionEvent event) {
        try {
            // 1. Define the path to the login FXML file
            // Adjust the path if your FXML files are located elsewhere
            URL fxmlUrl = getClass().getResource("/com/example/finalproject/Manager_Login-view.fxml");

            if (fxmlUrl == null) {
                throw new IOException("FXML file not found: Manager_Login-view.fxml. Check the path.");
            }

            // 2. Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);
            Scene loginScene = new Scene(fxmlLoader.load());

            // 3. Get the current Stage (window) from the button that was clicked
            Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            // 4. Set the new scene and update the title
            currentStage.setScene(loginScene);
            currentStage.setTitle("Login");
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Failed to load the Login View FXML.");
            e.printStackTrace();
            //  show an error alert to the user here
        }
    }
}

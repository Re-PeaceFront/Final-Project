package com.example.finalproject;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

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
     * Validates user input and displays confirmation.
     */
    public void onSignupButtonClick() {

        String name = nameField.getText();
        String password = passwordField.getText();
        String email = emailField.getText();
        String date = dateField.getText();

        if (name.isEmpty() || password.isEmpty() || email.isEmpty() || date.isEmpty()) {
            showAlert("Error", "All fields must be filled!");
            return;
        }

        String message = "Signup Successful!\n\n" +
                "Name: " + name + "\n" +
                "Email: " + email + "\n" +
                "Date: " + date;

        showAlert("Success", message);

        clearFields();
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
}

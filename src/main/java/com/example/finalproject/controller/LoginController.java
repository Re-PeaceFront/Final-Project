package com.example.finalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Universal Login Controller.
 * Handles the entry point for both Managers and Clients.
 *
 * @author Rene
 */
public class LoginController {

    @FXML private TextField usernameField;
    @FXML private PasswordField passwordField;

    /**
     * Main Login Logic.
     * Checks Manager file first, then Client file.
     */
    @FXML
    public void onLoginClick(ActionEvent event) {
        String id = usernameField.getText().trim();
        String pass = passwordField.getText().trim();

        if (id.isEmpty() || pass.isEmpty()) {
            showAlert("Error", "Please enter ID and Password.");
            return;
        }

        // 1. Try Manager Login
        if (checkCredentials("src/main/resources/com/example/finalproject/data/managers.csv", id, pass, 0, 1)) {
            switchScene(event, "/com/example/finalproject/Manager_Dashboard-view.fxml", "Manager Dashboard");
            return;
        }

        // 2. Try Client Login
        if (checkCredentials("src/main/resources/com/example/finalproject/data/clients.csv", id, pass, 0, 1)) {
            switchScene(event, "/com/example/finalproject/moviepage-view.fxml", "Movies");
            return;
        }

        showAlert("Failed", "Invalid Credentials.");
    }

    @FXML
    public void onNewClientClick(ActionEvent event) {
        switchScene(event, "/com/example/finalproject/client-view.fxml", "Sign Up");
    }

    // --- HELPER METHODS ---

    private boolean checkCredentials(String path, String inputId, String inputPass, int idCol, int passCol) {
        try {
            Scanner scanner = new Scanner(new File(path));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                String[] parts = line.split(",");
                if (parts.length > Math.max(idCol, passCol)) {
                    if (parts[idCol].trim().equals(inputId) && parts[passCol].trim().equals(inputPass)) {
                        scanner.close();
                        return true;
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + path);
        }
        return false;
    }

    private void switchScene(ActionEvent event, String fxml, String title) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxml));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Grandview Theater - " + title);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Could not load page: " + fxml);
        }
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
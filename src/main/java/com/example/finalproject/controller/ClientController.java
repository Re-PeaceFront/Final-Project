package com.example.finalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientController {

    @FXML
    public void onSignupButtonClick(ActionEvent actionEvent) throws IOException {
        // Load the SignUp page
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/finalproject/client-view.fxml"));
        Parent root = loader.load();
        // Get current stage
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        // Set new scene
        stage.setScene(new Scene(root));
        stage.setTitle("Sign Up Page ");
        stage.show();
    }
}


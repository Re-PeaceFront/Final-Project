package com.example.finalproject.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller class for the Manager Dashboard.
 * Handles navigation to specific management views (Movies, Rooms, Showtimes) and user sign-out.
 *
 * @author Rene
 */
public class ManagerDashboardController {

    /**
     * Navigates the user to the Movie Management view.
     *
     * @param event The action event triggered by the button click
     * @throws IOException If the FXML file is not found
     */
    @FXML
    public void onManageMoviesClick(ActionEvent event) throws IOException {
        switchScene(event, "/com/example/finalproject/Manager_MovieCatalog-view.fxml", "Manage Movies");
    }

    /**
     * Navigates the user to the Room Management view.
     *
     * @param event The action event triggered by the button click
     * @throws IOException If the FXML file is not found
     */
    @FXML
    public void onManageRoomsClick(ActionEvent event) throws IOException {
        switchScene(event, "/com/example/finalproject/Manager_RoomManagement-view.fxml", "Manage Rooms");
    }

    /**
     * Navigates the user to the Showtime Management view.
     *
     * @param event The action event triggered by the button click
     * @throws IOException If the FXML file is not found
     */
    @FXML
    public void onManageShowtimesClick(ActionEvent event) throws IOException {
        switchScene(event, "/com/example/finalproject/Manager_ShowtimeSchedule-view.fxml", "Manage Showtimes");
    }

    /**
     * Signs the user out and returns to the Login view.
     *
     * @param event The action event triggered by the button click
     * @throws IOException If the FXML file is not found
     */
    @FXML
    public void onSignOutClick(ActionEvent event) throws IOException {
        switchScene(event, "/com/example/finalproject/Manager_Login-view.fxml", "Login");
    }

    /**
     * Utility method to load a new FXML view and update the current stage.
     *
     * @param event    The event source used to retrieve the current stage
     * @param fxmlPath The relative path to the FXML file to load
     * @param title    The title to set for the new stage
     * @throws IOException If the FXML loader fails to load the resource
     */
    private void switchScene(ActionEvent event, String fxmlPath, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());

        // Retrieve the current stage from the event source, Node/button etc
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Grandview Theater - Manager ");
        stage.setScene(scene);
        stage.show();
    }
}
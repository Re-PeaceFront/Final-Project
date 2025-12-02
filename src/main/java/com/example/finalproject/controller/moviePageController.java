package com.example.finalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the Movie Page view.
 *
 * This class manages the behavior of the ListView that displays movie titles.
 * It populates the list and handles user interactions such as double-clicking
 * on a movie to open a new details window.
 *
 *
 * Author: Aya
 */
public class moviePageController implements Initializable {

    /**
     * ListView injected from the FXML file.
     * Displays the list of available movies.
     */
    @FXML
    private ListView<String> movieListView;

    /**
     * Called automatically after the FXML file has been loaded.
     *
     * Initializes the movie list and sets the mouse click event handler
     * to detect double-clicks on list items.
     *
     *
     * @param url the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Create the data list for the ListView
        ObservableList<String> items = FXCollections.observableArrayList(
                "Inside Out 2",
                "Dune: Part Two",
                "Oppenheimer"
        );

        // Assign the data to the ListView
        movieListView.setItems(items);

        // Handle double-click event on list items
        movieListView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                String selectedItem = movieListView.getSelectionModel().getSelectedItem();

            }
        });
    }


    public void onUpcomingButtonClick(ActionEvent actionEvent) {
        try {
            // 1. Define the path to the FXML file you provided

            URL fxmlUrl = getClass().getResource("/com/example/finalproject/Upcoming Movie.fxml");

            if (fxmlUrl == null) {
                throw new IOException("FXML file not found: Upcoming Movie.fxml");
            }

            FXMLLoader fxmlLoader = new FXMLLoader(fxmlUrl);

            // 2. Load the FXML root element
            Scene upcomingScene = new Scene(fxmlLoader.load());

            // 3. Get the current Stage (window) from the button that was clicked
            Stage currentStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

            // 4. Set the new scene on the current stage
            currentStage.setScene(upcomingScene);
            currentStage.setTitle("Upcoming Movie");
            currentStage.show();

        } catch (IOException e) {
            System.err.println("Failed to load the FXML view for the upcoming  page.");
            e.printStackTrace();
        }
    }
}

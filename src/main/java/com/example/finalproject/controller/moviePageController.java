package com.example.finalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

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
                openNewWindow(selectedItem);
            }
        });
    }

    /**
     * Opens a new window displaying details about the selected movie.
     *
     * @param data the movie title selected from the ListView
     */
    private void openNewWindow(String data) {
        Stage newStage = new Stage();

        BorderPane pane = new BorderPane();
        pane.setCenter(new javafx.scene.control.Label(data));

        Scene scene = new Scene(pane, 250, 150);
        newStage.setTitle("Details Window");
        newStage.setScene(scene);
        newStage.show();
    }
}

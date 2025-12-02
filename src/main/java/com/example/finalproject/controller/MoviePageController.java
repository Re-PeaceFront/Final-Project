package com.example.finalproject.controller;

import com.example.finalproject.model.MovieService;
import com.example.finalproject.model.SceneNavigator;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Controller class for the Movie Page view (moviepage-view.fxml).
 *
 * This controller's primary responsibility is to populate the ListView
 * and handle user input (button clicks, list double-clicks) by delegating
 * tasks like data fetching and navigation to separate classes.
 *
 * Author: Aya
 */
public class MoviePageController implements Initializable {

    // --- FXML UI Component ---
    @FXML
    private ListView<String> movieListView;

    // --- Class Constants ---
    // Define the FXML path here, making the transition cleaner
    private static final String UPCOMING_MOVIE_FXML = "/com/example/finalproject/upcomingmovie-view.fxml";

    /**
     * Called automatically after the FXML file has been loaded.
     * Initializes the movie list by fetching data from the MovieService.
     *
     * @param url            the location used to resolve relative paths for the root object
     * @param resourceBundle the resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // 1. Fetch data from the Model (MovieService)
        MovieService movieService = new MovieService();
        ObservableList<String> items = movieService.getAvailableMovies();

        // 2. Assign the data to the ListView
        movieListView.setItems(items);

        // 3. Handle double-click event on list items
        movieListView.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                // Delegate navigation to the SceneNavigator
                SceneNavigator.navigate(event, UPCOMING_MOVIE_FXML, "Movie Details");
            }
        });
    }
}
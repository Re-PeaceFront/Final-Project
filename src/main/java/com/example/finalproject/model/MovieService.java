package com.example.finalproject.model; // Use a 'model' package

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Author Aya
 */

public class MovieService {

    /**
     * Provides a list of currently available movie titles.
     * @return ObservableList of movie titles.
     */
    public ObservableList<String> getAvailableMovies() {
        return FXCollections.observableArrayList(
                "Inside Out 2",
                "Dune: Part Two",
                "Oppenheimer"
        );
    }
}
package com.example.finalproject.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Label;

import java.io.IOException;

public abstract class moviePageController {

    @FXML
    private ListView<String> movieListView;

    @FXML
    public void initialize() {
        ObservableList<String> movies = FXCollections.observableArrayList(
                "Inside Out 2",
                "Dune: Part Two",
                "Oppenheimer"
        );

        movieListView.setItems(movies);

        movieListView.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                String selected = movieListView.getSelectionModel().getSelectedItem();
                openNewWindow(selected);
            }
        });
    }

    private void openNewWindow(String movie) {
        Stage stage = new Stage();
        BorderPane pane = new BorderPane(new Label(movie));
        stage.setScene(new Scene(pane, 300, 150));
        stage.setTitle("Movie Details");
        stage.show();
    }

    public void onTicketsButtonClick(ActionEvent actionEvent) {

    }

    public abstract void start(Stage stage) throws IOException;
}

package com.example.finalproject.controller;

import com.example.finalproject.model.Movie;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controller for Add/Edit Movie Popup.
 *
 * @author Rene
 */
public class ManagerMovieFormController implements Initializable {

    @FXML private TextField titleField;
    @FXML private TextField durationField;
    @FXML private ComboBox<String> genreCombo;

    private Movie existingMovie; // If null = Add Mode. If set = Edit Mode.
    private final String CSV_PATH = "src/main/resources/com/example/finalproject/data/movies.csv";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreCombo.setItems(FXCollections.observableArrayList(
                "Action", "Sci-Fi", "Animation", "Drama", "Comedy", "Horror", "Romance"
        ));
    }

    /**
     * Called by the Catalog Controller to pass data for Editing.
     */
    public void setMovieData(Movie movie) {
        this.existingMovie = movie;
        titleField.setText(movie.getTitle());
        // Simple logic to remove " minutes" text if present for editing
        durationField.setText(movie.getDuration().replace(" minutes", ""));
        genreCombo.setValue(movie.getGenre());
    }

    @FXML
    public void onSaveClick(ActionEvent event) {
        String title = titleField.getText();
        String duration = durationField.getText();
        String genre = genreCombo.getValue();

        if (title.isEmpty() || duration.isEmpty() || genre == null) {
            showAlert("Validation Error", "Fill all fields.");
            return;
        }

        try {
            List<String> allLines = readAllLines();
            
            if (existingMovie == null) {
                // --- ADD MODE ---
                int newId = generateId(allLines);
                Movie newMovie = new Movie(newId, title, duration + " minutes", genre);
                allLines.add(newMovie.toCSV());
            } else {
                // --- EDIT MODE ---
                // Find line with matching ID and replace it
                for (int i = 0; i < allLines.size(); i++) {
                    String[] parts = allLines.get(i).split(",");
                    if (Integer.parseInt(parts[0]) == existingMovie.getMovieID()) {
                        // Keep same ID, update details
                        Movie updated = new Movie(existingMovie.getMovieID(), title, duration + " minutes", genre);
                        allLines.set(i, updated.toCSV());
                        break;
                    }
                }
            }

            writeLines(allLines);
            closeWindow(event);

        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Could not save movie.");
        }
    }

    @FXML
    public void onCancelClick(ActionEvent event) {
        closeWindow(event);
    }

    // --- HELPER METHODS ---

    private List<String> readAllLines() throws IOException {
        List<String> lines = new ArrayList<>();
        File file = new File(CSV_PATH);
        if (!file.exists()) return lines;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (!line.trim().isEmpty()) lines.add(line);
        }
        scanner.close();
        return lines;
    }

    private void writeLines(List<String> lines) throws IOException {
        FileWriter writer = new FileWriter(CSV_PATH, false); // Overwrite
        for (String line : lines) writer.write(line + "\n");
        writer.close();
    }

    private int generateId(List<String> lines) {
        int max = 0;
        for (String line : lines) {
            int id = Integer.parseInt(line.split(",")[0]);
            if (id > max) max = id;
        }
        return max + 1;
    }

    private void closeWindow(ActionEvent event) {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
    }

    private void showAlert(String title, String content) {
        new Alert(Alert.AlertType.WARNING, content).showAndWait();
    }
}
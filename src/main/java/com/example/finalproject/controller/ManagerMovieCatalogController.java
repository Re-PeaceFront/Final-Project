package com.example.finalproject.controller;

import com.example.finalproject.model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controller class for the Manager Movie Catalog view.
 * Responsible for reading movie data from a CSV file and displaying it
 * in a JavaFX TableView, as well as handling navigation.
 *
 * @author Rene
 */
public class ManagerMovieCatalogController implements Initializable {

    /**
     * The table view that displays the list of Movie objects.
     */
    @FXML private TableView<Movie> movieTable;

    /**
     * Table column responsible for displaying the Movie ID.
     */
    @FXML private TableColumn<Movie, Integer> colId;

    /**
     * Table column responsible for displaying the Movie Title.
     */
    @FXML private TableColumn<Movie, String> colTitle;

    /**
     * Table column responsible for displaying the Movie Duration.
     */
    @FXML private TableColumn<Movie, String> colDuration;

    /**
     * Table column responsible for displaying the Movie Genre.
     */
    @FXML private TableColumn<Movie, String> colGenre;

    /**
     * Observable list that holds the movie data in memory.
     * Changes to this list are automatically reflected in the TableView.
     */
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();

    /**
     * Called to initialize a controller after its root element has been completely processed.
     * Configures the table columns to match Movie class properties and triggers data loading.
     *
     * @param url The location used to resolve relative paths for the root object, or null if waiting for the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bind the table columns to the specific getter methods in the Movie class
        colId.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // Read the data from the persistence layer (CSV)
        loadMoviesFromCSV();

        // Assign the populated data list to the table view for display
        movieTable.setItems(movieList);
    }

    /**
     * Reads the "movies.csv" file line by line, parses the comma-separated values,
     * creates Movie objects, and adds them to the observable list.
     */
    private void loadMoviesFromCSV() {
        try {
            // Define the file path to the data source
            File file = new File("src/main/resources/com/example/finalproject/data/movies.csv");
            
            // Initialize Scanner to read the file
            Scanner scanner = new Scanner(file);

            // Iterate through the file as long as there is another line of text
            while (scanner.hasNextLine()) {
                String rawLine = scanner.nextLine();
                
                // Skip processing if the line is empty or whitespace only
                if (rawLine.trim().isEmpty()) continue;

                // Split the CSV line into an array of strings using the comma delimiter
                String[] parts = rawLine.split(",");

                // Parse the string data into the appropriate data types for the Movie object
                int id = Integer.parseInt(parts[0]);
                String title = parts[1];
                String duration = parts[2];
                String genre = parts[3];

                // Instantiate a new Movie object and add it to the memory list
                movieList.add(new Movie(id, title, duration, genre));
            }
            // Close the scanner to release system resources
            scanner.close();

        } catch (FileNotFoundException e) {
            // Handle the case where the data file is missing
            System.out.println("ERROR: The file 'movies.csv' was not found at the specified path.");
            e.printStackTrace();
        } catch (Exception e) {
            // Handle any other unexpected errors during file reading or parsing
            System.out.println("ERROR: An unexpected error occurred while loading movie data.");
            e.printStackTrace();
        }
    }

    /**
     * Handles the "Dashboard" button click event.
     * Loads the dashboard FXML view and switches the current scene to it.
     *
     * @param event The ActionEvent triggered by clicking the button
     * @throws IOException If the FXML file cannot be loaded
     */
    @FXML
    public void onDashboardClick(ActionEvent event) throws IOException {
        // Load the FXML file for the Dashboard view
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_Dashboard-view.fxml"));
        
        // Retrieve the current stage (window) from the event source
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        
        // Set the new scene on the stage to display the Dashboard
        stage.setScene(new Scene(fxmlLoader.load()));
    }
}
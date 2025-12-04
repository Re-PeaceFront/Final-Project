package com.example.finalproject.controller;

import com.example.finalproject.model.Movie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
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
 * Controller for the Movie Catalog.
 * Handles Reading (Load) and Deleting. Delegates "Create" and "Update" to the Popup form.
 * OOP Principle: Composition (Has a list of Movies) and Polymorphism (Implements Initializable).
 *
 * @author Rene
 */
public class ManagerMovieCatalogController implements Initializable {

    // VIEW: Link to FXML
    // ENCAPSULATION: FXML fields are private to prevent outside classes from messing with them.
    
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

    // MODEL: In-memory list of data
    /**
     * Observable list that holds the movie data in memory.
     * Changes to this list are automatically reflected in the TableView.
     */
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();
    
    /**
     * Path to the CSV file containing movie data.
     */
    private final String CSV_PATH = "src/main/resources/com/example/finalproject/data/movies.csv";

    /**
     * SETUP: Runs automatically. Links the Table Columns to the Movie Class fields.
     * "PropertyValueFactory" uses Reflection (Advanced OOP) to find "getMovieID", "getTitle", etc.
     * Called to initialize a controller after its root element has been completely processed.
     * Configures the table columns to match Movie class properties and triggers data loading.
     *
     * @param url The location used to resolve relative paths for the root object, or null if waiting for the location is not known.
     * @param resourceBundle The resources used to localize the root object, or null if the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Bind the table columns to the specific getter methods in the Movie class
        // PropertyValueFactory uses Reflection to automatically find getter methods
        colId.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));

        // Read the data from the persistence layer (CSV)
        loadMovies();
        
        // Assign the populated data list to the table view for display
        movieTable.setItems(movieList);
    }

    /**
     * CREATE: Open the popup with NO data (null).
     */
    @FXML
    public void onAddClick(ActionEvent event) {
        openForm(null, event);
    }

    /**
     * UPDATE: Open the popup WITH data (selected movie).
     */
    @FXML
    public void onEditClick(ActionEvent event) {
        Movie selected = movieTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a movie to edit.");
            return;
        }
        openForm(selected, event);
    }

    /**
     * DELETE: Logic to remove data.
     * 1. Check selection.
     * 2. Confirm with user.
     * 3. Perform "Delete from File" operation.
     */
    @FXML
    public void onDeleteClick(ActionEvent event) {
        Movie selected = movieTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No Selection", "Please select a movie to delete.");
            return;
        }

        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selected.getTitle() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            deleteMovieFromFile(selected.getMovieID());
            loadMovies(); // Refresh the table to show it's gone
        }
    }

    /**
     * LOGIC: Simple Deletion from CSV.
     * We cannot just "erase" a line in a text file.
     * Instead, we read EVERYTHING into memory, excluding the one we want to delete,
     * and then write EVERYTHING back to the file.
     */
    private void deleteMovieFromFile(int idToDelete) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(CSV_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                
                // Check ID (first item in CSV)
                int currentId = Integer.parseInt(line.split(",")[0]);
                
                // If it's NOT the one we are deleting, keep it.
                if (currentId != idToDelete) {
                    lines.add(line);
                }
            }
            scanner.close();

            // Overwrite the file with the new list
            FileWriter writer = new FileWriter(CSV_PATH, false);
            for (String l : lines) writer.write(l + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * HELPER: Handles opening the Popup Window.
     */
    private void openForm(Movie movieToEdit, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_MovieForm-view.fxml"));
            Parent root = loader.load();

            // If editing, pass the data to the popup controller
            ManagerMovieFormController controller = loader.getController();
            if (movieToEdit != null) controller.setMovieData(movieToEdit);

            Stage stage = new Stage();
            stage.setTitle(movieToEdit == null ? "Add Movie" : "Edit Movie");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL); // Block clicking the background window
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait(); // Wait here until popup closes

            loadMovies(); // Refresh table when they come back

        } catch (IOException e) { e.printStackTrace(); }
    }

    /**
     * Standard CSV Loading logic (Scanner -> Split -> Object -> List)
     * Reads the "movies.csv" file line by line, parses the comma-separated values,
     * creates Movie objects, and adds them to the observable list.
     */
    private void loadMovies() {
        // Clear existing data to prevent duplicates
        movieList.clear();
        try {
            // Define the file path to the data source
            File file = new File(CSV_PATH);
            
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
        } catch (Exception e) {
            // Handle any errors during file reading or parsing
            System.out.println("ERROR: An error occurred while loading movie data.");
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_Dashboard-view.fxml"));
        
        // Retrieve the current stage (window) from the event source and set new scene
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(loader.load()));
    }

    /**
     * NAVIGATION: Sign out and return to login page.
     * @param event The ActionEvent triggered by clicking the sign out button
     */
    @FXML
    public void onSignOutClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Login-view.fxml"));
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(loader.load()));
    }

    /**
     * HELPER: Displays a warning alert dialog to the user.
     * Encapsulates alert creation to reduce code duplication (DRY Principle).
     *
     * @param title The title of the alert dialog
     * @param content The message content to display
     */
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
}
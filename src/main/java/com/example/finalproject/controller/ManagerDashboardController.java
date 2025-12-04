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
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controller class for the Manager Dashboard.
 * Handles navigation to specific management views (Movies, Rooms, Showtimes) and user sign-out.
 * Also displays a quick movie catalog overview for dashboard convenience.
 *
 * @author Rene
 */
public class ManagerDashboardController implements Initializable {

    // FXML BINDINGS: Link to dashboard movie table for quick overview
    @FXML private TableView<Movie> dashboardMovieTable;
    @FXML private TableColumn<Movie, Integer> colId;
    @FXML private TableColumn<Movie, String> colTitle;
    @FXML private TableColumn<Movie, String> colGenre;
    @FXML private TableColumn<Movie, String> colDuration;

    // DATA: Observable list to hold movie data for quick display
    private ObservableList<Movie> movieList = FXCollections.observableArrayList();
    private final String MOVIES_CSV_PATH = "src/main/resources/com/example/finalproject/data/movies.csv";

    /**
     * INITIALIZATION: Called automatically when FXML loads.
     * Sets up the quick movie overview table with data from CSV.
     *
     * @param url The location used to resolve relative paths
     * @param resourceBundle The resources used to localize the root object
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // 1. Initialize table columns with Movie properties
        setupMovieTableColumns();

        // 2. Load data from CSV
        loadMoviesForDashboard();

        // 3. Bind data to table
        dashboardMovieTable.setItems(movieList);
    }

    /**
     * TABLE SETUP: Configures the dashboard movie table columns.
     * Links each column to the appropriate Movie class property using PropertyValueFactory.
     */
    private void setupMovieTableColumns() {
        // BINDING: Use reflection to bind columns to Movie getters with proper FXML fx:id bindings
        colId.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
    }

    /**
     * DATA LOADING: Reads movies from CSV and populates the dashboard table.
     * Provides a quick overview of all available movies for the manager.
     */
    private void loadMoviesForDashboard() {
        try {
            // CLEAR: Remove any existing data to prevent duplicates
            movieList.clear();

            File movieFile = new File(MOVIES_CSV_PATH);

            // FILE CHECK: Ensure the movies.csv file exists
            if (!movieFile.exists()) {
                System.out.println("DASHBOARD: Movies file not found at " + MOVIES_CSV_PATH);
                return;
            }

            // PARSING: Read and parse each line of the CSV file
            Scanner scanner = new Scanner(movieFile);
            int movieCount = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // SKIP: Empty lines to avoid parsing errors
                if (line.isEmpty()) continue;

                // SPLIT: Parse comma-separated values
                String[] parts = line.split(",");

                // VALIDATION: Ensure we have all required movie data
                if (parts.length >= 4) {
                    try {
                        // CREATE: New Movie object from CSV data
                        Movie movie = new Movie(
                            Integer.parseInt(parts[0].trim()),  // Movie ID
                            parts[1].trim(),                     // Title
                            parts[2].trim(),                     // Duration
                            parts[3].trim()                      // Genre
                        );

                        // ADD: Movie to the observable list
                        movieList.add(movie);
                        movieCount++;
                    } catch (NumberFormatException e) {
                        // HANDLE: Invalid movie ID format
                        System.out.println("DASHBOARD: Invalid movie ID in line: " + line);
                    }
                } else {
                    // HANDLE: Incomplete movie data
                    System.out.println("DASHBOARD: Incomplete movie data in line: " + line);
                }
            }

            scanner.close();
            System.out.println("DASHBOARD: Loaded " + movieCount + " movies for quick overview");

        } catch (Exception e) {
            // ERROR HANDLING: Log any file reading issues
            System.err.println("DASHBOARD: Error loading movies for dashboard overview");
            e.printStackTrace();
        }
    }

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
        switchScene(event, "/com/example/finalproject/Login-view.fxml", "Login");
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
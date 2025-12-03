package com.example.finalproject.controller;

import com.example.finalproject.model.Showtime;
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
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controller class for the Showtime Schedule view.
 * Manages the display of upcoming showtimes.
 * Reads data from "showtimes.csv" and displays it in a table.
 *
 * @author Rene
 */
public class ManagerShowtimeScheduleController implements Initializable {

    /**
     * TableView for displaying Showtime objects.
     */
    @FXML private TableView<Showtime> showtimeTable;

    /**
     * Column for the Date.
     */
    @FXML private TableColumn<Showtime, String> colDate;

    /**
     * Column for the Time.
     */
    @FXML private TableColumn<Showtime, String> colTime;

    /**
     * Column for the Movie ID (Foreign Key).
     */
    @FXML private TableColumn<Showtime, Integer> colMovieId;

    /**
     * Column for the Room ID (Foreign Key).
     */
    @FXML private TableColumn<Showtime, Integer> colRoomId;

    /**
     * In-memory list to store Showtime objects.
     */
    private ObservableList<Showtime> showtimeList = FXCollections.observableArrayList();

    /**
     * Initializes the controller logic.
     * Sets up table columns and loads data from the CSV file.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure the TableColumns to look for specific getters in the Showtime class
        // Example: "date" looks for getDate()
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colMovieId.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        // Execute data loading
        loadShowtimesFromCSV();

        // Update the UI table with the list data
        showtimeTable.setItems(showtimeList);
    }

    /**
     * Reads showtime data from "showtimes.csv".
     * Creates Showtime objects and populates the observable list.
     */
    private void loadShowtimesFromCSV() {
        try {
            // Define path to the CSV file
            File file = new File("src/main/resources/com/example/finalproject/data/showtimes.csv");
            Scanner scanner = new Scanner(file);

            // Iterate through file lines
            while (scanner.hasNextLine()) {
                String rawLine = scanner.nextLine();
                
                // Skip empty lines to ensure robustness
                if (rawLine.trim().isEmpty()) continue;

                // Split the line by comma
                String[] parts = rawLine.split(",");

                // Parse the data according to CSV structure:
                // ID (int), Date (String), Time (String), MovieID (int), RoomID (int)
                int id = Integer.parseInt(parts[0]);
                String date = parts[1];
                String time = parts[2];
                int movieId = Integer.parseInt(parts[3]);
                int roomId = Integer.parseInt(parts[4]);

                // Instantiate Showtime object and add to list
                showtimeList.add(new Showtime(id, date, time, movieId, roomId));
            }
            scanner.close(); // Prevent resource leaks
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not find showtimes.csv");
        } catch (Exception e) {
            System.out.println("ERROR: An error occurred while loading showtimes.");
            e.printStackTrace();
        }
    }

    /**
     * Navigates back to the Manager Dashboard.
     *
     * @param event The button click event.
     * @throws Exception If the FXML file is not found or cannot be loaded.
     */
    @FXML
    public void onDashboardClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_Dashboard-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
}
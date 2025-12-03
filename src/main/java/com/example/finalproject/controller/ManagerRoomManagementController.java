package com.example.finalproject.controller;

import com.example.finalproject.model.Room;
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
 * Controller class for the Room Management view.
 * Handles the logic for displaying the list of theater rooms and their capacities.
 * Reads data from "rooms.csv" and populates the TableView.
 *
 * @author Rene
 */
public class ManagerRoomManagementController implements Initializable {

    /**
     * TableView for displaying Room objects.
     */
    @FXML private TableView<Room> roomTable;

    /**
     * Column for the Room ID.
     */
    @FXML private TableColumn<Room, Integer> colRoomId;

    /**
     * Column for the Room Name.
     */
    @FXML private TableColumn<Room, String> colRoomName;

    /**
     * Column for the Room Capacity.
     */
    @FXML private TableColumn<Room, Integer> colCapacity;

    /**
     * In-memory list to hold the Room objects loaded from the file.
     */
    private ObservableList<Room> roomList = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     * This method is automatically called after the fxml file has been loaded.
     *
     * @param url The location used to resolve relative paths for the root object.
     * @param resourceBundle The resources used to localize the root object.
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Link the TableColumns to the properties in the Room class
        // "roomID" matches getRoomID(), "roomName" matches getRoomName(), etc.
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        // Retrieve data from the CSV file
        loadRoomsFromCSV();

        // Bind the data list to the table
        roomTable.setItems(roomList);
    }

    /**
     * Reads room data from the "rooms.csv" file.
     * Parses each line into a Room object and adds it to the list.
     */
    private void loadRoomsFromCSV() {
        try {
            // Locate the CSV file in the resources folder
            File file = new File("src/main/resources/com/example/finalproject/data/rooms.csv");
            Scanner scanner = new Scanner(file);

            // Loop through each line of the file
            while (scanner.hasNextLine()) {
                String rawLine = scanner.nextLine();
                
                // Safety check: ignore empty lines to prevent errors
                if (rawLine.trim().isEmpty()) continue;

                // Split the line by commas into parts
                String[] parts = rawLine.split(",");

                // Parse the parts into the correct data types
                // CSV Format: ID (int), Name (String), Capacity (int)
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                int capacity = Integer.parseInt(parts[2]);

                // Create a new Room object and add it to our collection
                roomList.add(new Room(id, name, capacity));
            }
            scanner.close(); // Close the file scanner
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Could not find rooms.csv");
        } catch (Exception e) {
            System.out.println("ERROR: An error occurred while loading rooms.");
            e.printStackTrace();
        }
    }

    /**
     * Returns the user to the main Dashboard view.
     *
     * @param event The button click event.
     * @throws Exception If the FXML file cannot be loaded.
     */
    @FXML
    public void onDashboardClick(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_Dashboard-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(fxmlLoader.load()));
    }
}
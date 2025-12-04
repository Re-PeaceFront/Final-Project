package com.example.finalproject.controller;

import com.example.finalproject.model.Room;
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
 * Controller class for the Room Management view.
 * Handles the logic for displaying the list of theater rooms and their capacities.
 * Reads data from "rooms.csv" and populates the TableView.
 *
 * @author Rene
 */
public class ManagerRoomManagementController implements Initializable {

    // CONSTANT: Path to the CSV file
    private static final String CSV_PATH = "src/main/resources/com/example/finalproject/data/rooms.csv";

    @FXML private TableView<Room> roomTable;
    @FXML private TableColumn<Room, Integer> colRoomId;
    @FXML private TableColumn<Room, String> colRoomName;
    @FXML private TableColumn<Room, Integer> colCapacity;

    private ObservableList<Room> roomList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // SETUP: Bind table columns to Room properties
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomID"));
        colRoomName.setCellValueFactory(new PropertyValueFactory<>("roomName"));
        colCapacity.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        // LOAD: Fetch data from CSV
        loadRooms();

        // DISPLAY: Set items to table
        roomTable.setItems(roomList);
    }

    /**
     * Standard CSV Loading logic (Scanner -> Split -> Object -> List)
     * Reads the "rooms.csv" file line by line, parses the comma-separated values,
     * creates Room objects, and adds them to the observable list.
     */
    private void loadRooms() {
        roomList.clear();
        try {
            File file = new File(CSV_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String rawLine = scanner.nextLine();
                if (rawLine.trim().isEmpty()) continue;

                String[] parts = rawLine.split(",");
                
                // VALIDATION: Ensure we have enough parts
                if (parts.length >= 3) {
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int capacity = Integer.parseInt(parts[2].trim());

                    roomList.add(new Room(id, name, capacity));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("ERROR: An error occurred while loading room data.");
            e.printStackTrace();
        }
    }

    @FXML
    public void onDashboardClick(ActionEvent event) throws IOException {
        switchScene(event, "/com/example/finalproject/Manager_Dashboard-view.fxml", "Manager Dashboard");
    }

    @FXML
    public void onSignOutClick(ActionEvent event) throws IOException {
        switchScene(event, "/com/example/finalproject/Login-view.fxml", "Login");
    }

    /**
     * Handles the delete room action.
     */
    @FXML
    public void onDeleteClick(ActionEvent event) {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            showAlert("No Selection", "Please select a room to delete.");
            return;
        }
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete " + selectedRoom.getRoomName() + "?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            deleteRoomFromFile(selectedRoom.getRoomID());
            loadRooms(); // Refresh the table to show it's gone
        }
    }

    /**
     * Handles the edit room capacity action.
     */
    @FXML
    public void onEditClick(ActionEvent event) {
        Room selectedRoom = roomTable.getSelectionModel().getSelectedItem();
        if (selectedRoom == null) {
            showAlert("No Selection", "Please select a room to edit.");
            return;
        }
        
        openForm(selectedRoom, event);
    }

    /**
     * Handles the add new room action.
     */
    @FXML
    public void onAddClick(ActionEvent event) throws IOException {
        openForm(null, event);
    }

    /**
     * Utility method to load a new FXML view and update the current stage.
     */
    private void switchScene(ActionEvent event, String fxmlPath, String title) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlPath));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Grandview Theater - " + title);
        stage.setScene(scene);
        stage.show();
    }
    
    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }
    
    /**
     * LOGIC: Simple Deletion from CSV.
     */
    private void deleteRoomFromFile(int idToDelete) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(CSV_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                
                int currentId = Integer.parseInt(line.split(",")[0]);
                if (currentId != idToDelete) {
                    lines.add(line);
                }
            }
            scanner.close();

            FileWriter writer = new FileWriter(CSV_PATH, false);
            for (String l : lines) writer.write(l + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * HELPER: Handles opening the Room Form Popup Window.
     */
    private void openForm(Room roomToEdit, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_RoomForm-view.fxml"));
            Parent root = loader.load();

            ManagerRoomFormController controller = loader.getController();
            if (roomToEdit != null) controller.setRoomData(roomToEdit);

            Stage stage = new Stage();
            stage.setTitle(roomToEdit == null ? "Add Room" : "Edit Room");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

            loadRooms();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
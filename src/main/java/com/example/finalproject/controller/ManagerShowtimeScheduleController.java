package com.example.finalproject.controller;

import com.example.finalproject.model.Showtime;
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
 * Controller class for the Showtime Schedule view.
 * Manages the display of upcoming showtimes.
 * Reads data from "showtimes.csv" and displays it in a table.
 *
 * @author Rene
 */
public class ManagerShowtimeScheduleController implements Initializable {

    // CONSTANT: Path to the CSV file
    private static final String CSV_PATH = "src/main/resources/com/example/finalproject/data/showtimes.csv";

    @FXML private TableView<Showtime> showtimeTable;
    @FXML private TableColumn<Showtime, String> colDate;
    @FXML private TableColumn<Showtime, String> colTime;
    @FXML private TableColumn<Showtime, Integer> colMovieId;
    @FXML private TableColumn<Showtime, Integer> colRoomId;

    private ObservableList<Showtime> showtimeList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Configure the TableColumns to look for specific getters in the Showtime class
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colMovieId.setCellValueFactory(new PropertyValueFactory<>("movieID"));
        colRoomId.setCellValueFactory(new PropertyValueFactory<>("roomID"));

        loadShowtimes();

        // Link the ObservableList to the table
        showtimeTable.setItems(showtimeList);
    }

    /**
     * Standard CSV loading logic
     */
    private void loadShowtimes() {
        showtimeList.clear();
        try {
            File file = new File(CSV_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String rawLine = scanner.nextLine();
                if (rawLine.trim().isEmpty()) continue;

                String[] parts = rawLine.split(",");

                // Validation
                if (parts.length >= 5) {
                    int showtimeID = Integer.parseInt(parts[0].trim());
                    String date = parts[1].trim();
                    String time = parts[2].trim();
                    int movieID = Integer.parseInt(parts[3].trim());
                    int roomID = Integer.parseInt(parts[4].trim());

                    showtimeList.add(new Showtime(showtimeID, date, time, movieID, roomID));
                }
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("ERROR: Failed to load showtime data.");
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
     * Handles the delete/cancel showtime action.
     */
    @FXML
    public void onDeleteClick(ActionEvent event) {
        Showtime selectedShowtime = showtimeTable.getSelectionModel().getSelectedItem();
        if (selectedShowtime == null) {
            showAlert("No Selection", "Select a showtime to delete.");
            return;
        }
        
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Delete selected showtime?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            deleteShowtimeFromFile(selectedShowtime);
            loadShowtimes(); // Refresh the table
        }
    }

    /**
     * Handles the edit/reschedule showtime action.
     */
    @FXML
    public void onEditClick(ActionEvent event) {
        Showtime selectedShowtime = showtimeTable.getSelectionModel().getSelectedItem();
        if (selectedShowtime == null) {
            showAlert("No Selection", "Select a showtime to edit.");
            return;
        }
        
        openForm(selectedShowtime, event);
    }

    /**
     * Handles the add new showtime action.
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
     * LOGIC: Simple Deletion from CSV using showtime ID.
     */
    private void deleteShowtimeFromFile(Showtime showtimeToDelete) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(new File(CSV_PATH));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.trim().isEmpty()) continue;
                
                String[] parts = line.split(",");
                if (parts.length >= 5) {
                    // CSV format: showtimeID,date,time,movieID,roomID
                    int csvShowtimeID = Integer.parseInt(parts[0].trim());
                    
                    // Only keep lines that don't match the showtime ID to delete
                    if (csvShowtimeID != showtimeToDelete.getShowtimeID()) {
                        lines.add(line);
                    }
                } else {
                    lines.add(line); // Keep malformed lines
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
     * HELPER: Handles opening the Showtime Form Popup Window.
     */
    private void openForm(Showtime showtimeToEdit, ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/finalproject/Manager_ShowtimeForm-view.fxml"));
            Parent root = loader.load();

            ManagerShowtimeFormController controller = loader.getController();
            if (showtimeToEdit != null) controller.setShowtimeData(showtimeToEdit);

            Stage stage = new Stage();
            stage.setTitle(showtimeToEdit == null ? "Schedule New Showtime" : "Reschedule Showtime");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(((Node) event.getSource()).getScene().getWindow());
            stage.showAndWait();

            loadShowtimes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
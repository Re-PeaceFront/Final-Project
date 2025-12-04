package com.example.finalproject.controller;

import com.example.finalproject.model.Room;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Controller for Add/Edit Room Popup.
 *
 * @author Rene
 */
public class ManagerRoomFormController {

    @FXML private TextField nameField;
    @FXML private TextField capacityField;

    private Room existingRoom;
    private final String CSV_PATH = "src/main/resources/com/example/finalproject/data/rooms.csv";

    public void setRoomData(Room room) {
        this.existingRoom = room;
        nameField.setText(room.getRoomName());
        capacityField.setText(String.valueOf(room.getCapacity()));
    }

    @FXML
    public void onSaveClick(ActionEvent event) {
        String name = nameField.getText();
        String capStr = capacityField.getText();

        if (name.isEmpty() || capStr.isEmpty()) {
            showAlert("Error", "Fill all fields.");
            return;
        }

        try {
            int capacity = Integer.parseInt(capStr);
            List<String> lines = readAllLines();

            if (existingRoom == null) {
                // Add
                int newId = generateId(lines);
                Room newRoom = new Room(newId, name, capacity);
                lines.add(newRoom.toCSV());
            } else {
                // Edit
                for (int i = 0; i < lines.size(); i++) {
                    if (Integer.parseInt(lines.get(i).split(",")[0]) == existingRoom.getRoomID()) {
                        Room updated = new Room(existingRoom.getRoomID(), name, capacity);
                        lines.set(i, updated.toCSV());
                        break;
                    }
                }
            }

            writeLines(lines);
            closeWindow(event);
        } catch (NumberFormatException e) {
            showAlert("Error", "Capacity must be a number.");
        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void onCancelClick(ActionEvent event) {
        closeWindow(event);
    }

    private List<String> readAllLines() throws Exception {
        List<String> lines = new ArrayList<>();
        File file = new File(CSV_PATH);
        if (!file.exists()) return lines;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String l = scanner.nextLine();
            if (!l.trim().isEmpty()) lines.add(l);
        }
        scanner.close();
        return lines;
    }

    private void writeLines(List<String> lines) throws Exception {
        FileWriter writer = new FileWriter(CSV_PATH, false);
        for (String l : lines) writer.write(l + "\n");
        writer.close();
    }

    private int generateId(List<String> lines) {
        int max = 0;
        for (String l : lines) {
            int id = Integer.parseInt(l.split(",")[0]);
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
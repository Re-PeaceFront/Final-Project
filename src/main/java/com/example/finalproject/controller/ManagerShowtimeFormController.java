package com.example.finalproject.controller;

import com.example.finalproject.model.Showtime;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;

/**
 * Controller for Add/Edit Showtime Popup.
 *
 * @author Rene
 */
public class ManagerShowtimeFormController implements Initializable {

    @FXML private ComboBox<String> movieCombo;
    @FXML private ComboBox<String> roomCombo;
    @FXML private DatePicker datePicker;
    @FXML private TextField timeField;

    private Showtime existingShowtime;
    private final String CSV_PATH = "src/main/resources/com/example/finalproject/data/showtimes.csv";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadOptions("src/main/resources/com/example/finalproject/data/movies.csv", movieCombo);
        loadOptions("src/main/resources/com/example/finalproject/data/rooms.csv", roomCombo);
    }

    public void setShowtimeData(Showtime st) {
        this.existingShowtime = st;
        timeField.setText(st.getTime());
        datePicker.setValue(LocalDate.parse(st.getDate()));
        
        // Select correct movie/room in combo
        for (String item : movieCombo.getItems()) {
            if (item.startsWith(st.getMovieID() + ":")) movieCombo.setValue(item);
        }
        for (String item : roomCombo.getItems()) {
            if (item.startsWith(st.getRoomID() + ":")) roomCombo.setValue(item);
        }
    }

    @FXML
    public void onConfirmClick(ActionEvent event) {
        if (movieCombo.getValue() == null || roomCombo.getValue() == null || datePicker.getValue() == null || timeField.getText().isEmpty()) {
            showAlert("Error", "Fill all fields.");
            return;
        }

        try {
            int movieId = Integer.parseInt(movieCombo.getValue().split(":")[0]);
            int roomId = Integer.parseInt(roomCombo.getValue().split(":")[0]);
            String date = datePicker.getValue().toString();
            String time = timeField.getText();

            List<String> lines = readAllLines();

            if (existingShowtime == null) {
                // Add
                int newId = generateId(lines);
                Showtime st = new Showtime(newId, date, time, movieId, roomId);
                lines.add(st.toCSV());
            } else {
                // Edit
                for (int i = 0; i < lines.size(); i++) {
                    if (Integer.parseInt(lines.get(i).split(",")[0]) == existingShowtime.getShowtimeID()) {
                        Showtime st = new Showtime(existingShowtime.getShowtimeID(), date, time, movieId, roomId);
                        lines.set(i, st.toCSV());
                        break;
                    }
                }
            }

            writeLines(lines);
            closeWindow(event);

        } catch (Exception e) { e.printStackTrace(); }
    }

    @FXML
    public void onCancelClick(ActionEvent event) {
        closeWindow(event);
    }

    // Reuse helper methods logic
    private void loadOptions(String path, ComboBox<String> combo) {
        try {
            Scanner s = new Scanner(new File(path));
            while (s.hasNextLine()) {
                String l = s.nextLine();
                if (!l.trim().isEmpty()) {
                    String[] p = l.split(",");
                    combo.getItems().add(p[0] + ": " + p[1]);
                }
            }
            s.close();
        } catch (Exception e) {}
    }

    private List<String> readAllLines() throws Exception {
        List<String> lines = new ArrayList<>();
        File f = new File(CSV_PATH);
        if(!f.exists()) return lines;
        Scanner s = new Scanner(f);
        while(s.hasNextLine()) {
            String l = s.nextLine();
            if(!l.trim().isEmpty()) lines.add(l);
        }
        s.close();
        return lines;
    }

    private void writeLines(List<String> lines) throws Exception {
        FileWriter w = new FileWriter(CSV_PATH, false);
        for(String l : lines) w.write(l+"\n");
        w.close();
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
package com.example.finalproject.controller;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

/**
 * Controller for the Sales Tracker screen (Showtime performance).
 * <p>
 * This controller is responsible for displaying ticket sales grouped by
 * showtime. Each row in the table represents a specific showtime and
 * contains:
 * <ul>
 *     <li>Movie name</li>
 *     <li>Showtime (date and time)</li>
 *     <li>Tickets sold</li>
 *     <li>Ticket price</li>
 *     <li>Total revenue (tickets * price)</li>
 * </ul>
 * <p>
 * The top summary cards display the total number of tickets sold and
 * the total revenue for all listed showtimes.
 * <p>
 * This class acts as the Controller in the MVC structure, coordinating
 * between the JavaFX view and the underlying sales data.
 *
 * @author Brody
 */
public class SalesTrackerController {

    // ===== FXML: Top summary texts =====

    /**
     * Text node displaying the monthly revenue value.
     */
    @FXML
    private Text monthlyRevenueText;

    /**
     * Text node displaying the monthly ticket sales value.
     */
    @FXML
    private Text monthlyTicketsText;

    // ===== FXML: Buttons =====

    /**
     * Button that represents the "Showtime" performance view.
     * This is the main view implemented in this controller.
     */
    @FXML
    private Button showtimeButton;

    /**
     * Button for the showroom view.
     * In this implementation, it simply informs the user that
     * this part is handled by another team member.
     */
    @FXML
    private Button showroomButton;

    /**
     * Button for the movie view.
     * In this implementation, it simply informs the user that
     * this part is handled by another team member.
     */
    @FXML
    private Button movieButton;

    // ===== FXML: Table and columns =====

    /**
     * Table displaying the showtime performance rows.
     */
    @FXML
    private TableView<ShowtimeRow> salesTable;

    /**
     * Column for the movie name.
     */
    @FXML
    private TableColumn<ShowtimeRow, String> movieNameColumn;

    /**
     * Column for the showtime (date and time).
     */
    @FXML
    private TableColumn<ShowtimeRow, String> showtimeColumn;

    /**
     * Column for the number of tickets sold.
     */
    @FXML
    private TableColumn<ShowtimeRow, Integer> ticketsSoldColumn;

    /**
     * Column for the ticket price.
     */
    @FXML
    private TableColumn<ShowtimeRow, Double> ticketPriceColumn;

    /**
     * Column for the revenue (tickets sold * ticket price).
     */
    @FXML
    private TableColumn<ShowtimeRow, Double> revenueColumn;

    /**
     * Observable list backing the TableView with showtime rows.
     */
    private final ObservableList<ShowtimeRow> showtimeRows = FXCollections.observableArrayList();

    /**
     * Initializes the controller after the FXML has been loaded.
     * <p>
     * Sets up the table columns, loads sample data and updates
     * the top summary values.
     */
    @FXML
    public void initialize() {
        setupColumns();
        loadSampleData();
        updateSummary();
    }

    /**
     * Configures the table column cell value factories.
     * <p>
     * This method binds each column to a property of the {@link ShowtimeRow}.
     */
    private void setupColumns() {
        movieNameColumn.setCellValueFactory(cellData ->
                cellData.getValue().movieNameProperty());
        showtimeColumn.setCellValueFactory(cellData ->
                cellData.getValue().showtimeProperty());
        ticketsSoldColumn.setCellValueFactory(cellData ->
                cellData.getValue().ticketsSoldProperty().asObject());
        ticketPriceColumn.setCellValueFactory(cellData ->
                cellData.getValue().ticketPriceProperty().asObject());
        revenueColumn.setCellValueFactory(cellData ->
                cellData.getValue().revenueProperty().asObject());

        salesTable.setItems(showtimeRows);
    }

    /**
     * Loads example showtime rows into the table.
     * <p>
     * In a complete application, this method would load data
     * from the domain model (showtimes and tickets).
     */
    private void loadSampleData() {
        // Example showtimes: movie, showtimeText, ticketsSold, ticketPrice
        addShowtime("Avengers", "2025-12-01 18:00", 40, 12.50);
        addShowtime("Avengers", "2025-12-01 21:00", 30, 12.50);
        addShowtime("Moana 2", "2025-12-02 17:00", 25, 11.00);
        addShowtime("Moana 2", "2025-12-02 20:00", 18, 11.00);
        addShowtime("Dune 3", "2025-12-03 19:30", 35, 13.75);
    }

    /**
     * Helper method to create and add a new {@link ShowtimeRow}.
     *
     * @param movieName   name of the movie
     * @param showtime    showtime description (date and time)
     * @param ticketsSold number of tickets sold
     * @param ticketPrice price per ticket
     */
    private void addShowtime(String movieName,
                             String showtime,
                             int ticketsSold,
                             double ticketPrice) {
        double revenue = ticketsSold * ticketPrice;
        ShowtimeRow row = new ShowtimeRow(movieName, showtime, ticketsSold, ticketPrice, revenue);
        showtimeRows.add(row);
    }

    /**
     * Handles the click on the "Showtime" button.
     * <p>
     * Since this screen already displays performance by showtime,
     * this method simply refreshes the summary values.
     */
    @FXML
    private void onShowtimeView() {
        // In this implementation, the view is always "by showtime".
        // We simply recalculate totals to ensure everything is up to date.
        updateSummary();
    }

    /**
     * Handles the click on the "Showroom" button.
     * <p>
     * This controller is responsible only for the showtime table.
     * When this button is clicked, an informational dialog is shown
     * to indicate that this part is implemented elsewhere.
     */
    @FXML
    private void onShowroomView() {
        showInfo("Not implemented in this view",
                "The showroom performance view is handled by another part of the application.");
    }

    /**
     * Handles the click on the "Movie" button.
     * <p>
     * This controller is responsible only for the showtime table.
     * When this button is clicked, an informational dialog is shown
     * to indicate that this part is implemented elsewhere.
     */
    @FXML
    private void onMovieView() {
        showInfo("Not implemented in this view",
                "The movie performance view is handled by another part of the application.");
    }

    /**
     * Recalculates the top summary for total tickets and revenue
     * based on the current table rows.
     * <p>
     * Any unexpected error will be caught and shown as an alert,
     * so that the UI does not crash.
     */
    private void updateSummary() {
        try {
            int totalTickets = 0;
            double totalRevenue = 0.0;

            for (ShowtimeRow row : showtimeRows) {
                totalTickets += row.getTicketsSold();
                totalRevenue += row.getRevenue();
            }

            monthlyTicketsText.setText(totalTickets + " Tickets");
            monthlyRevenueText.setText(String.format("$%,.2f", totalRevenue));

        } catch (Exception ex) {
            showError("Error calculating summary", ex.getMessage());
        }
    }

    /**
     * Shows an informational dialog with the given title and message.
     *
     * @param title   title of the dialog
     * @param message content text of the dialog
     */
    private void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    /**
     * Shows an error dialog with the given title and message.
     *
     * @param title   title of the dialog
     * @param message content text of the dialog
     */
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message == null ? "An unknown error occurred." : message);
        alert.showAndWait();
    }

    // ===== Inner class: row model for the showtime table =====

    /**
     * Row model representing a single showtime's performance.
     * <p>
     * This class stores the values that are displayed in the table:
     * movie name, showtime, tickets sold, ticket price, and revenue.
     */
    public static class ShowtimeRow {

        private final StringProperty movieName = new SimpleStringProperty();
        private final StringProperty showtime = new SimpleStringProperty();
        private final IntegerProperty ticketsSold = new SimpleIntegerProperty();
        private final DoubleProperty ticketPrice = new SimpleDoubleProperty();
        private final DoubleProperty revenue = new SimpleDoubleProperty();

        /**
         * Creates a new showtime row with the given values.
         *
         * @param movieName   name of the movie
         * @param showtime    showtime description (date and time)
         * @param ticketsSold number of tickets sold
         * @param ticketPrice ticket price
         * @param revenue     total revenue (ticketsSold * ticketPrice)
         */
        public ShowtimeRow(String movieName,
                           String showtime,
                           int ticketsSold,
                           double ticketPrice,
                           double revenue) {
            this.movieName.set(movieName);
            this.showtime.set(showtime);
            this.ticketsSold.set(ticketsSold);
            this.ticketPrice.set(ticketPrice);
            this.revenue.set(revenue);
        }

        public StringProperty movieNameProperty() {
            return movieName;
        }

        public StringProperty showtimeProperty() {
            return showtime;
        }

        public IntegerProperty ticketsSoldProperty() {
            return ticketsSold;
        }

        public DoubleProperty ticketPriceProperty() {
            return ticketPrice;
        }

        public DoubleProperty revenueProperty() {
            return revenue;
        }

        public int getTicketsSold() {
            return ticketsSold.get();
        }

        public double getRevenue() {
            return revenue.get();
        }
    }
}

/**
 * @author Rene
 */
package com.example.finalproject.model;

// DATA STORAGE: I'm gonna use CSV files to save our data and ArrayList for during app run yunno temporary
// I also added a new folder data in resources which contain all data we gonna use

// ENCAPSULATION: I created this class to represent when and where a movie is shown
public class Showtime {
    // ENCAPSULATION: I used private fields to protect showtime data
    private int showtimeID;
    private String date;
    private String time;
    private int movieID;
    private int roomID;
    
    // CONSTRUCTOR: I created a constructor to set up all showtime properties
    public Showtime(int showtimeID, String date, String time, int movieID, int roomID) {
        // I initialize all fields to create a complete showtime record
        this.showtimeID = showtimeID;
        this.date = date;
        this.time = time;
        this.movieID = movieID;
        this.roomID = roomID;
    }
    
    // ENCAPSULATION: I provide getters for controlled access to showtime data
    public int getShowtimeID() {
        // I return the showtimeID for unique identification
        return showtimeID;
    }
    
    public String getDate() {
        // I return the date as a String to avoid complex date parsing and keep it simple
        return date;
    }
    
    public String getTime() {
        // I return the time as a String for simplicity and to avoid time parsing errors
        return time;
    }
    
    public int getMovieID() {
        // FOREIGN KEY: I return movieID instead of a full Movie object to make CSV handling easier
        // This approach prevents circular references and keeps the data structure simple
        return movieID;
    }
    
    public int getRoomID() {
        // FOREIGN KEY: I return roomID instead of a full Room object for the same reason as movieID
        // This makes it safer to write to CSV files without complex object serialization
        return roomID;
    }
    
    // ENCAPSULATION: I provide setters for controlled modification
    public void setShowtimeID(int showtimeID) {
        this.showtimeID = showtimeID;
    }
    
    public void setDate(String date) {
        // I use String for date to avoid complex Date object handling and parsing issues
        this.date = date;
    }
    
    public void setTime(String time) {
        // I use String for time to keep the implementation simple and robust
        this.time = time;
    }
    
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    
    // DATA PERSISTENCE: I provide a method to convert showtime to CSV format
    public String toCSV() {
        // I format as CSV: showtimeID,date,time,movieID,roomID
        // Using IDs instead of objects makes CSV export much simpler and safer
        return showtimeID + "," + date + "," + time + "," + movieID + "," + roomID;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a scheduled showing of a specific movie in a specific room.
 * Each showtime includes the movie being shown, the room, the date and time,
 * and the number of tickets sold so far.
 *
 * Demonstrates encapsulation and input validation.
 *
 * Author: Brody
 */
public class Showtime {

    /**
     * Unique identifier of the showtime.
     */
    private long id;

    /**
     * Movie that will be shown.
     */
    private Movie movie;

    /**
     * Room where the showtime takes place.
     */
    private Room room;

    /**
     * Calendar date of the showtime.
     */
    private LocalDate date;

    /**
     * Start time of the showtime.
     */
    private LocalTime startTime;

    /**
     * Number of tickets sold for this showtime.
     */
    private int ticketsSold;

    /**
     * Creates a new Showtime instance with no tickets sold yet.
     *
     * @param id        unique identifier, must be non-negative
     * @param movie     movie being shown, cannot be null
     * @param room      room used for the showtime, cannot be null
     * @param date      date of the showtime, cannot be null
     * @param startTime start time of the showtime, cannot be null
     * @throws IllegalArgumentException if any required argument is invalid
     */
    public Showtime(long id,
                    Movie movie,
                    Room room,
                    LocalDate date,
                    LocalTime startTime) {

        setId(id);
        setMovie(movie);
        setRoom(room);
        setDate(date);
        setStartTime(startTime);
        this.ticketsSold = 0;
    }

    /**
     * Returns the unique identifier of this showtime.
     *
     * @return showtime id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this showtime.
     *
     * @param id new showtime id, must be non-negative
     * @throws IllegalArgumentException if id is negative
     */
    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Showtime ID must be non-negative.");
        }
        this.id = id;
    }

    /**
     * Returns the movie being shown.
     *
     * @return movie
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the movie being shown.
     *
     * @param movie movie, cannot be null
     * @throws IllegalArgumentException if movie is null
     */
    public void setMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null.");
        }
        this.movie = movie;
    }

    /**
     * Returns the room used for this showtime.
     *
     * @return room
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room used for this showtime.
     *
     * @param room room, cannot be null
     * @throws IllegalArgumentException if room is null
     */
    public void setRoom(Room room) {
        if (room == null) {
            throw new IllegalArgumentException("Room cannot be null.");
        }
        this.room = room;
    }

    /**
     * Returns the calendar date of this showtime.
     *
     * @return show date
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the calendar date of this showtime.
     *
     * @param date date, cannot be null
     * @throws IllegalArgumentException if date is null
     */
    public void setDate(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date cannot be null.");
        }
        this.date = date;
    }

    /**
     * Returns the start time of this showtime.
     *
     * @return start time
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of this showtime.
     *
     * @param startTime start time, cannot be null
     * @throws IllegalArgumentException if startTime is null
     */
    public void setStartTime(LocalTime startTime) {
        if (startTime == null) {
            throw new IllegalArgumentException("Start time cannot be null.");
        }
        this.startTime = startTime;
    }

    /**
     * Returns the number of tickets sold for this showtime.
     *
     * @return tickets sold
     */
    public int getTicketsSold() {
        return ticketsSold;
    }

    /**
     * Sets the number of tickets sold for this showtime.
     *
     * @param ticketsSold number of tickets, must be >= 0
     * @throws IllegalArgumentException if ticketsSold is negative
     */
    public void setTicketsSold(int ticketsSold) {
        if (ticketsSold < 0) {
            throw new IllegalArgumentException("Tickets sold cannot be negative.");
        }
        this.ticketsSold = ticketsSold;
    }

    /**
     * Increases the tickets sold count by the given positive amount.
     *
     * @param amount number of tickets to add, must be > 0
     * @throws IllegalArgumentException if amount is <= 0
     */
    public void addTickets(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Ticket amount must be positive.");
        }
        this.ticketsSold += amount;
    }
}

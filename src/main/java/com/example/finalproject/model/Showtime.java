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
    }
}
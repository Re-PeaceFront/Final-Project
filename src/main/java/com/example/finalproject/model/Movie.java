/**
 * @author Rene
 */
package com.example.finalproject.model;

// DATA STORAGE: I'm gonna use CSV files to save our data and ArrayList for during app run yunno temporary
// I also added a new folder data in resources which contain all data we gonna use

// ENCAPSULATION: I created this class to represent a Movie with proper data hiding
public class Movie {
    // ENCAPSULATION: I used private fields to protect the movie data from direct access
    private int movieID;
    private String title;
    private String duration;
    private String genre;
    
    // CONSTRUCTOR: I created a constructor to initialize all movie properties at once
    public Movie(int movieID, String title, String duration, String genre) {
        // I assign each parameter to the corresponding instance variable
        this.movieID = movieID;
        this.title = title;
        this.duration = duration;
        this.genre = genre;
    }
    
    // ENCAPSULATION: I provide getters for controlled access to private fields
    public int getMovieID() {
        // I return the movieID for identification and referencing
        return movieID;
    }
    
    public String getTitle() {
        // I return the title for display purposes
        return title;
    }
    
    public String getDuration() {
        // I return the duration as a String to keep it simple and avoid parsing issues
        return duration;
    }
    
    public String getGenre() {
        // I return the genre for categorization and filtering
        return genre;
    }
    
    // ENCAPSULATION: I provide setters for controlled modification of private fields
    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setDuration(String duration) {
        // I use String for duration to avoid complex time parsing and keep the code robust
        this.duration = duration;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    
    // DATA PERSISTENCE: I provide a method to convert the movie to CSV format for file storage
    public String toCSV() {
        // I format the movie data as CSV: movieID,title,duration,genre
        // This allows easy saving and loading from CSV files
        return movieID + "," + title + "," + duration + "," + genre;
    }
}

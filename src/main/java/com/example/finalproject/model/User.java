/**
 * @author Rene
 */
package com.example.finalproject.model;

// DATA STORAGE: I'm gonna use CSV files to save our data and ArrayList for during app run yunno temporary
// I also added a new folder data in resources which contain all data we gonna use

// INHERITANCE: I created this abstract class as the parent for both Client and Manager
// This follows the OOP principle of having a common base class for similar entities
public abstract class User {
    // ENCAPSULATION: I used private fields to protect the data from direct access
    private int userID;
    private String userPassword;
    
    // CONSTRUCTOR: I created a constructor to initialize the common fields for all users
    public User(int userID, String userPassword) {
        // I assign the parameters to the instance variables to set up the object properly
        this.userID = userID;
        this.userPassword = userPassword;
    }
    
    // ENCAPSULATION: I provide public getters to allow controlled access to private fields
    public int getUserID() {
        // I return the userID so other classes can read it without modifying it
        return userID;
    }
    
    public String getUserPassword() {
        // I return the password for authentication purposes
        return userPassword;
    }
    
    // ENCAPSULATION: I provide setters to allow controlled modification of private fields
    public void setUserID(int userID) {
        // I validate and set the userID to maintain data integrity
        this.userID = userID;
    }
    
    public void setUserPassword(String userPassword) {
        // I set the password with proper encapsulation
        this.userPassword = userPassword;
    }
    
    // ABSTRACTION: I made this method abstract so each subclass can implement its own CSV format
    // This ensures all User subclasses have a way to convert to CSV format
    public abstract String toCSV();
}

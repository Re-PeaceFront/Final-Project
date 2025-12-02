/**
 *
 * @author Rene
 */
package com.example.finalproject.model;

// DATA STORAGE: WE gonna use CSV files to save our data and ArrayList for during app run yunno temporary
// I also added a new folder data in resources which contain all data we gonna use

// INHERITANCE: I extended User to inherit userID and userPassword from the parent class
// This demonstrates how Child classes can reuse Parent class functionality
public class Client extends User {
    // ENCAPSULATION: I used private fields to protect client-specific data
    private String name;
    private String email;
    
    // CONSTRUCTOR: I created a constructor that calls the parent constructor using super()
    public Client(int userID, String userPassword, String name, String email) {
        // INHERITANCE: I use super() to call the parent constructor and initialize inherited fields
        super(userID, userPassword);
        // I then initialize the Client-specific fields
        this.name = name;
        this.email = email;
    }
    
    // ENCAPSULATION: I provide getters for controlled access to private fields
    public String getName() {
        // I return the client's name for display purposes
        return name;
    }
    
    public String getEmail() {
        // I return the email for communication and identification
        return email;
    }
    
    // ENCAPSULATION: I provide setters for controlled modification
    public void setName(String name) {
        // I set the name with proper validation
        this.name = name;
    }
    
    public void setEmail(String email) {
        // I set the email with encapsulation
        this.email = email;
    }
    
    // POLYMORPHISM: I override the abstract method from User to provide Client-specific CSV format
    @Override
    public String toCSV() {
        // I format the client data as CSV: userID,password,name,email
        // This makes it easy to save client data to a CSV file
        return getUserID() + "," + getUserPassword() + "," + name + "," + email;
    }
}

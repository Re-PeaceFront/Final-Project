/**
 * @author Rene
 */
package com.example.finalproject.model;

// DATA STORAGE: I'm gonna use CSV files to save our data and ArrayList for during app run yunno temporary
// I also added a new folder data in resources which contain all data we gonna use

// INHERITANCE: I extended User to inherit userID and userPassword functionality
// Manager has the same basic user properties but different responsibilities
public class Manager extends User {
    
    // CONSTRUCTOR: I created a constructor that initializes the Manager using the parent constructor
    public Manager(int userID, String userPassword) {
        // INHERITANCE: I use super() to call the User constructor and set up the basic user properties
        // This avoids code duplication and maintains the inheritance hierarchy
        super(userID, userPassword);
    }
    
    // POLYMORPHISM: I override the abstract toCSV method to provide Manager-specific CSV format
    @Override
    public String toCSV() {
        // I format the manager data as CSV: userID,password
        // Managers don't have additional fields like name/email, so I only include inherited fields
        return getUserID() + "," + getUserPassword();
    }
}

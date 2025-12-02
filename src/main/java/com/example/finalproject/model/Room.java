/**
 * @author Rene
 */
package com.example.finalproject.model;

// DATA STORAGE: I'm gonna use CSV files to save our data and ArrayList for during app run yunno temporary
// I also added a new folder data in resources which contain all data we gonna use

// ENCAPSULATION: I created this class to represent a theater room with proper data protection
public class Room {
    // ENCAPSULATION: I used private fields to control access to room data
    private int roomID;
    private String roomName;
    private int capacity;
    
    // CONSTRUCTOR: I created a constructor to initialize all room properties
    public Room(int roomID, String roomName, int capacity) {
        // I set each field with the provided parameters to create a complete room object
        this.roomID = roomID;
        this.roomName = roomName;
        this.capacity = capacity;
    }
    
    // ENCAPSULATION: I provide getters for controlled read access to private fields
    public int getRoomID() {
        // I return the roomID for identification and foreign key references
        return roomID;
    }
    
    public String getRoomName() {
        // I return the room name for display and identification purposes
        return roomName;
    }
    
    public int getCapacity() {
        // I return the capacity to know how many people can fit in this room
        return capacity;
    }
    
    // ENCAPSULATION: I provide setters for controlled modification of room data
    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
    
    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    // DATA PERSISTENCE: I provide a method to convert room data to CSV format
    public String toCSV() {
        // I format the room data as CSV: roomID,roomName,capacity
        // This makes it easy to save and load room information from files
        return roomID + "," + roomName + "," + capacity;
    }
}

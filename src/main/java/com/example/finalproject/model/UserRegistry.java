package com.example.finalproject.model;

import java.util.HashMap;
import java.util.Map;

/**
 * A simple in-memory registry to store user data (email and password).
 * This class provides methods to register a new user and validate login credentials.
 * Author Aya
 */
public class UserRegistry {

    // A static map to hold all registered users. Key: Email, Value: Password
    private static final Map<String, String> registeredUsers = new HashMap<>();

    /**
     * Registers a new user account if the email is not already in use.
     * @param email The user's email (used as the unique login ID).
     * @param password The user's password.
     * @return true if registration was successful, false if the email already exists.
     */
    public static boolean registerUser(String email, String password) {
        // Simple check to ensure the email is unique
        if (registeredUsers.containsKey(email)) {
            return false;
        }
        registeredUsers.put(email, password);
        // Optional: System.out.println("Registered user: " + email);
        return true;
    }

    /**
     * Checks if the provided email and password match a registered user.
     * @param email The login email.
     * @param password The login password.
     * @return true if the credentials are valid, false otherwise.
     */
    public static boolean validateUser(String email, String password) {
        // Check if the email exists AND the stored password matches
        return registeredUsers.containsKey(email) && registeredUsers.get(email).equals(password);
    }
}
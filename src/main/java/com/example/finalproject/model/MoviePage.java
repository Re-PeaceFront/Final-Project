package com.example.finalproject.model;

import com.example.finalproject.model.Movie;
import com.example.finalproject.model.Room;

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
public class MoviePage {

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
    public MoviePage(long id,
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
     * @param amount number of tickets to add, must be greater than 0
     * @throws IllegalArgumentException if amount is less than or equal to 0
     */
    public void addTickets(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Ticket amount must be positive.");
        }
        this.ticketsSold += amount;
    }
}



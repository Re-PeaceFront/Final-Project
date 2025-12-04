package com.example.finalproject.model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.finalproject.model.Movie;
import com.example.finalproject.model.Room;

/**
 * Represents a scheduled showing of a specific movie in a specific room.
 * Each movie page entry (showtime) includes the movie being shown, the room,
 * the calendar date, the start time, and the number of tickets sold so far.
 * <p>
 * This class is part of the Model layer in the MVC architecture. It focuses
 * only on business data and validation and does not depend on any JavaFX UI types.
 *
 * @author Brody
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
     * Always kept as a non-negative value.
     */
    private int ticketsSold;

    /**
     * Creates an empty MoviePage (showtime) instance.
     * Use setters to configure it. This constructor is required
     * by some frameworks and tools.
     */
    public MoviePage() {
        // default constructor
    }

    /**
     * Creates a fully initialized MoviePage (showtime) instance.
     *
     * @param id          unique identifier; must be &gt; 0
     * @param movie       movie that will be shown; must not be {@code null}
     * @param room        room where the showtime takes place; must not be {@code null}
     * @param date        calendar date of the showtime; must not be {@code null}
     * @param startTime   start time of the showtime; must not be {@code null}
     * @param ticketsSold initial number of tickets sold; must be &gt;= 0
     * @throws IllegalArgumentException if any argument violates the rules above
     */
    public MoviePage(long id,
                     Movie movie,
                     Room room,
                     LocalDate date,
                     LocalTime startTime,
                     int ticketsSold) {
        setId(id);
        setMovie(movie);
        setRoom(room);
        setDate(date);
        setStartTime(startTime);
        setTicketsSold(ticketsSold);
    }

    /**
     * Returns the unique identifier of this showtime.
     *
     * @return id value
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this showtime.
     *
     * @param id new identifier; must be &gt; 0
     * @throws IllegalArgumentException if {@code id} is less than or equal to 0
     */
    public void setId(long id) {
        if (id <= 0) {
            throw new IllegalArgumentException("MoviePage id must be positive.");
        }
        this.id = id;
    }

    /**
     * Returns the movie associated with this showtime.
     *
     * @return movie shown
     */
    public Movie getMovie() {
        return movie;
    }

    /**
     * Sets the movie for this showtime.
     *
     * @param movie movie to show; must not be {@code null}
     * @throws IllegalArgumentException if {@code movie} is {@code null}
     */
    public void setMovie(Movie movie) {
        if (movie == null) {
            throw new IllegalArgumentException("Movie cannot be null.");
        }
        this.movie = movie;
    }

    /**
     * Returns the room where this showtime takes place.
     *
     * @return room of the showtime
     */
    public Room getRoom() {
        return room;
    }

    /**
     * Sets the room for this showtime.
     *
     * @param room room to set; must not be {@code null}
     * @throws IllegalArgumentException if {@code room} is {@code null}
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
     * @return date of the showtime
     */
    public LocalDate getDate() {
        return date;
    }

    /**
     * Sets the calendar date of this showtime.
     *
     * @param date date to set; must not be {@code null}
     * @throws IllegalArgumentException if {@code date} is {@code null}
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
     * @return start time of the showtime
     */
    public LocalTime getStartTime() {
        return startTime;
    }

    /**
     * Sets the start time of this showtime.
     *
     * @param startTime time to set; must not be {@code null}
     * @throws IllegalArgumentException if {@code startTime} is {@code null}
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
     * @return number of tickets sold, guaranteed to be &gt;= 0
     */
    public int getTicketsSold() {
        return ticketsSold;
    }

    /**
     * Sets the number of tickets sold for this showtime.
     *
     * @param ticketsSold new count; must be &gt;= 0
     * @throws IllegalArgumentException if {@code ticketsSold} is negative
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
     * @param amount number of tickets to add; must be &gt; 0
     * @throws IllegalArgumentException if {@code amount} is less than or equal to 0
     */
    public void addTickets(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Ticket amount must be positive.");
        }
        setTicketsSold(this.ticketsSold + amount);
    }
}

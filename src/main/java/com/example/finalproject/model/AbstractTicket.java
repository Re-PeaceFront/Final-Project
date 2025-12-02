package com.example.finalproject.model;

/**
 * Abstract base class for different kinds of tickets.
 * Demonstrates abstraction and supports polymorphism by
 * allowing code to work with AbstractTicket instead of
 * concrete ticket implementations.
 *
 * Author: Brody
 */
public abstract class AbstractTicket {

    /**
     * Unique identifier of the ticket.
     */
    private long id;

    /**
     * Creates a new AbstractTicket instance.
     *
     * @param id unique identifier, must be non-negative
     * @throws IllegalArgumentException if id is negative
     */
    public AbstractTicket(long id) {
        setId(id);
    }

    /**
     * Returns the unique identifier of this ticket.
     *
     * @return ticket id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the unique identifier of this ticket.
     *
     * @param id new ticket id, must be non-negative
     * @throws IllegalArgumentException if id is negative
     */
    public void setId(long id) {
        if (id < 0) {
            throw new IllegalArgumentException("Ticket ID must be non-negative.");
        }
        this.id = id;
    }

    /**
     * Returns a human-readable ticket type (e.g. "Regular", "VIP").
     * Subclasses must provide their own value.
     *
     * @return ticket type label
     */
    public abstract String getTicketType();
}

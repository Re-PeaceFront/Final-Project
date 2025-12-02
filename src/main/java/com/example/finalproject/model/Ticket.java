package com.example.finalproject.model;

import java.time.LocalDateTime;

/**
 * Represents a ticket purchased by a client for a specific showtime.
 * Each ticket contains ownership information, seat number (if applicable),
 * and a unique e-ticket code.
 *
 * Demonstrates encapsulation (private fields with getters/setters),
 * uses input validation in setters, and participates in inheritance
 * by extending AbstractTicket.
 *
 * Author: Brody
 */
public class Ticket {

    /**
     * Client who purchased the ticket.
     */
    private Client client;

    /**
     * Showtime associated with this ticket.
     */
    private Showtime showtime;

    /**
     * Date and time when the ticket was purchased.
     */
    private LocalDateTime purchaseDateTime;

    /**
     * Seat number for this ticket, if assigned.
     */
    private String seatNumber;

    /**
     * Unique electronic ticket code for verification.
     */
    private String eTicketCode;

    /**
     * Creates a new Ticket instance.
     *
     * @param id               unique identifier, must be non-negative
     * @param client           purchasing client, cannot be null
     * @param showtime         associated showtime, cannot be null
     * @param purchaseDateTime purchase date/time, cannot be null
     * @param seatNumber       seat number (optional, can be null or blank)
     * @param eTicketCode      unique e-ticket code, cannot be null or blank
     * @throws IllegalArgumentException if any required argument is invalid
     */
    public Ticket(long id,
                  Client client,
                  Showtime showtime,
                  LocalDateTime purchaseDateTime,
                  String seatNumber,
                  String eTicketCode) {
        setClient(client);
        setShowtime(showtime);
        setPurchaseDateTime(purchaseDateTime);
        setSeatNumber(seatNumber);
        setETicketCode(eTicketCode);
    }

    /**
     * Returns the client who purchased this ticket.
     *
     * @return purchasing client
     */
    public Client getClient() {
        return client;
    }

    /**
     * Sets the client who purchased this ticket.
     *
     * @param client purchasing client, cannot be null
     * @throws IllegalArgumentException if client is null
     */
    public void setClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client cannot be null.");
        }
        this.client = client;
    }

    /**
     * Returns the showtime associated with this ticket.
     *
     * @return associated showtime
     */
    public Showtime getShowtime() {
        return showtime;
    }

    /**
     * Sets the showtime associated with this ticket.
     *
     * @param showtime showtime, cannot be null
     * @throws IllegalArgumentException if showtime is null
     */
    public void setShowtime(Showtime showtime) {
        if (showtime == null) {
            throw new IllegalArgumentException("Showtime cannot be null.");
        }
        this.showtime = showtime;
    }

    /**
     * Returns the date and time when the ticket was purchased.
     *
     * @return purchase date and time
     */
    public LocalDateTime getPurchaseDateTime() {
        return purchaseDateTime;
    }

    /**
     * Sets the date and time when the ticket was purchased.
     *
     * @param purchaseDateTime purchase date/time, cannot be null
     * @throws IllegalArgumentException if purchaseDateTime is null
     */
    public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
        if (purchaseDateTime == null) {
            throw new IllegalArgumentException("Purchase date/time cannot be null.");
        }
        this.purchaseDateTime = purchaseDateTime;
    }

    /**
     * Returns the seat number associated with this ticket.
     *
     * @return seat number or empty string if not assigned
     */
    public String getSeatNumber() {
        return seatNumber;
    }

    /**
     * Sets the seat number for this ticket.
     *
     * @param seatNumber seat identifier; null is allowed and will be stored as an empty string
     */
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = (seatNumber != null) ? seatNumber.trim() : "";
    }

    /**
     * Returns the unique e-ticket code used to verify this ticket.
     *
     * @return e-ticket code
     */
    public String getETicketCode() {
        return eTicketCode;
    }

    /**
     * Sets the unique e-ticket code for this ticket.
     *
     * @param eTicketCode e-ticket code, cannot be null or blank
     * @throws IllegalArgumentException if eTicketCode is null or blank
     */
    public void setETicketCode(String eTicketCode) {
        if (eTicketCode == null || eTicketCode.isBlank()) {
            throw new IllegalArgumentException("E-ticket code cannot be empty.");
        }
        this.eTicketCode = eTicketCode.trim();
    }

    /**
     * Returns the type of this ticket.
     * For this implementation, the ticket type is always "Regular".
     *
     * @return string "Regular"
     */
    public String getTicketType() {
        return "Regular";
    }
}

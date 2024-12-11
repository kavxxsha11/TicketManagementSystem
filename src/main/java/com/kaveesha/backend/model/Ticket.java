package com.kaveesha.backend.model;

import lombok.Data;

// @Data is a Lombok annotation to create all the getters, setters, equals, hash, and toString methods, based on the methods in this class.
@Data
public class Ticket {

    // Unique identifier for the ticket
    private Long ticketId;

    // Unique identifier for the vendor
    private Long vendorId;

    // Unique identifier for the customer
    private Long customerId;

    // Flag to indicate if the ticket is sold or available
    private boolean isSold;
}

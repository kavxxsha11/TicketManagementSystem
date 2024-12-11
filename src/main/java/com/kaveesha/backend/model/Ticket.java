package com.kaveesha.backend.model;

import lombok.Data;

@Data
public class Ticket {
    private Long ticketId;
    private Long vendorId;
    private Long customerId;
    private boolean isSold;
}

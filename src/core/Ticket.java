package core;

import java.util.UUID;

public class Ticket {
    private UUID ticketId; // Unique identifier for the ticket
    private UUID customerId;
    private UUID vendorId;

    public Ticket(UUID ticketId, UUID customerId, UUID vendorId) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.vendorId = vendorId;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public UUID getCustomerId() {
        return customerId;
    }

    public void setCustomerId(UUID customerId) {
        this.customerId = customerId;
    }

    public UUID getVendorId() {
        return vendorId;
    }

    public void setVendorId(UUID vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", customerId=" + customerId +
                ", vendorId=" + vendorId +
                '}';
    }
}

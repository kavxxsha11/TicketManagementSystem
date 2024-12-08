package core;

public class Ticket {
    private int ticketId; // Unique identifier for the ticket
    private int customerId;
    private int vendorId;

    // Constructor to initialize the Ticket object
    public Ticket(int ticketId, int customerId, int vendorId) {
        this.ticketId = ticketId;
        this.customerId = customerId;
        this.vendorId = vendorId;
    }

    public int getTicketId() {
        return ticketId;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVendorId() {
        return vendorId;
    }

    public void setVendorId(int vendorId) {
        this.vendorId = vendorId;
    }

    @Override
    public String toString() {
        return " Ticket { " +
                "TicketId = " + ticketId +
                ", Customer Id = '" + customerId + '\'' +
                ", Vendor Id = " + vendorId +
                '}';
    }
}

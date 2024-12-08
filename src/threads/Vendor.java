package threads;

import core.Ticket;
import core.TicketPool;

import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

// Vendor class implements the Runnable interface
public class Vendor implements Runnable {
    private static Logger LOGGER = Logger.getLogger(Vendor.class.getName());
    private TicketPool ticketPool; // Shared ticket pool
    private int totalTickets; // Total tickets to release
    private int ticketReleaseRate; // Ticket releasing rate
    private String vendorName;
    private UUID vendorId;

    // Constructor to initialize the Vendor
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool; // Assign the ticket pool
        this.totalTickets = totalTickets; // Set total tickets
        this.ticketReleaseRate = ticketReleaseRate; // Set release rate
        this.vendorName = Thread.currentThread().getName();
        this.vendorId = UUID.randomUUID();
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.info(Thread.currentThread().getName() + " is interrupted. Exiting gracefully.");
                return;
            }
            try {
                Ticket ticket = new Ticket(UUID.randomUUID(), null, vendorId);
                ticketPool.addTickets(ticket);
                LOGGER.info(vendorId + " added ticket " + ticket);
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                LOGGER.warning(vendorId + " interrupted while sleeping. Exiting...");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public UUID getVendorId() {
        return vendorId;
    }
}
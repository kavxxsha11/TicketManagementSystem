package threads;

import core.Ticket;
import core.TicketPool;

import java.util.UUID;
import java.util.logging.Logger;

public class Vendor implements Runnable {
    private static Logger LOGGER = Logger.getLogger(Vendor.class.getName());
    private TicketPool ticketPool;
    private int totalTickets;
    private int ticketReleaseRate;
    private UUID vendorId;

    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.vendorId = UUID.randomUUID();
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.info(vendorId + " interrupted. Exiting gracefully.");
                return;
            }
            try {
                Ticket ticket = new Ticket(UUID.randomUUID(), null, vendorId);
                ticketPool.addTickets(ticket);
                LOGGER.info(vendorId + " added ticket: " + ticket);
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

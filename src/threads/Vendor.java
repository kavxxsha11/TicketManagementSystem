package threads;

import core.Ticket;
import core.TicketPool;

// Vendor class implements the Runnable interface
public class Vendor implements Runnable {
    private TicketPool ticketPool; // Shared ticket pool
    private int totalTickets; // Total tickets to release
    private int ticketReleaseRate; // Ticket releasing rate

    // Constructor to initialize the Vendor
    public Vendor(TicketPool ticketPool, int totalTickets, int ticketReleaseRate) {
        this.ticketPool = ticketPool; // Assign the ticket pool
        this.totalTickets = totalTickets; // Set total tickets
        this.ticketReleaseRate = ticketReleaseRate; // Set release rate
    }

    @Override
    public void run() {
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket(i,"Event",1000.0);
            ticketPool.addTickets(ticket);

            try {
                Thread.sleep(ticketReleaseRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
    }
}
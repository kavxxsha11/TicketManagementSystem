package threads;

import core.Ticket;
import core.TicketPool;

// Customer class implements the Runnable interface
public class Customer implements Runnable {
    private final TicketPool ticketPool; // Shared ticket pool
    private final int customerRetrievalRate; // Rate of ticket retrieval
    private final int amount; // Number of tickets the customer wants to retrieve

    // Constructor to initialize the Customer
    public Customer(TicketPool ticketPool, int customerRetrievalRate, int amount) {
        this.ticketPool = ticketPool; // Assign the ticket pool
        this.customerRetrievalRate = customerRetrievalRate; // Set retrieval rate
        this.amount = amount; // Set the number of tickets to retrieve
    }

    @Override
    public void run() {
        // Loop to retrieve the specified number of tickets
        for (int i = 0; i < amount; i++) {
            Ticket ticket = ticketPool.getTicket();
            if (ticket == null) {
                System.out.println("No more tickets available for " + Thread.currentThread().getName());
                break;
            }
            System.out.println("This ticket purchased by " + Thread.currentThread().getName() + ticket);

            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                System.out.println("Customer thread interrupted: " + e.getMessage());
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

package threads;

import core.Ticket;
import core.TicketPool;

// Customer class implements the Runnable interface
public class Customer implements Runnable {
    private TicketPool ticketPool; // Shared ticket pool
    private int customerRetrievalRate; // Rate of ticket retrieval
    private int amount; // Number of tickets the customer wants to retrieve

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
            System.out.println("This ticket purchased by " + Thread.currentThread().getName() + "Ticket is " + ticket);

            try {
                Thread.sleep(customerRetrievalRate * 1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

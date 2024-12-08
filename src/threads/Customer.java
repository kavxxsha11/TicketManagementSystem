package threads;

import core.Ticket;
import core.TicketPool;

import java.util.logging.Level;
import java.util.logging.Logger;

// Customer class implements the Runnable interface
public class Customer implements Runnable {
    private static Logger LOGGER = Logger.getLogger(Customer.class.getName());
    private TicketPool ticketPool; // Shared ticket pool
    private int customerRetrievalRate; // Rate of ticket retrieval
    private int amount; // Number of tickets the customer wants to retrieve
    private String customerName;
    private int customerID;

    // Constructor to initialize the Customer
    public Customer(TicketPool ticketPool, int customerRetrievalRate, int amount) {
        this.ticketPool = ticketPool; // Assign the ticket pool
        this.customerRetrievalRate = customerRetrievalRate; // Set retrieval rate
        this.amount = amount; // Set the number of tickets to retrieve
        this.customerName = Thread.currentThread().getName();
        this.customerID = 1;
    }

    @Override
    public void run() {
        // Loop to retrieve the specified number of tickets
        for (int i = 0; i < amount; i++) {
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.info(Thread.currentThread().getName() + " interrupted. Exiting gracefully. ");
                return;
            }

            try {
                Ticket ticket = ticketPool.getTicket();
                if (ticket != null) {
                    LOGGER.info(Thread.currentThread().getName() + " purchased ticket: " + ticket);

                } else {
                    LOGGER.warning(Thread.currentThread().getName() + " failed to purchase ticket.");
                }
                Thread.sleep(customerRetrievalRate * 1000);

            } catch (InterruptedException e) {
                LOGGER.warning(Thread.currentThread().getName() + " interrupted while sleeping. Exiting...");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}

package threads;

import core.Ticket;
import core.TicketPool;

import java.util.UUID;
import java.util.logging.Logger;

public class Customer implements Runnable {
    private static Logger LOGGER = Logger.getLogger(Customer.class.getName());
    private TicketPool ticketPool;
    private int customerRetrievalRate;
    private int amount;
    private UUID customerId;

    public Customer(TicketPool ticketPool, int customerRetrievalRate, int amount) {
        this.ticketPool = ticketPool;
        this.customerRetrievalRate = customerRetrievalRate;
        this.amount = amount;
        this.customerId = UUID.randomUUID();
    }

    @Override
    public void run() {
        for (int i = 0; i < amount; i++) {
            if (Thread.currentThread().isInterrupted()) {
                LOGGER.info(customerId + " interrupted. Exiting gracefully.");
                return;
            }

            try {
                Ticket ticket = ticketPool.getTicket();
                if (ticket != null) {
                    LOGGER.info(customerId + " purchased ticket: " + ticket);
                } else {
                    LOGGER.warning(customerId + " failed to purchase ticket.");
                }
                Thread.sleep(customerRetrievalRate * 1000);

            } catch (InterruptedException e) {
                LOGGER.warning(customerId + " interrupted while sleeping. Exiting...");
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public UUID getCustomerId() {
        return customerId;
    }
}

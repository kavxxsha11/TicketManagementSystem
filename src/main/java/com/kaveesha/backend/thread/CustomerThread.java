package com.kaveesha.backend.thread;

// The CustomerThread class implements the Runnable interface
// This class represents a customer thread that purchases tickets from the ticket pool.
public class CustomerThread implements Runnable {

    // Reference to the shared ticket pool
    private final TicketPool ticketPool;

    // Constructor to initialize the ticket pool reference
    public CustomerThread(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // The run method contains the logic executed by the customer thread
    @Override
    public void run() {
        // Continue processing while the ticket pool is running and the thread is not interrupted
        while (ticketPool.isRunning() && !Thread.currentThread().isInterrupted()) {
            try {
                // Purchase a ticket from the ticket pool
                ticketPool.purchaseTicket();

                // Pause the thread for 1 millisecond to simulate customer processing time
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // Handle thread interuption and set the interrupted flag
                Thread.currentThread().interrupt();
            }
        }
        // Print a message when the customer thread stops
        System.out.println("Customer thread stopped.");
    }
}

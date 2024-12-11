package com.kaveesha.backend.thread;

// The vendor thread class that implements the Runnable interface
// It simulates a vendor continuously adding tickets to the ticket pool
public class VendorThread implements Runnable {
    // Reference to the shared ticket pool
    private final TicketPool ticketPool;

    // Constructor to initialize the ticket pool reference
    public VendorThread(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    // The run method contains the logic executed by the thread
    @Override
    public void run() {
        // Continuously add tickets to the pool while the system is running and the thread is not interrupted
        while (ticketPool.isRunning() && !Thread.currentThread().isInterrupted()) {
            try {
                // Add a new ticket to the pool
                ticketPool.addTicket();

                // Pause the thread for 1 millisecond
                Thread.sleep(1);
            } catch (InterruptedException e) {
                // Handle the thread interruption and set the interrupted flag
                Thread.currentThread().interrupt();
            }
        }
        // Print a message when the thread stops
        System.out.println("Vendor thread stopped.");
    }
}

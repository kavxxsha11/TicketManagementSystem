package com.kaveesha.backend.service;

import com.kaveesha.backend.config.Configure;
import com.kaveesha.backend.thread.CustomerThread;
import com.kaveesha.backend.thread.VendorThread;
import com.kaveesha.backend.thread.TicketPool;
import org.springframework.stereotype.Service;

// The @Service annotation marks this class as a Spring-managed service.
@Service
public class TicketService {

    // Shared ticket pool for managing tickets between vendors and customers
    private final TicketPool ticketPool = new TicketPool();

    // Flag to track system running status
    private boolean running = false;

    // Configuration object loaded from configuration file
    private final Configure config;

    // Constructor to load configuration settings
    public TicketService() {
        this.config = Configure.loadConfiguration();
    }

    // Start the ticketing system, initializing vendor and customer threads
    public synchronized void startSystem() {
        // Check if system is already running
        if (running) {
            System.out.println("System is already running.");
            return;
        }
        running = true;

        // Start vendor threads based on the ticket release rate from configuration
        for (int i = 0; i < config.getTicketReleaseRate(); i++) {
            new Thread(new VendorThread(ticketPool)).start();
        }

        // Start customer threads based on the customer retrieval rate from configuration
        for (int i = 0; i < config.getCustomerRetrievalRate(); i++) {
            new Thread(new CustomerThread(ticketPool)).start();
        }

        System.out.println("System started with " + config.getTicketReleaseRate() + " vendor(s) and "
                           + config.getCustomerRetrievalRate() + " customer(s).");
    }

    // Stop the ticketing system
    public synchronized void stopSystem() {
        // Check if system is already stopped
        if (!running) {
            System.out.println("System is not running.");
            return;
        }
        running = false;
        System.out.println("System stopped.");
    }

    // Return the current running status of the system
    public boolean isRunning() {
        return running;
    }

    public int getAvailableTickets() {
        return ticketPool.getTicketCount();  // Assuming TicketPool has this method
    }
}

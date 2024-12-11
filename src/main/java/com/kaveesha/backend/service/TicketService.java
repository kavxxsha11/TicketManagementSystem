package com.kaveesha.backend.service;

import com.kaveesha.backend.config.Configure;
import com.kaveesha.backend.thread.CustomerThread;
import com.kaveesha.backend.thread.VendorThread;
import com.kaveesha.backend.thread.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class TicketService {

    private final TicketPool ticketPool = new TicketPool();
    private boolean running = false;
    private final Configure config;

    public TicketService() {
        this.config = Configure.loadConfiguration();
    }

    public synchronized void startSystem() {
        if (running) {
            System.out.println("System is already running.");
            return;
        }
        running = true;

        // Start vendor threads
        for (int i = 0; i < config.getTicketReleaseRate(); i++) {
            new Thread(new VendorThread(ticketPool)).start();
        }

        // Start customer threads
        for (int i = 0; i < config.getCustomerRetrievalRate(); i++) {
            new Thread(new CustomerThread(ticketPool)).start();
        }

        System.out.println("System started with " + config.getTicketReleaseRate() + " vendor(s) and "
                           + config.getCustomerRetrievalRate() + " customer(s).");
    }

    public synchronized void stopSystem() {
        if (!running) {
            System.out.println("System is not running.");
            return;
        }
        running = false;
        System.out.println("System stopped.");
    }

    public boolean isRunning() {
        return running;
    }
}

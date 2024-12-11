package com.kaveesha.backend.thread;

import java.util.LinkedList;
import java.util.Queue;

import com.kaveesha.backend.config.Configure;

// The TicketPool class manages a shared pool of tickets. It simulates a system where
// vendors add tickets to the pool, and customers can purchase them until the pool is full or empty.
public class TicketPool {

    // Queue to store tickets, representing the shared ticket pool
    private final Queue<String> tickets = new LinkedList<>();

    // Maximum capacity of the ticket pool
    public final int MAX_CAPACITY = Configure.loadConfiguration().getMaxTicketCapacity();

    // Flag to indicate if the simulation is running
    private boolean running = true;

    // Method to add a ticket to the pool if the pool is not full and the simulation is running
    public synchronized void addTicket() {
        // Check if the simulation has stopped, exit the method
        if (!running) {
            return;
        }

        // Check if the ticket pool is not full, add a ticket if there is space
        if (tickets.size() < MAX_CAPACITY) {
            // Add a new ticket to the pool and increment the ticket number
            tickets.add("Ticket-" + (tickets.size() + 1));
            System.out.println("Vendor added a ticket. Current tickets: " + tickets.size());
        } else {
            // If the ticket pool is full, stop the simulation
            System.out.println("Ticket pool is full. Stopping simulation.");
            stopSimulation();
        }
    }

    // Method to purchase a ticket from the pool if the simulation is running
    public synchronized void purchaseTicket() {
        // Check if the simulation has stopped, exit the method
        if (!running) {
            return;
        }

        // Check if there are tickets available, purchase a ticket if available
        if (!tickets.isEmpty()) {
            // Remove a ticket from the pool and simulate a purchase
            String ticket = tickets.poll();
            System.out.println("Customer purchased " + ticket + ". Remaining tickets: " + tickets.size());
        } else {
            // Notify that no tickets are available for purchase
            System.out.println("No tickets available for purchase.");
        }
    }

    // Stop the simulation when the ticket pool reaches maximum capacity
    private synchronized void stopSimulation() {
        // Set the running flag to false to stop the simulation
        running = false;
        System.out.println("Simulation has stopped as the ticket pool reached max capacity.");
    }

    // Return the current running status of the simulation
    public boolean isRunning() {
        return running;
    }

    // Return the current number of tickets in the pool
    public int getTicketCount() {
        return tickets.size();
    }
}

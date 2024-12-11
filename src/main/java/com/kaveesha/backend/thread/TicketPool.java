package com.kaveesha.backend.thread;

import java.util.LinkedList;
import java.util.Queue;

import com.kaveesha.backend.config.Configure;

public class TicketPool {
    private final Queue<String> tickets = new LinkedList<>();
    public final int MAX_CAPACITY = Configure.loadConfiguration().getMaxTicketCapacity();
    private boolean running = true;

    public synchronized void addTicket() {
        if (!running) {
            return;
        }

        if (tickets.size() < MAX_CAPACITY) {
            tickets.add("Ticket-" + (tickets.size() + 1));
            System.out.println("Vendor added a ticket. Current tickets: " + tickets.size());
        } else {
            System.out.println("Ticket pool is full. Stopping simulation.");
            stopSimulation();
        }
    }

    public synchronized void purchaseTicket() {
        if (!running) {
            return;
        }

        if (!tickets.isEmpty()) {
            String ticket = tickets.poll();
            System.out.println("Customer purchased " + ticket + ". Remaining tickets: " + tickets.size());
        } else {
            System.out.println("No tickets available for purchase.");
        }
    }

    private synchronized void stopSimulation() {
        running = false;
        System.out.println("Simulation has stopped as the ticket pool reached max capacity.");
    }

    public boolean isRunning() {
        return running;
    }
}

package com.backend.model;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<String> tickets = new LinkedList<>();
    private final int maxCapacity;

    public TicketPool(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    public synchronized void addTicket(String ticket) {
        while (tickets.size() >= maxCapacity) {
            try {
                wait(); // Wait until there is space
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        tickets.add(ticket);
        System.out.println("Added ticket: " + ticket);
        notifyAll(); // Notify waiting customers
    }

    public synchronized String retrieveTicket() {
        while (tickets.isEmpty()) {
            try {
                wait(); // Wait until there are tickets
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        String ticket = tickets.poll();
        System.out.println("Retrieved ticket: " + ticket);
        notifyAll(); // Notify waiting vendors
        return ticket;
    }
}

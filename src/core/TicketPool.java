package core;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> ticketQueue;
    private final int maximumCapacity;

    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    public synchronized void addTickets(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                System.out.println(Thread.currentThread().getName() + ": Pool full. Waiting to add ticket...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted while waiting to add a ticket: " + e.getMessage());
                return;
            }
        }

        ticketQueue.add(ticket);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + ": Added ticket with ID: " + ticket.getTicketId());
    }

    public synchronized Ticket getTicket() {
        while (ticketQueue.isEmpty()) {
            try {
                System.out.println(Thread.currentThread().getName() + ": No tickets available. Waiting...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted while waiting to get a ticket: " + e.getMessage());
                return null;
            }
        }

        Ticket ticket = ticketQueue.poll();
        notifyAll();
        System.out.println(Thread.currentThread().getName() + ": Retrieved ticket with ID: " + ticket.getTicketId());
        return ticket;
    }

    public synchronized int getCurrentTicketCount() {
        return ticketQueue.size();
    }

    public synchronized boolean isTicketPoolFull() {
        return ticketQueue.size() >= maximumCapacity;
    }
}

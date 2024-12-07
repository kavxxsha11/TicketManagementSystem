package core;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private final Queue<Ticket> ticketQueue;
    private final int maximumCapacity;

    public TicketPool() {
        this.maximumCapacity = 100;
        this.ticketQueue = new LinkedList<>();
    }

    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<>();
    }

    /*
    addTickets method used by Vendors to add tickets
     */
    public synchronized void addTickets(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted while waiting to add a new ticket: " + e.getMessage());
                return;
            }
        }

        ticketQueue.add(ticket);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + ": Ticket added: " + ticketQueue.size());
    }

    /*
    getTicket method used by Customers to buy tickets
     */
    public synchronized Ticket getTicket() {
        while (ticketQueue.isEmpty()) {
            try{
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Thread interrupted while waiting for a ticket: " + e.getMessage());
                return null;
            }
        }

        Ticket ticket = ticketQueue.poll();
        notifyAll();
        System.out.println(Thread.currentThread().getName() + ": Ticket purchased : " + ticketQueue.size());
        return ticket;
    }
}

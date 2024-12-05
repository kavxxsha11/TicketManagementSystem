package core;

import java.util.LinkedList;
import java.util.Queue;

public class TicketPool {
    private Queue<Ticket> ticketQueue;
    private int maximumCapacity;

    public TicketPool(int maximumCapacity) {
        this.maximumCapacity = maximumCapacity;
        this.ticketQueue = new LinkedList<Ticket>();
    }

    /*
    addTickets method used by Vendors to add tickets
     */
    public synchronized void addTickets(Ticket ticket) {
        while (ticketQueue.size() >= maximumCapacity) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        }

        this.ticketQueue.add(ticket);
        notifyAll();
        System.out.println(Thread.currentThread().getName() + ": Ticket added: " + ticketQueue.size());

    }
}

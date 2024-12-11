package com.kaveesha.backend.thread;

public class CustomerThread implements Runnable {
    private final TicketPool ticketPool;

    public CustomerThread(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (ticketPool.isRunning() && !Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.purchaseTicket();
                Thread.sleep(1); // Adjusted for per-millisecond processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Customer thread stopped.");
    }
}

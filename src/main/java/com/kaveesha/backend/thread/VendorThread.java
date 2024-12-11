package com.kaveesha.backend.thread;

public class VendorThread implements Runnable {
    private final TicketPool ticketPool;

    public VendorThread(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        while (ticketPool.isRunning() && !Thread.currentThread().isInterrupted()) {
            try {
                ticketPool.addTicket();
                Thread.sleep(1); // Adjusted for per-millisecond processing
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Vendor thread stopped.");
    }
}

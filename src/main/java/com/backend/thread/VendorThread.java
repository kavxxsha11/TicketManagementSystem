package com.backend.thread;

import com.backend.service.TicketService;

public class VendorThread extends Thread {
    private final TicketService ticketService;
    private final int releaseRate;

    public VendorThread(TicketService ticketService, int releaseRate) {
        this.ticketService = ticketService;
        this.releaseRate = releaseRate;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(1000 / releaseRate); // Control ticket release rate
                System.out.println("Vendor is selling tickets...");
                boolean ticketSold = ticketService.sellTicket();

                if (!ticketSold) {
                    System.out.println("Vendor stopped: No tickets left to sell.");
                    break;
                }
            }
        } catch (InterruptedException e) {
            System.out.println("Vendor thread interrupted.");
        }
    }
}

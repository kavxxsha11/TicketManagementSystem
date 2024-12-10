package com.backend.thread;

import com.backend.service.TicketService;

public class CustomerThread extends Thread {
    private final TicketService ticketService;
    private final int customerId;

    public CustomerThread(TicketService ticketService, int customerId) {
        this.ticketService = ticketService;
        this.customerId = customerId;
    }

    @Override
    public void run() {
        System.out.println("Customer " + customerId + " is attempting to buy a ticket...");
        boolean ticketSold = ticketService.sellTicket();

        if (ticketSold) {
            System.out.println("Customer " + customerId + " successfully purchased a ticket.");
        } else {
            System.out.println("Customer " + customerId + " failed to purchase a ticket. Tickets are sold out.");
        }
    }
}

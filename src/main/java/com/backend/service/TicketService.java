package com.backend.service;

import com.backend.model.Ticket;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {

    private static final String TICKET_FILE = "tickets.json";
    private final ObjectMapper objectMapper;

    public TicketService() {
        this.objectMapper = new ObjectMapper();
    }

    // Create a new ticket
    public Ticket createTicket(Ticket ticket) {
        List<Ticket> tickets = getAllTickets();
        tickets.add(ticket);
        writeTicketsToFile(tickets);
        return ticket;
    }

    // Get all tickets
    public List<Ticket> getAllTickets() {
        try {
            File file = new File(TICKET_FILE);
            if (file.exists()) {
                return objectMapper.readValue(file, new TypeReference<List<Ticket>>() {});
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return List.of();
    }

    // Get ticket by ID
    public Optional<Ticket> getTicketById(Long id) {
        List<Ticket> tickets = getAllTickets();
        return tickets.stream().filter(ticket -> ticket.getId().equals(id)).findFirst();
    }

    // Update a ticket
    public Ticket updateTicket(Long id, Ticket updatedTicket) {
        List<Ticket> tickets = getAllTickets();
        for (int i = 0; i < tickets.size(); i++) {
            if (tickets.get(i).getId().equals(id)) {
                tickets.set(i, updatedTicket);
                writeTicketsToFile(tickets);
                return updatedTicket;
            }
        }
        return null;
    }

    // Delete a ticket
    public boolean deleteTicket(Long id) {
        List<Ticket> tickets = getAllTickets();
        boolean removed = tickets.removeIf(ticket -> ticket.getId().equals(id));
        if (removed) {
            writeTicketsToFile(tickets);
        }
        return removed;
    }

    // Helper method to write tickets back to the file
    private void writeTicketsToFile(List<Ticket> tickets) {
        try {
            objectMapper.writeValue(new File(TICKET_FILE), tickets);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Sell a ticket (attempt to purchase)
    public synchronized boolean sellTicket() {
        List<Ticket> tickets = getAllTickets();
        for (Ticket ticket : tickets) {
            if (ticket.isActive()) {
                ticket.setActive(false);
                writeTicketsToFile(tickets);
                return true;
            }
        }
        return false;  // No active tickets left
    }
}

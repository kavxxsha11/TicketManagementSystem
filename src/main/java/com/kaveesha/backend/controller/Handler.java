package com.kaveesha.backend.controller;


import com.kaveesha.backend.service.TicketService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Handler {

    private final TicketService ticketService;

    public Handler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/api/system")
    public String handleSystemSignal(@RequestParam String signal) {
        switch (signal.toLowerCase()) {
            case "on":
                ticketService.startSystem();
                return "System started.";
            case "off":
                ticketService.stopSystem();
                return "System stopped.";
            case "status":
                return ticketService.isRunning() ? "System is running." : "System is stopped.";
            default:
                return "Invalid signal. Use 'on', 'off', or 'status'.";
        }
    }
}

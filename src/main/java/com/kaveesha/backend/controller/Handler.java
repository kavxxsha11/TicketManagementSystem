package com.kaveesha.backend.controller;

import com.kaveesha.backend.config.Configure;
import com.kaveesha.backend.service.TicketService;
import org.springframework.web.bind.annotation.*;

/**
 * REST Controller for handling system operations related to ticketing.
 */
@RestController
@RequestMapping("/api/system") // Map all requests starting with /api/system
@CrossOrigin(origins = "http://localhost:5173") // Allow frontend requests from http://localhost:5173
public class Handler {

    private final TicketService ticketService; // Service to handle ticket-related logic

    /**
     * Constructor to initialize the TicketService.
     */
    public Handler(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    /**
     * Configures the system based on the provided configuration data.
     * 
     * @param config Configuration object with system parameters.
     * @return the configuration object after saving.
     */
    @PostMapping("/configure") // Mapping POST requests for /api/system/configure
    public Configure configureSystem(@RequestBody Configure config) {
        // Save the configuration or process it as needed
        config.saveConfiguration(); // Save configuration data
        return config; // Return the saved configuration
    }

    /**
     * Starts the ticketing system.
     * 
     * @return a success message.
     */
    @PostMapping("/start") // Mapping POST requests for /api/system/start
    public String startSystem() {
        ticketService.startSystem(); // Call the service to start the system
        return "System started."; // Return a confirmation message
    }

    /**
     * Stops the ticketing system.
     * 
     * @return a success message.
     */
    @PostMapping("/stop") // Mapping POST requests for /api/system/stop
    public String stopSystem() {
        ticketService.stopSystem(); // Call the service to stop the system
        return "System stopped."; // Return a confirmation message

    }

    /**
     * Retrieves the current status of the system.
     * 
     * @return a string indicating the system's status.
     */
    @GetMapping("/status") // Mapping GET requests for /api/system/status
    public String getSystemStatus() {
        // Check if the system is running and return the appropriate status message
        return ticketService.isRunning() ? "System is running." : "System is stopped.";
    }

    /**
     * Retrieves the current number of available tickets in the system.
     * 
     * @return the number of available tickets.
     */
    @GetMapping("/tickets") // Mapping GET requests for /api/system/tickets
    public int getAvailableTickets() {
        return ticketService.getAvailableTickets(); // Return the available ticket count
    }

}

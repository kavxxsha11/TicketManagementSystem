package com.kaveesha.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// The @SpringBootApplication annotation indicates that this is the main entry point for a Spring Boot application.
// It automatically configures and launches the Spring context.
@SpringBootApplication
public class BackendApplication {
    // The main method is the entry point for the application.
    public static void main(String[] args) {
        // The SpringApplication.run method starts the Spring Boot application.
        SpringApplication.run(BackendApplication.class, args);

        // Print a message to the console to indicate that the backend is running.
        System.out.println("Real-Time Event Ticketing System Backend is running!");

        // Provides the URL to check the system status.
        System.out.println("Visit http://localhost:8080/api/system?signal=status to check the system status.");
    }
}

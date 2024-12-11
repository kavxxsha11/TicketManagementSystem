package com.kaveesha.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        System.out.println("Real-Time Event Ticketing System Backend is running!");
        System.out.println("Visit http://localhost:8080/api/system?signal=status to check the system status.");
    }
}

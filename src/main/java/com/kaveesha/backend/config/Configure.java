package com.kaveesha.backend.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

public class Configure {
    // Instance variables to hold configuration data
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    // Default constructor for JSON deserialization
    public Configure() {
    }

    // Constructor to initialize configuration properties
    public Configure(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Method to save configuration to a JSON file
    public void saveConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        try {
            // Write the configuration to a JSON file
            mapper.writeValue(new File("configData.json"), this);
            System.out.println("Configuration saved successfully to configData.json.");
        } catch (IOException e) {
            // Handle error if the configuration cannot be saved
            System.err.println("Failed to save configuration: " + e.getMessage());
        }
    }

    // Method to load configuration from a JSON file
    public static Configure loadConfiguration() {
        ObjectMapper mapper = new ObjectMapper();
        File file = new File("configData.json");

        // Check if the configuration file exists
        if (!file.exists()) {
            System.out.println("No existing configuration found. Please configure the system.");
            return null; // Return null if no configuration is found
        }

        try {
            // Read the configuration from the JSON file
            return mapper.readValue(file, Configure.class);
        } catch (IOException e) {
            // Handle error if the configuration cannot be loaded
            System.err.println("Failed to load configuration: " + e.getMessage());
            return null;
        }
    }

    // Getter and setter methods for the configuration properties
    public int getTotalTickets() {
        return totalTickets;
    }

    public void setTotalTickets(int totalTickets) {
        this.totalTickets = totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public void setTicketReleaseRate(int ticketReleaseRate) {
        this.ticketReleaseRate = ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public void setCustomerRetrievalRate(int customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }

    public void setMaxTicketCapacity(int maxTicketCapacity) {
        this.maxTicketCapacity = maxTicketCapacity;
    }

    // Override the toString method to display the configuration details
    @Override
    public String toString() {
        return "Configure{" +
                "totalTickets=" + totalTickets +
                ", ticketReleaseRate=" + ticketReleaseRate +
                ", customerRetrievalRate=" + customerRetrievalRate +
                ", maxTicketCapacity=" + maxTicketCapacity +
                '}';
    }
}

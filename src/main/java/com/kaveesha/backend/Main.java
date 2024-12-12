package com.kaveesha.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;

import com.kaveesha.backend.configuration.Configure;

public class Main {

    // The backend URL to interact with the system's API
    private static final String BACKEND_URL = "http://localhost:8080/api/system";

    // Boolean flag to keep track of the CLI's running state
    private static boolean isRunning = true;

    // Main method: Entry point for the CLI application
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Real-Time Event Ticketing System CLI!");

        // Main loop for the CLI, awaiting user commands
        while (isRunning) {
            // Display available commands to the user
            System.out.println("\nAvailable Commands:");
            System.out.println("1. configure   - Set system parameters.");
            System.out.println("2. start       - Start ticketing operations.");
            System.out.println("3. stop        - Stop ticketing operations.");
            System.out.println("4. status      - Check the system status.");
            System.out.println("5. exit        - Exit the CLI.");

            // Prompt the user to enter a command
            System.out.print("\nEnter your command: ");
            String command = scanner.nextLine().toLowerCase();

            // Execute the corresponding action based on the user input
            switch (command) {
                case "1":
                    configureSystem(scanner); // Configure system parameters
                    break;
                case "2":
                    System.out.println("Starting ticketing operations...");
                    controlSystem("on"); // Start the system
                    break;
                case "3":
                    System.out.println("Stopping ticketing operations...");
                    controlSystem("off"); // Stop the system
                    break;
                case "4":
                    checkStatus(); // Check system status
                    break;
                case "5":
                    System.out.println("Exiting CLI...");
                    shutdownBackend(); // Stop the backend system
                    isRunning = false; // Terminate the CLI
                    break;
                default:
                    System.out.println("Invalid command. Please try again."); // Handle invalid input
            }
        }
        scanner.close(); // Close the scanner resource
        System.out.println("CLI terminated."); // Notify that the CLI has terminated
    }

    // Method to configure system parameters
    private static void configureSystem(Scanner scanner) {
        Configure config = Configure.loadConfiguration(); // Load existing configuration

        // Check if a previous configuration exists
        if (config != null) {
            System.out.println("Existing configuration found:");
            System.out.println(config); // Display current configuration
            System.out.print("Do you want to use this configuration? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();

            // If user chooses to use existing configuration, skip configuration process
            if ("yes".equals(choice)) {
                System.out.println("Using existing configuration.");
                return;
            }
        }

        // Prompt the user to input new configuration values
        System.out.println("Configuring system parameters...");
        try {
            System.out.print("Enter total tickets: ");
            int totalTickets = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter ticket release rate (tickets per millisecond): ");
            int ticketReleaseRate = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter customer retrieval rate (tickets per millisecond): ");
            int customerRetrievalRate = Integer.parseInt(scanner.nextLine());

            System.out.print("Enter maximum ticket capacity: ");
            int maxTicketCapacity = Integer.parseInt(scanner.nextLine());

            // Create and save the new configuration
            config = new Configure(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
            config.saveConfiguration();
            System.out.println("Configuration saved successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values."); // Handle invalid number input
        }
    }

    // Method to control the system (start or stop)
    private static void controlSystem(String signal) {
        try {
            // Construct the URI with the appropriate signal (on/off)
            URI uri = URI.create(BACKEND_URL + "?signal=" + signal);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET"); // Send a GET request to the backend

            // Read the response from the backend
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("System response: " + response); // Output the backend response
            }
        } catch (IOException e) {
            System.out.println("Error communicating with the backend: " + e.getMessage()); // Handle errors
        }
    }

    // Method to check the current status of the system
    private static void checkStatus() {
        try {
            // Send a GET request to check the system status
            URI uri = URI.create(BACKEND_URL + "?signal=status");
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            // Read the status response from the backend
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("System Status: " + response); // Output the system's status
            }
        } catch (IOException e) {
            System.out.println("Error communicating with the backend: " + e.getMessage()); // Handle errors
        }
    }
    
    // Method to shut down the backend system
    private static void shutdownBackend() {
        System.out.println("Stopping backend...");
        try {
            // Send a GET request to stop the backend system
            URI uri = URI.create(BACKEND_URL + "?signal=off");
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            // Read the response from the backend
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("Backend response: " + response); // Output the backend response
            }
        } catch (IOException e) {
            System.out.println("Error communicating with the backend during shutdown: " + e.getMessage()); // Handle errors
        }
    }
}

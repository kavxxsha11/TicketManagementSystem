package com.kaveesha.backend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.util.Scanner;

import com.kaveesha.backend.config.Configure;

public class Main {
    private static final String BACKEND_URL = "http://localhost:8080/api/system";
    private static boolean isRunning = true;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to the Real-Time Event Ticketing System CLI!");

        while (isRunning) {
            System.out.println("\nAvailable Commands:");
            System.out.println("1. configure   - Set system parameters.");
            System.out.println("2. start       - Start ticketing operations.");
            System.out.println("3. stop        - Stop ticketing operations.");
            System.out.println("4. status      - Check the system status.");
            System.out.println("5. exit        - Exit the CLI.");

            System.out.print("\nEnter your command: ");
            String command = scanner.nextLine().toLowerCase();

            switch (command) {
                case "1":
                    configureSystem(scanner);
                    break;
                case "2":
                    System.out.println("Starting ticketing operations...");
                    controlSystem("on");
                    break;
                case "3":
                    System.out.println("Stopping ticketing operations...");
                    controlSystem("off");
                    break;
                case "4":
                    checkStatus();
                    break;
                case "5":
                    System.out.println("Exiting CLI...");
                    shutdownBackend();
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid command. Please try again.");
            }
        }
        scanner.close();
        System.out.println("CLI terminated.");
    }

    private static void configureSystem(Scanner scanner) {
        Configure config = Configure.loadConfiguration();

        if (config != null) {
            System.out.println("Existing configuration found:");
            System.out.println(config);
            System.out.print("Do you want to use this configuration? (yes/no): ");
            String choice = scanner.nextLine().toLowerCase();

            if ("yes".equals(choice)) {
                System.out.println("Using existing configuration.");
                return;
            }
        }

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

            config = new Configure(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
            config.saveConfiguration();
            System.out.println("Configuration saved successfully.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter numeric values.");
        }
    }

    private static void controlSystem(String signal) {
        try {
            URI uri = URI.create(BACKEND_URL + "?signal=" + signal);
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("System response: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error communicating with the backend: " + e.getMessage());
        }
    }

    private static void checkStatus() {
        try {
            URI uri = URI.create(BACKEND_URL + "?signal=status");
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("System Status: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error communicating with the backend: " + e.getMessage());
        }
    }

    private static void shutdownBackend() {
        System.out.println("Stopping backend...");
        try {
            URI uri = URI.create(BACKEND_URL + "?signal=off");
            HttpURLConnection connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String response = reader.readLine();
                System.out.println("Backend response: " + response);
            }
        } catch (IOException e) {
            System.out.println("Error communicating with the backend during shutdown: " + e.getMessage());
        }
    }
}

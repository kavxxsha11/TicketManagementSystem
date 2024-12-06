package ui;

import config.TicketingSystemConfig;
import java.util.Scanner;

public class CLI {
    public static TicketingSystemConfig configure() {
        Scanner scanner = new Scanner(System.in);

        // Get total tickets from user
        System.out.println("Enter Total Tickets: ");
        int totalTickets = getValidInput(scanner);

        // Get ticket release rate from user
        System.out.println("Enter Ticket Release Rate: ");
        int ticketReleaseRate = getValidInput(scanner);

        // Get customer retrieval rate from user
        System.out.println("Enter Customer Retrieval Rate: ");
        int customerRetrievalRate = getValidInput(scanner);

        // Get maximum ticket capacity from user
        System.out.println("Enter Max Ticket Capacity: ");
        int maxTicketCapacity = getValidInput(scanner);

        System.out.println("Configuration Complete!");

        return new TicketingSystemConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity);
    }

    private static int getValidInput(Scanner scanner) {
        int input;
        while (true) {
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear invalid input
                continue;
            }

            input = scanner.nextInt();
            if (input < 0){
                System.out.println("Please enter a positive number.");
            } else {
                break; // Exit the loop if the input is valid and positive
            }
        }
        return input;
    }

    private static boolean isRunning = false;

    public static void startSystem() {
        if (!isRunning){
            isRunning = true;
            System.out.println("Starting System...");
        } else{
            System.out.println("System is already running.");
        }
    }

    public static void stopSystem() {
        if (isRunning){
            isRunning = false;
            System.out.println("Stopping System...");
        } else{
            System.out.println("System is already stopped.");
        }
    }
}

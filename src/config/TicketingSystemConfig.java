package config;

import java.util.Scanner;

public class TicketingSystemConfig {
    private int totalTickets;
    private int ticketReleaseRate;
    private int customerRetrievalRate;
    private int maxTicketCapacity;

    public void configure() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Total Tickets: ");
        totalTickets = getValidInput(scanner);

        System.out.println("Enter Ticket Release Rate: ");
        ticketReleaseRate = getValidInput(scanner);

        System.out.println("Enter Customer Retrieval Rate: ");
        customerRetrievalRate = getValidInput(scanner);

        System.out.println("Enter Max Ticket Capacity: ");
        maxTicketCapacity = getValidInput(scanner);

        System.out.println("Configuration Complete!");
    }

    private int getValidInput(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println("Invalid input. Please enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    public int getTotalTickets() {
        return totalTickets;
    }

    public int getTicketReleaseRate() {
        return ticketReleaseRate;
    }

    public int getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicketCapacity() {
        return maxTicketCapacity;
    }
}

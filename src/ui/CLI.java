package ui;

import config.TicketingSystemConfig;
import java.util.Scanner;

public class CLI {
    public static TicketingSystemConfig () {
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
}

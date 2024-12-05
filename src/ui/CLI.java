package ui;

import config.TicketingSystemConfig;
import java.util.Scanner;

public class CLI {
    public static TicketingSystemConfig configure() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Total Tickets: ");
        int totalTickets = getValidInput(scanner);

        System.out.println("Enter Ticket Release Rate: ");
        int ticketReleaseRate = getValidInput(scanner);

        System.out.println("Enter Customer Retrieval Rate: ");
        int customerRetrievalRate = getValidInput(scanner);

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
                scanner.next();
                continue;
            }

            input = scanner.nextInt();
            if (input < 0){
                System.out.println("Please enter a positive number.");
            } else {
                break;
            }
        }
        return input;
    }
}

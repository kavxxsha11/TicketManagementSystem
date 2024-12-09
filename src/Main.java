import config.TicketingSystemConfig;
import core.TicketPool;
import threads.Customer;
import threads.Vendor;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static boolean isRunning = false;
    private static final List<Thread> threads = new ArrayList<>();
    private static TicketPool ticketPool;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Configure the system
        TicketingSystemConfig config = configure(scanner);
        ticketPool = new TicketPool(config.getMaxTicketCapacity());

        try {
            while (true) {
                System.out.println("\nCommands:\n \n1. start \n2. stop \n3. exit");
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "start":
                        startSystem(config);
                        break;
                    case "stop":
                        stopSystem();
                        break;
                    case "exit":
                        stopSystem();
                        System.out.println("Exiting System...");
                        return;
                    default:
                        System.out.println("Invalid command. \nPlease type 'start', 'stop', or 'exit'.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    public static TicketingSystemConfig configure(Scanner scanner) {
        LOGGER.info("Starting Configuration...\n");
        System.out.println("Enter Total Tickets: ");
        int totalTickets = getValidInput(scanner);

        System.out.println("Enter Ticket Release Rate: ");
        int ticketReleaseRate = getValidInput(scanner);

        System.out.println("Enter Customer Retrieval Rate: ");
        int customerRetrievalRate = getValidInput(scanner);

        System.out.println("Enter Max Ticket Capacity: ");
        int maxTicketCapacity = getValidInput(scanner);

        System.out.println("Enter Number of Vendors: ");
        int numVendors = getValidInput(scanner);

        System.out.println("Enter Number of Customers: ");
        int numCustomers = getValidInput(scanner);

        System.out.println("Configuration Complete!");
        LOGGER.info("System Configuration Completed Successfully!");

        return new TicketingSystemConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numVendors, numCustomers);
    }

    private static int getValidInput(Scanner scanner) {
        while (true) {
            try {
                if (!scanner.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a valid number.");
                    scanner.next();
                    continue;
                }

                int input = scanner.nextInt();
                if (input < 0) {
                    System.out.println("Please enter a positive number.");
                    continue;
                }
                scanner.nextLine();
                return input;
            } catch (Exception e) {
                LOGGER.log(Level.WARNING, "Error during input validation", e);
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.nextLine();
            }
        }
    }

    public static void startSystem(TicketingSystemConfig config) {
        synchronized (Main.class) {
            if (!isRunning) {
                isRunning = true;
                System.out.println("Starting System...");
                LOGGER.info("Initializing System with configuration: " + config);

                for (int i = 0; i < config.getNumVendors(); i++) {
                    Thread vendorThread = new Thread(new Vendor(ticketPool, config.getTotalTickets(), config.getTicketReleaseRate()), "Vendor- " + (i + 1));
                    threads.add(vendorThread);
                    vendorThread.start();
                }

                for (int i = 0; i < config.getNumCustomers(); i++) {
                    Thread customerThread = new Thread(new Customer(ticketPool, config.getCustomerRetrievalRate(), config.getTotalTickets()), "Customer- " + (i + 1));
                    threads.add(customerThread);
                    customerThread.start();
                }
            } else {
                System.out.println("System is already running.");
                LOGGER.warning("Attempt to start system already running.");
            }
        }
    }

    public static void stopSystem() {
        synchronized (Main.class) {
            if (isRunning) {
                isRunning = false;
                System.out.println("Stopping System...");
                LOGGER.info("Initiating system shutdown...");

                for (Thread thread : threads) {
                    thread.interrupt();
                    try {
                        thread.join();
                    } catch (InterruptedException e) {
                        LOGGER.log(Level.WARNING, "Error during thread interruption", e);
                        System.out.println("Thread interrupted while stopping: " + e.getMessage());
                    }
                }
                threads.clear();
                System.out.println("System shutdown completed.");
                LOGGER.info("System shutdown completed.");
            } else {
                System.out.println("System is already stopped.");
                LOGGER.warning("Attempt to stop system already stopped.");
            }
        }
    }
}

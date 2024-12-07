package ui;

import config.TicketingSystemConfig;
import core.TicketPool;
import threads.Customer;
import threads.Vendor;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CLI {
    private static boolean isRunning = false;
    private static final List<Thread> threads = new ArrayList<>();
    public static TicketPool ticketPool;

    public static TicketingSystemConfig configure(Scanner scanner) {
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

        System.out.println("Enter Number of Vendors: ");
        int numVendors = getValidInput(scanner);

        System.out.println("Enter Number of Customers: ");
        int numCustomers = getValidInput(scanner);

        System.out.println("Configuration Complete!");

        return new TicketingSystemConfig(totalTickets, ticketReleaseRate, customerRetrievalRate, maxTicketCapacity, numVendors, numCustomers);
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
                scanner.nextLine();
                break; // Exit the loop if the input is valid and positive
            }
        }
        return input;
    }

    public static void setTicketPool(TicketPool pool) {
        ticketPool = pool;
    }

    public static void startSystem(TicketingSystemConfig config) {
        synchronized (CLI.class) {
            if (!isRunning){
                isRunning = true;
                System.out.println("Starting System...");

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
            } else{
                System.out.println("System is already running.");
            }
        }
    }

    public static void stopSystem() {
        synchronized (CLI.class) {
            if (isRunning){
                isRunning = false;
                System.out.println("Stopping System...");
                for (Thread thread : threads){
                    thread.interrupt();
                    try{
                        thread.join();
                    } catch (InterruptedException e) {
                        System.out.println("Thread interrupted while stopping: " + e.getMessage());
                    }
                }
                threads.clear();
                System.out.println("System stopped.");
            } else{
                System.out.println("System is already stopped.");
            }
        }
    }
}

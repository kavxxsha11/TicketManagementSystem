package com.backend;

import com.backend.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

@SpringBootApplication
public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        Config config = handleConfiguration();
        SpringApplication.run(Main.class, args);

        scheduleTicketLogging(config);

        System.out.println("System logs available at http://localhost:8080/log");
        System.out.println("Ticket information available at http://localhost:8080/tickets");
    }

    private static Config handleConfiguration() {
        Config config = Config.loadConfiguration();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            if (config == null) {
                config = new Config();
                System.out.println("No existing configuration found. Please enter new configuration:");

                System.out.print("Enter total tickets: ");
                config.setTotalTickets(scanner.nextInt());

                System.out.print("Enter ticket release rate: ");
                config.setTicketReleaseRate(scanner.nextInt());

                System.out.print("Enter customer retrieval rate: ");
                config.setCustomerRetrievalRate(scanner.nextInt());

                System.out.print("Enter max ticket capacity: ");
                config.setMaxTicketCapacity(scanner.nextInt());

                config.saveConfiguration();
                break; // Exit loop after saving new configuration
            } else {
                System.out.print("Config file found. Use existing configuration? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();

                if (response.equals("yes")) {
                    break; // Exit loop and use existing configuration
                } else if (response.equals("no")) {
                    config = null; // Clear current config to allow entering new values
                } else {
                    System.out.println("Invalid input. Please type 'yes' or 'no'.");
                }
            }
        }
        return config;
    }

    private static void scheduleTicketLogging(Config config) {
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                logger.info("Ticket pool status - Total: {}, Remaining: {}",
                        config.getTotalTickets(),
                        config.getTotalTickets() / 2); // Example logic
            }
        }, 0, 5000); // Logs every 5 seconds
    }
}

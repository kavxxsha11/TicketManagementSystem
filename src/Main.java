import config.TicketingSystemConfig;
import core.TicketPool;
import ui.CLI;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TicketingSystemConfig config = CLI.configure(scanner);
        TicketPool ticketPool = new TicketPool(config.getTotalTickets());
        CLI.setTicketPool(ticketPool);

        try {
            while (true) {
                System.out.println("\nCommands:\n \n1. start \n2. stop \n3. exit");
                String command = scanner.nextLine().trim().toLowerCase();

                switch (command) {
                    case "start":
                        CLI.startSystem(config);
                        break;
                    case "stop":
                        CLI.stopSystem();
                        break;
                    case "exit":
                        CLI.stopSystem();
                        System.out.println("Exiting System...");
                        return;
                    default:
                        System.out.println("Invalid command. Please type 'start', 'stop', or 'exit'.");
                }
            }
        } finally {
            scanner.close();
        }
    }
}
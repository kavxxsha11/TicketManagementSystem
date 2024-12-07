package config;

public class TicketingSystemConfig {
    private final int totalTickets;
    private final int ticketReleaseRate;
    private final int customerRetrievalRate;
    private final int maxTicketCapacity;
    private final int numVendors;
    private final int numCustomers;

    public TicketingSystemConfig(int totalTickets, int ticketReleaseRate, int customerRetrievalRate, int maxTicketCapacity, int numVendors, int numCustomers) {
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicketCapacity = maxTicketCapacity;
        this.numVendors = numVendors;
        this.numCustomers = numCustomers;
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

    public int getNumVendors() {
        return numVendors;
    }

    public int getNumCustomers() {
        return numCustomers;
    }
}

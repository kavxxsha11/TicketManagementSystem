package core;

public class Ticket {
    private int ticketId; // Unique identifier for the ticket
    private String title; // Title of the ticket
    private double price; // Price of the ticket

    // Constructor to initialize the Ticket object
    public Ticket(int ticketId, String title, double price) {
        this.ticketId = ticketId;
        this.title = title;
        this.price = price;
    }

    // Getters for ticketId, title, and price
    public int getTicketId() {
        return ticketId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    // Setters for ticketId, price, and title
    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "ticketId=" + ticketId +
                ", title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}

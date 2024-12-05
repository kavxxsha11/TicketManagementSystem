package core;

public class Ticket {
    private int ticketId;
    private String title;
    private double price;

    public Ticket(int ticketId, String title, double price) {
        this.ticketId = ticketId;
        this.title = title;
        this.price = price;
    }

    public int getTicketId() {
        return ticketId;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

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

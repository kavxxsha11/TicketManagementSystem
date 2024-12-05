package core;

public class Ticket {
    private int ticketId;
    private String title;
    private int price;

    public Ticket(int ticketId, String title, int price) {
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

    public int getPrice() {
        return price;
    }

    public void setTicketId(int ticketId) {
        this.ticketId = ticketId;
    }

    public void setPrice(int price) {
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

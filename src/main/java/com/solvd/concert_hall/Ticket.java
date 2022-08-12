package main.java.com.solvd.concert_hall;

public class Ticket extends BuyableItem {
    private Event event;

    public Ticket(Event event, double price, int amount) {
        super(event.getName(), price, amount);
        this.event = event;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Ticket))
            return false;
        Ticket t = (Ticket) o;
        if ((t.getEvent().equals(event) && t.getPrice() == this.getPrice()) && t.getAmount() == this.getAmount()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = result * 19 + this.getName().hashCode();
        result = (int) (result * 19 + Math.round(this.getPrice()));
        result = result * 19 + this.getEvent().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return event.toString() + " for $" + String.format("%.2f", this.getPrice()) + "  with " + this.getAmount() + "left over";
    }
}

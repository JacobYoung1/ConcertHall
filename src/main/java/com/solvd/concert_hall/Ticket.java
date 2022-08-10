package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.abstract_classes.Sellable;

public class Ticket extends Sellable {
    private Event event;

    public Ticket(Event event, double price, int amount) {
        super(price, event.getName(), amount);
        this.event = event;
    }

    public Event getEvent() {
        return event;
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

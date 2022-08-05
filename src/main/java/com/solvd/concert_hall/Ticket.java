package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.abstract_classes.Sellable;

public class Ticket extends Sellable {
    private Event event;

    public Ticket(Event event, double price, int amount) {
        this.event = event;
        super.setName(event.getName());
        super.setPrice(price);
        super.setAmount(amount);
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

        return event.hashCode() * 1000 + this.getAmount();
    }

    @Override
    public String toString() {

        return event.toString() + "  with " + this.getAmount() + "left over";
    }
}

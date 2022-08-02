package main.java.com.solvd.concert_hall;

public class Ticket extends Sellable {
    private Event event;

    public Ticket(Event event, double price, int amount) {
        this.event = event;
        super.name = event.getName();
        super.price = price;
        super.amount = amount;
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
        if ((t.getEvent().equals(event) && t.price == price) && t.amount == amount) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return event.hashCode() * 1000 + amount;
    }

    @Override
    public String toString() {

        return event.toString() + "  with " + amount + "left over";
    }
}

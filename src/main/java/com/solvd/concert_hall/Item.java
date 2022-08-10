package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.abstract_classes.Sellable;

public class Item extends Sellable {
    public Item(String name, double price, int amount) {
        super(price, name, amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item))
            return false;
        Item i = (Item) o;
        if (i.getName().equals(this.getName()) && (i.getPrice() == this.getPrice())) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = 7;
        result = result * 19 + this.getName().hashCode();
        result = (int) (result * 19 + Math.round(this.getPrice()));
        return result;
    }

    @Override
    public String toString() {
        return this.getName() + " for $" + String.format("%.2f", this.getPrice()) + "  with " + this.getAmount() + "left.";
    }
}

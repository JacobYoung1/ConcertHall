package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.abstract_classes.Sellable;

public class Item extends Sellable {
    public Item(String name, double price, int amount) {
        super.setName(name);
        super.setPrice(price);
        super.setAmount(amount);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item))
            return false;
        Item i = (Item) o;
        if ((i.getName().equals(this.getName()) && i.getPrice() == this.getPrice()) && i.getAmount() == this.getAmount()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return this.getName().hashCode() * 1000 + this.getAmount();
    }

    @Override
    public String toString() {

        return this.getName() + " for $" + this.getPrice() + "  with " + this.getAmount() + "left";
    }
}

package main.java.com.solvd.concert_hall;

public class Item extends Sellable{
    public Item(String name, double price, int amount) {
        super.name = name;
        super.price = price;
        super.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Item))
            return false;
        Item i = (Item) o;
        if ((i.name.equals(name) && i.price == price) && i.amount == amount) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return name.hashCode() * 1000 + amount;
    }

    @Override
    public String toString() {

        return name + " for $" + price + "  with " + amount + "left";
    }
}

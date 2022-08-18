package main.java.com.solvd.concert_hall.entities;

public class BuyableItem {
    private double price;
    private String name;
    private int amount;

    public BuyableItem(String name, double price, int amount) {
        this.price = price;
        this.name = name;
        this.amount = amount;
    }

    public void addStock(int newStock) {
        amount = amount + newStock;
    }

    public void buyStock() {
        amount--;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof BuyableItem))
            return false;
        BuyableItem i = (BuyableItem) o;
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

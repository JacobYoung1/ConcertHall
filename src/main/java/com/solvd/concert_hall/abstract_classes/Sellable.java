package main.java.com.solvd.concert_hall.abstract_classes;

public abstract class Sellable {
    private double price;
    private String name;
    private int amount;

    public void addStock(int newStock) {
        amount = amount + newStock;
    }

    public void deleteStock() {
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
}

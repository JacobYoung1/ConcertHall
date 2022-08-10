package main.java.com.solvd.concert_hall.abstract_classes;

public abstract class Sellable {
    private double price;
    private String name;
    private int amount;

    public Sellable (double price, String name, int amount) {
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
}

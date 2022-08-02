package main.java.com.solvd.concert_hall;

public abstract class Sellable {
    double price;
    String name;
    int amount;

    public void addStock(int newStock) {
        amount = amount + newStock;
    }

    public void deleteStock() {
        amount--;
    }
}

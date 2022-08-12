package main.java.com.solvd.concert_hall.interfaces;

import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;

public interface IShop<T> {

    T buyItem(int item, double money) throws OutOfChoiceBoundsException;

    void addStock(int item, int amount);

    void addItem(T item);

    void removeItem(T item);
}

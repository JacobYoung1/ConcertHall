package main.java.com.solvd.concert_hall.interfaces;

import java.util.ArrayList;

public interface ISell<T> {

    T buyItem(int item, double money);

    boolean deleteItem(T item);
}

package main.java.com.solvd.concert_hall;

import java.util.ArrayList;

public interface ISell<T> {

    T buyItem(int item, double money);

    boolean deleteItem(T item);
}

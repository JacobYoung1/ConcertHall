package main.java.com.solvd.concert_hall;

import java.util.ArrayList;

public interface ISubject<T> {
    ArrayList<IObserver> observers = new ArrayList<IObserver>();

    void addObserver(IObserver observer);
    void deleteObserver(IObserver observer);
    //used to update the observers in the array
    void notifyObservers(T message);
}

package main.java.com.solvd.concert_hall.interfaces;

import main.java.com.solvd.concert_hall.interfaces.IObserver;

import java.util.ArrayList;

public interface ISubject {
    ArrayList<IObserver> observers = new ArrayList<>();

    void addObserver(IObserver observer);
    void deleteObserver(IObserver observer);
}

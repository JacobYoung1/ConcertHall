package main.java.com.solvd.concert_hall.interfaces;

import main.java.com.solvd.concert_hall.entities.Event;

public interface IObserver {
    void createEventsUpdate(Event event);
    void deleteEventsUpdate(Event event);
}

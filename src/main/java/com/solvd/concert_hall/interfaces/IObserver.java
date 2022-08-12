package main.java.com.solvd.concert_hall.interfaces;

import main.java.com.solvd.concert_hall.Event;

public interface IObserver {

    /* used to update the observer through the subject's actions */
    void createTicketsUpdate(Event event);
    void deleteTicketsUpdate(Event event);
}

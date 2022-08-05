package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.interfaces.IObserver;
import main.java.com.solvd.concert_hall.interfaces.ISubject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Calender implements ISubject<Event> {
    public ArrayList<Event> events;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private LocalDateTime date;

    public Calender(int year, int month, int day, int hour, int minute) {
        this.events = new ArrayList<Event>();
        this.date = date.of(year, month, day, hour, minute);
    }
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void deleteObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(Event event) {
        for (IObserver o : observers) {
            o.update(event);
        }
    }

    //Used to check if an event is currently happening
    private boolean isHappening(Event event) {
        LocalDateTime tempDate = event.date.plusMinutes(event.lengthMinutes);
        if(date.isBefore(tempDate) && date.isAfter(event.date)) {
            return true;
        }
        return false;
    }

    //returns an event if one is going at the current time
    public Event getCurrentEvent() {
        for(Event e: events) {
            if(isHappening(e)) {
                return e;
            }
        }
        return null;
    }

    //adds an event while notifying the Ticketbooth to make tickets for the event
    public boolean addEvent(Event event) {
        LocalDateTime tempDate1 = event.date.plusMinutes(event.lengthMinutes);
        LocalDateTime tempDate2;
        for(Event e: events) {
            tempDate2 = e.date.plusMinutes(e.lengthMinutes);
            if(!(event.date.isAfter(tempDate2) || e.date.isAfter(tempDate1))){
                return false;
            }
        }
        notifyObservers(event);
        addCalenderEvent(event);
        return true;
    }

    //this passes time
    public final void passTime(int minutes) {
        date.plusMinutes(minutes);
        for(Event e: events) {
            if(e.date.plusMinutes(e.lengthMinutes).isBefore(date)) {
                notifyObservers(e);
                events.remove(e);
            }
        }
    }

    //used to sort the events array every time a new one is added
    private void addCalenderEvent(Event event){
        events.add(event);
        for(int i = events.size() - 1; i >= 1; i--) {
            if(events.get(i).date.isAfter(events.get(i - 1).date)) {
                return;
            }
            events.add(i, events.get(i - 1));
            events.add(i - 1, event);
        }
    }

    //prints the time
    public void printTime() {
        System.out.println(printTime(date));
    }

    //a static method for giving a string when given a localdatetime
    public static String printTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return date.format(formatter);
    }
}

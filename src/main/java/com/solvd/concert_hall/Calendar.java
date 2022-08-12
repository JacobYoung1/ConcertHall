package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.exceptions.NegativeNumberException;
import main.java.com.solvd.concert_hall.interfaces.IObserver;
import main.java.com.solvd.concert_hall.interfaces.ISubject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Calendar implements ISubject<Event> {
    public ArrayList<Event> events;
    private LocalDateTime date;

    /**
     * This is the Constructor for Calendar that takes a year, month, day, hour, and minute.
     *
     @param  year  the int year you are setting the calendar too
     *
     @param  month  the int month you are setting the calendar too
     *
     @param  day  the int day you are setting the calendar too
     *
     @param  hour  the int hour you are setting the calendar too
     *
     @param  minute  the int minute you are setting the calendar too
     */
    public Calendar(int year, int month, int day, int hour, int minute) {
        this.events = new ArrayList<Event>();
        this.date = date.of(year, month, day, hour, minute);
    }

    /**
     * A method that adds Observers to the observers ArrayList.
     *
     @param  observer  the IObserver being added to the observers ArrayList
     */
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * A method that deletes Observers from the observers ArrayList.
     *
     @param  observer  the IObserver being deleted from the observers ArrayList
     */
    @Override
    public void deleteObserver(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * A private method that takes an Event and checks if it is currently happening during the
     * date on the Calendar.
     *
     @param  event  the Event that will be checked on if it is happening
     *
     @return      the result if the event is happening or not
     *
     @see         boolean
     */
    private boolean isHappening(Event event) {
        LocalDateTime tempDate = event.getDate().plusMinutes(event.getLengthMinutes());
        if(date.isBefore(tempDate) && date.isAfter(event.getDate())) {
            return true;
        }
        return false;
    }

    /**
     * A method that gets the Event that is currently happening based on Calendar's date.
     * It returns null if there isn't any event happening
     *
     @return      the event currently happening
     *
     @see         Event
     */
    public Event getCurrentEvent() {
        for(Event e: events) {
            if(isHappening(e)) {
                return e;
            }
        }
        return null;
    }

    /**
     * A method that adds an Event to the events ArrayList if it isn't happening during a previously entered
     * event. It will return true if it is added and false if it isn't. It also updates all observers that an
     * event was added.
     *
     @param  event  the Event that may or may not be added
     *
     @return      the result of if the event was added or not
     *
     @see         boolean
     */
    public boolean addEvent(Event event) {
        LocalDateTime tempDate1 = event.getDate().plusMinutes(event.getLengthMinutes());
        LocalDateTime tempDate2;
        for(Event e: events) {
            tempDate2 = e.getDate().plusMinutes(e.getLengthMinutes());
            if(!(event.getDate().isAfter(tempDate2) || e.getDate().isAfter(tempDate1))){
                return false;
            }
        }
        for (IObserver o : observers) {
            o.createTicketsUpdate(event);
        }
        events.add(event);
        return true;
    }

    /**
     * A method to "pass time" in minutes for the Calendar's date. It throws a NegativeNumberException if given a
     * negative number of minutes. It also deletes any events that have passed in those minutes and updates
     * all of the observers.
     *
     @param  minutes  the int amount of minutes that will pass
     *
     */
    public final void passTime(int minutes) throws NegativeNumberException {
        if (minutes < 0) {
            throw new NegativeNumberException("You cannot reverse time.");
        }
        date.plusMinutes(minutes);
        for (Event e : events) {
            if (e.getDate().plusMinutes(e.getLengthMinutes()).isBefore(date)) {
                for (IObserver o : observers) {
                    o.deleteTicketsUpdate(e);
                }
                events.remove(e);
            }
        }
    }

    /**
     * A method for printing the Calendar's date.
     */
    public void printTime() {
        System.out.println(printTime(date));
    }

    /**
     * A static method that takes a LocalDateTime and returns a String version of it.
     *
     @param  date  the LocalDateTime you want a String version of
     *
     @return      the string representation of the date
     *
     @see         String
     */
    public static String printTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm.");
        return date.format(formatter);
    }
}

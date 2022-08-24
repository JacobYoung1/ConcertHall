package main.java.com.solvd.concert_hall.services;

import main.java.com.solvd.concert_hall.entities.Event;
import main.java.com.solvd.concert_hall.exceptions.NegativeNumberException;
import main.java.com.solvd.concert_hall.interfaces.IObserver;
import main.java.com.solvd.concert_hall.interfaces.ISubject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.stream.Stream;

public class Calendar implements ISubject {
    private ArrayList<Event> events;
    private LocalDateTime date;

    /**
     * This is the Constructor for Calendar that takes a LocalDateTime.
     *
     * @param  date The LocalDateTime that you are setting the Calendar's date to.
     */
    public Calendar(LocalDateTime date) {
        this.events = new ArrayList<>();
        this.date = date;
    }

    /**
     * This is the Constructor for Calendar that has no parameters and sets it to the current LocalDateTime.
     */
    public Calendar() {
        this.events = new ArrayList<>();
        this.date = LocalDateTime.now();
    }

    /**
     * A method that adds Observers to the observers ArrayList.
     *
     * @param  observer  The IObserver being added to the observers ArrayList.
     */
    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * A method that deletes Observers from the observers ArrayList.
     *
     * @param  observer  The IObserver being deleted from the observers ArrayList.
     */
    @Override
    public void deleteObserver(IObserver observer) {
        observers.remove(observer);
    }

    /**
     * A private method that takes an Event and checks if it is currently happening during the
     * date on the Calendar.
     *
     * @param  event  The Event that will be checked on if it is happening.
     * @return The boolean result on if the event is happening or not.
     */
    private boolean isHappening(Event event) {
        LocalDateTime tempDate = event.getDate().plusMinutes(event.getLengthMinutes());
        boolean isBetween = date.isBefore(tempDate) && date.isAfter(event.getDate());
        return ((isBetween || date.isEqual(tempDate)) || date.isEqual(event.getDate()));
    }

    /**
     * A method that gets the Event that is currently happening based on Calendar's date.
     * It returns null if there isn't any event happening
     *
     * @return The Event currently happening.
     */
    public Event getCurrentEvent() {
        return events.stream()
                .filter(e -> isHappening(e))
                .findFirst()
                .orElse(new Event("", LocalDateTime.now(), 0, 0));
    }

    /**
     * A method that adds an Event to the events ArrayList if it isn't happening during a previously entered
     * event. It will return true if it is added and false if it isn't. It also updates all observers that an
     * event was added.
     *
     * @param  event  The Event that may or may not be added.
     */
    public void addEvent(Event event) {
        LocalDateTime tempDate1 = event.getDate().plusMinutes(event.getLengthMinutes());
        events.stream().forEach(e -> {
            LocalDateTime tempDate2 = e.getDate().plusMinutes(e.getLengthMinutes());
            if(!(event.getDate().isAfter(tempDate2) || e.getDate().isAfter(tempDate1))){
                return;
            }
        });
        observers.stream().forEach(o -> {
            o.createEventsUpdate(event);
        });
        events.add(event);
    }

    /**
     * A method to "pass time" in minutes for the Calendar's date. It throws a NegativeNumberException if given a
     * negative number of minutes. It also deletes any events that have passed in those minutes and updates
     * all of the observers.
     *
     * @param  minutes  The int amount of minutes that will pass.
     * @throws NegativeNumberException  Throws if inputted time is negative.
     */
    public final void passTime(int minutes) throws NegativeNumberException {
        if (minutes < 0) {
            throw new NegativeNumberException("You cannot reverse time.");
        }
        date.plusMinutes(minutes);
        events.stream().forEach(e -> {
            LocalDateTime tempDate;
            tempDate = e.getDate();
            tempDate = tempDate.plusMinutes(e.getLengthMinutes());
            if (tempDate.isBefore(date)) {
                for (IObserver o : observers) {
                    o.deleteEventsUpdate(e);
                }
                events.remove(e);
            }
        });
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
     * @param  date  The LocalDateTime you want a String version of.
     * @return The String representation of the date.
     */
    public static String printTime(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm.");
        return date.format(formatter);
    }
}

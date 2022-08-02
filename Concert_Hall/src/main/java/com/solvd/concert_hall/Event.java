package main.java.com.solvd.concert_hall;

import java.time.LocalDateTime;

public class Event {
    private String name;
    public LocalDateTime date;
    public int lengthMinutes;
    public double price;

    public Event(String name, int year, int month, int day, int hour, int minute, int lengthMinutes, double price) {
        this.name = name;
        this.date = date.of(year, month, day, hour, minute);
        this.lengthMinutes = lengthMinutes;
        this.price = price;
    }
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Event))
            return false;
        Event e = (Event) o;
        if ((e.getName().equals(name) && e.date.equals(date)) && e.lengthMinutes == lengthMinutes) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {

        return name.hashCode() * 1000 + date.hashCode() * 100 + lengthMinutes * 10;
    }

    @Override
    public String toString() {

        return name + " at " + Calender.printTime(date) + " for " + lengthMinutes + " for $" + price;
    }
}

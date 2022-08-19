package main.java.com.solvd.concert_hall.entities;

import main.java.com.solvd.concert_hall.services.Calendar;

import java.time.LocalDateTime;

public class Event {
    private String name;
    private LocalDateTime date;
    private int lengthMinutes;
    private double price;

    public Event(String name, LocalDateTime date, int lengthMinutes, double price) {
        this.name = name;
        this.date = date;
        this.lengthMinutes = lengthMinutes;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(int lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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
        int result = 7;
        result = result * 19 + this.getName().hashCode();
        result = result * 19 + this.date.hashCode();
        result = result * 19 + lengthMinutes;
        result = (int) (result * 19 + Math.round(this.price));
        return result;
    }

    @Override
    public String toString() {
        return name + " at " + Calendar.printTime(date) + " for " + lengthMinutes + " for $" + String.format("%.2f", this.price);
    }
}

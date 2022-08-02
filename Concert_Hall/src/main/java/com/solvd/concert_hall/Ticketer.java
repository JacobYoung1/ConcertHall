package main.java.com.solvd.concert_hall;

import java.util.Scanner;

public class Ticketer extends Employee implements IVerify<Ticket>, IDisplay{
    private Calender calender;

    public Ticketer(Calender calender, String name) {
        this.calender = calender;
        super.name = name;
    }
    @Override
    public boolean verify(Ticket ticket) {
        if(calender.getCurrentEvent().equals(ticket.getEvent())) {
            System.out.println("Enjoy the show.");
            return true;
        }
        return false;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void greeting() {
        System.out.println("Hello, my name is " + name + ". Ticket please.");
    }

    @Override
    public void display(Scanner scan, User user) {
        greeting();
        if(user.tickets.size() == 0) {
            System.out.println("Come back later when you have tickets");
        }
        for (Ticket t: user.tickets) {
            if(verify(t)) {
                for(Item i: user.items) {
                    System.out.println("You consumed a(n) " + i.name + ".");
                    user.items.remove(i);
                }
                System.out.println(t.name + " was awesome.");
                user.tickets.remove(t);
            }
        }
    }
}

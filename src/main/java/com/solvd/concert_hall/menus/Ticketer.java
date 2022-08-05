package main.java.com.solvd.concert_hall.menus;

import main.java.com.solvd.concert_hall.Calender;
import main.java.com.solvd.concert_hall.Item;
import main.java.com.solvd.concert_hall.Ticket;
import main.java.com.solvd.concert_hall.User;
import main.java.com.solvd.concert_hall.abstract_classes.Employee;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.IVerify;

import java.util.Scanner;

public class Ticketer extends Employee implements IVerify<Ticket>, IDisplay {
    private Calender calender;

    public Ticketer(Calender calender, String name) {
        this.calender = calender;
        super.setName(name);
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
    public void greeting() {
        System.out.println("Hello, my name is " + this.getName() + ". Ticket please.");
    }

    @Override
    public User display(Scanner scan, User user) {
        greeting();
        if(user.tickets.size() == 0) {
            System.out.println("Come back later when you have tickets");
        }
        for (Ticket t: user.tickets) {
            if(verify(t)) {
                for(Item i: user.items) {
                    System.out.println("You consumed a(n) " + i.getName() + ".");
                    user.items.remove(i);
                }
                System.out.println(t.getName() + " was awesome.");
                user.tickets.remove(t);
            }
        }
        return user;
    }
}

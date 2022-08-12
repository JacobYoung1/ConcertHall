package main.java.com.solvd.concert_hall.menus;

import main.java.com.solvd.concert_hall.Calendar;
import main.java.com.solvd.concert_hall.BuyableItem;
import main.java.com.solvd.concert_hall.Ticket;
import main.java.com.solvd.concert_hall.UserInventory;
import main.java.com.solvd.concert_hall.abstract_classes.Employee;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.IVerify;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class Ticketer extends Employee implements IVerify<Ticket>, IDisplay {
    private static final Logger logger = LogManager.getLogger(Ticketer.class);
    private Calendar calendar;

    public Ticketer(Calendar calendar, String name) {
        this.calendar = calendar;
        super.setName(name);
    }
    @Override
    public boolean verify(Ticket ticket) {
        if(calendar.getCurrentEvent().equals(ticket.getEvent())) {
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
    public UserInventory display(Scanner scan, UserInventory userInventory) {
        logger.info("entered display for ticketer");
        greeting();
        if(userInventory.getTickets().size() == 0) {
            System.out.println("Come back later when you have tickets");
        }
        for (Ticket t: userInventory.getTickets()) {
            if(verify(t)) {
                for(BuyableItem i: userInventory.getBuyableItems()) {
                    System.out.println("You consumed a(n) " + i.getName() + ".");
                    userInventory.deleteBuyableItem(i);
                }
                System.out.println(t.getName() + " was awesome.");
                userInventory.deleteTicket(t);
            }
        }
        return userInventory;
    }
}

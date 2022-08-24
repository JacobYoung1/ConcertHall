package main.java.com.solvd.concert_hall.services;

import main.java.com.solvd.concert_hall.entities.BuyableItem;
import main.java.com.solvd.concert_hall.entities.Ticket;
import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.entities.Employee;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.IVerify;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class Ticketer extends Employee implements IVerify<Ticket>, IDisplay {
    private static final Logger logger = LogManager.getLogger(Ticketer.class);
    private Calendar calendar = null;

    /**
     * This is the Constructor for Ticketer that takes a Calendar and a String name.
     *
     * @param  calendar The Calendar that the Ticketer will use for checking the validity of Tickets.
     * @param  name The String name of the Ticketer.
     */
    public Ticketer(Calendar calendar, String name) {
        super.setName(name);
        readCalender(calendar);
    }

    /**
     * This method is to update the Calendar that the Ticketer is reading.
     *
     * @param  calendar  The Calendar that the Ticketer will use for checking the validity of Tickets.
     */
    public void readCalender(Calendar calendar) {
        this.calendar = calendar;
    }

    /**
     * This method is to check the validity of a Ticket to the currently happening Event.
     *
     * @param  ticket  The Ticket that the Ticketer will verify if it is to the current Event.
     * @return The boolean value on if it is a valid ticket or not.
     */
    @Override
    public boolean verify(Ticket ticket) {
        System.out.println(calendar.getCurrentEvent());
        if(calendar.getCurrentEvent().equals(ticket.getEvent())) {
            return true;
        }
        return false;
    }

    /**
     * This method is to print out a greeting.
     */
    @Override
    public void greeting() {
        System.out.println("Hello, my name is " + this.getName() + ". Ticket please.");
    }

    /**
     * This method displays a screen for the Ticketer that takes a Scanner and the UserInventory which it
     * returns after display is done.
     *
     * @param  scan  The Scanner used by the method for user input.
     * @param  userInventory  The UserInventory of the user.
     * @return The UserInventory that has been modified during the method.
     */
    @Override
    public UserInventory display(Scanner scan, UserInventory userInventory) {
        logger.info("entered display for ticketer");
        greeting();
        if(userInventory.getTickets().size() == 0) {
            System.out.println("Come back later when you have tickets");
        }
        Ticket ticket = userInventory.getTickets().stream()
                .filter(this::verify).findFirst().orElse(null);
        if(ticket != null) {
            System.out.println("Enjoy the show.");
            System.out.println(ticket.getName() + " was awesome.");
            userInventory.deleteTicket(ticket);
        }
        userInventory.getBuyableItems()
                .forEach(i -> System.out.println("You consumed a(n) " + i.getName() + "."));
        userInventory.setBuyableItems(new ArrayList<>());
        return userInventory;
    }
}

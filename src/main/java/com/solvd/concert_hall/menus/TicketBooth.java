package main.java.com.solvd.concert_hall.menus;

import main.java.com.solvd.concert_hall.Calendar;
import main.java.com.solvd.concert_hall.Event;
import main.java.com.solvd.concert_hall.Ticket;
import main.java.com.solvd.concert_hall.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.IObserver;
import main.java.com.solvd.concert_hall.interfaces.IShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public final class TicketBooth implements IShop<Ticket>, IDisplay, IObserver {
    private static final Logger logger = LogManager.getLogger(TicketBooth.class);
    final int concertHallSeats = 425;
    private Calendar calendar;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private ArrayList<Ticket> inventory;

    public TicketBooth(Calendar calendar) {
        this.calendar = calendar;
        calendar.addObserver(this);
        inventory = new ArrayList<Ticket>();
    }

    @Override
    public UserInventory display(Scanner scan, UserInventory userInventory) throws OutOfChoiceBoundsException {
        logger.info("entered display for ticket booth");
        int choice1;
        double choice2;
        while(true) {
            System.out.println("Welcome to the Ticket Booth. Please type in the number of the item you want.");
            System.out.println("You may also type a negative number to leave the stand.");
            for (int i = 1; i <= inventory.size(); i++) {
                System.out.printf("%d.%s     $%2.2f\n", i, inventory.get(i - 1).getName(), inventory.get(i - 1).getPrice());
                System.out.println(inventory.get(i - 1).getEvent().getDate().format(formatter));
            }
            choice1 = scan.nextInt();
            if (choice1 <= -1) {
                System.out.println("Have a nice day.");
                return userInventory;
            } else {
                System.out.println("Please enter how much you will pay.");
                choice2 = scan.nextDouble();
                Ticket ticket = buyItem(choice1 - 1, choice2);
                if (ticket != null) {
                    userInventory.addTicket(ticket);
                    System.out.println("Here is your ticket.");
                    System.out.printf("Your change is $%2.2f. Have a nice day!\n", choice2 - inventory.get(choice1).getPrice());
                } else {
                    System.out.println("I'm sorry, but you cannot buy this ticket.");
                }
            }
        }
    }

    @Override
    public Ticket buyItem(int item, double money) throws OutOfChoiceBoundsException {
        try {
            inventory.get(item);
        } catch (Exception e) {
            logger.error("out of bounds number " + item + " from array length of " + inventory.size());
            throw new OutOfChoiceBoundsException("That item number does not exist.");
        }
        if((money < inventory.get(item).getPrice()) || (inventory.get(item).getAmount() == 0)) {
            return null;
        }
        inventory.get(item).buyStock();
        return inventory.get(item);
    }

    @Override
    public void addStock(int item, int amount) {
        inventory.get(item).addStock(amount);
    }

    @Override
    public void addItem(Ticket item) {
        inventory.add(item);
        for(int i = inventory.size() - 1; i >= 1; i--) {
            if(inventory.get(i).getEvent().getDate().isAfter(inventory.get(i - 1).getEvent().getDate())) {
                return;
            }
            Collections.swap(inventory, i, i-1);
        }
    }

    @Override
    public void removeItem(Ticket item) {
        inventory.remove(item);
    }

    @Override
    public void createTicketsUpdate(Event event) {
        Ticket ticket = new Ticket(event, event.getPrice(), concertHallSeats);
        addItem(ticket);
    }

    @Override
    public void deleteTicketsUpdate(Event event) {
        for (Ticket t : inventory) {
            if (t.getEvent().equals(event)) {
                removeItem(t);
                return;
            }
        }
    }
}

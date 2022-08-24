package main.java.com.solvd.concert_hall.services;

import main.java.com.solvd.concert_hall.entities.Event;
import main.java.com.solvd.concert_hall.entities.Ticket;
import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.IObserver;
import main.java.com.solvd.concert_hall.interfaces.IShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.format.DateTimeFormatter;
import java.util.*;

public final class TicketBooth implements IShop<Ticket>, IDisplay, IObserver {
    private static final Logger logger = LogManager.getLogger(TicketBooth.class);
    final int concertHallSeats = 425;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private ArrayList<Ticket> inventory;

    /**
     * This is the Constructor for Ticketer that takes a Calendar and a String name.
     *
     * @param  calendar  The Calendar that the TicketBooth will observe for updates on events.
     */
    public TicketBooth(Calendar calendar) {
        calendar.addObserver(this);
        inventory = new ArrayList<>();
    }

    /**
     * This method displays a screen for the TicketBooth that takes a Scanner and the UserInventory which it
     * returns after display is done.
     *
     * @param  scan  The Scanner used by the method for user input.
     * @param  userInventory  The UserInventory of the user.
     * @return The UserInventory that has been modified during the method.
     * @throws OutOfChoiceBoundsException Throws if users input goes out of range of the options.
     */
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

    /**
     * A method for buying a Ticket from the TicketBooth inventory. It takes the index of the Ticket
     * being bought and the amount of money being given to buy it. It will return a Ticket if it is bought.
     *
     * @param  item  The int index of the Ticket being bought.
     * @param  money  The double amount of money being given to buy the Ticket.
     * @return The Ticket that is bought or null if it wasn't.
     * @throws OutOfChoiceBoundsException Throws if users input goes out of range of the options.
     */
    @Override
    public Ticket buyItem(int item, double money) throws OutOfChoiceBoundsException {
        if(inventory.size() - 1 < item) {
            logger.error("out of bounds number " + item + " from array length of " + inventory.size());
            throw new OutOfChoiceBoundsException("That item number does not exist.");
        }
        if((money < inventory.get(item).getPrice()) || (inventory.get(item).getAmount() == 0)) {
            return null;
        }
        inventory.get(item).buyStock();
        return inventory.get(item);
    }

    /**
     * A method that adds an int amount of stock to the int index item in the ConcessionStand inventory.
     *
     * @param  item  The int index of the item in the TicketBooth inventory.
     * @param  amount  The int amount of stock being added to the item.
     */
    @Override
    public void addStock(int item, int amount) {
        inventory.get(item).addStock(amount);
    }

    @Override
    public void addItem(Ticket item) {
        inventory.add(item);
        List<Ticket> list = inventory.stream()
                .sorted(Comparator.comparing(i -> i.getEvent().getDate()))
                .toList();
        inventory = new ArrayList<>(list);
    }

    @Override
    public void removeItem(Ticket item) {
        inventory.remove(item);
    }

    /**
     * A method that updates TicketBooth to update its inventory with new Tickets for the new Event.
     *
     * @param  event  The Event that was recently created.
     */
    @Override
    public void createEventsUpdate(Event event) {
        Ticket ticket = new Ticket(event, event.getPrice(), concertHallSeats);
        addItem(ticket);
    }

    /**
     * A method that updates TicketBooth to update its inventory by getting rid of the Tickets associated with the
     * deleted event.
     *
     * @param  event  The Event that was recently deleted.
     */
    @Override
    public void deleteEventsUpdate(Event event) {
        inventory.forEach(t -> {
            if (t.getEvent().equals(event)) {
                removeItem(t);
                return;
            }
        });
    }
}

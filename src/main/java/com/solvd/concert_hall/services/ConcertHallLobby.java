package main.java.com.solvd.concert_hall.services;

import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.NegativeNumberException;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

public class ConcertHallLobby implements IDisplay {
    private static final Logger logger = LogManager.getLogger(ConcertHallLobby.class);
    private Calendar calendar;
    private ConcessionStand concessionStand;
    private TicketBooth ticketBooth;
    private Ticketer ticketer;

    /**
     * This is the Constructor for ConcertHallLobby. It takes a Calendar, ConcessionStand, TicketBooth, and Ticketer;
     *
     * @param calendar The Calendar that will be used in the ConcertHall.
     * @param concessionStand The ConcessionStand that will be used in the ConcertHall.
     * @param ticketBooth The TicketBooth that will be used in the ConcertHall.
     * @param ticketer The Ticketer that will be used in the ConcertHall.
     */
    public ConcertHallLobby(Calendar calendar, ConcessionStand concessionStand, TicketBooth ticketBooth, Ticketer ticketer) {
        this.calendar = calendar;
        this.concessionStand = concessionStand;
        this.ticketBooth = ticketBooth;
        this.ticketer = ticketer;
    }

    /**
     * This method displays a screen for the ConcertHallLobby that takes a Scanner and the UserInventory which it
     * returns after display is done.
     *
     * @param  scan  The Scanner used by the method for user input.
     * @param  userInventory  The UserInventory of the user.
     * @return The UserInventory that has been modified during the method.
     */
    @Override
    public UserInventory display(Scanner scan, UserInventory userInventory) {
        int choice = 0;
        while(choice >= 0) {
            System.out.println("You find yourself in the lobby of THE concert hall of concert halls");
            System.out.print("It is currently ");
            calendar.printTime();
            System.out.println("Where would you like to go?");
            System.out.println("You can go to the ticket booth (type 1), to the ticketer (type 2), or the concession stand (type 3).");
            System.out.println("You can also wait for time to pass (type 4). You can also leave by typing a negative number.");
            choice = scan.nextInt();
            logger.trace("Made choice of " + choice);
            switch (choice) {
                case 1:
                    try {
                        userInventory = ticketBooth.display(scan, userInventory);
                    } catch (OutOfChoiceBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    choice = 0;
                    break;
                case 2:
                    userInventory = ticketer.display(scan, userInventory);
                    choice = 0;
                    break;
                case 3:
                    try {
                        userInventory = concessionStand.display(scan, userInventory);
                    } catch (OutOfChoiceBoundsException e) {
                        System.out.println(e.getMessage());
                    }
                    choice = 0;
                    break;
                case 4:
                    System.out.println("Please enter the amount of minutes you want to pass.");
                    choice = scan.nextInt();
                    try {
                        calendar.passTime(choice);
                        System.out.println(choice + " minutes have passed.");
                    } catch (NegativeNumberException e) {
                        System.out.println(e.getMessage());
                    }
                    choice = 0;
                    break;
                default:
                    break;
            }
            ticketer.readCalender(calendar);
        }
        System.out.println("Your time at the concert hall has ended.");
        return userInventory;
    }
}

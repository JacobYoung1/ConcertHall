package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.entities.BuyableItem;
import main.java.com.solvd.concert_hall.entities.Event;
import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.NegativeNumberException;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;

import java.util.Scanner;

import main.java.com.solvd.concert_hall.services.Calendar;
import main.java.com.solvd.concert_hall.services.ConcessionStand;
import main.java.com.solvd.concert_hall.services.TicketBooth;
import main.java.com.solvd.concert_hall.services.Ticketer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is a simulation of a ConcertHall with the ability to visit a TicketBooth, ConcessionStand, and the ConcertHall
 * itself when visiting the Ticketer.
 *
 * @author  Jacob Young
 */
public class MainActivity {
    private static final Logger logger = LogManager.getLogger(MainActivity.class);
    public static void main(String[] args) {

        /* Basic setup */
        Calendar calendar = new Calendar(2022,8,2,5,45);
        UserInventory userInventory = new UserInventory();
        Scanner scan = new Scanner(System.in);
        TicketBooth ticketBooth = new TicketBooth(calendar);
        ConcessionStand concessionStand = new ConcessionStand();
        Ticketer ticketer = new Ticketer(calendar, "Joshua Miles");

        /* Adding details */
        Event event1 = new Event("John's Recital", 2022, 9, 14, 12, 30, 100, 5.00);
        Event event2 = new Event("Jacob's Recital", 2022, 9, 12, 11, 30, 100, 5.00);
        Event event3 = new Event("Jack's Recital", 2022, 9, 11, 11, 30, 100, 5.00);
        Event event4 = new Event("Jill's Recital", 2022, 9, 10, 11, 30, 100, 5.00);
        BuyableItem buyableItem1 = new BuyableItem("Pepsi", 2.10, 20);
        BuyableItem buyableItem2 = new BuyableItem("Sprite", 1.10, 20);
        BuyableItem buyableItem3 = new BuyableItem("Hershey Bar", 1.90, 20);
        calendar.addEvent(event1);
        calendar.addEvent(event2);
        calendar.addEvent(event3);
        calendar.addEvent(event4);
        concessionStand.addItem(buyableItem1);
        concessionStand.addItem(buyableItem2);
        concessionStand.addItem(buyableItem3);
        ticketer.readCalender(calendar);

        /* This is where the user interface occurs. */
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
                        throw new RuntimeException(e);
                    }
                    break;
                case 2:
                    userInventory = ticketer.display(scan, userInventory);
                    break;
                case 3:
                    try {
                        userInventory = concessionStand.display(scan, userInventory);
                    } catch (OutOfChoiceBoundsException e) {
                        throw new RuntimeException(e);
                    }
                    break;
                case 4:
                    System.out.println("Please enter the amount of minutes you want to pass.");
                    choice = scan.nextInt();
                    try {
                        calendar.passTime(choice);
                    } catch (NegativeNumberException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(choice + " minutes have passed.");
                    break;
                default:
                    break;
            }
            ticketer.readCalender(calendar);
        }
        System.out.println("Your time at the concert hall has ended.");
    }
}

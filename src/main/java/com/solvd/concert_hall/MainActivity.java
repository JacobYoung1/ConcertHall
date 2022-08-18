package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.entities.BuyableItem;
import main.java.com.solvd.concert_hall.entities.Event;
import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.NegativeNumberException;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;

import java.util.Scanner;

import main.java.com.solvd.concert_hall.services.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This is a simulation of a ConcertHall with the ability to visit a TicketBooth, ConcessionStand, and the ConcertHall
 * itself when visiting the Ticketer.
 *
 * @author  Jacob Young
 */
public class MainActivity {
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

        ConcertHallLobby concertHallLobby = new ConcertHallLobby(calendar, concessionStand, ticketBooth, ticketer);

        /* This is where the user interface occurs. */
        concertHallLobby.display(scan, userInventory);
    }
}

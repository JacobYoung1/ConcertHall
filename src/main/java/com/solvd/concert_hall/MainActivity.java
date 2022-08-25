package main.java.com.solvd.concert_hall;

import main.java.com.solvd.concert_hall.entities.UserInventory;
import java.util.Scanner;
import main.java.com.solvd.concert_hall.services.*;

/**
 * This is a simulation of a ConcertHall with the ability to visit a TicketBooth, ConcessionStand, and the ConcertHall
 * itself when visiting the Ticketer.
 *
 * @author  Jacob Young
 */
public class MainActivity {
    public static void main(String[] args) {

        /* Basic setup */
        Calendar calendar = FileSetUp.calendarSetUp1("src/main/resources/currentDate.txt");
        TicketBooth ticketBooth = new TicketBooth(calendar);
        calendar = FileSetUp.calendarSetUp2("src/main/resources/events.txt", calendar);
        ConcessionStand concessionStand = FileSetUp.concessionStandSetUp("src/main/resources/buyableItems.txt");
        Ticketer ticketer = new Ticketer(calendar, "Joshua Miles");
        ticketer.readCalender(calendar);
        UserInventory userInventory = new UserInventory();
        ConcertHallLobby concertHallLobby = new ConcertHallLobby(calendar, concessionStand, ticketBooth, ticketer);

        /* This is where the user interface occurs. */
        Scanner scan = new Scanner(System.in);
        concertHallLobby.display(scan, userInventory);
    }
}

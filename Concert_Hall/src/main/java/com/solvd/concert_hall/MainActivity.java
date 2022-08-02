package main.java.com.solvd.concert_hall;

import java.util.Scanner;

public class MainActivity {
    public static void main(String[] args) {
        //Basic setup
        Calender calender = new Calender(2022,8,2,5,45);
        User user = new User();
        Scanner scan = new Scanner(System.in);
        TicketBooth ticketBooth = new TicketBooth(calender);
        ConcessionStand concessionStand = new ConcessionStand();
        Ticketer ticketer = new Ticketer(calender,"Joshua Miles");

        //Adding details
        Event event1 = new Event("John's Recital", 2022, 9, 10, 12, 30, 100, 5.00);
        Event event2 = new Event("Jacob's Recital", 2022, 9, 11, 11, 30, 100, 5.00);
        Item item1 = new Item("Pepsi", 2.10, 20);
        Item item2 = new Item("Sprite", 1.10, 20);
        Item item3 = new Item("Hershey Bar", 1.90, 20);
        calender.addEvent(event1);
        calender.addEvent(event2);
        concessionStand.addItem(item1);
        concessionStand.addItem(item2);
        concessionStand.addItem(item3);

        //This is where the user interface occurs
        int choice = 0;
        while(choice >= 0) {
            System.out.println("You find yourself in the lobby of THE concert hall of concert halls");
            System.out.println("Where would you like to go?");
            System.out.println("You can go to the ticket booth (type 1), to the ticketer (type 2), or the concession stand (type 3).");
            System.out.println("You can also wait for time to pass (type 4). You can also leave by typing a negative number.");
            choice = scan.nextInt();
            if (choice == 1) {
                ticketBooth.display(scan, user);
            }
            if (choice == 2) {
                ticketer.display(scan, user);
            }
            if (choice == 3) {
                concessionStand.display(scan, user);
            }
            if(choice == 4) {
                System.out.println("Please enter the amount of minutes you want to pass.");
                choice = scan.nextInt();
                calender.passTime(choice);
                System.out.println(choice + " minutes have passed.");
            }
        }
        System.out.println("Your time at the concert hall has ended.");
    }
}

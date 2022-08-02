package main.java.com.solvd.concert_hall;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public final class TicketBooth implements ISell<Ticket>, IDisplay, IObserver<Event>{
    final int concertHallSeats = 425;
    private Calender calender;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private ArrayList<Ticket> inventory;

    public TicketBooth(Calender calender) {
        this.calender = calender;
        calender.addObserver(this);
        inventory = new ArrayList<Ticket>();
    }

    @Override
    public void display(Scanner scan, User user) {
        int choice1;
        double choice2;
        while(true) {
            System.out.println("Welcome to the Ticket Booth. Please type in the number of the item you want.");
            System.out.println("You may also type a negative number to leave the stand.");
            for (int i = 1; i <= inventory.size(); i++) {
                System.out.printf("%d.%s     $%2.2f\n", i, inventory.get(i - 1).name, inventory.get(i - 1).price);
                System.out.println(inventory.get(i - 1).getEvent().date.format(formatter));
            }
            choice1 = scan.nextInt();
            if (choice1 <= -1) {
                System.out.println("Have a nice day.");
                return;
            } else {
                System.out.println("Please enter how much you will pay.");
                choice2 = scan.nextDouble();
                Ticket ticket = buyItem(choice1, choice2);
                if(ticket != null) {
                    user.tickets.add(ticket);
                }
            }
        }
    }

    @Override
    public Ticket buyItem(int item, double money) {
        for(Ticket t: inventory) {
            if(calender.events.get(item).equals(t)) {
                if(money < t.price) {
                    System.out.println("I'm sorry, but that is not enough money for this ticket.");
                    return null;
                } else if (t.amount == 0) {
                    System.out.println("I'm sorry, but we are out of this item.");
                    return null;
                }
                if(deleteItem(t)) {
                    System.out.println("Here is your ticket. Have a nice day!");
                    System.out.printf("Your change is $%2.2f. Have a nice day!\n",money - inventory.get(item).price);
                    return t;
                }
            }
        }
        return null;
    }

    @Override
    public boolean deleteItem(Ticket item) {
        if(inventory.contains(item)) {
            inventory.get(inventory.indexOf(item)).deleteStock();
            return true;
        }
        return false;
    }

    @Override
    public void update(Event event) {
        for (Ticket t : inventory) {
            if (t.getEvent().equals(event)) {
                inventory.remove(t);
                return;
            }
        }
        Ticket ticket = new Ticket(event, event.price, concertHallSeats);
        inventory.add(ticket);
    }
}

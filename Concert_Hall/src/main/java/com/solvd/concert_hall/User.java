package main.java.com.solvd.concert_hall;

import java.util.ArrayList;

public class User {
    static ArrayList<Item> items;
    static ArrayList<Ticket> tickets;

    public User() {
        //creates the user's inventory
        items = new ArrayList<Item>();
        tickets = new ArrayList<Ticket>();
    }
}

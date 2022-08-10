package main.java.com.solvd.concert_hall;

import java.util.ArrayList;

public class UserInventory {
    public ArrayList<Item> items;
    public ArrayList<Ticket> tickets;

    public UserInventory() {
        //creates the user's inventory
        items = new ArrayList<Item>();
        tickets = new ArrayList<Ticket>();
    }
}

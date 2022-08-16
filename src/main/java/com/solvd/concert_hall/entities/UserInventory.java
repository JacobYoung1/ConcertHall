package main.java.com.solvd.concert_hall.entities;

import java.util.ArrayList;

public class UserInventory {
    private ArrayList<BuyableItem> buyableItems;
    private ArrayList<Ticket> tickets;

    public UserInventory() {
        buyableItems = new ArrayList<BuyableItem>();
        tickets = new ArrayList<Ticket>();
    }

    public ArrayList<BuyableItem> getBuyableItems() {
        return buyableItems;
    }

    public ArrayList<Ticket> getTickets() {
        return tickets;
    }

    public void setBuyableItems(ArrayList<BuyableItem> buyableItems) {
        this.buyableItems = buyableItems;
    }

    public void setTickets(ArrayList<Ticket> tickets) {
        this.tickets = tickets;
    }

    public void addBuyableItem(BuyableItem buyableItem) {
        buyableItems.add(buyableItem);
    }

    public void addTicket(Ticket ticket) {
        tickets.add(ticket);
    }

    public void deleteBuyableItem(BuyableItem buyableItem) {
        buyableItems.remove(buyableItem);
    }

    public void deleteTicket(Ticket ticket) {
        tickets.remove(ticket);
    }
}

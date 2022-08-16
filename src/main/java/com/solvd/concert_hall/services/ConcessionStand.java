package main.java.com.solvd.concert_hall.services;

import main.java.com.solvd.concert_hall.entities.BuyableItem;
import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.IShop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.Scanner;

public class ConcessionStand implements IShop<BuyableItem>, IDisplay {
    private static final Logger logger = LogManager.getLogger(ConcessionStand.class);
    private ArrayList<BuyableItem> inventory;

    /**
     * This is the Constructor for ConcessionStand.
     */
    public ConcessionStand() {
        inventory = new ArrayList<BuyableItem>();
    }

    /**
     * This method displays a screen for the ConcessionStand that takes a Scanner and the UserInventory which it
     * returns after display is done.
     *
     @param  scan  the Scanner used by the method for user input
     *
     @param  userInventory  the inventory of the user
     *
     @return    the UserInventory that has been modified during the method
     */
    @Override
    public UserInventory display(Scanner scan, UserInventory userInventory) throws OutOfChoiceBoundsException {
        logger.info("entered display for concession stand");
        int choice1;
        double choice2;
        while(true) {
            System.out.println("Welcome to the Concession Stand. Please type in the number of the item you want.");
            System.out.println("You may also type a negative number to leave the stand.");
            for (int i = 1; i <= inventory.size(); i++) {
                System.out.printf("%d.%s     $%2.2f\n", i, inventory.get(i - 1).getName(), inventory.get(i - 1).getPrice());
            }
            choice1 = scan.nextInt();
            if (choice1 <= -1) {
                System.out.println("Have a nice day.");
                return userInventory;
            } else {
                System.out.println("Please enter how much you will pay.");
                choice2 = scan.nextDouble();
                BuyableItem buyableItem = buyItem(choice1 - 1, choice2);
                if (buyableItem != null) {
                    userInventory.addBuyableItem(buyableItem);
                    System.out.println("Here is your" + inventory.get(choice1).getName() + ".");
                    System.out.printf("Your change is $%2.2f. Have a nice day!\n", choice2 - inventory.get(choice1).getPrice());
                } else {
                    System.out.println("I'm sorry, but you cannot buy this item.");
                }
            }
        }
    }

    /**
     * A method for buying a BuyableItem from the ConcessionStand inventory. It takes the index of the BuyableItem
     * being bought and the amount of money being given to buy it. It will return a BuyableItem if it is bought.
     *
     @param  item  the int index of the BuyableItem being bought
     *
     @param  money  the double amount of money being given to buy the BuyableItem
     *
     @return      the BuyableItem that is bought or null if it wasn't
     */
    @Override
    public BuyableItem buyItem(int item, double money) throws OutOfChoiceBoundsException {
        try {
            inventory.get(item);
        } catch (Exception e) {
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
     @param  item  the int index of the item in the ConcessionStand inventory
     *
     @param  amount  the int amount of stock being added to the item
     */
    @Override
    public void addStock(int item, int amount) {
        inventory.get(item).addStock(amount);
    }

    /**
     * A method that adds a BuyableItem to the ConcessionStand inventory or, if it is already there, adds it to the
     * stock.
     *
     @param  buyableItem  the BuyableItem that is being added to the ConcessionStand inventory
     */
    @Override
    public void addItem(BuyableItem buyableItem) {
        if(inventory.contains(buyableItem)) {
            addStock(inventory.indexOf(buyableItem), buyableItem.getAmount());
            return;
        }
        inventory.add(buyableItem);
        return;
    }

    /**
     * A method that removes a BuyableItem from the ConcessionStand inventory.
     *
     @param  buyableItem  the BuyableItem that is being removed from the ConcessionStand inventory
     */
    @Override
    public void removeItem(BuyableItem buyableItem) {
        inventory.remove(buyableItem);
    }
}

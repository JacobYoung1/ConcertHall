package main.java.com.solvd.concert_hall.menus;

import main.java.com.solvd.concert_hall.BuyableItem;
import main.java.com.solvd.concert_hall.UserInventory;
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

    public ConcessionStand() {
        inventory = new ArrayList<BuyableItem>();
    }

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

    @Override
    public void addStock(int item, int amount) {
        inventory.get(item).addStock(amount);
    }

    /* used to add items to the stand */
    @Override
    public void addItem(BuyableItem buyableItem) {
        if(inventory.contains(buyableItem)) {
            addStock(inventory.indexOf(buyableItem), buyableItem.getAmount());
            return;
        }
        inventory.add(buyableItem);
        return;
    }

    @Override
    public void removeItem(BuyableItem item) {
        inventory.remove(item);
    }
}

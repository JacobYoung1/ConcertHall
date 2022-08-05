package main.java.com.solvd.concert_hall.menus;

import main.java.com.solvd.concert_hall.Item;
import main.java.com.solvd.concert_hall.User;
import main.java.com.solvd.concert_hall.interfaces.IDisplay;
import main.java.com.solvd.concert_hall.interfaces.ISell;

import java.util.ArrayList;
import java.util.Scanner;

public class ConcessionStand implements ISell<Item>, IDisplay {
    private ArrayList<Item> inventory;

    public ConcessionStand() {
        inventory = new ArrayList<Item>();
    }
    @Override
    public User display(Scanner scan, User user) {
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
                return user;
            } else {
                System.out.println("Please enter how much you will pay.");
                choice2 = scan.nextDouble();
                Item item = buyItem(choice1, choice2);
                if(item != null) {
                    user.items.add(item);
                }
            }
        }
    }

    @Override
    public Item buyItem(int item, double money) {
        if(money < inventory.get(item).getPrice()) {
            System.out.println("I'm sorry, but that is not enough money for this.");
            return null;
        } else if (inventory.get(item).getAmount() == 0) {
            System.out.println("I'm sorry, but we are out of this item.");
            return null;
        }
        if(deleteItem(inventory.get(item))) {
            System.out.println("Here is your" + inventory.get(item).getName() + ".");
            System.out.printf("Your change is $%2.2f. Have a nice day!\n",money - inventory.get(item).getPrice());
            return inventory.get(item);
        }
        return null;
    }

    @Override
    public boolean deleteItem(Item item) {
        if(inventory.contains(item)) {
            inventory.get(inventory.indexOf(item)).deleteStock();
            return true;
        }
        return false;
    }

    //used to add items to the stand
    public boolean addItem(Item item) {
        if(inventory.contains(item)) {
            return false;
        }
        inventory.add(item);
        return true;
    }
}

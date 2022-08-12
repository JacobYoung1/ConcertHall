package main.java.com.solvd.concert_hall.interfaces;

import main.java.com.solvd.concert_hall.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;

import java.util.Scanner;

public interface IDisplay {

    /* used to display the classes interface */
    UserInventory display(Scanner scan, UserInventory userInventory) throws OutOfChoiceBoundsException;
}

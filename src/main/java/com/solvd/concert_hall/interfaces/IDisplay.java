package main.java.com.solvd.concert_hall.interfaces;

import main.java.com.solvd.concert_hall.entities.UserInventory;
import main.java.com.solvd.concert_hall.exceptions.OutOfChoiceBoundsException;

import java.util.Scanner;

public interface IDisplay {
    UserInventory display(Scanner scan, UserInventory userInventory) throws OutOfChoiceBoundsException;
}

package main.java.com.solvd.concert_hall.services;

import main.java.com.solvd.concert_hall.entities.BuyableItem;
import main.java.com.solvd.concert_hall.entities.Event;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class FileSetUp {
    private static final Logger logger = LogManager.getLogger(FileSetUp.class);

    /**
     * This method uses currentDate.txt to create a Calendar for the ConcertHall.
     *
     * @return The Calendar made by the method.
     */
    public static Calendar calendarSetUp1(String path) {
        File file = new File(path);
        if(!file.exists()) {
            logger.error("currentDate.txt not found");
            return new Calendar();
        }
        try {
            List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return new Calendar(LocalDateTime.parse(lines.get(0), formatter));
        } catch (IOException e) {
            logger.error("could not read file: " + e.getMessage());
            return new Calendar();
        } catch (IndexOutOfBoundsException e) {
            logger.error("date is not there: " + e.getMessage());
            return new Calendar();
        }
    }

    /**
     * This method uses events.txt and a Calendar to add events for the ConcertHall when returning the Calendar.
     *
     * @param calendar The Calendar which the Events will be added to.
     * @return The Calendar with events added.
     */
    public static Calendar calendarSetUp2(String path, Calendar calendar) {
        File file = new File(path);
        if(!file.exists()) {
            logger.error("events.txt not found");
            return calendar;
        }
        try {
            List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            lines.forEach(line -> {
                String[] sections = line.split("::");
                String name = sections[0];
                LocalDateTime date = LocalDateTime.parse(sections[1], formatter);
                int length = Integer.parseInt(sections[2]);
                double price = Double.parseDouble(sections[3]);
                Event event = new Event(name, date, length, price);
                calendar.addEvent(event);
            });
            return calendar;
        } catch (IOException e) {
            logger.error("could not read file: " + e.getMessage());
            return calendar;
        }
    }

    /**
     * This method uses buyableItems.txt to create a ConcessionStand and to add buyableItems to it.
     *
     * @return The ConcessionStand made by the method.
     */
    public static ConcessionStand concessionStandSetUp(String path) {
        File file = new File(path);
        if(!file.exists()) {
            logger.error("buyableItems.txt not found");
            return new ConcessionStand();
        }
        try {
            List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
            ConcessionStand concessionStand = new ConcessionStand();
            lines.forEach(line -> {
                String[] sections = line.split("::");
                String name = sections[0];
                double price = Double.parseDouble(sections[1]);
                int amount = Integer.parseInt(sections[2]);
                BuyableItem buyableItem = new BuyableItem(name, price, amount);
                concessionStand.addItem(buyableItem);
            });
            return concessionStand;
        } catch (IOException e) {
            logger.error("could not read file: " + e.getMessage());
            return new ConcessionStand();
        }
    }
}

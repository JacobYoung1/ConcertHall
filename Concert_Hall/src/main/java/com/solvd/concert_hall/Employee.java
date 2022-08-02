package main.java.com.solvd.concert_hall;

public abstract class Employee {
    String name;

    public abstract String getName();
    public abstract void setName(String name);
    public void greeting() {
        System.out.println("Hello");
    }
}

package main.java.com.solvd.concert_hall.abstract_classes;

public abstract class Employee {
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void greeting() {
        System.out.println("Hello");
    }
}

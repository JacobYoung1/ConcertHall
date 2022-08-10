package main.java.com.solvd.concert_hall.interfaces;

public interface IObserver<T> {
    //used to update the observer through the subject's actions
    void createUpdate(T message);
    void deleteUpdate(T message);
}

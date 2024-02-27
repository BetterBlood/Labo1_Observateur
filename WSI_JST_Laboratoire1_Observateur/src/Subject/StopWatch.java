package Subject;

import Observer.IObservable;

import javax.swing.*;
import java.util.LinkedList;

public class StopWatch implements ISubjectable {
    // region Field
    private final int SECOND_IN_HOUR = 3600;
    private final int SECOND_IN_MINUTE = 60;

    private LinkedList<IObservable> observers; // TODO WSI : Contraire à l'uml, devrait être abstrait ?
    private final Timer TIMER; // TODO WSI : Quel Timer utiliser ?
    private int second;

    private static int nextId;
    private final int ID = nextId++;
    // endregion

    // region Ctor
    public StopWatch() {
        TIMER = new Timer(1, e -> incrementAndNotify());
    }
    // endregion

    // region Method
    @Override
    public void attach(IObservable observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void detach(IObservable observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (IObservable o : observers) {
            o.update();
        }
    }

    public void initTimer() {
        second = 0;
        notifyObservers();
    }

    public void startTimer() {
        TIMER.start();
    }

    public void stopTimer() {
        TIMER.stop();
    }

    @Override
    public String toString() {
        return "Chrono #" + ID;
    }

    public int getHour() {
        return second / SECOND_IN_HOUR;
    }

    public int getMinute() {
        return second / SECOND_IN_MINUTE;
    }

    public int getSecond() {
        return second;
    }

    private void incrementAndNotify() {
        ++second;
        notifyObservers();
    }
    // endregion
}

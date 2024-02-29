package Subject;
import javax.swing.*;

public class StopWatch extends Subject {
    // region Field
    private final int SECOND_IN_HOUR = 3600;
    private final int SECOND_IN_MINUTE = 60;

    private final Timer TIMER;
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

    public void reset()
    {
        second = 0;
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

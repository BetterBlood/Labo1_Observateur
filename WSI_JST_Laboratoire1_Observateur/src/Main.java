import javafx.concurrent.Task;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        Timer t = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("test");
            }
        };
        t.schedule(task,1);

    }
}
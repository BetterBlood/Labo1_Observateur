package Observer;

import Subject.StopWatch;

import javax.swing.*;
import java.awt.*;

public abstract class Clock extends JPanel implements IObservable {
    // region Field
    protected int hour;
    protected int minute;
    protected int second;

    protected final StopWatch SUBJECT;

    protected final int HEIGHT = 200;
    protected final int WIDTH = 200;

    protected final JLabel label;
    // endregion

    // region Ctor
    public Clock(StopWatch subject) {
        this.SUBJECT = subject;
        this.SUBJECT.attach(this);
        label = new JLabel();
        add(label);
        update();
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    // endregion

    // region Method
    @Override
    public void update() {
        this.hour = SUBJECT.getHour();
        this.minute = SUBJECT.getMinute();
        this.second = SUBJECT.getSecond();
    }
    // endregion
}

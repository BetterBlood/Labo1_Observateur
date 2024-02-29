package Observer;

import Subject.StopWatch;

import javax.swing.*;
import java.awt.*;

public abstract class Clock extends JPanel implements IObservable {
    // region Field
    protected int hour;
    protected int minute;
    protected int second;

    private final StopWatch SUBJECT;

    private final int HEIGHT = 200;
    private final int WIDTH = 200;

    // endregion

    // region Ctor
    public Clock(StopWatch subject) {
        this.SUBJECT = subject;
        this.SUBJECT.attach(this);
        setLayout(new FlowLayout());
        setSize(200,200);
        update();
    }
    // endregion

    // region Method
    @Override
    public void update() {
        this.hour = SUBJECT.getHour();
        this.minute = SUBJECT.getMinute();
        this.second = SUBJECT.getSecond();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(Toolkit.getDefaultToolkit().getImage("/images/cadran_chiffres_arabes.jpg").getScaledInstance(200, 200, Image.SCALE_DEFAULT), 0, 0, this);
    }
    // endregion
}

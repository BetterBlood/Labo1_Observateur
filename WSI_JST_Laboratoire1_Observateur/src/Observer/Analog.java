package Observer;

import Subject.StopWatch;

import java.awt.*;

public abstract class Analog extends Clock {
    // region Field
    private Color hourHandColor;
    private Color minuteHandColor;
    private Color secondHandColor;

    private final Image image;
    // endregion

    // region Ctor
    public Analog(StopWatch subject, String clockImagePath,Color hourHandColor, Color minuteHandColor, Color secondHandColor) {
        super(subject);

        this.hourHandColor = hourHandColor;
        this.minuteHandColor = minuteHandColor;
        this.secondHandColor = secondHandColor;

        image = Toolkit.getDefaultToolkit().getImage(clockImagePath). getScaledInstance(WIDTH, HEIGHT, Image.SCALE_AREA_AVERAGING);
    }
    // endregion

    // region Methode
    @Override
    public void update(){
        super.update();
        //repaint();
    }
    // endregion

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,0,0,this);
        g.drawString(SUBJECT.toString(), 80, 120);
        // TODO Regarder comment dessiner les aiguilles
    }
}

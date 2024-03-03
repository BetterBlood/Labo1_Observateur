package Observer;

import Subject.StopWatch;
import java.awt.*;
import java.awt.geom.Line2D;
import java.lang.Math;

public abstract class Analog extends Clock {
    // region Field
    private final Color hourHandColor;
    private final Color minuteHandColor;
    private final Color secondHandColor;

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
        repaint(); // needed for needles
    }
    // endregion

    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,0,0,this);
        g.drawString(SUBJECT.toString(), 80, 120);

        Graphics2D g2 = (Graphics2D)g;
        drawNeedle(g2, secondHandColor, Math.toRadians(second * 6), 1, 0.9);
        drawNeedle(g2, minuteHandColor, Math.toRadians(minute * 6), 2, 0.7);
        drawNeedle(g2, hourHandColor, Math.toRadians(hour * 30 + (minute/2.)), 4, 0.4);
    }

    private void drawNeedle(Graphics2D g2, Color color, double rad, int width, double length) {
        Point centre = new Point(getWidth() / 2, getHeight() / 2);
        Point needleEnd = new Point(
                (int) (centre.x + Math.sin(rad) * (centre.x * length)),
                (int) (centre.y - Math.cos(rad) * (centre.y * length)));

        g2.setColor(color);
        g2.setStroke(new BasicStroke(width));
        
        g2.draw(new Line2D.Double(centre.x, centre.y, needleEnd.x, needleEnd.y));
    }
}
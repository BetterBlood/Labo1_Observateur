package Observer;

import Subject.StopWatch;
import javax.swing.*;
import java.awt.*;

public class Digital extends Clock {
    // region Field
    private final JLabel label;
    // endregion

    // region Ctor
    public Digital(StopWatch subject) {
        super(subject);
        setBackground(Color.GRAY);
        label = new JLabel();
        this.setLayout(new GridBagLayout());
        add(label);
    }
    // endregion

    // region Methode
    @Override
    public void update(){
        super.update();
        label.setText(SUBJECT.toString() + " : " + hour + "h " + minute + "m " + second + "s");
    }
    // endregion
}

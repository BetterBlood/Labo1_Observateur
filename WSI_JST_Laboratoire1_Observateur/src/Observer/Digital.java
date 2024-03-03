package Observer;

import Subject.StopWatch;
import java.awt.*;

public class Digital extends Clock {
    // region Ctor
    public Digital(StopWatch subject) {
        super(subject);

        setBackground(Color.GRAY);
        this.setLayout(new GridBagLayout());
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
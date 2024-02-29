package Observer;

import Subject.StopWatch;

import java.awt.*;

public class Arab extends Analog {
    // region Ctor
    public Arab(StopWatch subject) {
        super(subject, "images/cadran_chiffres_arabes.jpg", Color.BLACK, Color.BLUE, Color.RED);
    }
    // endregion
}

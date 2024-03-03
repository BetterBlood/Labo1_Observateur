package Observer;

import Subject.StopWatch;
import java.awt.*;

public class Roman extends Analog {
    // region Ctor
    public Roman(StopWatch subject) {
        super(subject, "images/cadran_chiffres_romains.jpg", Color.BLACK, Color.GRAY, Color.YELLOW);
    }
    // endregion
}

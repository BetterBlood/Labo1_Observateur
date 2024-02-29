package Observer;

import Subject.StopWatch;

public abstract class Analog extends Clock {
    // region Ctor
    public Analog(StopWatch subject) {
        super(subject);

    }
    // endregion
}

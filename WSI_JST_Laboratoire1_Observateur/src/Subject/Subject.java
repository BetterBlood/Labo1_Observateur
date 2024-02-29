package Subject;

import Observer.IObservable;
import java.util.LinkedList;

public abstract class Subject {
    // region Field
    private LinkedList<IObservable> observers;
    // endregion

    protected Subject()
    {
        observers = new LinkedList<>();
    }

    // region Method
    public void attach(IObservable observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(IObservable observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (IObservable o : observers) {
            o.update();
        }
    }
    // endregion
}

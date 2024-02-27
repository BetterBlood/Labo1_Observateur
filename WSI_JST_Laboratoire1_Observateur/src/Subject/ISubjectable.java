package Subject;

import Observer.IObservable;

public interface ISubjectable { // TODO WSI : Voir si peut être une interface ?
    // region Method
    void attach(IObservable observer);
    void detach(IObservable observer);
    void notifyObservers();
    // endregion
}

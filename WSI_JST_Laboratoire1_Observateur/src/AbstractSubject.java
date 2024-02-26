import java.util.LinkedList;

abstract public class AbstractSubject {

    LinkedList<AbstractObserver> observers;

    protected AbstractSubject()
    {
        observers = new LinkedList<>();
    }

    public void specNotify()
    {
        for (AbstractObserver observer : observers) {
            observer.update();
        }
    }

    public void subscrieb(AbstractObserver observer)
    {
        observers.add(observer);
    }

    public void unsubscrieb(AbstractObserver observer)
    {
        observers.remove(observer); // TODO : voir si on doit ajouter un hash truc et/ou un equals dans observer je me rappel plus
    }
}

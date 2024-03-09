package Subject;

import Observer.IObservable;
import java.util.LinkedList;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe abstraite représente un sujet observé par des observateurs.
 *                Elle maintient une liste d'observateurs et fournit des méthodes pour
 *                attacher, détacher et notifier les observateurs en cas de changement
 *                d'état du sujet.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public abstract class Subject {
    // region Field
    private final LinkedList<IObservable> observers;
    // endregion

    // region Ctor
    /**
     * Nom         : Subject
     * Description : Constructeur par défaut de la classe Subject. Initialise la liste
     *               des observateurs.
     */
    protected Subject() {
        observers = new LinkedList<>();
    }
    // endregion

    // region Method
    /**
     * Nom             : attach
     * Description     : Attache un observateur à ce sujet. Si l'observateur n'est pas déjà
     *                   attaché, l'ajoute à la liste des observateurs.
     * @param observer : L'observateur à attacher.
     */
    public void attach(IObservable observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    /**
     * Nom             : detach
     * Description     : Détache un observateur de ce sujet. Si l'observateur est attaché,
     *                   le retire de la liste des observateurs.
     * @param observer : L'observateur à détacher.
     */
    public void detach(IObservable observer) {
        observers.remove(observer);
    }

    /**
     * Nom         : notifyObservers
     * Description : Notifie tous les observateurs attachés à ce sujet. Appelle la méthode
     *               update de chaque observateur pour les informer d'un changement d'état du sujet.
     */
    public void notifyObservers() {
        for (IObservable o : observers) {
            o.update();
        }
    }
    // endregion
}

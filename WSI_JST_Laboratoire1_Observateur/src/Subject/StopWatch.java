package Subject;
import javax.swing.*;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe représente un chronomètre. Elle étend la classe Subject
 *                et utilise un objet Timer pour mesurer le temps. Cette classe permet
 *                de démarrer, arrêter, réinitialiser le chronomètre et d'obtenir
 *                les heures, les minutes et les secondes écoulées depuis le démarrage
 *                du chronomètre.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public class StopWatch extends Subject {
    // region Field
    private final int SECOND_IN_HOUR = 3600;
    private final int SECOND_IN_MINUTE = 60;
    private final int SECOND_IN_MILLISECOND = 1000;

    private final Timer TIMER;
    private int second;

    private static int nextId = 1;
    private final int ID = nextId++;
    // endregion

    // region Ctor
    /**
     * Nom         : StopWatch
     * Description : Constructeur de la classe StopWatch, initialise un chronomètre avec un objet Timer.
     */
    public StopWatch() {
        TIMER = new Timer(SECOND_IN_MILLISECOND, e -> incrementAndNotify());
    }
    // endregion

    // region Method
    /**
     * Nom         : swapRunning
     * Description : Bascule l'état de fonctionnement du chronomètre entre démarré et arrêté.
     */
    public void swapRunning() {
        if (TIMER.isRunning()) stopTimer();
        else startTimer();
    }

    /**
     * Nom         : startTimer
     * Description : Démarre le chronomètre.
     */
    public void startTimer() {
        TIMER.start();
    }

    /**
     * Nom         : stopTimer
     * Description : Arrête le chronomètre.
     */
    public void stopTimer() {
        TIMER.stop();
    }

    /**
     * Nom         : resetTimer
     * Description : Réinitialise le chronomètre en remettant le temps écoulé à zéro.
     */
    public void resetTimer()
    {
        second = 0;
        notifyObservers();
    }

    /**
     * Nom         : toString
     * Description : Renvoie une représentation sous forme de chaîne de caractères du chronomètre.
     * @return     : Une chaîne de caractères représentant le chronomètre.
     */
    @Override
    public String toString() {
        return "Chrono #" + ID;
    }

    /**
     * Nom         : getHour
     * Description : Renvoie le nombre d'heures écoulées depuis le démarrage du chronomètre.
     * @return     : Le nombre d'heures écoulées.
     */
    public int getHour() {
        return second / SECOND_IN_HOUR;
    }

    /**
     * Nom         : getMinute
     * Description : Renvoie le nombre de minutes écoulées depuis le démarrage du chronomètre.
     * @return     : Le nombre de minutes écoulées.
     */
    public int getMinute() {
        return (second / SECOND_IN_MINUTE) % SECOND_IN_MINUTE;
    }

    /**
     * Nom         : getSecond
     * Description : Renvoie le nombre de secondes écoulées depuis le démarrage du chronomètre.
     * @return     : Le nombre de secondes écoulées.
     */
    public int getSecond() {
        return second % SECOND_IN_MINUTE;
    }

    /**
     * Nom         : incrementAndNotify
     * Description : Incrémente le temps écoulé d'une seconde et notifie les observateurs du changement.
     */
    private void incrementAndNotify() {
        ++second;
        notifyObservers();
    }
    // endregion
}
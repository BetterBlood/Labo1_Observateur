package Observer;

import Subject.StopWatch;
import javax.swing.*;
import java.awt.*;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe abstraite représente une horloge dans une interface graphique.
 *                Elle étend JPanel et implémente l'interface IObservable pour observer un
 *                chronomètre donné. Les classes concrètes héritant de cette classe doivent
 *                fournir une implémentation pour afficher l'horloge dans leur contexte spécifique.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public abstract class Clock extends JPanel implements IObservable {
    // region Field
    protected int hour;
    protected int minute;
    protected int second;

    protected final StopWatch SUBJECT;

    protected final int HEIGHT = 200;
    protected final int WIDTH = 200;

    protected final JLabel label;
    // endregion

    // region Ctor
    /**
     * Nom            : Clock
     * Description    : Constructeur de la classe Clock, initialise une horloge avec le chronomètre
     *                  spécifié comme sujet.
     * @param subject : Le chronomètre associé à cette horloge.
     */
    public Clock(StopWatch subject) {
        this.SUBJECT = subject;
        this.SUBJECT.attach(this);
        this.label = new JLabel();

        add(label);
        update();

        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }
    // endregion

    // region Method
    /**
     * Nom         : update
     * Description : Met à jour l'heure, les minutes et les secondes de l'horloge en fonction
     *               de l'état actuel du chronomètre associé.
     */
    @Override
    public void update() {
        this.hour = SUBJECT.getHour();
        this.minute = SUBJECT.getMinute();
        this.second = SUBJECT.getSecond();
    }
    // endregion
}
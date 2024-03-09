package Observer;

import Subject.StopWatch;
import java.awt.*;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe représente une horloge numérique dans une interface graphique.
 *                Elle hérite de la classe Clock et est spécifiquement conçue pour afficher
 *                l'heure, les minutes et les secondes dans un format numérique.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public class Digital extends Clock {
    // region Ctor
    /**
     * Nom            : Digital
     * Description    : Constructeur de la classe Digital, initialise une horloge numérique
     *                  avec le chronomètre spécifié comme sujet.
     * @param subject : Le chronomètre associé à cette horloge numérique.
     */
    public Digital(StopWatch subject) {
        super(subject);

        setBackground(Color.GRAY);
        this.setLayout(new GridBagLayout());
    }
    // endregion

    // region Methode
    /**
     * Nom         : update
     * Description : Met à jour l'affichage de l'heure, des minutes et des secondes dans
     *               l'horloge numérique en fonction de l'état actuel du chronomètre associé.
     */
    @Override
    public void update(){
        super.update();
        label.setText(SUBJECT.toString() + " : " + hour + "h " + minute + "m " + second + "s");
    }
    // endregion
}
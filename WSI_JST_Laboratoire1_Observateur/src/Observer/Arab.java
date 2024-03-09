package Observer;

import Subject.StopWatch;
import java.awt.*;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe représente une horloge analogique avec des chiffres arabes
 *                comme chiffres pour les heures. Elle hérite de la classe Analog et
 *                est spécifiquement conçue pour fonctionner avec un chronomètre
 *                donné en tant qu'observateur.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public class Arab extends Analog {
    // region Ctor
    /**
     * Nom            : Arab
     * Description    : Constructeur de la classe Arab, initialise une horloge analogique avec des
     *                  chiffres arabes pour les heures.
     * @param subject : Le chronomètre associé à cette horloge analogique.
     */
    public Arab(StopWatch subject) {
        super(subject, "images/cadran_chiffres_arabes.jpg", Color.BLACK, Color.BLUE, Color.RED);
    }
    // endregion
}
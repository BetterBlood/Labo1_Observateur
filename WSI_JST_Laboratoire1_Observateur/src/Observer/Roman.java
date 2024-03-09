package Observer;

import Subject.StopWatch;
import java.awt.*;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe représente une horloge analogique avec des chiffres romains comme
 *                marqueurs pour les heures. Elle hérite de la classe Analog et est spécifiquement
 *                conçue pour fonctionner avec un chronomètre donné en tant qu'observateur.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public class Roman extends Analog {
    // region Ctor
    /**
     * Nom            : Roman
     * Description    : Constructeur de la classe Roman, initialise une horloge analogique
     *                  avec des chiffres romains comme marqueurs pour les heures.
     * @param subject : Le chronomètre associé à cette horloge analogique.
     */
    public Roman(StopWatch subject) {
        super(subject, "images/cadran_chiffres_romains.jpg", Color.BLACK, Color.GRAY, Color.YELLOW);
    }
    // endregion
}

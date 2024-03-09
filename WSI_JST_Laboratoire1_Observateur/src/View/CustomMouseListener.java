package View;

import Subject.StopWatch;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe représente un listener de souris personnalisé qui déclenche
 *                l'action de démarrer ou d'arrêter un chronomètre lorsqu'un clic de souris
 *                est détecté. Elle implémente l'interface MouseListener pour écouter les
 *                événements de la souris.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public class CustomMouseListener implements MouseListener {
    // region Field
    private final StopWatch chrono;
    // endregion

    // region Ctor
    /**
     * Nom           : CustomMouseListener
     * Description   : Constructeur de la classe CustomMouseListener.
     *                 Initialise le chronomètre associé à cet écouteur de souris.
     * @param chrono : Le chronomètre à contrôler.
     */
    public CustomMouseListener(StopWatch chrono) {
        this.chrono = chrono;
    }
    // endregion

    // region Method
    /**
     * Nom         : mouseClicked
     * Description : Action déclenchée lorsque la souris "est cliquée".
     *               Inverse l'état du chronomètre (démarrage ou arrêt).
     * @param e    : L'événement de clic de souris.
     */
    @Override
    public void mouseClicked(MouseEvent e) {
        chrono.swapRunning();
    }

    @Override
    public void mousePressed(MouseEvent e) { // nothing to do
    }

    @Override
    public void mouseReleased(MouseEvent e) { // nothing to do
    }

    @Override
    public void mouseEntered(MouseEvent e) { // nothing to do
    }

    @Override
    public void mouseExited(MouseEvent e) { // nothing to do
    }
    // endregion
}

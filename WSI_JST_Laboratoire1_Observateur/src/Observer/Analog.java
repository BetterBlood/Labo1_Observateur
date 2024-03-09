package Observer;

import Subject.StopWatch;
import java.awt.*;
import java.awt.geom.Line2D;
import java.lang.Math;

/**
 * -----------------------------------------------------------------------------------
 * @Authors      : Slimani Walid & Steiner Jeremiah
 * @Description  : Cette classe abstraite représente une horloge analogique dans une
 *                interface graphique. Elle étend la classe Clock et implémente un
 *                observateur pour un chronomètre donné (à travers la classe Clock).
 *                Cette horloge affiche les valeurs du chronomètre sous forme analogique,
 *                avec des aiguilles pour les heures, les minutes et les secondes, ainsi
 *                qu'une représentation visuelle du chronomètre.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public abstract class Analog extends Clock {
    // region Field
    private final Color hourHandColor;
    private final Color minuteHandColor;
    private final Color secondHandColor;

    private final Image image;
    // endregion

    // region Ctor
    /**
     * Nom                    : Analog
     * Description            : Constructeur de la classe Analog, initialise une horloge analogique
     *                          avec les paramètres spécifiés.
     * @param subject         : Le chronomètre associé à cette horloge analogique.
     * @param clockImagePath  : Le chemin d'accès de l'image représentant l'horloge.
     * @param hourHandColor   : La couleur de l'aiguille des heures.
     * @param minuteHandColor : La couleur de l'aiguille des minutes.
     * @param secondHandColor : La couleur de l'aiguille des secondes.
     */
    public Analog(StopWatch subject, String clockImagePath,Color hourHandColor, Color minuteHandColor, Color secondHandColor) {
        super(subject);

        this.hourHandColor = hourHandColor;
        this.minuteHandColor = minuteHandColor;
        this.secondHandColor = secondHandColor;

        image = Toolkit.getDefaultToolkit().getImage(clockImagePath). getScaledInstance(WIDTH, HEIGHT, Image.SCALE_AREA_AVERAGING);
    }
    // endregion

    // region Methode
    /**
     * Nom         : update
     * Description : Met à jour l'horloge analogique en appelant la méthode update de
     *               la classe mère et en redessinant l'horloge avec les nouvelles valeurs.
     */
    @Override
    public void update(){
        super.update();
        repaint(); // needed for needles
    }

    /**
     * Nom         : paintComponent
     * Description : Redessine le composant graphique en affichant l'image de
     *               l'horloge et en dessinant les aiguilles correspondant aux
     *               heures, minutes et secondes.
     * @param g    : Le contexte graphique dans lequel dessiner.
     */
    @Override
    protected void paintComponent(Graphics g) {
        g.drawImage(image,0,0,this);
        g.drawString(SUBJECT.toString(), 80, 120);

        Graphics2D g2 = (Graphics2D)g;
        drawNeedle(g2, secondHandColor, Math.toRadians(second * 6), 1, 0.9);
        drawNeedle(g2, minuteHandColor, Math.toRadians(minute * 6), 2, 0.7);
        drawNeedle(g2, hourHandColor, Math.toRadians(hour * 30 + (minute/2.)), 4, 0.4);
    }

    /**
     * Nom           : drawNeedle
     * Description   : Dessine une aiguille à partir des coordonnées spécifiées,
     *                 avec la couleur, la largeur et la longueur spécifiées.
     * @param g2     : Le contexte graphique dans lequel dessiner.
     * @param color  : La couleur de l'aiguille.
     * @param rad    : L'angle en radians de l'aiguille.
     * @param width  : La largeur de l'aiguille.
     * @param length : La longueur de l'aiguille.
     */
    private void drawNeedle(Graphics2D g2, Color color, double rad, int width, double length) {
        Point centre = new Point(image.getWidth(this) / 2, image.getHeight(this) / 2);
        Point needleEnd = new Point(
                (int) (centre.x + Math.sin(rad) * (centre.x * length)),
                (int) (centre.y - Math.cos(rad) * (centre.y * length)));

        g2.setColor(color);
        g2.setStroke(new BasicStroke(width));
        
        g2.draw(new Line2D.Double(centre.x, centre.y, needleEnd.x, needleEnd.y));
    }
    // endregion
}
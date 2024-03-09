package View;

import Observer.Arab;
import Observer.Clock;
import Observer.Digital;
import Observer.Roman;
import Subject.StopWatch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;

/**
 * -----------------------------------------------------------------------------------
 * @Authors     : Slimani Walid & Steiner Jeremiah
 * @Description : Cette classe représente la fenêtre principale de l'application.
 *                Elle permet de créer et de gérer plusieurs chronomètres ainsi que
 *                leurs différentes représentations graphiques.
 * @Info        : /
 * -----------------------------------------------------------------------------------
 **/

public class MainWindow extends JFrame{

    // region Filed
    private final LinkedList<StopWatch> chronos;
    private final Point windowLocation;
    // endregion

    // region Ctor
    /**
     * Nom             : MainWindow
     * Description     : Constructeur de la classe MainWindow. Crée une fenêtre
     *                   principale avec un nombre spécifié de chronomètres.
     * @param nbrTimer : Le nombre de chronomètres à créer.
     */
    public MainWindow(int nbrTimer)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        chronos = new LinkedList<>();

        JPanel panel = new JPanel();
        setTitle("Panneau de contrôle");

        Dimension screenSize = toolkit.getScreenSize();
        windowLocation = new Point((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);
        setLocation(windowLocation);

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < nbrTimer; ++i)
        {
            chronos.add(new StopWatch());
            addNewLine(panel, i);
        }
        addLastLine(panel, nbrTimer);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(panel);
        setResizable(false);
    }
    // endregion

    //region Private Methods
    /**
     * Nom               : creatClockFrame
     * Description       : Crée et configure une nouvelle fenêtre d'horloge avec le titre spécifié
     *                     et l'horloge donnée.
     * @param frameTitle : Le titre de la fenêtre d'horloge.
     * @param clock      : L'horloge à afficher dans la fenêtre.
     * @return           : La nouvelle instance de JFrame configurée avec l'horloge.
     */
    private JFrame creatClockFrame(String frameTitle, Clock clock) {
        JFrame clockFrame = new JFrame();
        clockFrame.setTitle(frameTitle);
        clockFrame.setLocation(windowLocation);
        clockFrame.add(clock);
        clockFrame.setVisible(true);
        clockFrame.setResizable(false);
        clockFrame.pack();
        return clockFrame;
    }

    /**
     * Nom                : creatRomanObserver
     * Description        : Crée et attache un observateur d'horloge romaine au chronomètre donné.
     * @param chronoIndex : L'index du chronomètre dans la liste des chronomètres.
     */
    private void creatRomanObserver(int chronoIndex)
    {
        StopWatch chrono = chronos.get(chronoIndex);

        Roman clock = new Roman(chrono);
        chrono.attach(clock);

        JFrame clockFrame = creatClockFrame("Horloge Romaine", clock);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Roman clock " + chrono);
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    /**
     * Nom                : creatArabObserver
     * Description        : Crée et attache un observateur d'horloge arabe au chronomètre donné.
     * @param chronoIndex : L'index du chronomètre dans la liste des chronomètres.
     */
    private void creatArabObserver(int chronoIndex)
    {
        StopWatch chrono = chronos.get(chronoIndex);

        Arab clock = new Arab(chrono);
        chrono.attach(clock);

        JFrame clockFrame = creatClockFrame("Horloge Arabe", clock);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Arabe clock " + chrono);
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    /**
     * Nom                : creatDigitalObserver
     * Description        : Crée et attache un observateur d'horloge numérique au chronomètre donné.
     * @param chronoIndex : L'index du chronomètre dans la liste des chronomètres.
     */
    private void creatDigitalObserver(int chronoIndex)
    {
        StopWatch chrono = chronos.get(chronoIndex);

        Digital clock = new Digital(chrono);
        chrono.attach(clock);

        JFrame clockFrame = creatClockFrame("Horloge Numérique", clock);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Numérique clock " + chrono);
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    /**
     * Nom               : addNewLine
     * Description       : Ajoute une nouvelle ligne de contrôle de chronomètre à un JPanel donné.
     * @param panFather  : Le JPanel auquel ajouter la nouvelle ligne.
     * @param lineNumber : Le numéro de ligne correspondant au chronomètre dans la liste des chronomètres.
     */
    private void addNewLine(JPanel panFather, int lineNumber)
    {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setLayout(flowLayout);

        JLabel chronoName = new JLabel(chronos.get(lineNumber).toString());

        JButton buttonStart = new JButton("Démarrer");
        JButton buttonStop = new JButton("Arrêter");
        JButton buttonReset = new JButton("Réinitialiser");
        JButton buttonRome = new JButton("Cadran romain");
        JButton buttonArab = new JButton("Cadran Arabe");
        JButton buttonNum = new JButton("Numérique");

        buttonStart.addActionListener(ae -> chronos.get(lineNumber).startTimer());
        buttonStop.addActionListener(ae -> chronos.get(lineNumber).stopTimer());

        buttonReset.addActionListener(ae -> {
            chronos.get(lineNumber).stopTimer();
            chronos.get(lineNumber).reset();
        });

        buttonRome.addActionListener(ae -> creatRomanObserver(lineNumber));
        buttonArab.addActionListener(ae -> creatArabObserver(lineNumber));
        buttonNum.addActionListener(ae -> creatDigitalObserver(lineNumber));

        panel.add(chronoName);
        panel.add(buttonStart);
        panel.add(buttonStop);
        panel.add(buttonReset);
        panel.add(buttonRome);
        panel.add(buttonArab);
        panel.add(buttonNum);

        panFather.add(panel);
    }

    /**
     * Nom              : addLastLine
     * Description      : Ajoute la dernière ligne de contrôle de tous les chronomètres à un JPanel donné.
     * @param panFather : Le JPanel auquel ajouter la dernière ligne.
     * @param nbrTimer  : Le nombre total de chronomètres.
     */
    private void addLastLine(JPanel panFather, int nbrTimer)
    {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setLayout(flowLayout);

        JLabel tousLesChronos = new JLabel("Tous les chronos");

        JButton buttonRome = new JButton("Cadran romain");
        JButton buttonArab = new JButton("Cadran Arabe");
        JButton buttonNum = new JButton("Numérique");

        buttonRome.addActionListener(ae -> {
            JFrame clockFrame = new JFrame();
            clockFrame.setTitle("HorlogeS RomaineS");
            clockFrame.setLocation(windowLocation);
            clockFrame.setLayout(new FlowLayout());
            clockFrame.setSize(200 * nbrTimer,200);

            for (int i = 0; i < nbrTimer; ++i)
            {
                StopWatch chrono = chronos.get(i);

                Roman clock = new Roman(chrono);
                chrono.attach(clock);

                clockFrame.add(clock);
                clockFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                        chrono.detach(clock);
                        System.out.println("close Romans clocks => Roman " + chrono + " closed");
                    }
                });
                clock.addMouseListener(new CustomMouseListener(chrono));
            }
            clockFrame.setVisible(true);
            clockFrame.pack();
        });

        buttonArab.addActionListener(ae -> {
            JFrame clockFrame = new JFrame();
            clockFrame.setTitle("HorlogeS Arabes");
            clockFrame.setLocation(windowLocation);
            clockFrame.setLayout(new FlowLayout());

            for (int i = 0; i < nbrTimer; ++i)
            {
                StopWatch chrono = chronos.get(i);

                Arab clock = new Arab(chrono);
                chrono.attach(clock);

                clockFrame.add(clock);
                clockFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                        chrono.detach(clock);
                        System.out.println("close Arabes clocks => Arabe " + chrono + " closed");
                    }
                });
                clock.addMouseListener(new CustomMouseListener(chrono));
            }
            clockFrame.setVisible(true);
            clockFrame.pack();
        });

        buttonNum.addActionListener(ae -> {
            JFrame clockFrame = new JFrame();
            clockFrame.setTitle("HorlogeS NumériqueS");
            clockFrame.setLocation(windowLocation);
            clockFrame.setLayout(new FlowLayout());

            for (int i = 0; i < nbrTimer; ++i)
            {
                StopWatch chrono = chronos.get(i);

                Digital clock = new Digital(chrono);
                chrono.attach(clock);

                clockFrame.add(clock);
                clockFrame.addWindowListener(new WindowAdapter() {
                    public void windowClosing(WindowEvent e) {
                        // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                        chrono.detach(clock);
                        System.out.println("close Numériques clocks => Numérique " + chrono + " closed");
                    }
                });
                clock.addMouseListener(new CustomMouseListener(chrono));
            }
            clockFrame.setVisible(true);
            clockFrame.pack();
        });

        panel.add(tousLesChronos);
        panel.add(buttonRome);
        panel.add(buttonArab);
        panel.add(buttonNum);

        panFather.add(panel);
    }
    // endregion
}
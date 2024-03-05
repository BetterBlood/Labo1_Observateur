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

public class MainWindow extends JFrame{

    LinkedList<StopWatch> chronos;

    public MainWindow(int nbrTimer)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        chronos = new LinkedList<>();

        //JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        setTitle("Panneau de contrôle");

        Dimension screenSize = toolkit.getScreenSize();
        setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        //setPreferredSize(new Dimension(720, 41 * (nbrTimer) + 80));

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        for (int i = 0; i < nbrTimer; ++i)
        {
            addNewLine(panel, i);
            chronos.add(new StopWatch());
        }
        addLastLine(panel, nbrTimer);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        add(panel);
        setResizable(false);
    }

    private JFrame creatClockFrame(String frameTitle, Clock clock) {
        JFrame clockFrame = new JFrame();
        clockFrame.setTitle(frameTitle);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        clockFrame.setLayout(new FlowLayout());
        clockFrame.setSize(200,200);
        clockFrame.add(clock);
        clockFrame.setVisible(true);
        clockFrame.pack();
        return clockFrame;
    }

    private void creatRomanObserver(int chronoId)
    {
        StopWatch chrono = chronos.get(chronoId);

        Roman clock = new Roman(chrono);
        chrono.attach(clock);

        JFrame clockFrame = creatClockFrame("Horloge Romaine", clock);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Roman clock");
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    private void creatArabObserver(int chronoId)
    {
        StopWatch chrono = chronos.get(chronoId);

        Arab clock = new Arab(chrono);
        chrono.attach(clock);

        JFrame clockFrame = creatClockFrame("Horloge Arabe", clock);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Arabe clock");
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    private void creatDigitalObserver(int chronoId)
    {
        StopWatch chrono = chronos.get(chronoId);

        Digital clock = new Digital(chrono);
        chrono.attach(clock);

        JFrame clockFrame = creatClockFrame("Horloge Numérique", clock);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Numérique clock");
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    public void addNewLine(JPanel panFather, int lineNumber)
    {
        JPanel panel = new JPanel();
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        panel.setLayout(flowLayout);

        JLabel chronoName = new JLabel("Chrono #" + (lineNumber + 1));

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

    public void addLastLine(JPanel panFather, int nbrTimer)
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
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);
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
                        System.out.println("close Romans clocks");
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
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);
            clockFrame.setLayout(new FlowLayout());
            clockFrame.setSize(200 * nbrTimer,200);

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
                        System.out.println("close Arabes clocks");
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
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);
            clockFrame.setLayout(new FlowLayout());
            clockFrame.setSize(200 * nbrTimer,200);

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
                        System.out.println("close numériques clocks");
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
}
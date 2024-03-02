package View;

import Observer.Arab;
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

        JFrame frame = new JFrame();
        frame.setTitle("Panneau de contrôle");

        Dimension screenSize = toolkit.getScreenSize();
        frame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        frame.setSize(720, 45 * (nbrTimer) + 80); // TODO : fit content

        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setAlignment(FlowLayout.RIGHT);
        frame.setLayout(flowLayout);
        frame.setResizable(false);

        for (int i = 0; i < nbrTimer; ++i)
        {
            addNewLine(frame, i);
            chronos.add(new StopWatch());
        }
        addLastLine(frame, nbrTimer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void creatRomanObserver(int chronoId)
    {
        StopWatch chrono = chronos.get(chronoId);

        Roman clock = new Roman(chrono);
        chrono.attach(clock);

        JFrame clockFrame = new JFrame();
        clockFrame.setTitle("Horloge Romaine");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        clockFrame.setLayout(new FlowLayout());
        clockFrame.setSize(200,200);
        clockFrame.add(clock);
        clockFrame.setVisible(true);

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

        JFrame clockFrame = new JFrame();
        clockFrame.setTitle("Horloge Arabe");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        clockFrame.setLayout(new FlowLayout());
        clockFrame.setSize(200,200);
        clockFrame.add(clock);
        clockFrame.setVisible(true);

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

        JFrame clockFrame = new JFrame();
        clockFrame.setTitle("Horloge Numérique");
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        clockFrame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        clockFrame.setLayout(new FlowLayout());
        clockFrame.setSize(200,200);
        clockFrame.add(clock);
        clockFrame.setVisible(true);

        clockFrame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.detach(clock);
                System.out.println("close Numérique clock");
            }
        });

        clock.addMouseListener(new CustomMouseListener(chrono));
    }

    public void addNewLine(JFrame frame, int lineNumber)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel chronoName = new JLabel("Chrono #" + (lineNumber + 1));

        JButton buttonStart = new JButton("Démarrer");
        JButton buttonStop = new JButton("Arrêter");
        JButton buttonReset = new JButton("Réinitialiser");
        JButton buttonRome = new JButton("Cadran romain");
        JButton buttonArab = new JButton("Cadran Arabe");
        JButton buttonNum = new JButton("Numérique");

        // TODO : voir si on remplace par des lambda ? les new ActionListener()
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chronos.get(lineNumber).startTimer();
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chronos.get(lineNumber).stopTimer();
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chronos.get(lineNumber).stopTimer();
                chronos.get(lineNumber).reset();
            }
        });

        buttonRome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                creatRomanObserver(lineNumber);
            }
        });

        buttonArab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                creatArabObserver(lineNumber);
            }
        });

        buttonNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                creatDigitalObserver(lineNumber);
            }
        });

        panel.add(chronoName);
        panel.add(buttonStart);
        panel.add(buttonStop);
        panel.add(buttonReset);
        panel.add(buttonRome);
        panel.add(buttonArab);
        panel.add(buttonNum);

        frame.add(panel);
    }

    public void addLastLine(JFrame frame, int nbrTimer)
    {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel tousLesChronos = new JLabel("Tous les chronos");

        JButton buttonRome = new JButton("Cadran romain");
        JButton buttonArab = new JButton("Cadran Arabe");
        JButton buttonNum = new JButton("Numérique");

        buttonRome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
            }
        });

        buttonArab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
            }
        });

        buttonNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
            }
        });

        panel.add(tousLesChronos);
        panel.add(buttonRome);
        panel.add(buttonArab);
        panel.add(buttonNum);

        frame.add(panel);
    }
}

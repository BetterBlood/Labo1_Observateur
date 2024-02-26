import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Window {

    LinkedList<ConcreteSubject> chronos;

    public Window(int nbrTimer)
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
            chronos.add(new ConcreteSubject(i));
        }
        addLastLine(frame, nbrTimer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void displayChrono(int chronoId, EnumChronoType chronoType)
    {
        // subcrieb the observer to the relevant chrono
        ConcreteSubject chrono = chronos.get(chronoId);
        ConcreteObserver observer = new ConcreteObserver(chrono, chronoId, chronoType);
        chrono.subscrieb(observer);
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

        // TODO : s'inscrire au sujet (chrono) correspondant
        buttonStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chronos.get(lineNumber).timer.start();
            }
        });

        buttonStop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chronos.get(lineNumber).timer.stop();
            }
        });

        buttonReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                chronos.get(lineNumber).timer.stop();
                chronos.get(lineNumber).reset();
            }
        });

        buttonRome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                displayChrono(lineNumber, EnumChronoType.ROMAIN);
            }
        });

        buttonArab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                displayChrono(lineNumber, EnumChronoType.ARABE);
            }
        });

        buttonNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                displayChrono(lineNumber, EnumChronoType.NUMERIQUE);
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

        // TODO : faire que les chrono s'ouvrent dans la meme fenetre
        // TODO : s'inscrire au sujet (chrono) correspondant
        buttonRome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < nbrTimer; ++i)
                {
                    displayChrono(i, EnumChronoType.ROMAIN);
                }
            }
        });

        buttonArab.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < nbrTimer; ++i)
                {
                    displayChrono(i, EnumChronoType.ARABE);
                }
            }
        });

        buttonNum.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                for (int i = 0; i < nbrTimer; ++i)
                {
                    displayChrono(i, EnumChronoType.NUMERIQUE);
                }
            }
        });

        panel.add(tousLesChronos);
        panel.add(buttonRome);
        panel.add(buttonArab);
        panel.add(buttonNum);

        frame.add(panel);
    }
}

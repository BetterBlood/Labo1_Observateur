import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class Window {

    Image cadranChiffresArabes;
    Image cadranChiffresRomains;

    LinkedList<ConcreteSubject> chronos;

    public Window(int nbrTimer)
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // path ok ! TODO : mais elles s'affichent pas....
        cadranChiffresArabes = toolkit.getImage("images\\cadran_chiffres_arabes.jpg").getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        cadranChiffresRomains = toolkit.getImage("mages\\cadran_chiffres_romains.jpg").getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT);

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
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,200);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);

        // TODO : Chaque fenêtre doit pouvoir être fermée. (se désinscrire du Concrete subject)
        // TODO : Cliquer sur l’affichage d’un chronomètre met ce dernier en pause s’il était en marche ou le
        //          (re)démarre s’il était à l’arrêt.
        final int chronoIdDisplay = chronoId + 1;
        switch (chronoType)
        {
            case ARABE -> {
                System.out.println("Chrono " + chronoIdDisplay + " : Arabe");
                panel.getGraphics().drawImage(cadranChiffresArabes, 0, 0, panel);
            }
            case ROMAIN -> {
                System.out.println("Chrono " + chronoIdDisplay + " : Romain");
                panel.getGraphics().drawImage(cadranChiffresRomains, 0, 0, panel);
            }
            case NUMERIQUE -> System.out.println("Chrono " + chronoIdDisplay + " : Numérique");
        }
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

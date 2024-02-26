import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ConcreteObserver extends AbstractObserver {
    private long time;
    private ConcreteSubject chrono;

    Image image;

    public ConcreteObserver(ConcreteSubject subject, int subjectId, EnumChronoType chronoType)
    {
        chrono = subject;

        update();

        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(200,200);
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setLocation((int)screenSize.getWidth()/3, (int)screenSize.getHeight()/3);

        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setVisible(true);
        frame.add(panel);
        frame.setVisible(true);
        
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                // Chaque fenêtre se désinscrit du Concrete subject à la fermeture
                chrono.unsubscrieb(ConcreteObserver.this);
                System.out.println("close test");
            }
        });
        // TODO : Cliquer sur l’affichage d’un chronomètre met ce dernier en pause s’il était en marche ou le
        //          (re)démarre s’il était à l’arrêt.

        final int chronoIdDisplay = subjectId + 1;
        switch (chronoType)
        {
            case ARABE -> {
                System.out.println("Chrono " + chronoIdDisplay + " : Arabe");
                image = cadranChiffresArabes;
            }
            case ROMAIN -> {
                System.out.println("Chrono " + chronoIdDisplay + " : Romain");
                image = cadranChiffresRomains;
            }
            case NUMERIQUE ->
            {
                System.out.println("Chrono " + chronoIdDisplay + " : Numérique");
                image = numerique;
            }
        }
    }

    @Override
    public void update() {
        time = chrono.getTime();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, this);
    }
}

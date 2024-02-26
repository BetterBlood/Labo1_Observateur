import javax.swing.*;
import java.awt.*;

abstract public class AbstractObserver extends JPanel {

    Image cadranChiffresArabes;
    Image cadranChiffresRomains;

    public AbstractObserver()
    {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        // path ok ! TODO : mais elles s'affichent pas....
        cadranChiffresArabes = toolkit.getImage("images\\cadran_chiffres_arabes.jpg").getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        cadranChiffresRomains = toolkit.getImage("mages\\cadran_chiffres_romains.jpg").getScaledInstance(200, 200, java.awt.Image.SCALE_DEFAULT);
    }

    abstract public void update();
}

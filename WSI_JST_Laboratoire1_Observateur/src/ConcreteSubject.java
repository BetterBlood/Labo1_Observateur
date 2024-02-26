import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class ConcreteSubject {
    Timer timer;
    Long timeInSecondes;

    public ConcreteSubject(int id)
    {
        timeInSecondes = (long)0;
        ActionListener secForChronos = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                System.out.println("chronoRunning #" + id + " " + ++timeInSecondes);
                // TODO : appel à notify
            }
        };
        timer = new Timer(1000, secForChronos);
    }

    public void reset()
    {
        timeInSecondes = (long)0;
    }

    /**
     * retrieve la data depuis le concrete observer
     * @return
     */
    public long getData()
    {
        return timeInSecondes;
    }

}

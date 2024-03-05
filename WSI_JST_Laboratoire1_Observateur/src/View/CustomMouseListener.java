package View;

import Subject.StopWatch;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CustomMouseListener implements MouseListener {
    StopWatch chrono;

    public CustomMouseListener(StopWatch chrono) {
        this.chrono = chrono;
    }

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
}

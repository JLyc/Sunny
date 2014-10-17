package penguins.gui;

import penguins.shapes.ShapeLong;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * Created by socha on 17.10.2014.
 */
public class CustomMouseListener implements MouseListener, MouseMotionListener {

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        GameBoard.getInstance().piantThis(e.getPoint());
        if(GameBoard.shapeLong == null) {
            GameBoard.shapeLong = new ShapeLong(e.getPoint());
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        GameBoard.getInstance().repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        GameBoard.shapeLong.move(e.getPoint());
        GameBoard.getInstance().repaint();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}

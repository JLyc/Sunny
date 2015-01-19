package penguins.gui;

import penguins.shapes.ShapeLong;

import javax.swing.*;
import java.awt.*;

/**
 * Created by socha on 17.10.2014.
 */
public class GameBoard extends JPanel {
    private static final GameBoard INSTANCE = new GameBoard();
    public static ShapeLong shapeLong;
    private Graphics g;

    public GameBoard() {
        CustomMouseListener mouseListener = new CustomMouseListener();
        this.setSize(GuiConstants.FRAME_SIZE);
        this.setBackground(Color.BLUE);
        this.addMouseListener(mouseListener);
        this.addMouseMotionListener(mouseListener);
    }

    public static GameBoard getInstance() {
        return INSTANCE;
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        if(shapeLong !=null)
        {
            shapeLong.paintShape(g);
        }
    }

    public Graphics getG() {
        return this.getGraphics();
    }

    void piantThis(Point e)
    {
        this.getGraphics().drawString("position "+(int ) e.getX()+" : " + (int )e.getY() , (int )e.getX(),(int ) e.getY());
    }


}

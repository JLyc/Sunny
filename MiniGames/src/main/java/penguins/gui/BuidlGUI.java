package penguins.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by socha on 17.10.2014.
 */
public class BuidlGUI extends JFrame{

    public BuidlGUI() throws HeadlessException {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("Penguins Of Madagascar");
        this.setSize(GuiConstants.FRAME_SIZE);
        this.setLocationRelativeTo(null);
        this.getRootPane().setContentPane(GameBoard.getInstance());

        this.setResizable(false);
        this.setVisible(true);
    }
}

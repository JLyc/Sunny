package ui_creator;

import javax.swing.*;

/**
 * Created by socha on 9.10.2014.
 */
public class GuiBuilder extends JFrame implements GuiPropperties{

    public GuiBuilder() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Sunny silent mode");
        getRootPane().setContentPane(new TopLevelPanel());
        setLayout(null);
        setSize(MAIN_FRAME_DIMENSION);
        setLocation(MAIN_FRAME_LOCATION);

        setResizable(false);
        setVisible(true);
    }
}

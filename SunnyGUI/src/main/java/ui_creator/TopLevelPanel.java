package ui_creator;

import javax.swing.*;
import java.awt.*;

/**
 * Created by socha on 9.10.2014.
 */
public class TopLevelPanel extends Container {

    public TopLevelPanel() {
        JPanel panel = new JPanel();
        panel.setSize(300, 300);
        panel.setBackground(Color.BLACK);
        add(panel);
    }
}

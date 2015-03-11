package test_package;

import javax.swing.*;
import java.awt.*;

/**
 * Created by JLyc on 27. 2. 2015.
 */
public class JDialogTemplate extends JDialog {

    public JDialogTemplate(Frame owner, String title, boolean modal) {
        super(owner, title, modal);
    }

    public static void main(String[] args) {
        JDialogTemplate h = new JDialogTemplate(null, "String", true);
        
    }
}

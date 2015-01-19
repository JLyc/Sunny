package penguins.gui;


/**
 * Created by socha on 17.10.2014.
 */
public class MainThread

{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuidlGUI();
            }
        });


    }
}

package penguins.gui;

import neural_center.initialization.AbilitiesRegistrar;
import neural_center.initialization.SunnyAbillities;

/**
 * Created by socha on 17.10.2014.
 */
public class MainThread implements AbilitiesRegistrar
{
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuidlGUI();
            }
        });


    }

    @Override
    public void registerAbilitie() {
        SunnyAbillities.registerAbillities(this);
    }

    @Override
    public void execute() {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BuidlGUI();
            }
        });
    }

    @Override
    public String getAbilitieName() {
        return "Penguins of Madagascar";
    }
}

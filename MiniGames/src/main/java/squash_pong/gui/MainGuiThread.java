package squash_pong.gui;

import neural_center.initialization.AbilitiesRegistrar;
import neural_center.initialization.SunnyAbillities;

/**
 * @author JLyc
 * @version 20.02.2013
 */

public class MainGuiThread implements AbilitiesRegistrar
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainGuiCreator.getGUIInstance();
			}
		});
		;
	}

    @Override
    public void registerAbilitie() {
        SunnyAbillities.registerAbillities(this);
    }

    @Override
    public void execute() {
        javax.swing.SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                javax.swing.SwingUtilities.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        MainGuiCreator.getGUIInstance();
                    }
                });
            }
        });;
    }

    @Override
    public String getAbilitieName() {
        return "squash";
    }
}

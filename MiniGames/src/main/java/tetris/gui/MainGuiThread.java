package tetris.gui;

import neural_center.initialization.AbilitiesRegistrar;
import neural_center.initialization.SunnyAbillities;

import java.applet.Applet;


/**
 * @author JLyc
 * @version 08.04.2013 v1
 */

public class MainGuiThread extends Applet implements AbilitiesRegistrar
{
	private static final long serialVersionUID = 1L;

	@Override
	public void init()
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainGuiCreator.getInstance();
			}
		});;
	}

	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainGuiCreator.getInstance();
			}
		});;
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
                MainGuiCreator.getInstance();
            }
        });;
    }

    @Override
    public String getAbilitieName() {
        return "Tetris";
    }
}

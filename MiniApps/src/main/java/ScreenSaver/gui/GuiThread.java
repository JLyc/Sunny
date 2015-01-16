package ScreenSaver.gui;

import neural_center.initialization.AbilitiesRegistrar;
import neural_center.initialization.SunnyAbillities;

import java.applet.Applet;

/**
 * @author JLyc
 * @version 22.04.2013
 */

public class GuiThread extends Applet implements AbilitiesRegistrar
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
				GuiConstructor.getInstance().createGui();
			}
		});;
	}

	public static void main(String[] JLyc)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				GuiConstructor.getInstance().createGui();
			}
		});
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
                        GuiConstructor.getInstance().createGui();
                    }
                });
            }
        });;
    }

    @Override
    public String getAbilitieName() {
        return "ScreenSaver";
    }
}

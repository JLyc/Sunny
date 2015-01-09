package ScreenSaver.gui;

import java.applet.Applet;

/**
 * @author JLyc
 * @version 22.04.2013
 */

public class GuiThread extends Applet
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
}

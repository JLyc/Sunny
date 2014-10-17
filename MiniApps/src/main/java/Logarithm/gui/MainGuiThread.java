package Logarithm.gui;

import java.applet.Applet;

/**
 * @author JLyc
 * @version 22.04.2013
 */

public class MainGuiThread extends Applet
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
				new CreateGUI();
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
				new CreateGUI();
			}
		});
		;
	}
}

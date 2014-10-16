package Tetris.gui;

import java.applet.Applet;

/**
 * @author JLyc
 * @version 08.04.2013 v1
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
}

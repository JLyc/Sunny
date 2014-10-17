package squash_pong.gui;

/**
 * @author JLyc
 * @version 20.02.2013
 */

public class MainGuiThread
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				MainGuiCreator.createGUI();
			}
		});
		;
	}
}

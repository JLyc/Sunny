package ScreenSaver.gui;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;

	public MainPanel()
	{
		add(Screen.getInstance());
	}
}

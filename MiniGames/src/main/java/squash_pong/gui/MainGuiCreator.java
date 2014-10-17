package squash_pong.gui;

import static squash_pong.gui.XYPositionConstants.FRAME_HIGHT;
import static squash_pong.gui.XYPositionConstants.FRAME_WIDTH;

import javax.swing.*;
import java.awt.*;


public class MainGuiCreator extends JFrame
{
	private static final long serialVersionUID = 1L;

	private static MainGuiCreator creatorInstance = null;

	private MainGuiCreator()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Squash");
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HIGHT));
		this.getRootPane().setContentPane(new MaintPanel());
		this.addKeyListener(new InputKeyListener());
		this.pack();
		this.setVisible(true);
	}

	public static void createGUI()
	{
		if (creatorInstance == null)
		{
			creatorInstance = new MainGuiCreator();
		}
	}

	public static MainGuiCreator getGUIInstance()
	{
		createGUI();
		return creatorInstance;
	}
}

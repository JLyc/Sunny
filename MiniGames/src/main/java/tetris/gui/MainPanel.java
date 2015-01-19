package tetris.gui;


import java.awt.Color;

import javax.swing.JPanel;

public class MainPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static MainPanel instance = null;

	private MainPanel()
	{
		this.setLayout(null);
		this.setSize(XYPositionConstants.FRAME_WIDTH-18, XYPositionConstants.FRAME_HIGHT-10);
		this.setBackground(Color.BLACK.brighter());
		add(GameBoard.getInstance());
		add(ScoreBoard.getInstance());
	}


	public static  MainPanel getInstance()
	{
		if(instance==null)
		{
			instance= new MainPanel();
		}
		return instance;
	}
}

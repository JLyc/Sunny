package tetris.gui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainGuiCreator extends JFrame implements XYPositionConstants
{
	private static final long serialVersionUID = 1L;

	private static MainGuiCreator creatorInstance = null;
	public JPanel basicPanel = new JPanel();

	private MainGuiCreator()
	{
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("tetris by JLyc");
		this.setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HIGHT));
		this.getRootPane().setContentPane(MainPanel.getInstance());
		this.addKeyListener(new TetrisKeyListener());
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
	}

	public static MainGuiCreator getInstance()
	{
		if (creatorInstance == null)
		{
			creatorInstance = new MainGuiCreator();
		}
		return creatorInstance;
	}
}

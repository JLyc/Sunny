package Logaritmus.gui;

import java.awt.Color;

import javax.swing.JFrame;

public class CreateGUI extends JFrame
{
	private static final long serialVersionUID = 1L;

	public CreateGUI()
	{
		this.setBackground(Color.BLUE);
		this.setSize(500, 600);
		this.getRootPane().setContentPane(MainPanel.getMainPanelInst());
		this.setResizable(false);
		this.setVisible(true);
	}
}

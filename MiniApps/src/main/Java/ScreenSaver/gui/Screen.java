package ScreenSaver.gui;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import ScreenSaver.logic.Ball;

public class Screen extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static Screen instance = null;
	public static Ball ball = new Ball();

	private Screen()
	{
		this.setPreferredSize(new Dimension(1300,1000));
	}

	public static Screen getInstance()
	{
		if(instance==null)
		{
			instance = new Screen();
		}
		return instance;
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
	}
}

package ScreenSaver.gui;

import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import ScreenSaver.engine.ScreenSaving;

public class GuiConstructor extends JFrame
{
	private static final long serialVersionUID = 1L;
	private static GuiConstructor instance;

	private GuiConstructor()
	{}

	public void createGui()
	{
		if(instance!=null)
		{
			this.setDefaultCloseOperation(EXIT_ON_CLOSE);
			this.setTitle("Crazy Pool Ball");
			this.setPreferredSize(new Dimension(300,350));
			this.addKeyListener(new KeyListener()
			{

				@Override
				public void keyTyped(KeyEvent e)
				{
					new Thread(new ScreenSaving()).start();
				}

				@Override
				public void keyReleased(KeyEvent e)
				{}

				@Override
				public void keyPressed(KeyEvent e)
				{}
			});
			this.getRootPane().setContentPane(new MainPanel());
			this.pack();
			this.setVisible(true);
		}
	}

	public static GuiConstructor getInstance()
	{
		if(instance==null)
		{
			instance=new GuiConstructor();
		}
		return instance;
	}

}

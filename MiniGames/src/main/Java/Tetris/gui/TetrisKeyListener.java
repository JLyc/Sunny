package Tetris.gui;

import game.Direction;
import game.Rotation;
import game.TetrisEngine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TetrisKeyListener extends KeyAdapter
{
	@Override
	public void keyPressed(KeyEvent e)
	{
		super.keyPressed(e);

		switch (e.getKeyCode())
		{
			case KeyEvent.VK_N:
				Thread t = new Thread(new TetrisEngine());
				t.start();
				break;
			case KeyEvent.VK_A:
				TetrisEngine.currentBrick.synchronizedTransmieter(Direction.LEFT);
				break;
			case KeyEvent.VK_D:
				TetrisEngine.currentBrick.synchronizedTransmieter(Direction.RIGHT);
				break;
			case KeyEvent.VK_S:
				TetrisEngine.currentBrick.synchronizedTransmieter(Direction.DOWN);
				break;
			case KeyEvent.VK_E:
				TetrisEngine.currentBrick.synchronizedTransmieter(Rotation.CLOCKWISE);
				break;
			case KeyEvent.VK_Q:
				TetrisEngine.currentBrick.synchronizedTransmieter(Rotation.ANTICLOCKWISE);
				break;
			case KeyEvent.VK_ENTER:
				System.exit(0);
				break;
			default:
				break;
		}
	}
}

package squash_pong.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputKeyListener implements KeyListener
{
	private RocketMoveThread player1Thread = new RocketMoveThread(Court.p1Rocket);
	private Thread player1 = new Thread(player1Thread);
	private RocketMoveThread player2Thread = new RocketMoveThread(Court.p2Rocket);
	private Thread player2 = new Thread(player2Thread);
	private Thread startGame = new Thread(new BallMovement());

	private static final int LEFT = -1;
	private static final int RIGHT = +1;

	@Override
	public void keyPressed(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_D:
				if(!player1.isAlive())
				{
					player1 = new Thread(player1Thread);
					player1.start();
				}
				player1Thread.setMovementDirection(RIGHT);
				break;
			case KeyEvent.VK_A:
				if(!player1.isAlive())
				{
					player1 = new Thread(player1Thread);
					player1.start();
				}
				player1Thread.setMovementDirection(LEFT);
				break;
			case KeyEvent.VK_RIGHT:
				if(!player2.isAlive())
				{
					player2 = new Thread(player2Thread);
					player2.start();
				}
				player2Thread.setMovementDirection(RIGHT);
				break;
			case KeyEvent.VK_LEFT:
				if(!player2.isAlive())
				{
					player2 = new Thread(player2Thread);
					player2.start();
				}
				player2Thread.setMovementDirection(LEFT);
				break;
			default:
				if(!startGame.isAlive())
				{
					startGame = new Thread(new BallMovement());
					startGame.start();
				}
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_A:
				player1Thread.setMovementDirection(0);
				player1.interrupt();
				break;
			case KeyEvent.VK_D:
				player1Thread.setMovementDirection(0);
				player1.interrupt();
				break;
			case KeyEvent.VK_RIGHT:
				player2Thread.setMovementDirection(0);
				player2.interrupt();
				break;
			case KeyEvent.VK_LEFT:
				player2Thread.setMovementDirection(0);
				player2.interrupt();
				break;
			default:
				break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

}

package squash_pong.gui;

import squash_pong.gui.objects.Ball;
import squash_pong.gui.objects.Rocket;

import java.awt.*;
import java.util.Random;

public class BallMovement implements Runnable
{
    Ball ball = Court.ball;
	private Random rnd = new Random();
	private int x = (rnd.nextInt(2) == 1) ? 1 : -1;
	private int y = (rnd.nextInt(2) == 1) ? 1 : -1;
	private int currentX = rnd.nextInt(100) + 100;
	private int currentY = rnd.nextInt(100) + 75;

	private static final int LEFT_OR_UP = -1;
	private static final int RIHT_OR_DOWN = +1;

	@Override
	public void run()
	{
		while(squashWinRule())
		{
			moveOrBounce();
			ball.setPoint(new Point(currentX,currentY));
			Court.getInstance().repaint();
			try{
				Thread.sleep(20);
			}
			catch(InterruptedException e)
			{
				System.out.println("thread interupted");
			}
		}
		Court.scorePlayer1=0;
		Court.scorePlayer2=0;
	}

	private boolean squashWinRule()
	{
		boolean gameContinue = true;
		if (Court.scorePlayer1 >15 || Court.scorePlayer2 > 15)
		{
			gameContinue = false;
			if (Court.scorePlayer1 >= 14 && Court.scorePlayer2 >= 14)
			{
				gameContinue=true;
				if (Math.abs(Court.scorePlayer1 - Court.scorePlayer2) > 2)
				{
					gameContinue = false;
				}
			}
		}
		return gameContinue;
	}

	public void moveOrBounce()
	{
		int positionX = ball.getPoint().x + x;
		int positionY = ball.getPoint().y + y;

		if (positionY == 195)
		{
			if ((Court.p2Rocket.getPoint().x - Ball.BALL_SIZE <= positionX && positionX <= Court.p2Rocket.getPoint().x + Rocket.RACKET_WIDTH) && (Court.playsPlayer == 1))
			{
				y = LEFT_OR_UP;
				Court.playsPlayer = 2;
			}
			if ((Court.p1Rocket.getPoint().x - Ball.BALL_SIZE <= positionX && positionX <= Court.p1Rocket.getPoint().x + Rocket.RACKET_WIDTH) && (Court.playsPlayer == 2))
			{
				y = LEFT_OR_UP;
				Court.playsPlayer = 1;
			}
		}
		else if (positionX <= XYPositionConstants.COURT_WEST_POINT)
		{
			x = RIHT_OR_DOWN;
		}
		else if (positionX >= XYPositionConstants.COURT_EAST_POINT - 15)
		{
			x = LEFT_OR_UP;
		}
		else if (positionY <= XYPositionConstants.COURT_NORTH_POINT)
		{
			y = RIHT_OR_DOWN;
		}
		else if (positionY == XYPositionConstants.COURT_SOUTH_POINT)
		{
			if (Court.playsPlayer == 1)
			{
				Court.scorePlayer2++;
				Court.playsPlayer=2;
			}
			else
			{
				Court.scorePlayer1++;
				Court.playsPlayer=1;
			}
			positionX = rnd.nextInt(100) + 50;
			positionY = rnd.nextInt(100) + 50;
		}

		currentX = positionX;
		currentY = positionY;

	}
}

package squash_pong.gui;

import squash_pong.gui.objects.ObjectMovement;
import squash_pong.gui.objects.Rocket;

import java.awt.*;

public class RocketMoveThread implements Runnable
{
	private int movementDirection = 0;
	private int currectPosition = XYPositionConstants.COURT_WEST_POINT + ((XYPositionConstants.COURT_EAST_POINT - XYPositionConstants.COURT_WEST_POINT) / 2);
	private ObjectMovement objecstInst = null;

	public RocketMoveThread(ObjectMovement objecstInst)
	{
		this.objecstInst = objecstInst;
	}

	@Override
	public void run()
	{
		currectPosition = objecstInst.getPoint().x;
		while (isBetweenBorder(currectPosition += movementDirection))
		{
			try
			{
				objecstInst.setPoint(new Point(currectPosition, objecstInst.getPoint().y));

				if (movementDirection != 0)
				{
					Court.getInstance().repaint(objecstInst.getPoint().x - 1, objecstInst.getPoint().y, Rocket.RACKET_WIDTH + 3, Rocket.RACKET_HIGHT + 1);
					Court.getInstance().repaint(objecstInst.getPoint().x - 1, objecstInst.getPoint().y, Rocket.RACKET_WIDTH + 3, Rocket.RACKET_HIGHT + 1);
				}
				Thread.sleep(15);
			}
			catch (InterruptedException e)
			{
				return;
			}
		}
	}

	private boolean isBetweenBorder(int position)
	{
		boolean isBetweenBorder = false;

		if (XYPositionConstants.COURT_WEST_POINT < position && position < (XYPositionConstants.COURT_EAST_POINT - Rocket.RACKET_WIDTH))
		{
			isBetweenBorder = true;
		}
		return isBetweenBorder;
	}

	public void setMovementDirection(int movementDirection)
	{
		this.movementDirection = movementDirection;
	}

	public int getMovementDirection()
	{
		return movementDirection;
	}
}

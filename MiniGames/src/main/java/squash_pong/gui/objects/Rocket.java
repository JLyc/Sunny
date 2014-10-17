package squash_pong.gui.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public class Rocket implements ObjectMovement
{
	public static final int RACKET_WIDTH = 40;
	public static final int RACKET_HIGHT = 10;
	private Point point =  new Point(50,210);
	private Color rocketColor = new Color(0,0,0);

	@Override
	public void setPoint(Point point)
	{
		this.point = point;
	}

	@Override
	public Point getPoint()
	{
		return point;
	}

	public void setRocketColor(Color rocketColor)
	{
		this.rocketColor = rocketColor;
	}

	public Color getRocketColor()
	{
		return rocketColor;
	}

	public void paint(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillRoundRect(point.x, point.y, 45, 15, 10, 10);
		g.setColor(Color.BLACK);
		g.drawRoundRect(point.x, point.y, 45, 15, 10, 10);
	}
}

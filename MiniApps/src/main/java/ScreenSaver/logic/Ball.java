package ScreenSaver.logic;

import java.awt.Color;
import java.awt.Point;

public class Ball
{
	public static final int BALL_SIZE = 15;
	private Point point =  new Point(150,150);
	private Color ballColor = new Color(255,255,255);
	private Color ballOutlineColor = new Color(255,0,0);

	public void setPoint(Point point)
	{
		this.point = point;
	}

	public Point getPoint()
	{
		return point;
	}

	public Color getBallColor()
	{
		return ballColor;
	}

	public void setBallColor(Color ballColor)
	{
		this.ballColor = ballColor;
	}

	public Color getBallOutlineColor()
	{
		return ballOutlineColor;
	}

	public void setBallOutlineColor(Color ballOutlineColor)
	{
		this.ballOutlineColor = ballOutlineColor;
	}
}

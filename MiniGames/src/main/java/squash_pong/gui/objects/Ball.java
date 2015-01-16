package squash_pong.gui.objects;

import java.awt.Color;
import java.awt.Point;

public class Ball implements ObjectMovement
{
	public static final int BALL_SIZE = 15;
	private Point point =  new Point(150,150);
	private Color ballColor = new Color(100,100,0);

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

	public Color getBallColor()
	{
		return ballColor;
	}

	public void setBallColor(Color ballColor)
	{
		this.ballColor = ballColor;
	}
}

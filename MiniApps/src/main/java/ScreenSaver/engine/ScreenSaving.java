package ScreenSaver.engine;

import ScreenSaver.gui.Screen;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class ScreenSaving implements Runnable
{
	int[] xPoints =
	{ 10, 20, 10, 20 };
	int[] yPoints =
	{ 50, 100, 50, 100 };
	private Random rnd = new Random();

	Color[] color = {
			Color.BLACK,
			Color.BLUE,
			Color.CYAN,
			Color.GRAY,
			Color.GREEN,
			Color.MAGENTA,
			Color.ORANGE,
			Color.PINK,
			Color.RED,
			Color.YELLOW
	};

	@Override
	public void run()
	{
		while (true)
		{
			int startPointX = rnd.nextInt(900) + 50;
			int startPointY = rnd.nextInt(900) + 50;

			int endPointX = rnd.nextInt(900) + 50;
			int endPointY = rnd.nextInt(900) + 50;

			int thickness = rnd.nextInt(25) + 5;
			int deviation = (int) (Math.sin(45)*thickness);

			int startNextX = startPointX+ deviation;
			int startNextY = startPointY + deviation;

			thickness = rnd.nextInt(25) + 5;
			deviation = (int) (Math.sin(45)*thickness);


			int endNextY = endPointY + deviation;
			deviation = (startPointX>endPointX)? deviation*-1:deviation;
			int endNextX = endPointX + deviation;

			xPoints[0] = startPointX;
			yPoints[0] = startPointY;
			xPoints[1] = endPointX;
			yPoints[1] = endPointY;
			xPoints[2] = endNextX;
			yPoints[2] = endNextY;
			xPoints[3] = startNextX;
			yPoints[3] = startNextY;

			int index = 0;
			while (index <= 10)
			{

				xPoints[0] = xPoints[1]; // startPointX;
				yPoints[0] = yPoints[1]; // startPointY;
				xPoints[3] = xPoints[2];
				yPoints[3] = yPoints[2];

				xPoints[1] = rnd.nextInt(900) + 50; // endPointX;
				yPoints[1] = rnd.nextInt(900) + 50; // endPointY;

				thickness = rnd.nextInt(25) + 5;
				deviation = (int) (Math.sin(45)*thickness);

				yPoints[2] = yPoints[1] + deviation;

//				deviation = (xPoints[0]>xPoints[1])? deviation*-1:deviation;
				xPoints[2] = xPoints[1] + deviation;


				drawScreenSaverLine();

				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
				index++;
			};
			Screen.getInstance().repaint();
		}
	}

	private void drawScreenSaverLine()
	{
		Graphics screen = Screen.getInstance().getGraphics();
		// screen.fillRect(10, 50, 20, 100);
		screen.setColor(color[rnd.nextInt(color.length)]);
		screen.fillPolygon(xPoints, yPoints, xPoints.length);
//		int hypotenuse =
		getThickness(xPoints[0], yPoints[0], xPoints[3], yPoints[3]);
//		screen.setColor(Color.GREEN);
//		screen.fillOval((xPoints[0] - (hypotenuse / 2)), yPoints[0], hypotenuse, hypotenuse);
//		hypotenuse = getThickness(xPoints[1], yPoints[1], xPoints[2], yPoints[2]);
//		screen.setColor(Color.GREEN);
//		screen.fillOval((xPoints[1] - (hypotenuse / 2)), yPoints[1], hypotenuse, hypotenuse);
	}

	private int getThickness(int x1, int y1, int x2, int y2)
	{
		double ordinateX = Math.abs(x1 - x2);
		double ordinateY = Math.abs(y1 - y2);

		return (int) Math.sqrt((ordinateX * ordinateX) + (ordinateY * ordinateY));
	}

}

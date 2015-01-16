package squash_pong.gui;

import squash_pong.gui.objects.Ball;
import squash_pong.gui.objects.Rocket;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class Court extends JPanel
{
	private static final long serialVersionUID = 1L;
	private static Graphics courtGraphics;
	public static Ball ball = new Ball();
	public static Rocket p1Rocket = new Rocket();
	public static Rocket p2Rocket = new Rocket();
	private static Court instance = new Court();
	private static Random rnd = new Random();
	public static int playsPlayer = rnd.nextInt(1)+1;
	public static int scorePlayer1= 0;
	public static int scorePlayer2= 0;


	public Court()
	{
//		this.setBackground(Color.BLUE);
		this.setSize(500, 500);
		p1Rocket.setRocketColor(Color.RED);
		p2Rocket.setRocketColor(Color.BLUE);
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		courtGraphics = g;
		courtGraphics.drawString("player 1 vs. player 2: \n", 70,20);
		courtGraphics.drawString(scorePlayer1 +"  vs. " +  scorePlayer2 , 105,40);
		createCourt();
		courtGraphics.setColor(ball.getBallColor());
		courtGraphics.fillOval(ball.getPoint().x, ball.getPoint().y, Ball.BALL_SIZE, Ball.BALL_SIZE);

		if(playsPlayer==2)
		{
			courtGraphics.setColor(p1Rocket.getRocketColor());
			courtGraphics.fillRoundRect(p1Rocket.getPoint().x, p1Rocket.getPoint().y, Rocket.RACKET_WIDTH, Rocket.RACKET_HIGHT, 10, 10);
			courtGraphics.setColor(p2Rocket.getRocketColor());
			courtGraphics.drawRoundRect(p2Rocket.getPoint().x, p2Rocket.getPoint().y, Rocket.RACKET_WIDTH, Rocket.RACKET_HIGHT, 10, 10);
		}
		else
		{
			courtGraphics.setColor(p2Rocket.getRocketColor());
			courtGraphics.fillRoundRect(p2Rocket.getPoint().x, p2Rocket.getPoint().y, Rocket.RACKET_WIDTH, Rocket.RACKET_HIGHT, 10, 10);
			courtGraphics.setColor(p1Rocket.getRocketColor());
			courtGraphics.drawRoundRect(p1Rocket.getPoint().x, p1Rocket.getPoint().y, Rocket.RACKET_WIDTH, Rocket.RACKET_HIGHT, 10, 10);
		}

	}

	private void createCourt()
	{
		courtGraphics.drawLine(XYPositionConstants.COURT_WEST_POINT, XYPositionConstants.COURT_NORTH_POINT, XYPositionConstants.COURT_WEST_POINT, XYPositionConstants.COURT_SOUTH_POINT);
		courtGraphics.drawLine(XYPositionConstants.COURT_WEST_POINT, XYPositionConstants.COURT_NORTH_POINT, XYPositionConstants.COURT_EAST_POINT, XYPositionConstants.COURT_NORTH_POINT);
		courtGraphics.drawLine(XYPositionConstants.COURT_EAST_POINT, XYPositionConstants.COURT_NORTH_POINT, XYPositionConstants.COURT_EAST_POINT, XYPositionConstants.COURT_SOUTH_POINT);
	}

	public static Court getInstance()
	{
		return instance;
	}
}

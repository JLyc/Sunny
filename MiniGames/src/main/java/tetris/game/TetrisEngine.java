package tetris.game;

import tetris.bricks.*;
import tetris.gui.GameBoard;
import tetris.gui.ScoreBoard;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class TetrisEngine implements Runnable
{
	public static Brick currentBrick;
	public Brick nextBrick;
	private Calendar startTime = Calendar.getInstance();

	private List<Brick> brickList = new ArrayList<Brick>();

	private void createStack()
	{
		brickList.add(new LBrick());
		brickList.add(new JBrick());
		brickList.add(new OBrick());
		brickList.add(new SBrick());
		brickList.add(new ZBrick());
		brickList.add(new IBrick());
		brickList.add(new EBrick());
	}

	@Override
	public void run()
	{
		Random r = new Random();
		GameBoard.getInstance().newGamePreparation();
		createStack();
		nextBrick = brickList.get(r.nextInt(brickList.size()));
		while (true)
		{
			try
			{
				currentBrick = nextBrick;
				nextBrick = brickList.get(r.nextInt(brickList.size()));
				ScoreBoard.getInstance().paintNextBrick(nextBrick);
				currentBrick.createBrick();
				Thread.sleep((long) (1000-ScoreBoard.getInstance().level*30));

				while (currentBrick.synchronizedTransmieter(Direction.DOWN))
				{
					lvlUpBaseOnTime();
					Thread.sleep((long) (1000-ScoreBoard.getInstance().level*30));
				}
			}
			catch (InterruptedException e)
			{
				GameBoard.getInstance().clearBoard();
				GameBoard.getInstance().repaint();
				return;
			}
		}
	}

	private void lvlUpBaseOnTime()
	{
		Calendar currentTime = Calendar.getInstance();

		long a = currentTime.getTimeInMillis();
		long b = startTime.getTimeInMillis();

		long rozdiel = a-b;
		if(rozdiel>(60000*ScoreBoard.getInstance().level))
		{
			ScoreBoard.getInstance().level++;

		}
		ScoreBoard.getInstance().repaintText();
		ScoreBoard.getInstance().paintNextBrick(nextBrick);
	}


}

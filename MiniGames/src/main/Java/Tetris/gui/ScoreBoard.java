package Tetris.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import bricks.Brick;

public class ScoreBoard extends JPanel
{
	private static final long serialVersionUID = 1L;

	private static final Font TEXT = new Font("Time New Roman", Font.BOLD, 16);
	private static ScoreBoard scoreBoardInst = null;
	private static Graphics2D canvas = null;
	public float level = 1;


	private ScoreBoard()
	{
		this.setBorder(BorderFactory.createRaisedBevelBorder());
		this.setBounds(300, 10, 150, 540);
		this.setBackground(Color.DARK_GRAY);
	}


	public static ScoreBoard getInstance()
	{
		if(scoreBoardInst==null)
		{
			scoreBoardInst= new ScoreBoard();
		}
		return scoreBoardInst;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		canvas = (Graphics2D) this.getGraphics();
		paintText(g);
	}

	public void paintText(Graphics canvas)
	{
		canvas.setFont(TEXT);
		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(20, 150, 100, 25);
		canvas.setColor(Color.RED);
		canvas.drawString("Next Brick", 20, 50);
		canvas.drawString("Level "+(int) Math.round(level), 20, 175);
		canvas.drawString("Movement a s d", 20, 200);
		canvas.drawString("Rotation q e", 20, 225);
		canvas.drawString("Start game n", 20, 250);
	}

	public void repaintText()
	{
		canvas.setFont(TEXT);
		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(20, 150, 100, 25);
		canvas.setColor(Color.RED);
		canvas.drawString("Level "+(int) Math.round(level), 20, 175);
	}

	public void paintNextBrick(Brick brickInst)
	{
		int positionX = 50;
		int positionY = 100;

		canvas.setColor(Color.GRAY.brighter());
		canvas.drawRect(positionX-1- XYPositionConstants.BRICK_SIZE, positionY-1- XYPositionConstants.BRICK_SIZE, 3* XYPositionConstants.BRICK_SIZE+1, 3* XYPositionConstants.BRICK_SIZE+1);

		canvas.setColor(Color.DARK_GRAY);
		canvas.fillRect(positionX- XYPositionConstants.BRICK_SIZE, positionY- XYPositionConstants.BRICK_SIZE, 3* XYPositionConstants.BRICK_SIZE, 3* XYPositionConstants.BRICK_SIZE);

		Integer[][] brick = brickInst.getRotationPosition();

		for (int i = 0; i < brick[1].length; i++)
		{
			int[] nextBrickCompenzation = brickInst.resolvePozitionByGridNumber(brickInst.getRotationPosition()[1][i]);
			drawDecoratedBrick(positionX-(nextBrickCompenzation[0]* XYPositionConstants.BRICK_SIZE), positionY+(nextBrickCompenzation[1]* XYPositionConstants.BRICK_SIZE));
		}
	}

	public void drawDecoratedBrick(int positionX, int positionY)
	{
		int brickSize = 20;
		canvas.setColor(Color.GRAY);
		canvas.fillRect(positionX, positionY, brickSize, brickSize);
		canvas.setColor(Color.GRAY.darker());
		canvas.fillRect(positionX, positionY, brickSize, (int) (brickSize*0.2));
		canvas.fillRect(positionX, positionY, (int) (brickSize*0.2), brickSize);
		canvas.setColor(Color.GRAY.brighter());
		for (int i = 0; i< (int) (brickSize*0.2); i++)
		{
			canvas.drawLine(positionX+i, positionY+brickSize - i, positionX + brickSize - i, positionY+brickSize - i);
			canvas.drawLine(positionX+brickSize - i, positionY+i, positionX + brickSize - i, positionY+brickSize - i);
		}
	}
}

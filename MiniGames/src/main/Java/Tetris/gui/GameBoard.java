package Tetris.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GameBoard extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	private static GameBoard gbInstance = null;
	private static boolean[][] gridGameMap = null;
	private Graphics2D canvas = null;
	
	private GameBoard()
	{
		this.setLocation(0, 0);
		this.setSize(new Dimension(270, 550));
		setBackground(Color.BLACK);
	}
	
	public static GameBoard getInstance()
	{
		if (gbInstance == null)
		{
			gbInstance = new GameBoard();
		}
		return gbInstance;
	}
	
	@Override
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		canvas = (Graphics2D) getGraphics();
		canvas.translate(XYPositionConstants.BOARD_MARGIN_OFFSET + getVerticalMargin(),
				XYPositionConstants.BOARD_MARGIN_OFFSET + getHorizontalMargin());
		g.drawString("Press \"N\" start game", 50, 100);
		g.drawString("Press \"Enter\" end game", 50, 120);
	}
	
	public void newGamePreparation()
	{
		gridGameMap = new boolean[getRowCount() + 2][getColumnsCount()];
		clearBoard();
		repaintBoard();
	}
	
	private void drawGridLines()
	{
		canvas.drawString("Tetris v 0.1", 50, -20);
		canvas.setColor(Color.GRAY);
		int gameBoardWidth = getBoardWidth();
		int gameBoardHeight = getBoardHeight();
		
		for (int meridianSpacing = 0; meridianSpacing <= gameBoardWidth; meridianSpacing += XYPositionConstants.BRICK_SIZE)
		{
			canvas.drawLine(meridianSpacing, 0, meridianSpacing,
					gameBoardHeight);
		}
		
		for (int parallelSpacing = 0; parallelSpacing <= gameBoardHeight; parallelSpacing += XYPositionConstants.BRICK_SIZE)
		{
			canvas.drawLine(0, parallelSpacing, gameBoardWidth, parallelSpacing);
		}
	}
	
	public void clearBoard()
	{
		for (int Lyc = 0; Lyc < gridGameMap.length; Lyc++)
		{
			for (int Lucy = 0; Lucy < gridGameMap[Lyc].length; Lucy++)
			{
				gridGameMap[Lyc][Lucy] = false;
			}
		}
	}
	
	public void repaintBoard()
	{
		canvas.setColor(Color.BLACK);
		canvas.fillRect(0, 0, getBoardWidth(), getBoardHeight());
		canvas.setColor(Color.GRAY);
		for (int Lyc = 0; Lyc < (gridGameMap.length - 2); Lyc++)
		{
			for (int Lucy = 0; Lucy < gridGameMap[Lyc].length; Lucy++)
			{
				if (gridGameMap[Lyc][Lucy] == true)
				{
					drawDecoratedBrick(Lucy * XYPositionConstants.BRICK_SIZE, Lyc * XYPositionConstants.BRICK_SIZE);
				}
			}
		}
		drawGridLines();
	}
	
	private int getBoardWidth()
	{
		int marginWidth = getVerticalMargin();
		int width = getWidth() - (marginWidth + (2 * XYPositionConstants.BOARD_MARGIN_OFFSET));
		return width;
	}
	
	private int getBoardHeight()
	{
		int marginWidth = getHorizontalMargin();
		int width = getHeight() - (marginWidth + (2 * XYPositionConstants.BOARD_MARGIN_OFFSET));
		return width;
	}
	
	private int getHorizontalMargin()
	{
		int marginWidth = getHeight() % XYPositionConstants.BRICK_SIZE;
		return marginWidth;
	}
	
	private int getVerticalMargin()
	{
		int marginHeight = getWidth() % XYPositionConstants.BRICK_SIZE;
		return marginHeight;
	}
	
	public int getRowCount()
	{
		int rowCount = getBoardHeight() / XYPositionConstants.BRICK_SIZE;
		return rowCount;
	}
	
	public int getColumnsCount()
	{
		int columnsCount = getBoardWidth() / XYPositionConstants.BRICK_SIZE;
		return columnsCount;
	}
	
	public void setNewBrickPosition(int row, int column)
	{
		int reversRowIndex = getRowCount() - row;
		gridGameMap[reversRowIndex][column] = true;
		drawDecoratedBrick(column * XYPositionConstants.BRICK_SIZE, reversRowIndex * XYPositionConstants.BRICK_SIZE);
		// repaintBoard();
	}
	
	public void resetBrickPosition(int row, int column)
	{
		int reversRowIndex = getRowCount() - row;
		gridGameMap[reversRowIndex][column] = false;
		// repaintBoard();
		// performance cutdown
		canvas.setColor(Color.BLACK);
		canvas.fillRect((column * XYPositionConstants.BRICK_SIZE), (reversRowIndex * XYPositionConstants.BRICK_SIZE),
				XYPositionConstants.BRICK_SIZE, XYPositionConstants.BRICK_SIZE);
		canvas.setColor(Color.GRAY);
		canvas.drawRect((column * XYPositionConstants.BRICK_SIZE), (reversRowIndex * XYPositionConstants.BRICK_SIZE),
				XYPositionConstants.BRICK_SIZE, XYPositionConstants.BRICK_SIZE);
	}
	
	public void drawDecoratedBrick(int positionX, int positionY)
	{
		canvas.setColor(Color.GRAY);
		canvas.fillRect(positionX, positionY, XYPositionConstants.BRICK_SIZE, XYPositionConstants.BRICK_SIZE);
		canvas.setColor(Color.GRAY.darker());
		canvas.fillRect(positionX, positionY, XYPositionConstants.BRICK_SIZE,
				(int) (XYPositionConstants.BRICK_SIZE * 0.2));
		canvas.fillRect(positionX, positionY, (int) (XYPositionConstants.BRICK_SIZE * 0.2),
				XYPositionConstants.BRICK_SIZE);
		canvas.setColor(Color.GRAY.brighter());
		for (int i = 0; i < (int) (XYPositionConstants.BRICK_SIZE * 0.2); i++)
		{
			canvas.drawLine(positionX + i, (positionY + XYPositionConstants.BRICK_SIZE) - i,
					(positionX + XYPositionConstants.BRICK_SIZE) - i, (positionY + XYPositionConstants.BRICK_SIZE) - i);
			canvas.drawLine((positionX + XYPositionConstants.BRICK_SIZE) - i, positionY + i,
					(positionX + XYPositionConstants.BRICK_SIZE) - i, (positionY + XYPositionConstants.BRICK_SIZE) - i);
		}
		canvas.setColor(Color.GRAY);
	}
	
	public boolean isPlacedBrickPosition(int positionX, int positionY)
	{
		boolean isThereNoBrick = true;
		int reversRowIndex = getRowCount() - positionX;
		if (gridGameMap[reversRowIndex][positionY] == true)
		{
			isThereNoBrick = false;
		}
		return isThereNoBrick;
	}
	
	public void findFullLineAndRemoveIt()
	{
		
		for (int Lyc = 0; Lyc < gridGameMap.length; Lyc++)
		{
			boolean isLineFull = false;
			for (int Lucy = 0; Lucy < gridGameMap[Lyc].length; Lucy++)
			{
				if (gridGameMap[Lyc][Lucy] == true)
				{
					isLineFull = true;
				}
				else
				{
					isLineFull = false;
					break;
				}
			}
			if (isLineFull)
			{
				removeFullLine(Lyc);
			}
		}
		repaintBoard();
	}
	
	private void removeFullLine(int lineNumber)
	{
		int rowAbou = -1;
		int rowUnder = +1;
		for (int Lyc = lineNumber + rowAbou; Lyc > 0; Lyc--)
		{
			for (int Lucy = 0; Lucy < gridGameMap[Lyc].length; Lucy++)
			{
				gridGameMap[Lyc + rowUnder][Lucy] = gridGameMap[Lyc][Lucy];
			}
		}
		ScoreBoard.getInstance().level += 0.3;
	}
	
}

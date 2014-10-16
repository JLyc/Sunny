package Tetris.bricks;

import game.BrickPositionPoint;
import game.Direction;
import game.Rotation;
import game.SwitchMarker;
import gui.GameBoard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public abstract class Brick
{
	protected GameBoard gameBoardInst = GameBoard.getInstance();

	abstract protected BrickPositionPoint getZeroBrickPosition();
	abstract protected void setZeroBrickPosition(BrickPositionPoint zeroBrickPosition);
	public abstract Integer[][] getRotationPosition();
	abstract protected void setRotationPosition(int currentRotation);
	protected abstract int getCurrentRotation();

	public synchronized boolean synchronizedTransmieter(SwitchMarker switcher)
	{
		if(switcher instanceof Direction)
		{
			return canMoveBrick((Direction) switcher);
		}
		else if(switcher instanceof Rotation)
		{
			rotateBricks((Rotation) switcher);
			return false;
		}
		else
		{
			return false;
		}
	}

	public int[] resolvePozitionByGridNumber(int gridNumber)
	{
		int[] positionIn2D = new int[2];
		switch (gridNumber)
		{
			case 1:
				positionIn2D[0] = 1;
				positionIn2D[1] = -1;
				return positionIn2D;
			case 2:
				positionIn2D[0] = 1;
				positionIn2D[1] = 0;
				return positionIn2D;
			case 3:
				positionIn2D[0] = 1;
				positionIn2D[1] = 1;
				return positionIn2D;
			case 4:
				positionIn2D[0] = 0;
				positionIn2D[1] = -1;
				return positionIn2D;
			case 5:
				positionIn2D[0] = 0;
				positionIn2D[1] = 0;
				return positionIn2D;
			case 6:
				positionIn2D[0] = 0;
				positionIn2D[1] = 1;
				return positionIn2D;
			case 7:
				positionIn2D[0] = -1;
				positionIn2D[1] = -1;
				return positionIn2D;
			case 8:
				positionIn2D[0] = -1;
				positionIn2D[1] = 0;
				return positionIn2D;
			case 9:
				positionIn2D[0] = -1;
				positionIn2D[1] = +1;
				return positionIn2D;
			default:
				positionIn2D[0] = 0;
				positionIn2D[1] = 0;
				return positionIn2D;
		}
	}

	public void createBrick() throws InterruptedException
	{
		gameBoardInst.findFullLineAndRemoveIt();

		setRotationPosition(0);

		int startPointXRelevant = gameBoardInst.getRowCount() - 1;
		int startPointYRelevant = gameBoardInst.getColumnsCount() / 2;


		setZeroBrickPosition(new BrickPositionPoint(startPointXRelevant, startPointYRelevant));

		for (int i = 0; i < getRotationPosition()[getCurrentRotation()].length; i++)
		{
			int[] nextBrickCompenzation = resolvePozitionByGridNumber(getRotationPosition()[getCurrentRotation()][i]);
			if (!gameBoardInst.isPlacedBrickPosition(getZeroBrickPosition().pointX - nextBrickCompenzation[0], getZeroBrickPosition().pointY + nextBrickCompenzation[1]))
			{
				throw new InterruptedException();
			}
			gameBoardInst.setNewBrickPosition(getZeroBrickPosition().pointX - nextBrickCompenzation[0], getZeroBrickPosition().pointY + nextBrickCompenzation[1]);
		}
	}

	protected boolean canMoveBrick(Direction dir)
	{
		boolean canMoveBrickDown = false;

		List<Integer> prienikList = new ArrayList<Integer>(brickColisionCheckPosition(dir));

		for (int n = 0; n < prienikList.size(); n++)
		{
			int[] nextBrickCompenzation = resolvePozitionByGridNumber(prienikList.get(n));

			if (canPerformAction(getZeroBrickPosition().pointX - nextBrickCompenzation[0] + dir.getDirectionX(), getZeroBrickPosition().pointY + nextBrickCompenzation[1] + dir.getDirectionY() ))
			{
				canMoveBrickDown = true;
			}
			else
			{
				canMoveBrickDown = false;
				break;
			}
		}

		if (canMoveBrickDown == true)
		{
			resetCurrentPositionToPaintNew();
			paintNewPosition(dir.getDirectionX(), dir.getDirectionY());
			setZeroBrickPosition(new BrickPositionPoint(getZeroBrickPosition().pointX + dir.getDirectionX(), getZeroBrickPosition().pointY +dir.getDirectionY()));
		}
		return canMoveBrickDown;
	}

	protected ArrayList<Integer> brickColisionCheckPosition(Direction dir)
	{
		List<Integer> currentBrick = Arrays.asList(getRotationPosition()[getCurrentRotation()]);
		int[] colisionBricks = new int[3];

		if (dir == Direction.DOWN)
		{
			for (int Lyc = 1; Lyc <= colisionBricks.length; Lyc++)
			{
				if (currentBrick.contains(Lyc))
				{
					colisionBricks[Lyc - 1] = Lyc;
					continue;
				}
				for (int Lucy = Lyc; Lucy <= (Lyc + 6); Lucy += 3)
				{
					if (currentBrick.contains(Lucy))
					{
						colisionBricks[Lyc - 1] = Lucy;
						break;
					}
				}
			}
		}
		else if (dir == Direction.RIGHT)
		{
			for (int Lucy = 3, Lucien = 0; Lucy <= 9; Lucy += 3, Lucien++)
			{
				if (currentBrick.contains(Lucy))
				{
					colisionBricks[Lucien] = Lucy;
					continue;
				}
				for (int Lyc = Lucy; Lyc >0; Lyc--)
				{
					if (currentBrick.contains(Lyc))
					{
						colisionBricks[Lucien] = Lyc;
						break;
					}
				}
			}
		}
		else if (dir == Direction.LEFT)
		{
			for (int Lucy = 1, Lucien = 0; Lucy <= 7; Lucy += 3, Lucien++)
			{
				if (currentBrick.contains(Lucy))
				{
					colisionBricks[Lucien] = Lucy;
					continue;
				}
				for (int Lyc = Lucy; Lyc < (Lucy + colisionBricks.length); Lyc++)
				{
					if (currentBrick.contains(Lyc))
					{
						colisionBricks[Lucien] = Lyc;
						break;
					}
				}
			}
		}
		else
		{
			System.err.println("No intersection");
		}


		HashSet<Integer> intersection = new HashSet<Integer>();
		for (Integer a : colisionBricks)
		{
			for (int b : getRotationPosition()[getCurrentRotation()])
			{
				if (a == b)
				{
					intersection.add(a);
				}
			}
		}

		List<Integer> uniqeIntersection = new ArrayList<Integer>(intersection);

		return (ArrayList<Integer>) uniqeIntersection;
	}

	protected boolean canPerformAction(int positionX, int positionY)
	{
		int compenzationForZeroStartIndex = 1;
		int westBorder = 0;
		int eastBorder = gameBoardInst.getColumnsCount() - compenzationForZeroStartIndex;
		int northBorder = 0;
		int southBorder = gameBoardInst.getRowCount();
		boolean canPerformAction = false;

		if (positionY >= westBorder && positionY <= eastBorder && positionX > northBorder && positionX <= southBorder)
		{
			canPerformAction = gameBoardInst.isPlacedBrickPosition(positionX, positionY);
		}

		return canPerformAction;
	}

	protected void rotateBricks(Rotation rotationDir)
	{
		int currentRotatePosition = getCurrentRotation();
		int nextRotatePosition = rotationLoop(getCurrentRotation(), rotationDir);

		List<Integer> current = Arrays.asList(getRotationPosition()[currentRotatePosition]);
		List<Integer> next = Arrays.asList(getRotationPosition()[nextRotatePosition]);
		List<Integer> difference = new ArrayList<Integer>();

		for (int i : next)
		{
			if(!current.contains(i))
			{
				difference.add(i);
			}
		}

		for (int k = 0; k < difference.size(); k++)
		{
			int[] nextBrickCompenzation = resolvePozitionByGridNumber(difference.get(k));
			if(!canPerformAction(getZeroBrickPosition().pointX - nextBrickCompenzation[0], getZeroBrickPosition().pointY + nextBrickCompenzation[1]))
				return;
		}

			resetCurrentPositionToPaintNew();
			setRotationPosition(rotationLoop(getCurrentRotation(), rotationDir));
			paintNewPosition(0, 0);
	}

	protected int rotationLoop(int rotationStartInt, Rotation rotationProp)
	{
		if (rotationStartInt == rotationProp.getMaxRotationIndexPoint())
			rotationStartInt = rotationProp.getNewRotationStartNumber();
		else
			rotationStartInt += rotationProp.getRotationChangeNumber();

		return rotationStartInt;
	}

	protected void resetCurrentPositionToPaintNew()
	{
		for (int n = 0; n < getRotationPosition()[getCurrentRotation()].length; n++)
		{
			int[] nextBrick = resolvePozitionByGridNumber(getRotationPosition()[getCurrentRotation()][n]);
			gameBoardInst.resetBrickPosition(getZeroBrickPosition().pointX - nextBrick[0], getZeroBrickPosition().pointY + nextBrick[1]);
		}
	}

	protected void paintNewPosition(int brickMoveX, int brickMoveY)
	{
		for (int k = 0; k < getRotationPosition()[getCurrentRotation()].length; k++)
		{
			int[] nextBrick = resolvePozitionByGridNumber(getRotationPosition()[getCurrentRotation()][k]);
			gameBoardInst.setNewBrickPosition(getZeroBrickPosition().pointX - nextBrick[0] + brickMoveX, getZeroBrickPosition().pointY + nextBrick[1] + brickMoveY);
		}
	}

}

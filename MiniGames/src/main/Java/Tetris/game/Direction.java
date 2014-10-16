package Tetris.game;

public enum Direction implements SwitchMarker
{
	UP(+1,0), DOWN(-1,0), LEFT(0,-1), RIGHT(0,+1);

	private int directionX;
	private int directionY;

	Direction(int directionX, int directionY)
	{
		this.directionX = directionX;
		this.directionY = directionY;
	}

	public int getDirectionX()
	{
		return directionX;
	}

	public int getDirectionY()
	{
		return directionY;
	}
}

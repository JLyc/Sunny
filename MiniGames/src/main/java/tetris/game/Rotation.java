package tetris.game;

public enum Rotation implements SwitchMarker
{
	CLOCKWISE(3,1,0), ANTICLOCKWISE(0,-1,3);

	private int maxRotationIndexPoint;
	private int rotationIncrementationNumber;
	private int newRotationStartNumber;

	private Rotation(int maxRotationIndexPoint, int rotationIncrementationNumber, int newRotationStartNumber)
	{
		this.maxRotationIndexPoint = maxRotationIndexPoint;
		this.rotationIncrementationNumber = rotationIncrementationNumber;
		this.newRotationStartNumber = newRotationStartNumber;
	}

	public int getMaxRotationIndexPoint()
	{
		return maxRotationIndexPoint;
	}

	public int getRotationChangeNumber()
	{
		return rotationIncrementationNumber;
	}

	public int getNewRotationStartNumber()
	{
		return newRotationStartNumber;
	}
}


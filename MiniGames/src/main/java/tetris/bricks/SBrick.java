package tetris.bricks;

import tetris.game.BrickPositionPoint;

//  _#__
//  _##_
//  __#_

public class SBrick extends Brick
{
	private Integer[][] rotationPosition =
	{
	{ 3,6,5,8 },
	{ 9,8,5,4 },
	{ 7,4,5,2 },
	{ 1,2,5,6 } };

	private int currentRotation = 0;

	private BrickPositionPoint zeroBrickPosition;

	@Override
	public BrickPositionPoint getZeroBrickPosition()
	{
		return zeroBrickPosition;
	}

	@Override
	public Integer[][] getRotationPosition()
	{
		return rotationPosition;
	}

	@Override
	protected int getCurrentRotation()
	{
		return currentRotation;
	}

	@Override
	protected void setZeroBrickPosition(BrickPositionPoint zeroBrickPosition)
	{
		this.zeroBrickPosition = zeroBrickPosition;
	}

	@Override
	protected void setRotationPosition(int currentRotation)
	{
		this.currentRotation = currentRotation;
	}
}

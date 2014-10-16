package Tetris.bricks;

import game.BrickPositionPoint;

//	_#__
//	_##_
//	_#__

public class EBrick extends Brick
{
	private Integer[][] rotationPosition =
		{
		{ 4,5,6,8 },
		{ 8,5,2,4 },
		{ 4,5,6,2 },
		{ 2,5,8,6 } };

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

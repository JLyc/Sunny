package Tetris.bricks;

import game.BrickPositionPoint;

//__#__
//__#__
//_##__

public class LBrick extends Brick
{
	private Integer[][] rotationPosition =
		{
		{ 3,2,5,8 },
		{ 9,6,5,4 },
		{ 7,8,5,2 },
		{ 1,4,5,6} };

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

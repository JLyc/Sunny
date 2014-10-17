package tetris.bricks;

import tetris.game.BrickPositionPoint;

//	__#_
//	_##_
//	_#__

public class ZBrick extends Brick
{
	private Integer[][] rotationPosition =
		{
		{ 1,4,5,8 },
		{ 6,5,8,7 },
		{ 8,5,4,1 },
		{ 3,2,5,4 } };

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

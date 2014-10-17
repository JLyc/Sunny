package tetris.bricks;

import tetris.game.BrickPositionPoint;
import tetris.game.Rotation;


//____
//_##_
//_##_

public class OBrick extends Brick
{
	private Integer[][] rotationPosition =
		{
		{ 1,2,5,4 },
		{ 1,2,5,4 },
		{ 1,2,5,4 },
		{ 1,2,5,4 } };

		private int currentRotation = 0;

		private BrickPositionPoint zeroBrickPosition;

		@Override
		public void rotateBricks(Rotation rotationDir)
		{}

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

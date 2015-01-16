package Logarithm.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.GeneralPath;
import java.text.DecimalFormat;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

;

public class Graf extends JPanel implements MouseMotionListener
{
	private static final long serialVersionUID = 1L;

	private double x2Points[];
	private double y2Points[];
	private int mousePosition = 0;
	private boolean isScreenNoCentered = true;
	private static Graf grafInst = null;
	private String grafType = "ln";

	private Graphics2D g2 = null;

	private final static int CENTER_X = 500;
	private final static int CENTER_Y = 500;
	private final static int PIXEL_SIZE_COMPENZATION = 100;
	private final static float GRANULATION = 0.01f;

	private Graf()
	{
		this.addMouseMotionListener(this);
		this.setPreferredSize(new Dimension(5000, 5000));
		this.revalidate();
	}

	public static Graf getGrafInst()
	{
		if (grafInst == null)
			grafInst = new Graf();

		return grafInst;
	}

	public void setGrafType(String grafType)
	{
		this.grafType = grafType;
	}

	@Override
	public void paint(Graphics g)
	{
		super.paint(g);
		;
		g2 = (Graphics2D) g;

		int from = MainPanel.getMainPanelInst().getBeginPoint();
		int to = MainPanel.getMainPanelInst().getEndPoint();

		drawXYAxis();

		getFunctionArray(from, to, GRANULATION);

		drawGraphLine();
		paintLogPointRelevantToMousePosition();
		centerScreen();
	}

	private void centerScreen()
	{
		if (isScreenNoCentered)
		{
			JScrollPane scrolPane = MainPanel.getMainPanelInst().getScrolPane();
			scrolPane.getHorizontalScrollBar().setValue((int) (CENTER_X / 1.5));
			scrolPane.getVerticalScrollBar().setValue((int) (CENTER_Y / 1.5));
			isScreenNoCentered = false;
		}
	}

	private void drawXYAxis()
	{
		g2.drawLine(0, CENTER_Y, this.getWidth(), CENTER_Y);
		g2.drawLine(CENTER_X, 0, CENTER_X, this.getHeight());
		drawXValues();
		drawYValues();
		g2.translate(CENTER_X, CENTER_Y);
	}

	private void drawXValues()
	{
		int spacingGranulationDepend = (int) (GRANULATION * PIXEL_SIZE_COMPENZATION * 10);

		for (int Lyc = CENTER_X, index = 1; Lyc > 0; Lyc -= spacingGranulationDepend, index++)
		{
			if (Lyc == CENTER_X)
			{
				index++;
				continue;
			}

			g2.fillRect(Lyc - 1, CENTER_Y - 4, 2, 8);

			if (index % 5 == 0)
			{
				g2.drawString(new DecimalFormat("-##.##").format(index * GRANULATION * 10), (Lyc - 32), CENTER_Y + 20);
				g2.fillRect(Lyc - 21, CENTER_Y - 6, 2, 12);
			}
		}

		for (int Lyc = CENTER_X, index = 1; Lyc < this.getWidth(); Lyc += spacingGranulationDepend, index++)
		{
			if (Lyc == CENTER_X)
			{
				index++;
				continue;
			}

			g2.fillRect(Lyc - 1, CENTER_Y - 4, 2, 8);

			if (index % 5 == 0)
			{
				g2.drawString(new DecimalFormat("##.##").format(index * GRANULATION * 10), (Lyc + 12), CENTER_Y + 20);
				g2.fillRect(Lyc + 19, CENTER_Y - 6, 2, 12);
			}
		}
	}

	private void drawYValues()
	{
		int spacingGranulationDepend = (int) (GRANULATION * PIXEL_SIZE_COMPENZATION * 10);

		for (int Lyc = CENTER_Y, index = 1; Lyc > 0; Lyc -= spacingGranulationDepend, index++)
		{
			if (Lyc == CENTER_Y)
			{
				index++;
				continue;
			}

			g2.fillRect(CENTER_X - 4, (Lyc) - 1, 8, 2);

			if (index % 5 == 0)
			{
				g2.drawString(new DecimalFormat("##.##").format(index * GRANULATION * 10), CENTER_X - 30, (Lyc - 15));
				g2.fillRect(CENTER_X - 6, Lyc - 21, 12, 2);
			}
		}

		for (int Lyc = CENTER_Y, index = 1; Lyc < this.getWidth(); Lyc += spacingGranulationDepend, index++)
		{
			if (Lyc == CENTER_Y)
			{
				index++;
				continue;
			}

			g2.fillRect(CENTER_X - 4, (Lyc) - 1, 8, 2);

			if (index % 5 == 0)
			{
				g2.drawString(new DecimalFormat("-##.##").format(index * GRANULATION * 10), CENTER_X - 35, (Lyc + 25));
				g2.fillRect(CENTER_X - 6, Lyc + 19, 12, 2);
			}
		}
	}

	private void drawGraphLine()
	{
		g2.setColor(Color.RED);
		GeneralPath polyline = new GeneralPath(GeneralPath.WIND_EVEN_ODD, x2Points.length);
		polyline.moveTo(x2Points[0] * PIXEL_SIZE_COMPENZATION, y2Points[0] * PIXEL_SIZE_COMPENZATION);
		for (int index = 1; index < x2Points.length; index++)
		{
			polyline.lineTo(x2Points[index] * PIXEL_SIZE_COMPENZATION, y2Points[index] * PIXEL_SIZE_COMPENZATION);
		}
		;
		g2.draw(polyline);
	}

	public void getFunctionArray(int logFrom, int logTo, float granulation)
	{
		int lenghtOfGraph = 0;
		int leftBorder = -1;
		int rightBorder = +1;
		int first = 0;
		int second = 0;
		int from = Math.abs(logFrom);

		if (logFrom < 0)
		{
			if (logTo <= 0)
			{
				lenghtOfGraph = Math.abs((-logTo) - logFrom);
				rightBorder = -1;
				first = lenghtOfGraph;
			}
			else
			{
				lenghtOfGraph = Math.abs(logTo + (-logFrom));
				leftBorder = -1;
				first = Math.abs(logFrom) + 1;
				second = Math.abs(logTo) + 1;
				from = 1;
			}
		}
		else
		{
			lenghtOfGraph = Math.abs(logTo - logFrom);
			leftBorder = +1;
			first = lenghtOfGraph;
		}

		x2Points = new double[lenghtOfGraph];
		y2Points = new double[lenghtOfGraph];

		int interval = Math.abs(logTo);
		if (leftBorder == -1)
		{
			interval = first;
		}

		int index1 = 0;
		int index2 = 0;

		do
		{
			if (leftBorder == -1)
			{
				for (int i = interval; i > from; i = i + leftBorder)
				{

					x2Points[index1] = (i * granulation * leftBorder);
					if (index1 > 0 && i == 0)
					{
						x2Points[index1] = x2Points[index1 - 1];
					}
					index1++;
				};
			}
			else
			{

				for (int i = from; i < interval; i = i + leftBorder)
				{

					x2Points[index1] = (i * granulation * leftBorder);
					if (index1 > 0 && i == 0)
					{
						x2Points[index1] = x2Points[index1 - 1];
					}
					index1++;
				};
			};

			for (int i = from; i < interval; i++)
			{
				y2Points[index2] = grafFunction(x2Points[index2]);
				index2++;
			};

			leftBorder += 2;
			interval = second;
		}
		while (leftBorder < rightBorder + 2);
	}

	private double grafFunction(double forNumber)
	{
		double result = 0;
		if (grafType.equalsIgnoreCase("log"))
		{
			result = -(java.lang.Math.log(forNumber));
		}
		else if (grafType.equalsIgnoreCase("ln"))
		{
			result = -(java.lang.Math.exp(forNumber));
		}

		return result;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		mousePosition = e.getX() - CENTER_X;
		repaint();
	}

	private void paintLogPointRelevantToMousePosition()
	{
		int pointSize = 10;
		double positionX = mousePosition * GRANULATION;
		double positionY = grafFunction(positionX);
		MainPanel.getMainPanelInst().xValue.setText(Double.toString(positionX));
		MainPanel.getMainPanelInst().yValue.setText(Double.toString(-positionY));
		g2.setColor(Color.GREEN);
		g2.fillOval((int) (positionX * PIXEL_SIZE_COMPENZATION) - (pointSize / 2), (int) (positionY * PIXEL_SIZE_COMPENZATION - (pointSize / 2)), pointSize, pointSize);
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}
}

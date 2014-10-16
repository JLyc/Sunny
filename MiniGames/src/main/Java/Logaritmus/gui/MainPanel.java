package Logaritmus.gui;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class MainPanel extends Container
{
	private static final long serialVersionUID = 1L;

	private static MainPanel mainPanelInst = null;
	private JScrollPane scrolPane = null;
	public JTextField xValue = new JTextField();
	public JTextField yValue = new JTextField();
	private JRadioButton logFunc;
	private JRadioButton lnFunc;
	public JTextField beginPoint = new JTextField("0");
	public JTextField endPoint = new JTextField("500");

	private MainPanel()
	{
		scrolPane = new JScrollPane(Graf.getGrafInst());
		scrolPane.setLocation(0, 100);
		scrolPane.setSize(496, 473);
		add(controlPanel());
		add(scrolPane);
	}

	public static MainPanel getMainPanelInst()
	{
		if(mainPanelInst==null)
		{
			mainPanelInst = new MainPanel();
		}
		return mainPanelInst;
	}

	public JScrollPane getScrolPane()
	{
		return scrolPane;
	}

	public JPanel controlPanel()
	{
		JPanel controlPane = new JPanel();
		controlPane.setSize(496,100);
		controlPane.setLayout(null);
		JLabel xNumber = new JLabel("X: ");
		xNumber.setBounds(300,20, 30,20);
		JLabel yNumber = new JLabel("Y: ");
		yNumber.setBounds(300,50, 30,20);
		xValue.setBounds(320, 20, 150, 20);
		xValue.setEditable(false);
		yValue.setBounds(320, 50, 150, 20);
		yValue.setEditable(false);

		JLabel begin = new JLabel("from: ");
		begin.setBounds(10,50, 40,20);
		JLabel end = new JLabel("to: ");
		end.setBounds(140,50, 30,20);
		beginPoint.setBounds(40, 50, 100, 20);
		beginPoint.setText("-150");
		endPoint.setBounds(160, 50, 100, 20);
		endPoint.setText("150");

		ActionListener al = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{

				if(e.getSource().equals(logFunc))
				{
					Graf.getGrafInst().setGrafType("log");
				}
				else if(e.getSource().equals(lnFunc))
				{
					Graf.getGrafInst().setGrafType("ln");
				}

			}

		};

		logFunc = new JRadioButton("Logaritmus Function", false);
		logFunc.setBounds(10,20,145,20);
		logFunc.addActionListener(al);
		lnFunc = new JRadioButton("Exponencial Function", true);
		lnFunc.setBounds(150,20,150,20);
		lnFunc.addActionListener(al);
		ButtonGroup gb = new ButtonGroup();
		gb.add(logFunc);
		gb.add(lnFunc);

		controlPane.add(xNumber);
		controlPane.add(yNumber);
		controlPane.add(xValue);
		controlPane.add(yValue);
		controlPane.add(logFunc);
		controlPane.add(lnFunc);
		controlPane.add(begin);
		controlPane.add(end);
		controlPane.add(beginPoint);
		controlPane.add(endPoint);
		return controlPane;
	}

	public int getEndPoint()
	{
		return Integer.parseInt(endPoint.getText());
	}

	public int getBeginPoint()
	{
		return  Integer.parseInt(beginPoint.getText());
	}
}

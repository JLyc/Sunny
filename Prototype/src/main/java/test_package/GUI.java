package test_package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by JLyc on 1/4/2015.
 */
public class GUI implements ActionListener{
	private JButton button1;
	private JButton button2;
	private JButton button3;



	public void test()
	{
		button1 = new JButton("A");
		button1.addActionListener(this);
		button2 = new JButton("B");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals("button1"))
		{
			button2.setName("C");
		}
	}
}

package squash_pong.gui;

import java.awt.Container;

public class MaintPanel extends Container
{
	private static final long serialVersionUID = 1L;

	public MaintPanel()
	{
		add(Court.getInstance());
	}
}

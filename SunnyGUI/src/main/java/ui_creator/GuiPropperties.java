package ui_creator;

import java.awt.*;

/**
 * Created by socha on 9.10.2014.
 */
public interface GuiPropperties {

    public static final Dimension MAIN_FRAME_DIMENSION = new Dimension(350,350);
    public static final Point MAIN_FRAME_LOCATION = new Point((int) (GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth() - MAIN_FRAME_DIMENSION.getWidth()), 0);

}

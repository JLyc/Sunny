package penguins.shapes;

import java.awt.*;

/**
 * Created by socha on 17.10.2014.
 */
public class ShapeLong {

    private static final int OFFSET = 20;

    private Point fixed;
    private Point variable;


    public ShapeLong(Point fixed) {
        this.fixed = fixed;
        variable = new Point(fixed.x + positiveOffset(), fixed.y);
    }

    private void moveShape(Point fixed) {
        this.fixed = fixed;
        variable = new Point(fixed.x + positiveOffset(), fixed.y);
    }

    public void move(Point point) {
        moveShape(point);
    }

    private int positiveOffset() {
        return OFFSET;
    }

    private int negativeOffset() {
        return -1 * OFFSET;
    }


    public void paintShape(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillPolygon(new Long().getPolygon(fixed));
        g.fillPolygon(new LongShort().getPolygon(variable));
    }
}

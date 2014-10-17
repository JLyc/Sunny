package penguins.shapes;

import java.awt.*;

/**
 * Created by socha on 17.10.2014.
 */
public class LongShort {
    private static int OFFSET = 20;
    private int[] xPoint = new int[5];
    private int[] yPoint = new int[5];


    private int[] xTemplate = {0,0,-1,0,+1};
    private int[] yTemplate = {0,-1,0,+1,0};

    public Polygon getPolygon(Point p) {

        // startpoint
        xPoint[0] = p.x;
        yPoint[0] = p.y;

        int x = 0;
        int y = 0;
        int xOld = p.x;
        int yOld = p.y;

        for(int point : xTemplate)
        {
            xPoint[x]= xOld + (point*OFFSET);
            xOld = xPoint[x++];
        }

        for(int point : yTemplate)
        {
            yPoint[y]= yOld + (point*OFFSET);
            yOld= yPoint[y++];
        }
        return new Polygon(xPoint, yPoint, xPoint.length);
    }
}

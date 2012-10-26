/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 *
 * @author SG0217432
 */
public class GraphicTools {

    public static Point drawString(String msg, int x, int y, Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(msg, x, y + fontMetrics.getHeight());
        return new Point(fontMetrics.stringWidth(msg), fontMetrics.getHeight());
    }

    public static Point drawString(String msg, int x, int y, Graphics g, Font f) {
        g.setFont(f);
        return drawString(msg, x, y, g);
    }

    public static void drawExtendArrow(Point p1, Point p2, Graphics g) throws Exception {
        if(p2.y >= (p1.y+10)) throw new Exception("Base class should be higher.");

        int middle = (p1.y + p2.y)/2;

        g.drawLine(p1.x, p1.y, p1.x, middle);
        g.drawLine(p1.x, middle, p2.x, middle);
        g.drawLine(p2.x, middle, p2.x, p2.y + 10);

        //draw triangle
        g.drawLine(p2.x -6, p2.y + 10, p2.x+6, p2.y+10);
        g.drawLine(p2.x -6, p2.y + 10, p2.x, p2.y);
        g.drawLine(p2.x +6, p2.y + 10, p2.x, p2.y);
    }
}

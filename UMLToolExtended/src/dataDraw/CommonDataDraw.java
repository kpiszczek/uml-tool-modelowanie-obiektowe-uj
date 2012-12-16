/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataDraw;

import data.Position;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import tools.Point;

/**
 *
 * @author SG0217432
 */
public class CommonDataDraw {

    protected Graphics g;

    public CommonDataDraw(Graphics g) {
        this.g = g;
    }

    public void setFontNormal() {
        Font cf = g.getFont();
        Font f = new Font(cf.getFamily(), 0, cf.getSize());
        g.setFont(f);
    }

    public void setFontBold() {
        Font cf = g.getFont();
        Font f = new Font(cf.getFamily(), Font.BOLD, cf.getSize());
        g.setFont(f);
    }

    public void setFontItalic() {
        Font cf = g.getFont();
        Font f = new Font(cf.getFamily(), Font.ITALIC, cf.getSize());
        g.setFont(f);
    }

    public void setFontItalicAndBold() {
        Font cf = g.getFont();
        Font f = new Font(cf.getFamily(), Font.ITALIC | Font.BOLD, cf.getSize());
        g.setFont(f);
    }

    public void updatePosition(Position current, Position p) {
        current.setHeight(current.getHeight() + p.getHeight());
        if (p.getWidth() > current.getWidth()) {
            current.setWidth(p.getWidth());
        }
    }

    public void drawString(String msg, Position current) {
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(msg, current.getX() + Constants.MARGIN, current.getY() + fontMetrics.getHeight());

        Position p = new Position();
        p.setHeight(fontMetrics.getHeight());
        p.setWidth(fontMetrics.stringWidth(msg) + 2 * Constants.MARGIN);

        updatePosition(current, p);
    }

    protected Point getStringSize(String msg) {
        FontMetrics fontMetrics = g.getFontMetrics();
        return new Point(fontMetrics.stringWidth(msg) + 2 * Constants.MARGIN, fontMetrics.getHeight());
    }

    protected void drawString(String msg, Point p) {
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(msg, p.x + Constants.MARGIN, p.y + fontMetrics.getHeight());
    }

    protected void drawVerticalConnection(Point p1, Point p2) {
        int middle = (p1.x + p2.x) / 2;
        g.drawLine(p1.x, p1.y, middle, p1.y);
        g.drawLine(middle, p1.y, middle, p2.y);
        g.drawLine(middle, p2.y, p2.x, p2.y);
    }

    protected void drawHorizontalConnection(Point p1, Point p2) {
        int middle = (p1.y + p2.y) / 2;
        g.drawLine(p1.x, p1.y, p1.x, middle);
        g.drawLine(p1.x, middle, p2.x, middle);
        g.drawLine(p2.x, middle, p2.x, p2.y);
    }
}

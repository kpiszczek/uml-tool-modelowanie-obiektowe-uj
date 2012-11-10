/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataDraw;

import data.ClassEntity;
import data.DataVector;
import data.Entity;
import data.InterfaceEntity;
import data.Member;
import data.Method;
import data.Position;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import tools.Point;

/**
 *
 * @author SG0217432
 */
public class DataDraw {

    private Graphics g;

    public DataDraw(Graphics g) {
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

    void updatePosition(Position current, Position p) {
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

    private Point getStringSize(String msg) {
        FontMetrics fontMetrics = g.getFontMetrics();
        return new Point(fontMetrics.stringWidth(msg) + 2 * Constants.MARGIN, fontMetrics.getHeight());
    }

    private void drawString(String msg, Point p) {
        FontMetrics fontMetrics = g.getFontMetrics();
        g.drawString(msg, p.x + Constants.MARGIN, p.y + fontMetrics.getHeight());
    }

    public void drawEntity(Entity entity) {
        Position current = entity.getPosition();
        current.clear();

        Position margin = new Position();
        margin.setHeight(Constants.MARGIN);
        updatePosition(current, margin);

        drawEntityName(entity, current);

        updatePosition(current, margin);
        int height_after_name = current.getHeight();
        updatePosition(current, margin);

        for (Member member : entity.getMembers()) {
            drawMember(member, current);
        }

        updatePosition(current, margin);
        int height_after_members = current.getHeight();
        updatePosition(current, margin);

        for (Method method : entity.getMethods()) {
            drawMethod(method, current);
        }
        updatePosition(current, margin);

        g.drawRect(current.getX(), current.getY(), current.getWidth(), height_after_name);
        g.drawRect(current.getX(), current.getY(), current.getWidth(), height_after_members);
        g.drawRect(current.getX(), current.getY(), current.getWidth(), current.getHeight());

        if (entity == DataVector.getInstance().getActiveEntity()) {
            drawEnvelope(current);
        }
    }

    public void drawConnections(Entity entity) {
        if (entity instanceof ClassEntity) {
            ClassEntity classEntity = (ClassEntity) entity;
            if (!classEntity.getBaseClass().isEmpty()) {
                Entity baseClass = DataVector.getInstance().findEntity(classEntity.getBaseClass());
                if (null != baseClass) {
                    drawExtendConnection(entity, baseClass);
                }
            }
        }

        for (String interfaceName : entity.getInterfacesImplemented()) {
            Entity baseInterface = DataVector.getInstance().findEntity(interfaceName);
            if (null != baseInterface) {
                drawExtendConnection(entity, baseInterface);
            }
        }

        for (Member member : entity.getMembers()) {
            Entity typeEntity = DataVector.getInstance().findEntity(member.getType());
            if (null != typeEntity) {
                member.getPosition().setWidth(entity.getPosition().getWidth());
                drawMemberConnection(member, typeEntity);
            }

        }
    }

    private void drawEntityName(Entity entity, Position base) {
        if (entity instanceof ClassEntity) {
            setFontBold();
        }

        if (entity instanceof InterfaceEntity) {
            setFontItalicAndBold();
        }

        drawString(entity.getName(), base);
    }

    private void drawMember(Member member, Position base) {
        Position current = member.getPosition();
        current.clear();

        current.setX(base.getX());
        current.setY(base.getSecondY());

        if (member.isStatic()) {
            setFontItalic();
        } else {
            setFontNormal();
        }

        drawString(member.toString(), current);

        updatePosition(base, current);
    }

    private void drawMethod(Method method, Position base) {
        Position current = method.getPosition();
        current.clear();

        current.setX(base.getX());
        current.setY(base.getSecondY());

        if (method.isStatic()) {
            setFontItalic();
        } else {
            setFontNormal();
        }

        drawString(method.toString(), current);

        updatePosition(base, current);
    }

    private void drawExtendConnection(Entity derived, Entity base) {
        Position derivedPos = derived.getPosition();
        Position basePos = base.getPosition();

        if (basePos.isBelow(derivedPos)) {
            drawExtendHorizontalConnection(derivedPos.getMiddleDown(), basePos.getMiddleUp());
        } else if (derivedPos.isBelow(basePos)) {
            drawExtendHorizontalConnection(derivedPos.getMiddleUp(), basePos.getMiddleDown());
        } else if (derivedPos.isLeft(basePos)) {
            drawExtendVerticalConnection(derivedPos.getRightMiddle(), basePos.getLeftMiddle());
        } else if (derivedPos.isRight(basePos)) {
            drawExtendVerticalConnection(derivedPos.getLeftMiddle(), basePos.getRightMiddle());
        }
    }

    private void drawExtendHorizontalConnection(Point p1, Point p2) {
        int y_before_triangle = 0;
        if (p1.y > p2.y) {
            y_before_triangle = p2.y + Constants.TRIANGLE_H;
        } else {
            y_before_triangle = p2.y - Constants.TRIANGLE_H;
        }

        drawHorizontalConnection(p1, new Point(p2.x, y_before_triangle));

        //draw triangle
        g.drawLine(p2.x - Constants.TRIANGLE_HALF_A, y_before_triangle, p2.x + Constants.TRIANGLE_HALF_A, y_before_triangle);
        g.drawLine(p2.x - Constants.TRIANGLE_HALF_A, y_before_triangle, p2.x, p2.y);
        g.drawLine(p2.x + Constants.TRIANGLE_HALF_A, y_before_triangle, p2.x, p2.y);
    }

    private void drawExtendVerticalConnection(Point p1, Point p2) {
        int x_before_triangle = 0;
        if (p1.x > p2.x) {
            x_before_triangle = p2.x + Constants.TRIANGLE_H;
        } else {
            x_before_triangle = p2.x - Constants.TRIANGLE_H;
        }

        drawVerticalConnection(p1, new Point(x_before_triangle, p2.y));

        //draw triangle
        g.drawLine(x_before_triangle, p2.y - Constants.TRIANGLE_HALF_A, x_before_triangle, p2.y + Constants.TRIANGLE_HALF_A);
        g.drawLine(x_before_triangle, p2.y - Constants.TRIANGLE_HALF_A, p2.x, p2.y);
        g.drawLine(x_before_triangle, p2.y + Constants.TRIANGLE_HALF_A, p2.x, p2.y);
    }

    private void drawVerticalConnection(Point p1, Point p2) {
        int middle = (p1.x + p2.x) / 2;
        g.drawLine(p1.x, p1.y, middle, p1.y);
        g.drawLine(middle, p1.y, middle, p2.y);
        g.drawLine(middle, p2.y, p2.x, p2.y);
    }

    private void drawHorizontalConnection(Point p1, Point p2) {
        int middle = (p1.y + p2.y) / 2;
        g.drawLine(p1.x, p1.y, p1.x, middle);
        g.drawLine(p1.x, middle, p2.x, middle);
        g.drawLine(p2.x, middle, p2.x, p2.y);
    }

    private void drawMemberConnection(Member member, Entity typeEntity) {
        Position memberPos = member.getPosition();
        Position typeEntityPos = typeEntity.getPosition();

        Point p_rhomb = null;
        if (memberPos.getMiddleX() < typeEntityPos.getMiddleX()) {
            p_rhomb = drawRightRhomb(memberPos.getRightMiddle());
        } else {
            p_rhomb = drawLeftRhomb(memberPos.getLeftMiddle());
        }

        if (memberPos.isLeft(typeEntityPos)) {
            drawMemberVerticalConnection(p_rhomb, typeEntityPos.getLeftMiddle(), member.getMinMax());
        } else if (memberPos.isRight(typeEntityPos)) {
            drawMemberVerticalConnection(p_rhomb, typeEntityPos.getRightMiddle(), member.getMinMax());
        } else if (memberPos.isBelow(typeEntityPos)) {
            drawMemberHorizontalConnection(p_rhomb, typeEntityPos.getMiddleDown(), member.getMinMax());
        } else if (memberPos.isAbove(typeEntityPos)) {
            drawMemberHorizontalConnection(p_rhomb, typeEntityPos.getMiddleUp(), member.getMinMax());
        }
    }

    private Point drawRightRhomb(Point p) {
        g.drawLine(p.x, p.y, p.x + Constants.RHOMB_H1, p.y - Constants.RHOMB_H2);
        g.drawLine(p.x, p.y, p.x + Constants.RHOMB_H1, p.y + Constants.RHOMB_H2);
        g.drawLine(p.x + Constants.RHOMB_H1, p.y - Constants.RHOMB_H2, p.x + 2 * Constants.RHOMB_H1, p.y);
        g.drawLine(p.x + Constants.RHOMB_H1, p.y + Constants.RHOMB_H2, p.x + 2 * Constants.RHOMB_H1, p.y);

        return new Point(p.x + 2 * Constants.RHOMB_H1, p.y);
    }

    private Point drawLeftRhomb(Point p) {
        g.drawLine(p.x, p.y, p.x - Constants.RHOMB_H1, p.y - Constants.RHOMB_H2);
        g.drawLine(p.x, p.y, p.x - Constants.RHOMB_H1, p.y + Constants.RHOMB_H2);
        g.drawLine(p.x - Constants.RHOMB_H1, p.y - Constants.RHOMB_H2, p.x - 2 * Constants.RHOMB_H1, p.y);
        g.drawLine(p.x - Constants.RHOMB_H1, p.y + Constants.RHOMB_H2, p.x - 2 * Constants.RHOMB_H1, p.y);

        return new Point(p.x - 2 * Constants.RHOMB_H1, p.y);
    }

    private void drawMemberVerticalConnection(Point p1, Point p2, String minMax) {
        drawVerticalConnection(p1, p2);
        Point minMaxSize = getStringSize(minMax);

        if (p1.x < p2.x) {
            drawString(minMax, new Point(p2.x - minMaxSize.x, p2.y - minMaxSize.y - Constants.MARGIN));
        } else {
            drawString(minMax, new Point(p2.x, p2.y - minMaxSize.y - Constants.MARGIN));
        }
    }

    private void drawMemberHorizontalConnection(Point p1, Point p2, String minMax) {
        drawHorizontalConnection(p1, p2);
        Point minMaxSize = getStringSize(minMax);

        if (p1.y < p2.y) {
            drawString(minMax, new Point(p2.x, p2.y - minMaxSize.y - Constants.MARGIN));
        } else {
            drawString(minMax, new Point(p2.x, p2.y + Constants.MARGIN));
        }
    }

    private void drawEnvelope(Position current) {
        g.setColor(Color.red);

        g.drawRect(current.getFirstX() - Constants.MARGIN,
                current.getFirstY() - Constants.MARGIN,
                current.getWidth() + 2 * Constants.MARGIN,
                current.getHeight() + 2 * Constants.MARGIN);

        g.setColor(Color.black);
    }
}

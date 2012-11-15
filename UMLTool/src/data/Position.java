/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import tools.Point;
import java.io.Serializable;

/**
 *
 * @author SG0217432
 */
public class Position implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 5444432970693169125L;
	int x;
    int y;
    int width;
    int height;

    public Position() {
        x = 0;
        y = 0;
        width = 0;
        height = 0;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getFirstX() {
        return x;
    }

    public int getSecondX() {
        return x + width;
    }

    public int getMiddleX() {

        return (getFirstX() + getSecondX()) / 2;
    }

    public int getFirstY() {
        return y;
    }

    public int getSecondY() {
        return y + height;
    }

    public int getMiddleY() {
        return (getFirstY() + getSecondY()) / 2;
    }

    public Point getLeftUp() {
        return new Point(getFirstX(), getFirstY());
    }

    public Point getMiddleUp() {
        return new Point(getMiddleX(), getFirstY());
    }

    public Point getRightUp() {
        return new Point(getSecondX(), getFirstY());
    }

    public Point getLeftMiddle() {
        return new Point(getFirstX(), getMiddleY());
    }

    public Point getMiddleMiddle() {
        return new Point(getMiddleX(), getMiddleY());
    }

    public Point getRightMiddle() {
        return new Point(getSecondX(), getMiddleY());
    }

    public Point getLeftDown() {
        return new Point(getFirstX(), getSecondY());
    }

    public Point getMiddleDown() {
        return new Point(getMiddleX(), getSecondY());
    }

    public Point getRightDown() {
        return new Point(getSecondX(), getSecondY());
    }

    public void clear() {
        width = 0;
        height = 0;
    }

    public boolean isLeft(Position other) {
        return getSecondX() <= other.getFirstX();
    }

    public boolean isRight(Position other) {
        return getFirstX() >= other.getSecondX();
    }

    public boolean isBelow(Position other) {
        return getFirstY() >= other.getSecondY();
    }

    public boolean isAbove(Position other) {
        return getSecondY() <= other.getFirstY();
    }

    public double distance(Position other) {
        if (isBelow(other)) {
            if (isLeft(other)) {
                return getLeftUp().distance(other.getRightDown());
            }

            if (isRight(other)) {
                return getRightUp().distance(other.getLeftDown());
            }

            return (double) getFirstY() - other.getSecondY();
        }

        if (isAbove(other)) {
            if (isLeft(other)) {
                return getLeftDown().distance(other.getRightUp());
            }

            if (isRight(other)) {
                return getRightDown().distance(other.getLeftUp());
            }

            return (double) other.getFirstY() - getSecondY();
        }

        if (isLeft(other)) {
            return other.getFirstX() - getSecondX();
        }

        return getFirstX() - other.getSecondX();
    }

    public boolean isIn(int x, int y) {
        return (getFirstX() <= x) && (getSecondX() >= x) && (getFirstY() <= y) && (getSecondY() >= y);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tools;

/**
 *
 * @author SG0217432
 */
public class Point {

    public int x;
    public int y;

    public Point() {
        this.x = 0;
        this.y = 0;

    }

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double distance(Point other){
        return Math.sqrt((double)(x-other.x)*(x-other.x)+(y-other.y)*(y-other.y));
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dataDraw;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import data.Interface;
import data.Method;
import data.MethodArgument;
import data.Member;
import data.Class;
import tools.GraphicTools;
import tools.Point;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
/**
 *
 * @author student
 */
public class DataDraw{
    private Graphics g;
    private Point cursor = new Point();
    private Map<String,RectAnchors> rectDict
            = new HashMap<String,RectAnchors>();
    private class RectAnchors{
        public Point top = new Point(0,0);
        public Point right = new Point(0,0);
        public Point bottom = new Point(0,0);
        public Point left = new Point(0,0);
    }

    public DataDraw(Graphics g){
        this.g = g;
    }

    public void drawEntity(Interface _interface){
        // lista wsp. y na których mają być narysowane kreski
        List<Integer> linesList = new ArrayList<Integer>();
        int maxWidth = 0;
        Point pTmp = new Point();
        int iTmp = 0;
        this.cursor.x = _interface.getX();
        this.cursor.y = _interface.getY();
        this.g.setFont(new Font("monospaced",Font.BOLD,12));
        pTmp = GraphicTools.drawString(_interface.getName(), this.cursor.x,
                this.cursor.y, this.g);
        this.g.setFont(new Font("monospaced",Font.PLAIN,12));
        if (pTmp.x > maxWidth) {
            maxWidth = pTmp.x;
        }
        this.cursor.y += pTmp.y + 2;
        linesList.add(this.cursor.y);
        this.cursor.y += 2;
        iTmp = this.drawMembers(_interface);
        if (iTmp > maxWidth){
            maxWidth = iTmp;
        }
        linesList.add(this.cursor.y);
        this.cursor.y += 2;
        iTmp = this.drawMethods(_interface);
        if (iTmp > maxWidth){
            maxWidth = iTmp;
        }
        if (iTmp == 0){
            linesList.remove(linesList.size()-1);
        }
        for (int line : linesList){
            this.g.drawLine(this.cursor.x, line,this.cursor.x + maxWidth, line);
        }
        // prostąkąt
        this.g.drawRect(this.cursor.x - 1,_interface.getY(),
                maxWidth+1,this.cursor.y - _interface.getY());
        // punkty zaczepienia strzałek na ścianach prostokątu klasy
        // przyjujemy, że przyczepiamy strzałki do środka każdej ściany -
        // - czyli w ogólnym przypadku wyjdzie z tego przysłowiowe "g"
        // ale na razie ciiiiii... ;)
        RectAnchors a = new RectAnchors();
        a.top = new Point(this.cursor.x-1 + (maxWidth+1)/2,
                _interface.getY());
        a.right = new Point(this.cursor.x + maxWidth,
                _interface.getY()/2 + this.cursor.y/2);
        a.bottom = new Point(this.cursor.x-1 + (maxWidth+1)/2,
                this.cursor.y);
        a.left = new Point(this.cursor.x-1,
                _interface.getY()/2 + this.cursor.y/2);
        this.rectDict.put(_interface.getName(), a);
    }

    public void drawConnections(Interface _interface){
        RectAnchors a = this.rectDict.get(_interface.getName());
        if (_interface instanceof Class){
            Class _class = (Class)_interface;
            Class baseClass = _class.getBaseClass();
            if (baseClass != null){
                if (!baseClass.getName().equals("")){
                    RectAnchors baseA = this.rectDict.get(baseClass.getName());
                    try{
                        GraphicTools.drawExtendArrow(a.top, baseA.bottom, this.g);
                    } catch(Exception e) {
                        //do nothing
                    }
                }
            }
        }
        for (Member member : _interface.getMembers()){
            if (this.rectDict.containsKey(member.getType())){
                RectAnchors membersA = this.rectDict.get(member.getType());
                // w zależności od tego jak są ułożone klasy między sobą
                // rysujemy strzłkę albo od lewej do prawej albo od prawej do lewej
                if (membersA.left.x > a.right.x){
                    this.drawAssociationArrow(a.right, membersA.left,
                            member.getMinMax());
                } else {
                    this.drawAssociationArrow(a.left,membersA.right,
                            member.getMinMax());
                }
            }
        }
    }
    
    private int drawMembers(Interface _interface){
        int maxWidth = 0;
        Point tmp = new Point();
        for (Member member : _interface.getMembers()){
            String str = member.getVisibility() + member.getName()
                    + " : " + member.getType();
            tmp = GraphicTools.drawString(str,
                    this.cursor.x, this.cursor.y, this.g);
            if (tmp.x > maxWidth){
                maxWidth = tmp.x;
            }
            this.cursor.y += tmp.y + 2;
        }
        return maxWidth;
    }

    private int drawMethods(Interface _interface){
        int maxWidth = 0;
        Point tmp = new Point();
        for (Method method : _interface.getMethods()){
            String str = method.getVisibility() + method.getName() + "(";
            // bo iterator ma fajną funkcję 'hasNext'
            Iterator<MethodArgument> itr = method.getArguments().iterator();
            while (itr.hasNext()) {
                MethodArgument arg = itr.next();
                str += arg.getName() + " : " + arg.getType();
                if (itr.hasNext()){
                    // jeśli istnieje następny argument to musimy go poprzedzić przecinkiem
                    str += ", ";
                } 
            }
            str += ")";
            tmp = GraphicTools.drawString(str,
                    this.cursor.x, this.cursor.y, this.g);
            if (tmp.x > maxWidth){
                maxWidth = tmp.x;
            }
            this.cursor.y += tmp.y + 4;
        }
        return maxWidth;
    }
    public void drawAssociationArrow(Point p1, Point p2,String minMax) {
        int middle = (p1.x + p2.x)/2;
        int m = 10;
        int p = 35;
        if (p1.x > p2.x){
            m = -m;
            p = -5;
            minMax = new StringBuffer(minMax).reverse().toString();
        }
        // krecha między klasami
        this.g.drawLine(p1.x + 2*m, p1.y, middle, p1.y);
        this.g.drawLine(middle,p1.y,middle,p2.y);
        this.g.drawLine(middle, p2.y, p2.x, p2.y);

        GraphicTools.drawString(minMax, p2.x - p, p2.y - 20, this.g);

        // romb
        this.g.drawLine(p1.x, p1.y, p1.x+m, p1.y+6);
        this.g.drawLine(p1.x, p1.y, p1.x+m, p1.y-6);
        this.g.drawLine(p1.x+m, p1.y+6, p1.x+2*m, p1.y);
        this.g.drawLine(p1.x+m, p1.y-6, p1.x+2*m, p1.y);
    }
}

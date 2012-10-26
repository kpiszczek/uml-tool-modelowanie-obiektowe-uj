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
import tools.GraphicTools;
import tools.Point;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author student
 */
public class DataDraw{
    private Graphics g;
    private Point cursor = new Point();

    public DataDraw(Graphics g){
        this.g = g;
    }

    public void drawEntity(Interface _interface){
        List<Integer> linesList = new ArrayList<Integer>();
        int max = 0;
        Point pTmp = new Point();
        int iTmp = 0;
        this.cursor.x = _interface.getX();
        this.cursor.y = _interface.getY();
        this.g.setFont(new Font("monospaced",Font.BOLD,12));
        pTmp = GraphicTools.drawString(_interface.getName(), this.cursor.x,
                this.cursor.y, this.g);
        this.g.setFont(new Font("monospaced",Font.PLAIN,12));
        if (pTmp.x > max) {
            max = pTmp.x;
        }
        this.cursor.y += pTmp.y + 2;
        linesList.add(this.cursor.y);
        this.cursor.y += 2;
        iTmp = this.drawMembers(_interface);
        if (iTmp > max){
            max = iTmp;
        }
        linesList.add(this.cursor.y);
        this.cursor.y += 2;
        iTmp = this.drawMethods(_interface);
        if (iTmp > max){
            max = iTmp;
        }
        if (iTmp == 0){
            linesList.remove(linesList.size()-1);
        }
        for (int line : linesList){
            this.g.drawLine(this.cursor.x, line,this.cursor.x + max, line);
        }
        this.g.drawRect(this.cursor.x - 1,_interface.getY(),
                max+1,this.cursor.y - _interface.getY());
    }

    public void drawConnections(Interface _interface){
        
    }
    
    private int drawMembers(Interface _interface){
        int max = 0;
        Point tmp = new Point();
        for (Member member : _interface.getMembers()){
            String str = member.getVisibility() + member.getName()
                    + " : " + member.getType();
            tmp = GraphicTools.drawString(str,
                    this.cursor.x, this.cursor.y, this.g);
            if (tmp.x > max){
                max = tmp.x;
            }
            this.cursor.y += tmp.y + 2;
        }
        return max;
    }

    private int drawMethods(Interface _interface){
        int max = 0;
        Point tmp = new Point();
        for (Method method : _interface.getMethods()){
            String str = method.getVisibility() + method.getName()
                    + "(";
            Iterator<MethodArgument> itr = method.getArguments().iterator();
            while (true) {
                if (itr.hasNext()){
                    MethodArgument arg = itr.next();
                    str += arg.getName() + " : " + arg.getType();
                    if (itr.hasNext()){
                        str += ", ";
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
            str += ")";
            tmp = GraphicTools.drawString(str,
                    this.cursor.x, this.cursor.y, this.g);
            if (tmp.x > max){
                max = tmp.x;
            }
            this.cursor.y += tmp.y + 4;
        }
        return max;
    }
}

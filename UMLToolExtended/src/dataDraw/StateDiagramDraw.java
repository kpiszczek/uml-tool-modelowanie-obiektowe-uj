/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dataDraw;

import java.awt.Graphics;

import tools.Point;

import data.Position;
import data.classDiagram.ClassEntity;
import data.stateDiagram.State;
import data.stateDiagram.Transition;

/**
 *
 * @author SG0217432
 */
public class StateDiagramDraw extends CommonDataDraw {

    public StateDiagramDraw(Graphics g) {
        super(g);
    }

    public void drawStateDiagram(ClassEntity entity) {
        for(State state : entity.getStateDiagram().getStates())
        {
            drawState(state);
        }

        for(Transition transition : entity.getStateDiagram().getTransitions())
        {
            drawTransition(transition);
        }
    }

    private void drawState(State state) {
        Position current = state.getPosition();
        current.clear();

        Position margin = new Position();
        margin.setHeight(Constants.MARGIN);
        updatePosition(current, margin);

        drawString(state.getName(), current);
        
        updatePosition(current, margin);

        g.drawRoundRect(current.getX()-10, current.getY()-10, current.getWidth()+20, current.getHeight()+20,20,20);
    }

    private void drawTransition(Transition transition) {
    	State source = transition.getSource();
    	State dest = transition.getDestination();
    	Position start = source.getPosition();
    	Position end = dest.getPosition();
    	
    	if (start.isBelow(end)){
    		drawHorizontalTransition(start.getMiddleUp(),end.getMiddleDown(),transition);
    		return;
    	} else if (end.isBelow(start)){
    		drawHorizontalTransition(start.getMiddleDown(),end.getMiddleUp(),transition);
    		return;
    	} else if (end.isLeft(start)){    		
    		drawVerticalTransition(start.getLeftMiddle(),end.getRightMiddle(),transition);
    		return;
    	} else if (end.isRight(start)){
    		drawVerticalTransition(start.getRightMiddle(),end.getLeftMiddle(),transition);
    		return;
    	}   	
    }
    
    private void drawVerticalTransition(Point start, Point end,Transition t){
    	if (start.x < end.x){
	    	start.x += 10;
	    	end.x -= 10;
	    	this.g.drawLine(end.x-5, end.y+5, end.x, end.y);
	    	this.g.drawLine(end.x-5, end.y-5, end.x, end.y);
	    	Point p = new Point();
	    	p.x = (start.x + end.x)/2 - 30;
	    	p.y = (start.y + end.y)/2;
	    	this.drawString(t.toString(), p);
    	} else {
    		start.x -= 10;
    		end.x += 10;
	    	this.g.drawLine(end.x+5, end.y+5, end.x, end.y);
	    	this.g.drawLine(end.x+5, end.y-5, end.x, end.y);
	    	Point p = new Point();
	    	p.x = (start.x + end.x)/2 - 30;
	    	p.y = (start.y + end.y)/2 - 20;
	    	this.drawString(t.toString(), p);
    	}

    	this.drawVerticalConnection(start,end);
    }
    
    private void drawHorizontalTransition(Point start,Point end,Transition t){
    	if (start.y > end.y){
	    	start.y -= 10;
	    	end.y += 10;
	    	Point p = new Point();
	    	p.y = (start.y + end.y)/2 - 20;
	    	p.x = (start.x + end.x)/2 - 15;
	    	this.drawString(t.toString(), p);
	    	this.g.drawLine(end.x-5, end.y+5, end.x, end.y);
	    	this.g.drawLine(end.x+5, end.y+5, end.x, end.y);
    	} else {
    		start.y += 10;
    		end.y -= 10;
	    	this.g.drawLine(end.x-5, end.y-5, end.x, end.y);
	    	this.g.drawLine(end.x+5, end.y-5, end.x, end.y);
	    	Point p = new Point();
	    	p.y = (start.y + end.y)/2;
	    	p.x = (start.x + end.x)/2 - 15;
	    	this.drawString(t.toString(), p);
    	}   	

    	this.drawHorizontalConnection(start,end);
    }
}

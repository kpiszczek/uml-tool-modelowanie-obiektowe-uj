/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import dataDraw.StateDiagramDraw;
import data.DataVector;
import data.MoveableElement;
import data.classDiagram.Entity;
import data.Position;
import dataDraw.Constants;
import dataDraw.ClassDiagramDraw;
import java.awt.*;
import java.awt.event.*;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;

/**
 *
 * @author SG0217432
 */
public class WorkArea extends JPanel implements MouseMotionListener, Observer,
        MouseListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5215260191373859003L;
	/**
     * true if we are in drag
     */
    boolean inDrag = false;
    /**
     * starting location of a drag
     */
    int startX = -1, startY = -1;
    /**
     * starting location of a entity - starting location
     */
    int deltaX = -1, deltaY = -1;
    /**
     * current location of a drag
     */
    int curX = -1, curY = -1;

    WorkArea() {
        super();
        setPreferredSize(new Dimension(1024, 768));

        addMouseMotionListener(this);
        addMouseListener(this);

        // delete action
        Action deleteAction;
        deleteAction = new AbstractAction() {

            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public void actionPerformed(ActionEvent e) {
                Entity entity = DataVector.getInstance().getActiveEntity();
                if (entity != null) {
                    DataVector.getInstance().getEntityList().remove(entity);
                }

                repaint();
            }
        };

        this.getActionMap().put("deleteAction", deleteAction);
        this.getInputMap().put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "deleteAction");

        DataVector.getInstance().addObserver(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);

        if (null == DataVector.getInstance().getActiveStateDiagram()) {
            ClassDiagramDraw dataDraw = new ClassDiagramDraw(g);
            for (Entity entity : DataVector.getInstance().getEntityList()) {
                dataDraw.drawEntity(entity);
            }

            for (Entity entity : DataVector.getInstance().getEntityList()) {
                dataDraw.drawConnections(entity);
            }
        }
        else
        {
            StateDiagramDraw dataDraw = new StateDiagramDraw(g);
            dataDraw.drawStateDiagram(DataVector.getInstance().getActiveStateDiagram());
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Point p = e.getPoint();
        curX = p.x;
        curY = p.y;

        MoveableElement entity = DataVector.getInstance().getActiveElement();
        if (entity != null) {
            entity.getPosition().setX(curX - deltaX);
            entity.getPosition().setY(curY - deltaY);
        }

        if (inDrag) {
            repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Point p = e.getPoint();
        startX = p.x;
        startY = p.y;
        inDrag = true;

        DataVector.getInstance().setActiveElement(startX, startY);

        if (null != DataVector.getInstance().getActiveElement()) {
            Position pos = DataVector.getInstance().getActiveElement().getPosition();
            deltaX = startX - pos.getX();
            deltaY = startY - pos.getY();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Point p = e.getPoint();
        curX = p.x;
        curY = p.y;
        inDrag = false;

        MoveableElement entity = DataVector.getInstance().getActiveElement();
        if (entity != null) {
            entity.getPosition().setX(curX - deltaX);
            entity.getPosition().setY(curY - deltaY);
        }

        if (entity != null && DataVector.getInstance().countDistance(entity) < Constants.MIN_DISTANCE) {
            entity.getPosition().setX(startX - deltaX);
            entity.getPosition().setY(startY - deltaY);
        }

        repaint();
    }

    public void update(Observable o, Object arg) {
        repaint();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import data.DataVector;
import data.Interface;
import dataDraw.DataDraw;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author SG0217432
 */
public class WorkArea extends JPanel implements MouseMotionListener,
        MouseListener {

    WorkArea() {
        super();
        setPreferredSize(new Dimension(1024, 768));

        addMouseMotionListener(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        setBackground(Color.WHITE);
        DataDraw dataDraw = new DataDraw(g);
        for (Interface entity : DataVector.getInstance().getList()) {
            dataDraw.drawEntity(entity);
        }

        for (Interface entity : DataVector.getInstance().getList()) {
            dataDraw.drawConnections(entity);
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated method stub
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
    }
}

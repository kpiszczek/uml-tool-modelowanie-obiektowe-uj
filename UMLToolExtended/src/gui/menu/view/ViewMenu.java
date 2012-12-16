/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.view;

import data.classDiagram.ClassEntity;
import data.DataVector;
import data.classDiagram.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;

/**
 *
 * @author 
 */
public class ViewMenu extends JMenu implements ActionListener, Observer {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6924101681941304398L;
	ButtonGroup group;

    public ViewMenu() {
        super("View");
        group = new ButtonGroup();
        update();
        DataVector.getInstance().addObserver(this);
    }

    private void update() {
        removeAll();
        group = new ButtonGroup();

        JRadioButtonMenuItem rbMenuItem = new JRadioButtonMenuItem("Class Diagram");
        rbMenuItem.addActionListener(this);
        rbMenuItem.setActionCommand("ClassDiagram");
        group.add(rbMenuItem);
        add(rbMenuItem);

        if (null == DataVector.getInstance().getActiveStateDiagram()) {
            rbMenuItem.setSelected(true);
        }

        for (Entity entity : DataVector.getInstance().getEntityList()) {
            if (entity instanceof ClassEntity) {
                rbMenuItem = new JRadioButtonMenuItem("State Diagram - " + entity.getName());
                rbMenuItem.addActionListener(this);
                rbMenuItem.setActionCommand(entity.getName());
                group.add(rbMenuItem);
                add(rbMenuItem);

                if (null != DataVector.getInstance().getActiveStateDiagram()) {
                    Entity active = DataVector.getInstance().getActiveStateDiagram();
                    if(active.getName().equals(entity.getName()))
                    {
                        rbMenuItem.setSelected(true);
                    }
                }
            }
        }

    }

    public void actionPerformed(ActionEvent e) {
        DataVector.getInstance().setActiveStateDiagram(group.getSelection().getActionCommand());
    }

    public void update(Observable o, Object arg) {
        update();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.classDiagram;

import data.DataVector;
import data.classDiagram.Entity;
import data.classDiagram.InterfaceEntity;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author michau
 */
public class AddImplementationForm extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7642220322250528269L;
	private JComboBox  interfaceComboBox;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddImplementationForm() {
        super("Add implemented interface");

        boolean show = false;
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Interface:"));
        interfaceComboBox = new JComboBox();
        for (Entity e : DataVector.getInstance().getEntityList()){
            if (e instanceof InterfaceEntity){
                interfaceComboBox.addItem(e.getName());
                show = true;
            }
        }
        contentPane.add(interfaceComboBox);
        
        addButton = new JButton("Add");
        addButton.addActionListener(this);
        contentPane.add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        contentPane.add(cancelButton);
       
        if (show){
            setVisible(true);
            setSize(getPreferredSize());
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if(button == addButton){
            String interfaceEntityName = interfaceComboBox.getSelectedItem().toString();
            Entity activeEntity = DataVector.getInstance().getActiveEntity();
            
            if (activeEntity != null){
                activeEntity.addInterfaceImplemented(interfaceEntityName);
                DataVector.getInstance().refresh();
            }
        }

        setVisible(false);
    }
}


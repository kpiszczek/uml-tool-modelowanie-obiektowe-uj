/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import data.Entity;
import data.Method;
import data.DataVector;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
/**
 *
 * @author kuba
 */
public class DeleteMethodForm extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = -2184116085827385837L;
	private JComboBox methods;
    private JButton deleteButton;
    private JButton cancelButton;

    DeleteMethodForm(){
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Member"));
        methods = new JComboBox();
        Entity entity = DataVector.getInstance().getActiveEntity();
        if (entity != null){
            for (Method method: entity.getMethods()){
                methods.addItem(method.getName());
            }
        }
        contentPane.add(methods);

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(this);
        contentPane.add(deleteButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        contentPane.add(cancelButton);

        setVisible(true);
        setSize(getPreferredSize());
    }

    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();

        if(button == deleteButton){
            String n = this.methods.getSelectedItem().toString();

            if(!n.isEmpty())
            {
                Entity entity = DataVector.getInstance().getActiveEntity();
                if (entity != null){
                    Method m = entity.getMethod(n);
                    if (m!=null){
                       entity.getMethods().remove(m);
                        DataVector.getInstance().repaint();
                    }
                }
            }
        }
        setVisible(false);
    }
}

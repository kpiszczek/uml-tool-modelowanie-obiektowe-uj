/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import data.Entity;
import data.DataVector;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
/**
 *
 * @author kuba
 */
public class AddImplementedInterfaceForm extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3496654986744249080L;
	private JTextField impInterface;
    private JButton addButton;
    private JButton cancelButton;

    AddImplementedInterfaceForm(){
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Interface"));
        impInterface = new JTextField();
        contentPane.add(impInterface);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        contentPane.add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        contentPane.add(cancelButton);

        setVisible(true);
        setSize(getPreferredSize());
    }

    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();

        if(button == addButton){
            String n = this.impInterface.getText();

            if(!n.isEmpty())
            {
                Entity entity = DataVector.getInstance().getActiveEntity();
                if (entity != null){
                    entity.addInterfaceImplemented(n);
                    DataVector.getInstance().repaint();
                }
            }
        }
        setVisible(false);
    }

}

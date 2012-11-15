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
/**
 *
 * @author kuba
 */
public class DeleteEntityForm extends JFrame implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8680078388874930320L;
	private JButton yesButton;
    private JButton noButton;

    DeleteEntityForm(){
        super("Delete active class/interface");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Are you sure?"));
        contentPane.add(new JLabel(""));
        yesButton = new JButton("Yes");
        yesButton.addActionListener(this);
        contentPane.add(yesButton);
        noButton = new JButton("No");
        noButton.addActionListener(this);
        contentPane.add(noButton);

        setVisible(true);
        setSize(getPreferredSize());
    }
    public void actionPerformed(ActionEvent e){
        JButton button = (JButton) e.getSource();

        if(button == yesButton){
            Entity entity = DataVector.getInstance().getActiveEntity();
            if (entity != null){
                DataVector.getInstance().getEntityList().remove(entity);
                DataVector.getInstance().repaint();
            }
        }
        setVisible(false);
    }

}

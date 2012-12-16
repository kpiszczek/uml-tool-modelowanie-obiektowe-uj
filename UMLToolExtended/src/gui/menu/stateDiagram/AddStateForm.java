/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.stateDiagram;


import data.classDiagram.ClassEntity;
import data.stateDiagram.State;
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
 * @author SG0217432
 */
public class AddStateForm extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4663855026644330122L;
	private JTextField nameField;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddStateForm() {
        super("Add new state");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Class name "));
        nameField = new JTextField();
        contentPane.add(nameField);

        addButton = new JButton("Add");
        addButton.addActionListener(this);
        contentPane.add(addButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        contentPane.add(cancelButton);
       
        setVisible(true);
        setSize(getPreferredSize());
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if(button == addButton){
            String name = nameField.getText();

            if(!name.isEmpty())
            {
                State newState = new State();
                newState.setName(name);
                
                ClassEntity active = DataVector.getInstance().getActiveStateDiagram();
                active.getStateDiagram().addState(newState);
                
                DataVector.getInstance().refresh();
                
            }
        }

        setVisible(false);
    }
}

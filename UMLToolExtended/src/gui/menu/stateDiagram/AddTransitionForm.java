/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.stateDiagram;


import data.classDiagram.ClassEntity;
import data.classDiagram.Trigger;
import data.stateDiagram.State;
import data.stateDiagram.Transition;
import data.DataVector;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author SG0217432
 */
public class AddTransitionForm extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -4663855026644330122L;
	private JComboBox source;
	private JComboBox destination;
	private JComboBox trigger;
	private JTextField action;
	private JTextField msg;
	private JTextField condition;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddTransitionForm() {
        super("Add new transition");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));
        
        ClassEntity active = DataVector.getInstance().getActiveStateDiagram();
        
        
        source = new JComboBox();
        destination = new JComboBox();
        for (State s : active.getStateDiagram().getStates()){
            source.addItem(s.getName());
            destination.addItem(s.getName());
        }
        contentPane.add(new JLabel("Source state:"));
        contentPane.add(source);
        contentPane.add(new JLabel("Destination state:"));
        contentPane.add(destination);
        
        trigger = new JComboBox();
        for(Trigger t : active.getTriggers()){
        	trigger.addItem(t.getName());
        }
        contentPane.add(new JLabel("Trigger:"));
        contentPane.add(trigger);
        
        contentPane.add(new JLabel("Condition:"));
        condition = new JTextField();
        contentPane.add(condition);
        
        contentPane.add(new JLabel("Action:"));
        action = new JTextField();
        contentPane.add(action);
        
        contentPane.add(new JLabel("Message:"));
        msg = new JTextField();
        contentPane.add(msg);
        
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
        	Transition transition = new Transition();
        	
        	ClassEntity active = DataVector.getInstance().getActiveStateDiagram();
        	for (State s: active.getStateDiagram().getStates()){
        		if (source.getSelectedItem().toString() == s.getName()){
        			transition.setSource(s);
        		}
        		if (destination.getSelectedItem().toString() == s.getName()){
        			transition.setDestination(s);
        		}
        	}
        	for (Trigger t: active.getTriggers()){
        		if (trigger.getSelectedItem().toString() == t.getName()){
        			transition.setTrigger(t);
        			break;
        		}
        	}
        	transition.setCondition(condition.getText());
        	transition.setAction(action.getText());
        	transition.setMsgs(msg.getText());
        	active.getStateDiagram().addTransition(transition);
        	DataVector.getInstance().refresh();
        }

        setVisible(false);
    }
}
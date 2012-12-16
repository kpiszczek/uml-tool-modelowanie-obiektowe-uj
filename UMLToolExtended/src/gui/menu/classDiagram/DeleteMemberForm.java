/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.classDiagram;

import data.DataVector;
import data.classDiagram.Entity;
import data.classDiagram.Member;
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
public class DeleteMemberForm extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3196468948269290708L;
	private JComboBox  memberComboBox;
    private JButton    removeButton;
    private JButton    cancelButton;
    private Entity entity;

    public DeleteMemberForm() {
        super("Delete member");

        entity = DataVector.getInstance().getActiveEntity();
        if (entity == null){
            setVisible(false);
            return;
        }
        
        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));
        
        contentPane.add(new JLabel("Member:"));
        memberComboBox = new JComboBox();
        for (Member m : entity.getMembers()){
            memberComboBox.addItem(m.getName());
        }
        contentPane.add(memberComboBox);

        removeButton = new JButton("Remove");
        removeButton.addActionListener(this);
        contentPane.add(removeButton);

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        contentPane.add(cancelButton);
       
        setVisible(true);
        setSize(getPreferredSize());
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();

        if(button == removeButton){
            entity.removeMember(memberComboBox.getSelectedItem().toString());
            DataVector.getInstance().refresh();
        }

        setVisible(false);
    }
}

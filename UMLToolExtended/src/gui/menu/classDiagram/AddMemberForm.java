/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.classDiagram;

import data.DataVector;
import data.classDiagram.Member;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author michau
 */
public class AddMemberForm  extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 2783097325376509315L;
	private JTextField nameField;
    private JTextField defaultValueField;
    private JComboBox  visibilityComboBox;
    private JTextField typeField;
    private JTextField minMaxField;
    private JCheckBox isStaticCheckBox;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddMemberForm() {
        super("Add new member");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Name:"));
        nameField = new JTextField();
        contentPane.add(nameField);
        
        contentPane.add(new JLabel("Type:"));
        typeField = new JTextField();
        contentPane.add(typeField);
        
        contentPane.add(new JLabel("Default value:"));
        defaultValueField = new JTextField();
        contentPane.add(defaultValueField);
        
        contentPane.add(new JLabel("Visibility:"));
        visibilityComboBox = new JComboBox();
        visibilityComboBox.addItem("public");
        visibilityComboBox.addItem("private");
        visibilityComboBox.addItem("protected");
        contentPane.add(visibilityComboBox);
        
        contentPane.add(new JLabel("MinMax value:"));
        minMaxField = new JTextField();
        contentPane.add(minMaxField);
        
        contentPane.add(new JLabel("Static?"));
        isStaticCheckBox = new JCheckBox();
        contentPane.add(isStaticCheckBox);

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
                String unknown = "unknown";
                String type = typeField.getText();
                String defaultValue = defaultValueField.getText();
                String minMaxValue = minMaxField.getText();
                boolean isStatic = isStaticCheckBox.getModel().isSelected();
                String visibility = visibilityComboBox.getSelectedItem().toString();
                
                visibility = visibility.equals("public") ? "+" : visibility.equals("private") 
                        ? "-" : visibility.equals("protected") ? "#" : unknown;
                
                Member member = new Member();
                member.setName(name);
                member.setType(type != null ? type : unknown);
                member.setDefaultValue(defaultValue);
                member.setMinMax(minMaxValue);
                member.setStatic(isStatic);
                member.setVisibility(visibility);

                DataVector.getInstance().addMemberToActiveEntity(member);
            }
        }

        setVisible(false);
    }
}

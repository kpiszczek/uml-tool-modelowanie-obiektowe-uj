/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import data.Member;
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
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
/**
 *
 * @author kuba
 */
public class AddMemberForm  extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1516713949579135786L;
	private String[] vChoices = {"+","-","#"};
    private JTextField name;
    private JTextField type;
    private JTextField minMax;
    private JCheckBox isStatic;
    private JComboBox visibility;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddMemberForm() {
        super("Add new member");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Name"));
        name = new JTextField();
        contentPane.add(name);

        contentPane.add(new JLabel("Type"));
        type = new JTextField();
        contentPane.add(type);

        contentPane.add(new JLabel("Visibility"));
        visibility = new JComboBox();
        for (String item: this.vChoices){
            visibility.addItem(item);
        }
        contentPane.add(visibility);

        contentPane.add(new JLabel("Min-Max"));
        minMax = new JTextField();
        contentPane.add(minMax);

        contentPane.add(new JLabel("Static"));
        isStatic = new JCheckBox();
        contentPane.add(isStatic);

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
            String n = this.name.getText();

            if(!n.isEmpty())
            {
                Entity entity = DataVector.getInstance().getActiveEntity();
                if (entity != null){
                    entity.addMember(getMember());
                    DataVector.getInstance().repaint();
                }      
            }
        }
        setVisible(false);
    }
    private Member getMember(){
        Member m = new Member();
        m.setName(this.name.getText());
        m.setType(this.type.getText());
        if (!this.minMax.getText().isEmpty()){
            m.setMinMax(this.minMax.getText());
        }
        m.setVisibility(this.visibility.getSelectedItem().toString());
        m.setStatic(this.isStatic.isSelected());
        
        return m;
    }
}

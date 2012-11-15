/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui;

import data.Method;
import data.MethodArgument;
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
public class AddMethodForm  extends JFrame implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8429372071218441691L;
	private String[] vChoices = {"+","-","#"};
    private JTextField name;
    private JTextField returnType;
    private JCheckBox isStatic;
    private JComboBox visibility;
    private JTextField arguments;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddMethodForm() {
        super("Add new method");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Name"));
        name = new JTextField();
        contentPane.add(name);

        contentPane.add(new JLabel("Arguments"));
        arguments = new JTextField();
        contentPane.add(arguments);

        contentPane.add(new JLabel("Return type"));
        returnType = new JTextField();
        contentPane.add(returnType);

        contentPane.add(new JLabel("Visibility"));
        visibility = new JComboBox();
        for (String item: this.vChoices){
            visibility.addItem(item);
        }
        contentPane.add(visibility);

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
                    entity.addMethod(getMethod());
                    DataVector.getInstance().repaint();
                }
            }
        }
        setVisible(false);
    }
    private Method getMethod(){
        Method m = new Method();
        m.setName(this.name.getText());
        m.setReturnType(this.returnType.getText());
        m.setVisibility(this.visibility.getSelectedItem().toString());
        m.setStatic(this.isStatic.isSelected());
        String[] args = this.arguments.getText().split(",");
        for (String arg: args){
            arg = arg.replaceAll("\\s", "");
            String[] name_type = arg.split(":");
            MethodArgument a = new MethodArgument();
            a.setName(name_type[0]);
            a.setType(name_type[1]);
            m.addArgument(a);
        }
        return m;
    }
}

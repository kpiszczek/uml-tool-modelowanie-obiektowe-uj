/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import data.ClassEntity;
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
public class AddClassForm extends JFrame implements ActionListener {

    private JTextField nameField;
    private JTextField baseClassField;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddClassForm() {
        super("Add new class");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Class name "));
        nameField = new JTextField();
        contentPane.add(nameField);

        contentPane.add(new JLabel("Base class name "));
        baseClassField = new JTextField();
        contentPane.add(baseClassField);

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
            String baseClassName = baseClassField.getText();

            if(!name.isEmpty())
            {
                ClassEntity newClass = new ClassEntity();
                newClass.setName(name);
                newClass.setBaseClass(baseClassName);

                DataVector.getInstance().addNewEntity(newClass);
            }
        }

        setVisible(false);
    }
}

package gui;


import data.InterfaceEntity;
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
public class AddInterfaceForm extends JFrame implements ActionListener {

    private JTextField nameField;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddInterfaceForm() {
        super("Add new interface");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Interface name "));
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
                InterfaceEntity newInterface = new InterfaceEntity();
                newInterface.setName(name);

                DataVector.getInstance().addNewEntity(newInterface);
            }
        }

        setVisible(false);
    }
}

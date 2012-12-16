/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.classDiagram;

import data.DataVector;
import data.classDiagram.Method;
import data.classDiagram.MethodArgument;
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
public class AddMethodForm  extends JFrame implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -6873311677646378545L;
	private JTextField nameField;
    private JTextField typeField;
    private JTextField argumentsField;
    private JCheckBox isStaticCheckBox;
    private JComboBox visibilityComboBox;
    private JButton    addButton;
    private JButton    cancelButton;

    public AddMethodForm() {
        super("Add new method");

        Container contentPane = getContentPane();
        contentPane.setLayout(new GridLayout(0,2));

        contentPane.add(new JLabel("Name:"));
        nameField = new JTextField();
        contentPane.add(nameField);
        
        contentPane.add(new JLabel("Type:"));
        typeField = new JTextField();
        contentPane.add(typeField);
        
        contentPane.add(new JLabel("Arguments (separated with ;)"));
        argumentsField = new JTextField();
        contentPane.add(argumentsField);
        
        contentPane.add(new JLabel("Visibility:"));
        visibilityComboBox = new JComboBox();
        visibilityComboBox.addItem("public");
        visibilityComboBox.addItem("private");
        visibilityComboBox.addItem("protected");
        contentPane.add(visibilityComboBox);
        
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
                boolean isStatic = isStaticCheckBox.getModel().isSelected();
                String visibility = visibilityComboBox.getSelectedItem().toString();
                
                visibility = visibility.equals("public") ? "+" : visibility.equals("private") 
                        ? "-" : visibility.equals("protected") ? "#" : unknown;
                
                String[] arguments = argumentsField.getText().split(";");
                
                Method method = new Method();
                method.setName(name);
                method.setReturnType(type != null ? type : unknown);
                method.setStatic(isStatic);
                for (String arg : arguments){
                    String[] argArray = arg.split(":");
                    if (argArray.length < 2){
                        System.out.println("Invalid argument definition");
                        continue;
                    }
                    MethodArgument methodArgument = new MethodArgument();
                    String argName = argArray[0];
                    String argType;
                    String argDefaultValue;
                    if (argArray[1].contains("=")){
                        String[] argArray2 = argArray[1].split("=");
                        argType = argArray2[0];
                        argDefaultValue = argArray2[1];
                        methodArgument.setDefaultValue(argDefaultValue);
                    } else {
                        argType = argArray[1];
                    }
                    methodArgument.setName(argName);
                    methodArgument.setType(argType);
                    
                    method.addArgument(methodArgument);
                }
                
                DataVector.getInstance().addMethodToActiveEntity(method);
            }
        }

        setVisible(false);
    }
}

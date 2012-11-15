/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
/**
 *
 * @author kuba
 */
public class AddImplementedInterfaceItem extends JMenuItem implements ActionListener{
    /**
	 * 
	 */
	private static final long serialVersionUID = 3559851733180853604L;
	private AddImplementedInterfaceForm form;

    AddImplementedInterfaceItem(){
        super("Add implemented interface");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e){
        setForm(new AddImplementedInterfaceForm());
    }

	public AddImplementedInterfaceForm getForm() {
		return form;
	}

	public void setForm(AddImplementedInterfaceForm form) {
		this.form = form;
	}

}

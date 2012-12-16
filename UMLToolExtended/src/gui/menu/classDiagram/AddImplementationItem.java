/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.classDiagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author michau
 */
public class AddImplementationItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1177337276998265064L;
	private AddImplementationForm form;

    public AddImplementationItem() {
        super("Add implemented interface");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddImplementationForm());
    }

	public AddImplementationForm getForm() {
		return form;
	}

	public void setForm(AddImplementationForm form) {
		this.form = form;
	}
    
}

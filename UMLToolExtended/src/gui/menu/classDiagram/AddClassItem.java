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
 * @author SG0217432
 */
public class AddClassItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5228948782051574681L;
	private AddClassForm form;

    public AddClassItem() {
        super("Add new class");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddClassForm());
    }

	public AddClassForm getForm() {
		return form;
	}

	public void setForm(AddClassForm form) {
		this.form = form;
	}

}

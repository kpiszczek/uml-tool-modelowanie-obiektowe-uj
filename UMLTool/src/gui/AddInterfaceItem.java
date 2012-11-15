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
public class AddInterfaceItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1405706354489371579L;
	private AddInterfaceForm form;

    public AddInterfaceItem() {
        super("Add new interface");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddInterfaceForm());
    }

	public AddInterfaceForm getForm() {
		return form;
	}

	public void setForm(AddInterfaceForm form) {
		this.form = form;
	}

}

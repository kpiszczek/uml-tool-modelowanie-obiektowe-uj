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
public class AddInterfaceItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3362003407888728216L;
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

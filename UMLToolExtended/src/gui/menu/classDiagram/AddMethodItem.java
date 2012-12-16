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
public class AddMethodItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3340617036642603272L;
	private AddMethodForm form;

    public AddMethodItem() {
        super("Add new method");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddMethodForm());
    }

	public AddMethodForm getForm() {
		return form;
	}

	public void setForm(AddMethodForm form) {
		this.form = form;
	}

}

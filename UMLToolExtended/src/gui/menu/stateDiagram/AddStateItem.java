/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package gui.menu.stateDiagram;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;

/**
 *
 * @author SG0217432
 */
public class AddStateItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5228948782051574681L;
	private AddStateForm form;

    public AddStateItem() {
        super("Add new state");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddStateForm());
    }

	public AddStateForm getForm() {
		return form;
	}

	public void setForm(AddStateForm form) {
		this.form = form;
	}

}

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
public class AddTransitionItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5228948782051574681L;
	private AddTransitionForm form;

    public AddTransitionItem() {
        super("Add new transition");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddTransitionForm());
    }

	public AddTransitionForm getForm() {
		return form;
	}

	public void setForm(AddTransitionForm form) {
		this.form = form;
	}

}

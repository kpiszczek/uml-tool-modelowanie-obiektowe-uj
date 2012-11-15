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
public class DeleteMethodItem extends JMenuItem implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2660980664020783114L;
	private DeleteMethodForm form;

    public DeleteMethodItem() {
        super("Delete method");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new DeleteMethodForm());
    }

	public DeleteMethodForm getForm() {
		return form;
	}

	public void setForm(DeleteMethodForm form) {
		this.form = form;
	}
}
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
public class AddMemberItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2627810449440770801L;
	private AddMemberForm form;

    public AddMemberItem() {
        super("Add new member");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new AddMemberForm());
    }

	public AddMemberForm getForm() {
		return form;
	}

	public void setForm(AddMemberForm form) {
		this.form = form;
	}

}
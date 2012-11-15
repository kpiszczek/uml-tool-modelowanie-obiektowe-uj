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
public class DeleteMemberItem extends JMenuItem implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4975953237983970992L;
	private DeleteMemberForm form;

    public DeleteMemberItem() {
        super("Delete member");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        setForm(new DeleteMemberForm());
    }

	public DeleteMemberForm getForm() {
		return form;
	}

	public void setForm(DeleteMemberForm form) {
		this.form = form;
	}
}

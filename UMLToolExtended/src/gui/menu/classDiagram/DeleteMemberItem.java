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
public class DeleteMemberItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1823132276353501220L;
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

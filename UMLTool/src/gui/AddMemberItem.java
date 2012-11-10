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

    private AddMemberForm form;

    public AddMemberItem() {
        super("Add new member");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        form = new AddMemberForm();
    }

}
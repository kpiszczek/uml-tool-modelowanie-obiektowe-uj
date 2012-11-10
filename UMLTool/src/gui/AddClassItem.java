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
 * @author SG0217432
 */
public class AddClassItem extends JMenuItem implements ActionListener {

    private AddClassForm form;

    public AddClassItem() {
        super("Add new class");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        form = new AddClassForm();
    }
}

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
public class AddInterfaceItem extends JMenuItem implements ActionListener {

    private AddInterfaceForm form;

    public AddInterfaceItem() {
        super("Add new interface");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        form = new AddInterfaceForm();
    }

}

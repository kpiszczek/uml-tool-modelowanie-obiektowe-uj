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
public class AddMethodItem extends JMenuItem implements ActionListener {


    private AddMethodForm form;

    public AddMethodItem() {
        super("Add new method");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        form = new AddMethodForm();
    }

}

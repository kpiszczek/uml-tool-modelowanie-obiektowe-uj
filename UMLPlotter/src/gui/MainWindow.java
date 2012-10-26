/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

    WorkArea workArea = null;

    public MainWindow(String title) {
        super(title);
        setSize(new Dimension(1024, 768));
        addMenu();
        addActionListenerForMenuItems();

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        workArea = new WorkArea();
        contentPane.add(workArea);
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        JMenu actions = new JMenu("Actions");
        JMenu about = new JMenu("About");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(this);
        file.add(exitItem);

        actions.add(new JMenuItem("Add new class"));
        actions.add(new JMenuItem("Add new interface"));
        actions.add(new JMenuItem("Add new member"));
        actions.add(new JMenuItem("Add new method"));

        //add menus to menubar
        menuBar.add(file);
        menuBar.add(actions);
        menuBar.add(about);

        //menuBar.setVisible(true);
        setJMenuBar(menuBar);
    }

    private void addActionListenerForMenuItems() {
        JMenuBar menuBar = getJMenuBar();
        int menuBarSize = menuBar.getMenuCount();

        for (int i = 0; i < menuBarSize; ++i) {
            JMenu menu = menuBar.getMenu(i);
            int menuSize = menu.getMenuComponentCount();

            for (int j = 0; j < menuSize; ++j) {
                JMenuItem menuItem = menu.getItem(j);
                menuItem.addActionListener(this);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.*;
import javax.swing.*;

public class MainWindow extends JFrame {

    /**
	 * 
	 */
	private static final long serialVersionUID = 9177504429267757115L;
	WorkArea workArea = null;

    public MainWindow(String title) {
        super(title);
        setSize(new Dimension(1024, 768));
        addMenu();

        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        workArea = new WorkArea();
        contentPane.add(workArea);
    }

    private void addMenu() {
        JMenuBar menuBar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.add(new SaveDiagramItem());
        file.add(new OpenDiagramItem());
        
        JMenu actions = new JMenu("Actions");
        
        actions.add(new AddClassItem());
        actions.add(new AddInterfaceItem());
        actions.add(new AddMethodItem());
        actions.add(new AddMemberItem());
        actions.add(new AddImplementedInterfaceItem());
        actions.add(new DeleteEntityItem());
        actions.add(new DeleteMemberItem());
        actions.add(new DeleteMethodItem());

        //add menus to menubar
        menuBar.add(file);
        menuBar.add(actions);

        setJMenuBar(menuBar);
    }
}

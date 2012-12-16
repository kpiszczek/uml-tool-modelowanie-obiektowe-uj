/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import gui.menu.view.ViewMenu;
import gui.menu.file.ExportToTxtItem;
import gui.menu.file.ImportFromXmlItem;
import gui.menu.file.ExportToXmlItem;
import gui.menu.classDiagram.AddImplementationItem;
import gui.menu.classDiagram.DeleteMemberItem;
import gui.menu.classDiagram.DeleteMethodItem;
import gui.menu.classDiagram.AddInterfaceItem;
import gui.menu.classDiagram.AddMethodItem;
import gui.menu.classDiagram.AddClassItem;
import gui.menu.classDiagram.AddMemberItem;
import gui.menu.stateDiagram.*;
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
        JMenu actionsCD = new JMenu("Actions for Class Diagram");
        JMenu actionsSD = new JMenu("Actions for State Diagram");

        actionsCD.add(new AddClassItem());
        actionsCD.add(new AddInterfaceItem());
        actionsCD.add(new AddMethodItem());
        actionsCD.add(new AddMemberItem());
        actionsCD.add(new DeleteMemberItem());
        actionsCD.add(new DeleteMethodItem());
        actionsCD.add(new AddImplementationItem());
        
        actionsSD.add(new AddStateItem());
        actionsSD.add(new AddTransitionItem());

        file.add(new ImportFromXmlItem());
        file.add(new ExportToXmlItem());
        file.add(new ExportToTxtItem());
        
        //add menus to menubar
        menuBar.add(file);
        menuBar.add(new ViewMenu());
        menuBar.add(actionsCD);
        menuBar.add(actionsSD);

        setJMenuBar(menuBar);
    }
}

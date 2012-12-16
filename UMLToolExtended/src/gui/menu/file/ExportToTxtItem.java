/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.file;

import data.DataVector;
import data.classDiagram.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 *
 * @author michau
 */
public class ExportToTxtItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -7279071893397547345L;
	private final JFileChooser fc = new JFileChooser();

    public ExportToTxtItem() {
        super("Export to txt");
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showOpenDialog(this);
        BufferedWriter out = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            for (Entity entity : DataVector.getInstance().getEntityList()) {
                String strEntity = entity.getTxtForm();
                File fileEntity = new File(file.getAbsolutePath() + File.separator + entity.getName() + ".txt");
                System.out.println(strEntity);
                try {
                    fileEntity.createNewFile();
                    out = new BufferedWriter(new FileWriter(fileEntity));
                    out.write(strEntity);
                    out.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.file;

import com.thoughtworks.xstream.XStream;
import data.DataVector;
import data.classDiagram.Entity;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 *
 * @author Student
 */
public class ExportToXmlItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3132942883713684166L;
	private final JFileChooser fc = new JFileChooser();

    public ExportToXmlItem() {
        super("Export to XML");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showOpenDialog(this);
        PrintWriter pw = null;
        ObjectOutputStream out = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    FileWriter fw = new FileWriter(file);
                    pw = new PrintWriter(fw);
                    XStream stream = new XStream();
                    stream.autodetectAnnotations(true);
                    out = stream.createObjectOutputStream(pw, "data-vector");
                    for (Entity entity : DataVector.getInstance().getEntityList()) {
                        out.writeObject(entity);
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                } finally {
                    if (out != null){
                        try{
                            out.close();
                        } catch (IOException ex2){
                            
                        }
                    }
                }
            }
        } else {
        }
    }
}

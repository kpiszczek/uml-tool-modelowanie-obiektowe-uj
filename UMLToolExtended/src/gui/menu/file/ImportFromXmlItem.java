/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.menu.file;

import com.thoughtworks.xstream.XStream;
import data.classDiagram.ClassEntity;
import data.DataVector;
import data.classDiagram.Entity;
import data.classDiagram.InterfaceEntity;
import data.classDiagram.Member;
import data.classDiagram.Method;
import data.classDiagram.MethodArgument;
import data.Position;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;

/**
 *
 * @author Student
 */
public class ImportFromXmlItem extends JMenuItem implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3494260085880677831L;
	private final JFileChooser fc = new JFileChooser();

    public ImportFromXmlItem() {
        super("Import from XML");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        int returnVal = fc.showOpenDialog(this);
        FileReader reader = null;
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            if (!file.exists()) {
            } else {
                try {
                    XStream xstream = new XStream();
                    xstream.processAnnotations(new Class[]{Entity.class, Member.class, ClassEntity.class, Method.class, MethodArgument.class, Position.class, InterfaceEntity.class});
                    reader = new FileReader(file);
                    ObjectInputStream in = xstream.createObjectInputStream(reader);
                    DataVector.getInstance().clear();
                    while (true) {
                        try {
                            Entity entity = (Entity) in.readObject();
                            DataVector.getInstance().add(entity);
                        } catch (Exception ex) {
                            break;
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

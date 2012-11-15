package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

import com.google.gson.Gson;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;        			
import java.util.ArrayList;

import data.ClassEntity;
import data.DataVector;
import data.Entity;
import data.InterfaceEntity;
/**
 *
 * @author kuba
 */
public class SaveDiagramItem extends JMenuItem implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5300820842064429018L;
	private JFileChooser fileChooser;

    public SaveDiagramItem() {
        super("Save to file");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        fileChooser = new JFileChooser();
        fileChooser.setSelectedFile(new File("diagram.json"));
        int returnVal = fileChooser.showSaveDialog(SaveDiagramItem.this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
        	File file = fileChooser.getSelectedFile();
        	if(file.exists()){
        		try{
        			file.delete();
        		} catch(Exception err ){
        			System.out.println(err.toString());
        		}
        	}
        	try{
        		file.createNewFile();
       			file.setWritable(true);
       			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        			
       			ArrayList<Entity> entityList = DataVector.getInstance().getEntityList();
       	        ArrayList<ClassEntity> classList = new ArrayList<ClassEntity>();
       	        ArrayList<InterfaceEntity> interfaceList = new ArrayList<InterfaceEntity>();
       	        for (Entity entity : entityList){
       	        	if (entity instanceof ClassEntity){
        	       		classList.add((ClassEntity)entity);
        	       	} else if (entity instanceof InterfaceEntity){
        	       		interfaceList.add((InterfaceEntity)entity);
        	       	}
        	    }
        	    Gson gson = new Gson();
        	    String classJson = gson.toJson(classList);
        	    String interfaceJson = gson.toJson(interfaceList);
        	        
        	    writer.write(classJson);
        	    writer.newLine();
        	    writer.write(interfaceJson);
        	    writer.close();
        			
        	} catch(Exception err){
        		System.out.println(err.toString());
        	}
        }
    }
}
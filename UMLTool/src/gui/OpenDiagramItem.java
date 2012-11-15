
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader; 
import java.util.ArrayList;

import data.ClassEntity;
import data.DataVector;
import data.Entity;
import data.InterfaceEntity;
/**
 *
 * @author kuba
 */
public class OpenDiagramItem extends JMenuItem implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7239978295031552208L;
	/**
	 * 
	 */
	private JFileChooser fileChooser;

    public OpenDiagramItem() {
        super("Open file");
        addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        FileNameExtensionFilter filter = new FileNameExtensionFilter(
                "json files (*.json)","json");
        fileChooser = new JFileChooser();
        fileChooser.setFileFilter(filter);
        int returnVal = fileChooser.showOpenDialog(OpenDiagramItem.this);
        if (returnVal == JFileChooser.APPROVE_OPTION){
        	File file = fileChooser.getSelectedFile();
        	if(file.exists()){
                    try{
        		file.setReadable(true);
        		BufferedReader reader = new BufferedReader(new FileReader(file));
        		
        		Gson gson = new Gson();
        			
        	        Type classType = new TypeToken<ArrayList<ClassEntity>>(){}.getType();
        	        Type interfaceType = new TypeToken<ArrayList<InterfaceEntity>>(){}.getType();
        	        
        	        String classJson = reader.readLine();
        	        String interfaceJson = reader.readLine();
        	        reader.close();
        	        
        	        ArrayList<ClassEntity> newClassList = gson.fromJson(classJson, classType);      	        
        	        ArrayList<InterfaceEntity> newInterfaceList = gson.fromJson(interfaceJson,interfaceType);
        	        
        	        ArrayList<Entity> newEntityList = new ArrayList<Entity>();
        	        for (ClassEntity _class : newClassList){
        	        	newEntityList.add((Entity)_class);
        	        }
        	        for (InterfaceEntity _interface: newInterfaceList){
        	        	newEntityList.add((Entity)_interface);
        	        }
        	               
        	        DataVector.getInstance().setEntityList(newEntityList);
        	        DataVector.getInstance().repaint();
        			
        		} catch(Exception err){
        			System.out.println(err.toString());
        		}
        		
        	}
        }
    }
}
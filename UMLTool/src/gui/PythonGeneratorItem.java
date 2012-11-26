
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenuItem;
import javax.swing.JFileChooser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import data.ClassEntity;
import data.DataVector;
import data.Entity;
import data.InterfaceEntity;
import data.Member;
import data.Method;
import data.MethodArgument;
/**
 *
 * @author kuba
 */
public class PythonGeneratorItem extends JMenuItem implements ActionListener {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7239978295031552208L;
	/**and
	 * 
	 * 
	 */
	private JFileChooser dirChooser;
	private HashMap<String,String> typesMap = new HashMap<String,String>();
	
    public PythonGeneratorItem() {
        super("Generate Python code");
        addActionListener(this);
        typesMap.put("String","String");
        typesMap.put("Float","Number");
        typesMap.put("Integer", "Number");
        typesMap.put("void", "None");
        typesMap.put("List", "iterable");
        
    }
    private class Property{
    	public String setter;
    	public String getter;
    }
    public void actionPerformed(ActionEvent e) {
        dirChooser = new JFileChooser();
        dirChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int returnVal = dirChooser.showOpenDialog(PythonGeneratorItem.this);
        
        if (returnVal == JFileChooser.APPROVE_OPTION){
        	File selectedDir = dirChooser.getSelectedFile();
        	if(selectedDir.exists()){
        		//System.out.println(selectedDir.toString());
        		ArrayList<Entity> entityList = DataVector.getInstance().getEntityList();
        		ArrayList<InterfaceEntity> interfaceList = new ArrayList<InterfaceEntity>();
        		ArrayList<ClassEntity> classList = new ArrayList<ClassEntity>();
        		
        		for (Entity entity : entityList){
        			if (entity instanceof InterfaceEntity){
        				interfaceList.add((InterfaceEntity)entity);
        			}
        			else if (entity instanceof ClassEntity){
        				classList.add((ClassEntity)entity);
        			}
        		}
        		for (ClassEntity _class : classList){
        			String out = getClassString(_class);
        			File file = new File(selectedDir.toString() + "/" + _class.getName() + ".py");
        			if (file.exists()){
        				file.delete();
        			} 
        			try {
						file.createNewFile();
						file.setWritable(true);
						FileWriter writer = new FileWriter(file);
						writer.write(out);
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  			
        		}
        		for (InterfaceEntity _interface : interfaceList){
        			String out = getInterfaceString(_interface);
        			File file = new File(selectedDir.toString() + "/" + _interface.getName() + ".py");
        			if (file.exists()){
        				file.delete();
        			} 
        			try {
						file.createNewFile();
						file.setWritable(true);
						FileWriter writer = new FileWriter(file);
						writer.write(out);
						writer.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}  			
        		}
        	}
        }
    }
    
    private String getClassString(ClassEntity _class){
    	String tab = "    ";
    	String outString = "class " + _class.getName();
    	String superConst = "";
    	HashMap<String,Property> properties = new HashMap<String,Property>();
    	
    	if (_class.getBaseClass() != "" && _class.getBaseClass() != null){
    		outString += "(" + _class.getBaseClass() + ")";
    		superConst = tab + tab + "super(" + _class.getName() + ", self).__init__(*args,**kwargs)\n";
    	}
    	
    	outString += ":\n";
    	outString += tab + "def __init__(self,";
    	String inits = "";
    	
    	for (Member member : _class.getMembers()){
    		outString += member.getName() + ", ";
    		String mod = "";
    		if (member.getVisibility() == "-" || member.getVisibility() == "#"){
    			mod += "__";
    		}
    		inits += tab + tab + "self." + mod + member.getName() + " = " + member.getName() + "\n";
    	}
    	outString += "*args, **kwargs):\n";
    	
    	if (superConst != ""){
    		outString += superConst;
    	}
    	outString += inits + "\n";
    	for (Method method : _class.getMethods()){
    		String mod = "";
    		String name = method.getName();
    		String temp = "";
    		temp += name + "(self";	
    		if (method.getVisibility() == "-" || method.getVisibility() == "#"){
    			mod = "__";
    		}
    		if (method.isStatic()){
    			outString += tab + "@classmethod\n";
    		}
    		for (MethodArgument arg : method.getArguments()){
    			temp += ", " + arg.getName();
    			if (arg.getDefaultValue() != ""){
    				temp += "=" + arg.getDefaultValue();
    			}
    		}
    		temp += ")";
    		String type = "";
			if (this.typesMap.containsKey(method.getReturnType())){
				type = this.typesMap.get(method.getReturnType());
			} else {
				type = method.getReturnType();
			}
			
			String docString = temp + " -> " + type;
    		outString += tab + "def " + mod + temp + ":\n";
    		outString += tab + tab + "\"\"\"" + docString + "\"\"\"\n";
    		if (name.startsWith("set")){
    			if (properties.containsKey(name.substring(3,name.length()).toLowerCase())){
    				Property prop = properties.get(name.substring(3,name.length()).toLowerCase());
    				prop.setter = method.getName();
    			} else {
    				Property prop = new Property();
    				prop.setter = method.getName();
    				properties.put(name.substring(3,name.length()).toLowerCase(),prop);
    			}
    			outString += tab + tab + "this.__" + name.substring(3,name.length()).toLowerCase()
    					+ " = " + name.substring(3,name.length()).toLowerCase() + "\n";
    		} else if (name.startsWith("get")){
    			if (properties.containsKey(name.substring(3,name.length()).toLowerCase())){
    				Property prop = properties.get(name.substring(3,name.length()).toLowerCase());
    				prop.getter = method.getName();
    			} else {
    				Property prop = new Property();
    				prop.getter = method.getName();
    				properties.put(name.substring(3,name.length()).toLowerCase(),prop);
    			}
    			outString += tab + tab + "return this.__"
    					+ name.substring(3,name.length()).toLowerCase() + "\n";
    			
    		}else{
    			outString += tab + tab + "raise NotImplementedError\n";
    		}
    		outString += "\n";
    	}
    	for (String var : properties.keySet()){
    		Property prop = properties.get(var);
    		if (prop.setter != null && prop.getter != null){
    			outString += tab + var + " = property(" + prop.getter + ", " + prop.setter + ")\n\n";
    		}
    	}
    	for (String _interface : _class.getInterfacesImplemented()){
    		outString += _interface + ".register(" + _class.getName() + ")\n";
    	}
    	return outString;
    }
    
    private String getInterfaceString(InterfaceEntity _interface){
    	String outString = "from abc import ABCMeta, abstractmethod\n\n";
    	String tab = "    ";
    	outString += "class " + _interface.getName() + "(metaclass=ABCMeta):\n";
    	
    	for (Member member : _interface.getMembers()){
    		outString += tab + "@property\n" + tab + "@abstractmethod\n";
    		outString += tab + "def " + member.getName() + "(self):\n" + tab + tab + "pass\n\n";
    		outString += tab + "@" + member.getName() + ".setter\n" + tab + "@abstractmethod\n";
    		outString += tab + "def " + member.getName() + "(self,val):\n" + tab + tab + "pass\n\n";
    	}
    	for (Method method : _interface.getMethods()){
    		String name = method.getName();
    		
    		String temp = "";
    		temp += name + "(self";	
    		if (method.isStatic()){
    			outString += tab + "@classmethod\n";
    		}
    		for (MethodArgument arg : method.getArguments()){
    			temp += ", " + arg.getName();
    			if (arg.getDefaultValue() != ""){
    				temp += "=" + arg.getDefaultValue();
    			}
    		}
    		temp += ")";
    		String type = "";
			if (this.typesMap.containsKey(method.getReturnType())){
				type = this.typesMap.get(method.getReturnType());
			} else {
				type = method.getReturnType();
			}
			
			String docString = temp + " -> " + type;
			outString += tab + "@abstractmethod\n";
    		outString += tab + "def " + temp + ":\n";
    		outString += tab + tab + "\"\"\"" + docString + "\"\"\"\n";
    		outString += tab + tab + "pass\n\n";   		
    	}
    	
    	for (String inter : _interface.getInterfacesImplemented()){
    		outString += inter + ".register(" + _interface.getName() + ")\n";
    	}
    	return outString;
    }
}
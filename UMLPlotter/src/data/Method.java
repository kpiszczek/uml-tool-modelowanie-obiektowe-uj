/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Student
 */
public class Method {
    private List<MethodArgument> arguments = new ArrayList<MethodArgument>();
    private String name = "";
    private String returnType = "void";
    private String visibility = "+";
    private boolean isStatic = false;
    private boolean isFinal = false;

    public Method(){
        this.arguments = new ArrayList<MethodArgument>();
        this.name = "";
        this.returnType = "void";
        this.visibility = "+";
        this.isStatic = false;
        this.isFinal = false;
    }
    public Method(String name){
        this.name = name;
    }
    public Method(String name,String returnType){
        this.name = name;
        this.returnType = returnType;
    }
    public Method(ArrayList arguments,String name, String returnType, String visibility,
            boolean isStatic, boolean isFinal){
        this.name = name;
        this.arguments = arguments;
        this.returnType = returnType;
        this.visibility = visibility;
        this.isStatic =  isStatic;
        this.isFinal = isFinal;
    }
    /**
     * @return the arguments
     */
    public List<MethodArgument> getArguments() {
        return arguments;
    }

    /**
     * @param arguments the arguments to set
     */
    public void setArguments(ArrayList<MethodArgument> arguments) {
        this.arguments = arguments;
    }

    public void addArgument(MethodArgument arg){
        this.arguments.add(arg);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the returnType
     */
    public String getReturnType() {
        return returnType;
    }

    /**
     * @param returnType the returnType to set
     */
    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the isStatic
     */
    public boolean isIsStatic() {
        return isStatic;
    }

    /**
     * @param isStatic the isStatic to set
     */
    public void setIsStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    /**
     * @return the isFinal
     */
    public boolean isIsFinal() {
        return isFinal;
    }

    /**
     * @param isFinal the isFinal to set
     */
    public void setIsFinal(boolean isFinal) {
        this.isFinal = isFinal;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author Student
 */
public class MethodArgument {
    private String type = "";
    private String name = "";
    private String defaultValue = "";
    private boolean isFinal = false;

    public MethodArgument (){
        this.type = "";
        this.name = "";
        this.defaultValue = "";
        this.isFinal = false;
    }
    public MethodArgument(String type,String name){
        this.type = type;
        this.name = name;
    }
    public MethodArgument(String type, String name, String defaultValue,
            boolean isFinal){
        this.type = type;
        this.name = name;
        this.defaultValue = defaultValue;
        this.isFinal = isFinal;
    }
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
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
     * @return the defaultValue
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * @param defaultValue the defaultValue to set
     */
    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author Student
 */
public class Member {
    private String type = "";
    private String name = "";
    private String visibility = "";
    private boolean isStatic = false;
    private boolean isFinal = false;
    private String defaultValue = "";
    private String minMax = "";

    public Member(){
        this.type = "";
        this.name = "";
        this.visibility = "";
        this.isStatic = false;
        this.isFinal = false;
        this.defaultValue = "";
        this.minMax = "";
    }
    public Member(String type,String name){
        this.type = type;
        this.name = name;
        this.visibility = "+";
        this.isStatic = false;
        this.isFinal = false;
        this.defaultValue = "";
        this.minMax = "";
    }
    
    public Member(String type, String name, String visibility,
            boolean isStatic,  boolean isFinal, String defaultValue, String minMax){
        this.type = type;
        this.name = name;
        this.visibility = visibility;
        this.isStatic = isStatic;
        this.isFinal = isFinal;
        this.defaultValue = defaultValue;
        this.minMax = minMax;
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
     * @return the minMax
     */
    public String getMinMax() {
        return minMax;
    }

    /**
     * @param minMax the minMax to set
     */
    public void setMinMax(String minMax) {
        this.minMax = minMax;
    }
}

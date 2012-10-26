/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;

/**
 *
 * @author Student
 */
public class Class extends Interface {
    private boolean isAbstract = false;
    private Class baseClass = null;

    public Class(){
        this.isAbstract = false;
        this.baseClass = null;
    }
    public Class(String name){
        this.setName(name);
        this.isAbstract = false;
        this.baseClass = null;
    }

    public Class(String name,Class baseClass){
        this.setName(name);
        this.baseClass = baseClass;
    }
    /**
     * @return the isAbstract
     */
    public boolean isIsAbstract() {
        return isAbstract;
    }

    /**
     * @param isAbstract the isAbstract to set
     */
    public void setIsAbstract(boolean isAbstract) {
        this.isAbstract = isAbstract;
    }

    /**
     * @return the baseClass
     */
    public Class getBaseClass() {
        return baseClass;
    }

    /**
     * @param baseClass the baseClass to set
     */
    public void setBaseClass(Class baseClass) {
        this.baseClass = baseClass;
    }
}

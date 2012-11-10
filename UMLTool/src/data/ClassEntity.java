/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

public class ClassEntity extends Entity {

    private String baseClass;

    public ClassEntity() {
        super();
        baseClass = "";
    }

    public String getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(String baseClass) {
        this.baseClass = baseClass;
    }
}

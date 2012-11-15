/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.io.Serializable;

public class ClassEntity extends Entity implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -7269589836094851836L;
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

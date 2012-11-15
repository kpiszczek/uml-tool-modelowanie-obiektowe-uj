/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;
import java.io.Serializable;

/**
 *
 * @author
 */
public class MethodArgument implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 6986304411090851199L;
	private String name;
    private String type;
    private String defaultValue;

    public MethodArgument() {
        this.type = "";
        this.name = "";
        this.defaultValue = "";
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        String s = this.name + " : " + this.type;

        if (!this.defaultValue.isEmpty()) {
            s += " = " + this.defaultValue;
        }

        return s;
    }
}

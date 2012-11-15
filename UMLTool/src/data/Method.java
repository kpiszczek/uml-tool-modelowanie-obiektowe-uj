/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;

public class Method implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1355681863024823141L;
	private String name;
    private String returnType;
    private boolean isStatic;
    private String visibility;
    private List<MethodArgument> arguments;
    private Position position;

    public Method() {
        this.name = "";
        this.returnType = "void";
        this.isStatic = false;
        this.visibility = "+";
        this.arguments = new ArrayList<MethodArgument>();
        this.position = new Position();
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReturnType() {
        return returnType;
    }

    public void setReturnType(String returnType) {
        this.returnType = returnType;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public void addArgument(MethodArgument argument) {
        this.arguments.add(argument);
    }

    public List<MethodArgument> getArguments() {
        return arguments;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        String s = this.visibility + this.name;

        s += "(";
        if (!this.arguments.isEmpty()) {
            for (MethodArgument ma : this.arguments) {
                s += ma.toString() + ", ";
            }
            s = s.substring(0, s.lastIndexOf(", "));
        }
        s += ")";

        if (!this.returnType.equals("void") && !this.returnType.isEmpty()) {
            s += " : " + this.returnType;
        }

        return s;
    }

    public MethodArgument getLastArgument(){
        return arguments.get(arguments.size() - 1);
    }
}

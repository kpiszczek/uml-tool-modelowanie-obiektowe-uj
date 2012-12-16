/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import data.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XStreamAlias("method")
public class Method {

    private String name;
    private String returnType;
    private boolean isStatic;
    private String visibility;
    @XStreamImplicit
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
        return arguments != null ? arguments : Collections.<MethodArgument>emptyList();
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
        if (this.arguments != null && !this.arguments.isEmpty()) {
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

    public MethodArgument getLastArgument() {
        return arguments.get(arguments.size() - 1);
    }

    public String getTxtForm(boolean isSetterOrGetter) {
        StringBuilder buf = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        
        buf.append("\t");
        if (visibility.equals("+")){
            buf.append("public ");
        } else if (visibility.equals("-")){
            buf.append("private ");
        } else if (visibility.equals("#")){
            buf.append("protected ");
        }
        
        if (isStatic){
            buf.append("static ");
        }
        
        buf.append(!returnType.isEmpty() ? returnType : "void").append(" ");
        
        buf.append(name).append("(");
        
        for (int i = 0; i < getArguments().size() ; ++i){
            buf.append(getArguments().get(i).getTxtForm());
            if (i != getArguments().size()-1){
                buf.append(", ");
            }
        }
        
        buf.append(")");
        
        if (isSetterOrGetter){
            String memberName = name.substring(3).toLowerCase();
            buf.append("{");
            buf.append(newLine);
            buf.append("\t\t");
            if (name.startsWith("get")){
                buf.append("return ");
                buf.append(memberName).append(";");
            } else {
                buf.append("this.").append(memberName);
                buf.append(" = ").append(memberName).append(";");
            }
            buf.append(newLine).append("\t").append("}");
        }
        
        return buf.toString();
    }
}

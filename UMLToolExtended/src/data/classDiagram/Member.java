/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import data.Position;

@XStreamAlias("member")
public class Member {

    private String name;
    private String type;
    private String defaultValue;
    private boolean isStatic;
    private String visibility;
    private String minMax;
    private Position position;

    public Member() {
        this.name = "";
        this.type = "";
        this.defaultValue = "";
        this.isStatic = false;
        this.visibility = "+";
        this.minMax = "1";
        this.position = new Position();
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public void setStatic(boolean isStatic) {
        this.isStatic = isStatic;
    }

    public String getMinMax() {
        return minMax;
    }

    public void setMinMax(String minMax) {
        this.minMax = minMax;
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

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        String s = this.visibility + this.name + " : " + this.type;

        if (!this.minMax.equals("1")) {
            s += "[" + this.minMax + "]";
        }

        if (!this.defaultValue.isEmpty()) {
            s += " = " + this.defaultValue;
        }

        return s;
    }
    
    public String getTxtForm(){
        StringBuilder buf = new StringBuilder();
        
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
        
        buf.append(type).append(" ");
        
        buf.append(name);
        
        if (!defaultValue.isEmpty()){
            buf.append(" = ").append(defaultValue);
        }
        
        buf.append(";").append(System.getProperty("line.separator"));
        
        return buf.toString();
    }
}

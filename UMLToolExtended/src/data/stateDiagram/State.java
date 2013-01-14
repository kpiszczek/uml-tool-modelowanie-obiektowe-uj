/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stateDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import data.MoveableElement;
import data.Position;

/**
 *
 * @author SG0217432
 */
@XStreamAlias("state")
public class State implements MoveableElement {
    private String name;
    private Position position;

    public State() {
        name = "";
        position = new Position();
    }

    public String getName() {
        return name.replaceAll("\\s", "");
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return this.position;
    }

    public void setPosition(Position position) {
        this.position = position;
    } 
    
    public String getTxtForm(){
    	StringBuilder buf = new StringBuilder();
        @SuppressWarnings("unused")
		final String newLine = System.getProperty("line.separator");  
    	return buf.toString();
    }
}

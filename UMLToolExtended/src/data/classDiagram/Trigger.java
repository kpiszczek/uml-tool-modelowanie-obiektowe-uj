/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data.classDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import data.Position;

/**
 *
 * @author SG0217432
 */
@XStreamAlias("trigger")
public class Trigger {
    private String name;
    private String visibility;
    private Position position;

    public Trigger() {
        position = new Position();
        visibility = "";
        name = "";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    public String toString(){
        return this.visibility + this.name + "()";
    }
}

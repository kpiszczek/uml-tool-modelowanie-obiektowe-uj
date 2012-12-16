/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stateDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import data.classDiagram.Trigger;

/**
 *
 * @author SG0217432
 */

@XStreamAlias("transition")
public class Transition {

    private Trigger trigger;
    private String condition;
    private String action;
    private String msgs;
    private State source;
    private State destination;

    public Transition() {
        trigger = null;
        condition = "";
        action = "";
        msgs = "";
        source = null;
        destination = null;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getMsgs() {
        return msgs;
    }

    public void setMsgs(String msgs) {
        this.msgs = msgs;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public void setTrigger(Trigger trigger) {
        this.trigger = trigger;
    }

    public State getDestination() {
        return destination;
    }

    public void setDestination(State destination) {
        this.destination = destination;
    }

    public State getSource() {
        return source;
    }

    public void setSource(State source) {
        this.source = source;
    }

    @Override
    public String toString() {
        String returnStr = "";
        if (trigger != null) {
            returnStr += trigger.getName();
        }

        if (!condition.isEmpty()) {
            returnStr += "[" + condition + "]";
        }

        if (!action.isEmpty()) {
            returnStr += "/" + action;
        }

        if (!msgs.isEmpty()) {
            returnStr += "^" + msgs;
        }

        return returnStr;
    }
}

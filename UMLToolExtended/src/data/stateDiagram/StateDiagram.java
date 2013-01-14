/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stateDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import data.MoveableElement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SG0217432
 */
@XStreamAlias("state-diagram")
public class StateDiagram {

    private State enterState;
    @XStreamImplicit
    private List<State> states;
    @XStreamImplicit
    private List<Transition> transitions;
    private State activeState;
    private String className;

    public StateDiagram(String className) {
        enterState = null;
        states = new ArrayList<State>();
        transitions = new ArrayList<Transition>();
        activeState = null;
        this.className = className;
    }

    public void addState(State state) {
        states.add(state);
    }

    public void addTransition(Transition transition) {
        transitions.add(transition);
    }

    public List<State> getStates() {
        return states;
    }

    public List<Transition> getTransitions() {
        return transitions;
    }

    public State getEnterState() {
        return enterState;
    }

    public void setEnterState(State enterState) {
        this.enterState = enterState;
    }

    public MoveableElement getActiveState() {
        return activeState;
    }
    
    public void setClassName(String className){
    	this.className = className;
    }

    public void setActiveState(int x, int y) {
        for (State state : states) {
            if (state.getPosition().isIn(x, y)) {
                activeState = state;
            }
        }
    }
    
    public String getTxtForm(){
    	StringBuilder buf = new StringBuilder();
        final String newLine = System.getProperty("line.separator");
               
        buf.append("public abstract class StateBase{").append(newLine).append(newLine);
        for (Transition t: this.transitions){
        	buf.append("\tpublic abstract void ");
        	buf.append(t.getTrigger().getName().toLowerCase());	
        	buf.append("(").append(this.className).append(" ");
        	buf.append(this.className.toLowerCase().toCharArray()[0]);
        	buf.append(")").append("{}").append(newLine);
        }
        buf.append("}").append(newLine);
        
        for (State s : this.getStates()){
        	Character var = this.className.toLowerCase().toCharArray()[0];
        	buf.append(newLine);
            buf.append("public class ").append(s.getName()).append(" extends StateBase{");
            buf.append(newLine);
            for (Transition t: this.transitions){           	
            	if	(t.getSource() == s){
            		buf.append("\tpublic override void ").append(t.getTrigger().getName().toLowerCase());
            		buf.append("(").append(this.className).append(" ");
            		buf.append(var);
            		buf.append("){").append(newLine);
            		
            		if (t instanceof ConditionalTransition){
            			ConditionalTransition ct = (ConditionalTransition)t;
            			buf.append("\t\tif (").append(var).append(".").append(t.getCondition());
            			buf.append("(){").append(newLine);
            			buf.append("\t\t\t").append(var).append(".State = new ");
            			buf.append(ct.getDestination().getName()).append("();").append(newLine);
            			buf.append("\t\t} else {").append(newLine);
            			buf.append("\t\t\t").append(var).append(".State = new ");
            			buf.append(ct.getFailureDestination().getName()).append("();").append(newLine);
            			buf.append("\t\t}").append(newLine);
            		} else {
            			buf.append("\t\t").append(var).append(".State = new ");
            			buf.append(t.getDestination().getName()).append("();").append(newLine);
            		}
            		buf.append("\t}").append(newLine);
            	}
            }
            buf.append("}").append(newLine);
        }
    	return buf.toString();
    }

	
}

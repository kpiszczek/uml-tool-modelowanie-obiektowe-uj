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

    public StateDiagram() {
        enterState = null;
        states = new ArrayList<State>();
        transitions = new ArrayList<Transition>();
        activeState = null;
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

    public void setActiveState(int x, int y) {
        for (State state : states) {
            if (state.getPosition().isIn(x, y)) {
                activeState = state;
            }
        }
    }
}

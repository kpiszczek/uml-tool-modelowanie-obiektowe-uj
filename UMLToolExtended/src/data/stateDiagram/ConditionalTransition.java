/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.stateDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 *
 * @author SG0217432
 */
@XStreamAlias("conditional-transition")
public class ConditionalTransition extends Transition {

    private State failureDestination;
    private String expression;
    private String trueExpression;
    private String falseExpression;

    public ConditionalTransition() {
        failureDestination = null;
        expression = "";
        trueExpression = "";
        falseExpression = "";
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    public State getFailureDestination() {
        return failureDestination;
    }

    public void setFailureDestination(State failureDestination) {
        this.failureDestination = failureDestination;
    }

    public String getFalseExpression() {
        return falseExpression;
    }

    public void setFalseExpression(String falseExpression) {
        this.falseExpression = falseExpression;
    }

    public String getTrueExpression() {
        return trueExpression;
    }

    public void setTrueExpression(String trueExpression) {
        this.trueExpression = trueExpression;
    }
}

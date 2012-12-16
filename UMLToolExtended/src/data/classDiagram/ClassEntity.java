/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import data.stateDiagram.StateDiagram;
import java.util.ArrayList;
import java.util.List;

@XStreamAlias("class-entity")
public class ClassEntity extends Entity {

    private String baseClass;
    @XStreamImplicit
    private List<Trigger> triggers;
    private StateDiagram stateDiagram;

    public ClassEntity() {
        super();
        baseClass = "";
        stateDiagram = new StateDiagram();
        triggers = new ArrayList<Trigger>();
    }

    public String getBaseClass() {
        return baseClass;
    }

    public void setBaseClass(String baseClass) {
        this.baseClass = baseClass;
    }

    public StateDiagram getStateDiagram() {
        return stateDiagram;
    }

    public void setStateDiagram(StateDiagram stateDiagram) {
        this.stateDiagram = stateDiagram;
    }

    public List<Trigger> getTriggers() {
        return triggers;
    }

    public void addTrigger(Trigger trigger) {
        this.triggers.add(trigger);
    }

    public void removeTrigger(String name) {
        for (Trigger m : triggers) {
            int index = triggers.indexOf(m);
            if (m.getName().equals(name)) {
                triggers.remove(index);
                break;
            }
        }
    }

    @Override
    public String getTxtForm() {
        StringBuilder buf = new StringBuilder();
        final String newLine = System.getProperty("line.separator");

        buf.append(this.getClass().getPackage()).append(";").append(newLine).append(newLine);

        buf.append("public class ").append(getName()).append(" ");
        buf.append(!baseClass.isEmpty() ? "extends " + baseClass + " " : "");

        if (!getInterfacesImplemented().isEmpty()) {
            buf.append("implements ");
            for (int i = 0; i < getInterfacesImplemented().size(); ++i) {
                buf.append(getInterfacesImplemented().get(i));
                if (i != getInterfacesImplemented().size() - 1) {
                    buf.append(", ");
                }
            }
        }

        buf.append("{").append(newLine).append(newLine);

        List<String> memberNames = new ArrayList<String>();
        for (Member m : getMembers()) {
            memberNames.add(m.getName());
            buf.append(m.getTxtForm());
        }

        buf.append(newLine);

        boolean isSetterOrGetter = false;
        for (Method m : getMethods()) {
            for (String memberName : memberNames) {
                if (m.getName().toLowerCase().contains(memberName.toLowerCase())
                        && (m.getName().startsWith("get") || m.getName().startsWith("set"))) {
                    isSetterOrGetter = true;
                }
            }
            buf.append(m.getTxtForm(isSetterOrGetter));
            if (!isSetterOrGetter) {
                buf.append("{}");
            }
            isSetterOrGetter = false;
            buf.append(newLine);
        }

        buf.append("}");

        return buf.toString();
    }
}

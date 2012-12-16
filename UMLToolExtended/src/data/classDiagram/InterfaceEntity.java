/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data.classDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("interface-entity")
public class InterfaceEntity extends Entity {

    @Override
    public String getTxtForm() {
        StringBuilder buf = new StringBuilder();
        final String newLine = System.getProperty("line.separator");

        buf.append(this.getClass().getPackage()).append(";").append(newLine).append(newLine);

        buf.append("public interface ").append(getName()).append(" ");

        if (!getInterfacesImplemented().isEmpty()) {
            buf.append("extends ");
            for (int i = 0; i < getInterfacesImplemented().size(); ++i) {
                buf.append(getInterfacesImplemented().get(i));
                if (i != getInterfacesImplemented().size()-1) {
                    buf.append(", ");
                }
            }
        }

        buf.append("{").append(newLine).append(newLine);

        for (Member m : getMembers()) {
            buf.append(m.getTxtForm());
        }
        
        buf.append(newLine);

        for (Method m : getMethods()) {
            buf.append(m.getTxtForm(false));
            buf.append(";");
            buf.append(newLine);
        }

        buf.append("}");

        return buf.toString();
    }
  
}

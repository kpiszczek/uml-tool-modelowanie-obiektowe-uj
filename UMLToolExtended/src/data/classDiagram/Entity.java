/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data.classDiagram;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import data.MoveableElement;
import data.Position;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XStreamAlias("entity")
public abstract class Entity implements MoveableElement {

    private String name;
    @XStreamImplicit
    private List<Member> members;
    @XStreamImplicit
    private List<Method> methods;
    @XStreamImplicit
    private List<String> interfacesImplemented;
    private Position position;

    public Entity() {
        this.name = "";
        this.members = new ArrayList<Member>();
        this.methods = new ArrayList<Method>();
        this.interfacesImplemented = new ArrayList<String>();
        this.position = new Position();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Member> getMembers() {
        return members != null ? members : Collections.<Member>emptyList();
    }

    public List<Method> getMethods() {
        return methods != null ? methods : Collections.<Method>emptyList();
    }

    public List<String> getInterfacesImplemented() {
        return interfacesImplemented != null ? interfacesImplemented : Collections.<String>emptyList();
    }

    public void addMember(Member newMember) {
        this.members.add(newMember);
    }

    public void addMethod(Method newMethod) {
        this.methods.add(newMethod);
    }

    public void addInterfaceImplemented(String newInterface) {
        this.interfacesImplemented.add(newInterface);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public void removeMember(String name) {
        for (Member m : members) {
            int index = members.indexOf(m);
            if (m.getName().equals(name)) {
                members.remove(index);
                break;
            }
        }
    }

    public void removeMethod(String name) {
        for (Method m : methods) {
            int index = methods.indexOf(m);
            if (m.getName().equals(name)) {
                methods.remove(index);
                break;
            }
        }
    }

    public Member getLastMember() {
        return members.get(members.size() - 1);
    }

    public Method getLastMethod() {
        return methods.get(methods.size() - 1);
    }

    @Override
    public String toString() {
        return name;
    }

    public abstract String getTxtForm();
}

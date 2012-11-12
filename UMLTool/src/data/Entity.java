/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    private String name;
    private List<Member> members;
    private List<Method> methods;
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
    
    public Member getMember(String name){
        for (Member member : members){
            if(member.getName() == null ? name == null : member.getName().equals(name)){
                return member;
            }
        }
        return null;
    }

    public List<Member> getMembers() {
        return members;
    }

    public Method getMethod(String name){
        for (Method method : methods){
            if(method.getName() == null ? name == null : method.getName().equals(name)){
                return method;
            }
        }
        return null;
    }
    public List<Method> getMethods() {
        return methods;
    }

    public List<String> getInterfacesImplemented() {
        return interfacesImplemented;
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

    @Override
    public String toString() {
        return name;
    }

    public Member getLastMember() {
        return members.get(members.size() - 1);
    }

    public Method getLastMethod() {
        return methods.get(methods.size() - 1);
    }
}

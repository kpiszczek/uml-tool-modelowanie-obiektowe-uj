/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Student
 */
public class Interface {
     private List<Member> members = new ArrayList<Member>();
     private List<Method> methods = new ArrayList<Method>();
     private String name = "";
     private String _package = "";
     private String visibility = "";
     private List<Interface> implementedInterfaces = new ArrayList<Interface>();
     private int x = 0;
     private int y = 0;

     public Interface(){
         this.members = new ArrayList<Member>();
         this.methods = new ArrayList<Method>();
         this.name = "";
         this._package = "";
         this.visibility = "";
         this.implementedInterfaces = new ArrayList<Interface>();
         this.x = 0;
         this.y = 0;
     }
     public Interface(String name){
         this.name = name;
     }
     public Interface(ArrayList members, ArrayList methods, String name,
             String _package, String visibility, ArrayList implementedInterfaces,int x, int y){
         this.members = members;
         this.methods = methods;
         this.name = name;
         this._package = _package;
         this.visibility = visibility;
         this.implementedInterfaces = implementedInterfaces;
         this.x = x;
         this.y = y;     
     }

    /**
     * @return the members
     */
    public List<Member> getMembers() {
        return members;
    }

    /**
     * @param members the members to set
     */
    public void setMembers(ArrayList<Member> members) {
        this.members = members;
    }
    public void addMember(Member member){
        this.members.add(member);
    }

    /**
     * @return the methods
     */
    public List<Method> getMethods() {
        return methods;
    }

    /**
     * @param methods the methods to set
     */
    public void setMethods(ArrayList<Method> methods) {
        this.methods = methods;
    }

    public void addMethod(Method method){
        this.methods.add(method);
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the pack
     */
    public String getPackage() {
        return _package;
    }

    /**
     * @param pack the pack to set
     */
    public void setPackage(String _package) {
        this._package = _package;
    }

    /**
     * @return the visibility
     */
    public String getVisibility() {
        return visibility;
    }

    /**
     * @param visibility the visibility to set
     */
    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    /**
     * @return the implemented_interfaces
     */
    public List<Interface> getImplementedInterfaces() {
        return implementedInterfaces;
    }

    /**
     * @param implemented_interfaces the implemented_interfaces to set
     */
    public void setImplementedInterfaces(ArrayList<Interface> implemented_interfaces) {
        this.implementedInterfaces = implemented_interfaces;
    }

    public void addImplementedInterface(Interface _interface){
        this.implementedInterfaces.add(_interface);
    }

    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package data;
import java.util.List;
import java.util.ArrayList;
/**
 *
 * @author Student
 */
public final class DataVector {
    private DataVector(){};
    private List<Interface> list = new ArrayList<Interface>();
    private static volatile DataVector instance = null;
    public static synchronized DataVector getInstance(){
        if (instance == null){
            instance = new DataVector();
            instance.list = new ArrayList<Interface>();
        }        
        return instance;
    }
    public void addInterface(Interface input){
        this.list.add(input);
    }

    public void add(Interface input){
        this.list.add(input);
    }
    /**
     * @return the list
     */
    public List<Interface> getList() {
        return list;
    }

    /**
     * @param list the list to set
     */
    public void setList(List<Interface> list) {
        this.list = list;
    }
    public static void main(){
        DataVector dv = DataVector.getInstance();
        //=========================
        Interface person = new Interface();
        person.setName("Person");

        Member member = new Member();
        member.setName("firstName");
        member.setType("String");
        person.addMember(member);

        member = new Member();
        member.setName("lastName");
        member.setType("String");
        person.addMember(member);

        dv.addInterface(person);
        //========================
        Class professor = new Class();
        professor.setName("Professor");
        professor.addImplementedInterface(person);

        member = new Member();
        member.setName("salary");
        member.setType("Dollars");
        professor.addMember(member);

        dv.addInterface(professor);
        //=========================
        Class student = new Class();
        student.setName("Student");
        student.addImplementedInterface(person);

        member = new Member();
        member.setName("major");
        member.setType("String");
        student.addMember(member);

        dv.addInterface(student);
        //=========================
        Class flight = new Class();
        flight.setName("Flight");

        member = new Member();
        member.setName("flightNumber");
        member.setType("Integer");
        flight.addMember(member);

        member = new Member();
        member.setName("departureTime");
        member.setType("Date");
        flight.addMember(member);

        member = new Member();
        member.setName("flightDuration");
        member.setType("Minutes");
        member.setDefaultValue("60");
        flight.addMember(member);

        member = new Member();
        member.setName("passangers");
        member.setType("List<FrequentFlyer>");
        flight.addMember(member);
        
        Method method = new Method();
        method.setName("delayFlight");
        MethodArgument methodArgument = new MethodArgument();
        methodArgument.setName("numberOfMinutes");
        methodArgument.setType("Minutes");
        method.addArgument(methodArgument);
        flight.addMethod(method);

        method = new Method();
        method.setName("getArrivalTime");
        method.setReturnType("Date");
        flight.addMethod(method);
        
        dv.addInterface(flight);
        //=======================
        Class frequentFlyer = new Class();
        frequentFlyer.setName("FrequentFlyer");

        member = new Member();
        member.setName("firstName");
        member.setType("String");
        frequentFlyer.addMember(member);

        member = new Member();
        member.setName("lastName");
        member.setType("String");
        frequentFlyer.addMember(member);

        member = new Member();
        member.setName("frequentFlyerNumber");
        member.setType("String");
        frequentFlyer.addMember(member);

        member = new Member();
        member.setName("flights");
        member.setType("List<Flight>");
        frequentFlyer.addMember(member);
        
        dv.addInterface(frequentFlyer);
        //===============================
        Class mileageCredit = new Class();
        mileageCredit.setName("MileageCredit");

        member = new Member();
        member.setName("baseMiles");
        member.setType("Integer");
        mileageCredit.addMember(member);

        member = new Member();
        member.setName("bonusMiles");
        member.setType("Integer");
        mileageCredit.addMember(member);

        dv.addInterface(mileageCredit);
    }
}

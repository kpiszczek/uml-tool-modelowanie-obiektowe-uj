/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.classDiagram.ClassEntity;
import data.DataVector;
import data.classDiagram.InterfaceEntity;
import data.classDiagram.Member;
import data.classDiagram.Method;
import data.classDiagram.MethodArgument;
import data.classDiagram.Trigger;
import data.stateDiagram.ConditionalTransition;
import data.stateDiagram.State;
import data.stateDiagram.Transition;
import gui.MainWindow;
import javax.swing.JFrame;

/**
 *
 * @author SG0217432
 */
public class Main {

    public static void main(String[] args) {

        ClassEntity person = new ClassEntity();
        person.setName("Person");

        Member firstName = new Member();
        firstName.setName("firstName");
        firstName.setType("String");

        person.addMember(new Member());
        person.getLastMember().setName("firstName");
        person.getLastMember().setType("String");

        person.addMember(new Member());
        person.getLastMember().setName("lastName");
        person.getLastMember().setType("String");

        ClassEntity professor = new ClassEntity();
        professor.setName("Professor");
        professor.setBaseClass("Person");

        professor.addMember(new Member());
        professor.getLastMember().setName("Salary");
        professor.getLastMember().setType("Dollars");
        professor.getLastMember().setVisibility("-");

        professor.addMethod(new Method());
        professor.getLastMethod().setName("setSalary");

        professor.getLastMethod().addArgument(new MethodArgument());
        professor.getLastMethod().getLastArgument().setName("salary");
        professor.getLastMethod().getLastArgument().setType("Dollars");

        professor.addMethod(new Method());
        professor.getLastMethod().setName("getSalary");
        professor.getLastMethod().setReturnType("Dollars");

        ClassEntity student = new ClassEntity();
        student.setName("Student");
        student.setBaseClass("Person");
        student.addMember(new Member());
        student.getLastMember().setName("major");
        student.getLastMember().setType("String");
        student.addMethod(new Method());
        student.getLastMethod().setName("study");
        student.getLastMethod().setReturnType("Knowledge");

        ClassEntity book = new ClassEntity();
        book.setName("Book");

        book.addMember(new Member());
        book.getLastMember().setName("author");
        book.getLastMember().setType("String");
        book.addMember(new Member());
        book.getLastMember().setName("title");
        book.getLastMember().setType("String");

        State inPrintery = new State();
        inPrintery.setName("In Printery");
        inPrintery.getPosition().setX(100);
        inPrintery.getPosition().setY(100);

        State inStore = new State();
        inStore.setName("In Store");
        inStore.getPosition().setX(500);
        inStore.getPosition().setY(100);

        State inLibrary = new State();
        inLibrary.setName("In Library");
        inLibrary.getPosition().setX(500);
        inLibrary.getPosition().setY(500);

        State hasStudent = new State();
        hasStudent.setName("Has Student");
        hasStudent.getPosition().setX(100);
        hasStudent.getPosition().setY(500);

        book.getStateDiagram().addState(inPrintery);
        book.getStateDiagram().addState(inStore);
        book.getStateDiagram().addState(inLibrary);
        book.getStateDiagram().addState(hasStudent);

        Trigger print = new Trigger();
        print.setVisibility("+");
        print.setName("Print");

        Transition printTra = new Transition();
        printTra.setTrigger(print);
        printTra.setSource(inPrintery);
        printTra.setDestination(inStore);

        Trigger sell = new Trigger();
        sell.setVisibility("+");
        sell.setName("Sell");

        Transition sellTra = new Transition();
        sellTra.setTrigger(sell);
        sellTra.setSource(inStore);
        sellTra.setDestination(inLibrary);

        Trigger borrow = new Trigger();
        borrow.setVisibility("+");
        borrow.setName("Borrow");

        ConditionalTransition borrowTra = new ConditionalTransition();
        borrowTra.setTrigger(borrow);
        borrowTra.setSource(inLibrary);
        borrowTra.setFailureDestination(inStore);
        borrowTra.setCondition("isNotBroken");
        borrowTra.setFalseExpression("false");
        borrowTra.setTrueExpression("true");
        borrowTra.setDestination(hasStudent);

        Trigger returnTr = new Trigger();
        returnTr.setName("Return");
        returnTr.setVisibility("+");

        Transition returnTra = new Transition();
        returnTra.setTrigger(returnTr);
        returnTra.setSource(hasStudent);
        returnTra.setDestination(inLibrary);

        book.addTrigger(print);
        book.addTrigger(sell);
        book.addTrigger(borrow);
        book.addTrigger(returnTr);

        book.getStateDiagram().addTransition(printTra);
        book.getStateDiagram().addTransition(sellTra);
        book.getStateDiagram().addTransition(borrowTra);
        book.getStateDiagram().addTransition(returnTra);

        Member books = new Member();
        books.setType("Book");
        books.setName("books");
        books.setVisibility("-");
        books.setMinMax("0..*");
        student.addMember(books);

        Member booksAtLeastOne = new Member();
        booksAtLeastOne.setName("books");
        booksAtLeastOne.setType("Book");
        booksAtLeastOne.setVisibility("-");
        booksAtLeastOne.setMinMax("1..*");
        professor.addMember(booksAtLeastOne);

        InterfaceEntity mammal = new InterfaceEntity();
        mammal.addMethod(new Method());
        mammal.getLastMethod().setName("breathe");
        mammal.addMethod(new Method());
        mammal.getLastMethod().setName("eat");
        MethodArgument food = new MethodArgument();
        food.setName("food");
        food.setType("Food");
        mammal.getLastMethod().addArgument(food);
        mammal.setName("IMammal");

        InterfaceEntity materialObject = new InterfaceEntity();
        materialObject.addMethod(new Method());
        materialObject.getLastMethod().setName("getTax");
        materialObject.getLastMethod().setReturnType("double");
        materialObject.addMember(new Member());
        materialObject.getLastMember().setName("tax");
        materialObject.getLastMember().setType("double");
        materialObject.getLastMember().setVisibility("-");
        materialObject.setName("IMaterialObject");

        person.addInterfaceImplemented("IMammal");
        book.addInterfaceImplemented("IMaterialObject");

        person.getPosition().setX(250);
        person.getPosition().setY(100);

        professor.getPosition().setX(100);
        professor.getPosition().setY(300);

        book.getPosition().setX(350);
        book.getPosition().setY(300);

        student.getPosition().setX(500);
        student.getPosition().setY(300);

        mammal.getPosition().setX(250);
        mammal.getPosition().setY(5);

        materialObject.getPosition().setX(400);
        materialObject.getPosition().setY(100);

        DataVector.getInstance().add(person);
        DataVector.getInstance().add(professor);
        DataVector.getInstance().add(student);
        DataVector.getInstance().add(book);
        DataVector.getInstance().add(mammal);
        DataVector.getInstance().add(materialObject);

        MainWindow mainWindow = new MainWindow("UML Tool");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

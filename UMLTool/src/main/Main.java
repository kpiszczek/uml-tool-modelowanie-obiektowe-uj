/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import data.ClassEntity;
import data.DataVector;
import data.Member;
import data.Method;
import data.MethodArgument;
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

        ClassEntity book = new ClassEntity();
        book.setName("Book");

        book.addMember(new Member());
        book.getLastMember().setName("author");
        book.getLastMember().setType("String");
        book.addMember(new Member());
        book.getLastMember().setName("title");
        book.getLastMember().setType("String");

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

        person.getPosition().setX(250);
        person.getPosition().setY(100);

        professor.getPosition().setX(100);
        professor.getPosition().setY(300);

        book.getPosition().setX(350);
        book.getPosition().setY(300);

        student.getPosition().setX(500);
        student.getPosition().setY(300);

        DataVector.getInstance().add(person);
        DataVector.getInstance().add(professor);
        DataVector.getInstance().add(student);
        DataVector.getInstance().add(book);

        MainWindow mainWindow = new MainWindow("UML Tool");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import data.DataVector;
import data.Interface;
import data.Class;
import dataDraw.DataDraw;
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

        data.Class person = new Class("Person");
        person.addMember(new Member("String","firstName"));
        person.addMember(new Member("String", "lastName"));

        data.Class professor = new data.Class("Professor", person);

        Member memberDollars = new Member("Dollars", "Salary");
        memberDollars.setVisibility("-");
        professor.addMember(memberDollars);

        Method setSalary = new Method("setSalary");
        setSalary.addArgument(new MethodArgument("Dollars", "salary"));
        professor.addMethod(setSalary);

        Method getSalary = new Method("getSalary", "Dollars");
        professor.addMethod(getSalary);

        data.Class student = new data.Class("Student", person);
        student.addMember(new Member("String", "major"));

        data.Class book = new data.Class("Book");
        book.addMember(new Member("String","author"));
        book.addMember(new Member("String", "title"));

        Member books = new Member("Book", "books");
        books.setVisibility("-");
        books.setMinMax("0..*");
        student.addMember(books);

        Member booksAtLeastOne = new Member("Book", "books");
        booksAtLeastOne.setVisibility("-");
        booksAtLeastOne.setMinMax("1..*");
        professor.addMember(booksAtLeastOne);

        person.setX(250);
        person.setY(100);

        professor.setX(100);
        professor.setY(300);

        book.setX(350);
        book.setY(300);

        student.setX(500);
        student.setY(300);

        DataVector.getInstance().add(person);
        DataVector.getInstance().add(professor);
        DataVector.getInstance().add(student);
        DataVector.getInstance().add(book);

        MainWindow mainWindow = new MainWindow("UML Tool");
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

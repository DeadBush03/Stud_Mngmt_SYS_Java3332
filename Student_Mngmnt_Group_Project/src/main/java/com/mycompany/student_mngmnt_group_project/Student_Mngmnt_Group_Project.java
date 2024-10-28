package com.mycompany.student_mngmnt_group_project;

/*
 * @authors
 * Caleb Westerman, 
 * Jonathan Embler
 * Jack
 */

//PROJECT REQUIREMENTS

/*
-----
This project is a Student Management System 
that should be able to handle various operations,
including adding students, tracking grades, 
calculating averages, and generating reports (highest/lowest grades),
and File input/output for loading/saving information respectively
-----
The system should display a menu with options for users to select operations 
like adding, removing, or viewing student details
-----
At LEAST three key classes:
- `Student` (attributes: ID, name, grades, etc.)
- `Subject` (attributes: name, grade, etc.)
- `StudentManagementSystem` (handling all system operations)

- Apply the principles of Encapsulation to ensure data hiding.
- Use Inheritance and Polymorphism where appropriate

REPORTS:
The system should generate reports such as
- Average grade for each student
- Highest and lowest grades for a specific subject
- A list of students sorted by grade

Remember to ensure error handling and input validation
*/

//scanner and file manipulation
import java.util.Scanner;
import java.io.*;

//Please remember to put your name before the class/method you're working on
//this is to prevent people from accidentally working on the same thing

public class StudentManagementSystem 
{//I did change the name of this class... "Student_Mngmnt_Group_Project" was the original in case it matters

    public static void main(String[] args) 
    {  
        System.out.println("This program takes information from the user about \nstudents, classes, and grades and outputs reports from user input.");
        System.out.println("STUDENT MANAGEMENT SYSTEM");
    }// end main
    
}// END OF CLASS

//Caleb --Change this as you see fit, it's very basic
class Student
{//this class holds information about a student
    //attributes: ID, name, grades, etc.)
    private String ID = "000000"; //id won't be manipulated, it's a string
    private String Name = "Nameless"; //default name for a student
    //grades may need to be an array, whose size is the number of 'Subject' classes...
    private double Grades;

     //accessors
    public String getName()
    {return Name;}
    public String getID()
    {return ID;}
    public double getGrade() //remember this may need to be an array
    {return Grades;}

    //mutators
    public void setName(String N)
    {Name = N;}
    public void setID(String stuId)
    {ID = stuId;}
    public void setGrade(double grade) //this may also need to be an array, in which case need for loop to copy values
    {Grades = grade; }
}

//Caleb --Change this as you see fit, it's very basic
class Subject
{//this class stores information on a subject like 'Math', 'English', etc
    //attributes are name, grade, etc.
    
    private String ClassName = "No Subject"; //no initial subject
    private String ClassID = "0000"; //no class id
    private double ClassGrade = -1.0; //nonsense initial value

    //accessors
    public String getName()
    {return ClassName;}
    public String getID()
    {return ClassID;}
    public double getGrade()
    {return ClassGrade;}

    //mutators
    public void setName(String nm)
    {ClassName = nm;}
    public void setID(String id)
    {ClassID = id;}
    public void setGrade(double gr)
    {ClassGrade = gr;}

}

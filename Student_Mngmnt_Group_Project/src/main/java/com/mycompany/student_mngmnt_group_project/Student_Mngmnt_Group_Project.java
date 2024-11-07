package com.mycompany.student_mngmnt_group_project;

/*
 * @authors
 * Caleb Westerman, 
 * Jonathan Embler,
 * Jack Hebert.
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
{

    public static void main(String[] args) 
    {  
                
        Scanner keyboard = new Scanner(System.in); //user input
        
        //----- Student and Subject classes for testing
        //trying to figure out how Student and Subject interact
        System.out.println("How many students do you have?");
        int SIZE = keyboard.nextInt(); //i have no validation because this is temporary
        
        Student classroom[]= new Student[SIZE]; //five students in array
        
        Student testStu = new Student();
        testStu.setName("Test Student");
        testStu.setID("69420");
        testStu.setGrade(100.00);
        
        //-----
        
        System.out.println("This program takes information from the user about "
                + "\nstudents, classes, and grades and outputs reports from user input.");
        System.out.println("STUDENT MANAGEMENT SYSTEM");

        int exit = -1; //exit when user says to
        do 
        {
            System.out.println("Enter a number to select from the menu.");
            System.out.println("0 - Exit Program\n1 - Alter Student Information\n"
                    + "2 - Access or Create File\n3 - Output Student Averages Report\n"
                    + "4 - Output Student Report\n5 - Output Subject Report");
            exit = keyboard.nextInt();
            
            //case for each possible input
            switch(exit)
            {
                case 0:
                    System.out.println("EXITING PROGRAM...");
                    
                    break;
                    
                case 1: //student information
                    System.out.println("You chose to Alter Student Information");
                    //call student manual input
                    
                    //Probably ask user if they want to add/remove a student (with validation in case the user tries anything impossible)
                    
                    classroom[0] = testStu; //test student as first value in Student array
                    for (int i=1; i<SIZE; i++)
                        {
                        classroom[i] = createStudent();
                        }
                    break;
                    
                case 2: //access file
                    System.out.println("You chose to Access or Create a File");
                    //may get user to choose whether to input or output file contents
                    //file input can be for one or multiple students, have input validation to make sure a file doesn't have nonsense
                    
                    //file output just puts current students into a file, such that it can be read later.
                    //make it so they can either create a file for i/o or use an existing file
                    break;
                    
                case 3: //Output AVERAGES
                    System.out.println("OUTPUTTING AVERAGE STUDENT GRADES...");
                    //output the average grades for each student
                    
                    //this depends heavily on how grades are stored
                    //does not necessarily need to be sorted though
                    break;
                    
                case 4: //student report sorted by grades
                    System.out.println("OUTPUTTING ALL STUDENT REPORTS...");
                    //just output existing student classes?
                    //every student sorted by grade... may have multiple instances of the same student for different subjects?
                    //need to sort this in the final edition. Sort by grade. <--------
                    for (int i=0; i<SIZE; i++)
                        {
                        reportStudent(classroom[i]);
                        }
                    
                    break;
                    
                case 5: //subject report
                    System.out.println("OUTPUTTING SUBJECT REPORT...");
                    //output existing Subject class
                    //Highest and Lowest grades for a specific subject
                    //have user pick from possible subjects
                    
                    //could all subjects be premade?
                    break;
                    
                default: //default is essentially for invalid input
                    System.out.println("INVALID INPUT. Please enter an integer from 0-5 (inclusive)");
            }// end switch statement
        }    
        while(!(exit == 0)); //repeat this until user enters '0'
        
        
    }// end main
    
    //Caleb -- Once again, very basic and should probably be replaced
    static Student createStudent()
    {//method that creates a basic student class
        System.out.println("This method makes students, including names, ID, and grades for classes.");
        Student madeStudent = new Student();
        Scanner keyboard = new Scanner(System.in);
        
    System.out.println("Please enter the student's name");
    String input = keyboard.nextLine();
    madeStudent.setName(input);
    
    System.out.println("Please enter the student's ID number"); //maybe check and make sure its all numbers?
    String inputID = keyboard.nextLine();
    madeStudent.setID(inputID);
    
    System.out.println("Please enter the student's grade"); //this part might need a complete rework depending on how grades are stored
    double inputGrade = keyboard.nextDouble();
    madeStudent.setGrade(inputGrade);
        
        return madeStudent;
    }//end createStudent
    
    static void reportStudent(Student someone)
    {//takes a student object and outputs it nicely
        System.out.println("STUDENT REPORT\n---------------");
        System.out.println("Name:" + someone.getName());
        System.out.println("ID:" + someone.getID());
        System.out.println("Grade(s):" + someone.getGrade()); //just assume this will need to change
        System.out.println("---------------");
    }
    
}// END OF CLASS

//Caleb --Change this as you see fit, it's very basic
//Student may be an aggregate of the 'Subject' class, because 'Subject' can have many students within it
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

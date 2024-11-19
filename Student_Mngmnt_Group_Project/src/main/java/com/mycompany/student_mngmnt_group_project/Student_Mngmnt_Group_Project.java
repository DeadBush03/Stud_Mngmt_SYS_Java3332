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
import java.util.ArrayList;

//Please remember to put your name before the class/method you're working on
//this is to prevent people from accidentally working on the same thing

public class StudentManagementSystem 
{

    public static void main(String[] args) 
    {  
                
        Scanner keyboard = new Scanner(System.in); //user input
	//variable to hold name of file to save to or import from
	    //can be used to allow user to enter name of file if we decide to do that
	String fileName;
	//int variable to use for situations in which user needs to enter an input outside of Main Menu. made so that we can use a variable that doesnt
	    //interfere with exit variable, etc.
	int tempInput;
        
        //----- Student and Subject classes for testing

        //subjects array
        final int numClasses = 4; //the fixed number of classes we have
        
        Subject ClassSubjects[] = new Subject[numClasses]; //number of subjects
        //basic MATH subject
        ClassSubjects[0] = new Subject();
        ClassSubjects[0].setID("1401");
        ClassSubjects[0].setName("MATH");
        //basic ENGL subject
        ClassSubjects[1] = new Subject();
        ClassSubjects[1].setID("1201");
        ClassSubjects[1].setName("ENGL");
        //basic COSC subject
        ClassSubjects[2] = new Subject();
        ClassSubjects[2].setID("2346");
        ClassSubjects[2].setName("COSC");
        //basic HIST subject
        ClassSubjects[3] = new Subject();
        ClassSubjects[3].setID("1302");
        ClassSubjects[3].setName("HIST");
        
        for(int i = 0; i < ClassSubjects.length; i++)
            {//make a 'default student' to prevent errors
            Student nobody = new Student();
            ClassSubjects[i].addStudent(nobody);
            }
        
        //----- end testing
        
        System.out.println("This program takes information from the user about "
                + "\nstudents, classes, and grades and outputs reports from user input.");
        System.out.println("STUDENT MANAGEMENT SYSTEM");

        int exit = -1; //exit when user says to
        do 
        {
            System.out.println("Enter a number to select from the menu.");
            System.out.println("0 - Exit Program\n" + 
				"1 - Alter Student Information\n" +
              			"2 - Access or Create File\n" + 
				"3 - Output Student Averages Report\n" +
            			"4 - Output Student Report\n"+
				"5 - Output Subject Report");
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
                    //add
                    System.out.println("What subject do you want to add one student to?");
                    for (int x = 0; x < ClassSubjects.length; x++)
                    {//present the possible choices of subject
                        System.out.println("Enter " + x + ": " + ClassSubjects[x].getName() );
                    }
                    //get the index directly from the user
                    int userInputSubject = keyboard.nextInt();
                    //validation
                    while (userInputSubject < 0 || userInputSubject > ClassSubjects.length)
                        {//if user input is invalid
                        System.out.println("Your input was invalid, re-enter your choice: ");
                        userInputSubject = keyboard.nextInt();
                        } //keep re-taking input until they are within a valid range
                    
                    
                    System.out.println("For the Subject: " + ClassSubjects[userInputSubject].getName());
                    ClassSubjects[userInputSubject].addStudent(createStudent());
                    
                    break;
                    
                case 2: //outputting to a file or importing from a file
                    try{
                    System.out.println("You have chosen to Save to or Import from a Text File.");
                    //Asking user if they want to back out of this option, save to a file, or import from a file
                    System.out.println("Do you want to 0)Exit this option, 1)Import information from a .txt file, or 2)Save to a .txt file?");
                    System.out.print("Please enter the number of the option you want to choose: ");
                    tempInput = keyboard.nextInt();
                    if(tempInput == 0){
                        System.out.println("Exit input recognized. Returning to Main Menu...");
                        break;
                    }

		    //option to import data from a file, not done with this one yet. Will complete output before this. - Jack
                    else if (tempInput == 1){
                        //POTENTIAL: maybe look into having the user name the file they are importing from? -Jack
                        fileName = "StudentInfo.txt";
                        System.out.println("You have chosen to import data from file, 'StudentInfo.txt'.");
                        //Make File class object to import data from
                        File importFile = new File("StudentInfo.txt");
                        Scanner inputFile = new Scanner(importFile);
                        //include a try-catch statement here to make sure file is valid
                        System.out.println("The file you chose was valid! Importing data now...");
                        //use Scanner object to read data from each line, or something, in txt file
                            //once using Scanner, assign that line to an object/field corresponding to what that line is for.
                                // ^^ Will require the txt file to have its info formatted/saved in a certain way, probably in way not user friendly?
                                //Could look into making way info is saved into txt file more user friendly and still having import function work.
                        break;
                    } 

		    //option to save all student data to a text file. Mostly finished, need to fix saveInfo function, but that's about it. Maybe polish. - Jack
                    else if (tempInput == 2){
                        //POTENTIAL: maybe look into having the user name the file they want to create and save to? - Jack
                        fileName = "StudentInfo.txt";
                        System.out.println("You have chosen to save your student information to file, 'StudentInfo.txt'.");
                        saveInfo(fileName);
                    }
                    }
                    //catch for if file is not found for either save option or import option
                    catch(FileNotFoundException e){
                        System.out.println("File not found: " + e.getMessage());
                    }
                    //file input can be for one or multiple students, have input validation to make sure a file doesn't have nonsense
                    
                    //file output just puts current students into a file, such that it can be read later.
                    //make it so they can either create a file for i/o or use an existing file
                    break;
                    
                case 3: //Output AVERAGES
                    System.out.println("OUTPUTTING AVERAGE OF A STUDENT'S GRADE...");
                    //output the average grades for each student
                    //have user choose the ID of a student to find the average grade for
                    
                    //search through all Subjects, and find every student with the same ID. Sum grades, and divide by number of grades.
                    
                    break;
                    
                case 4: //student report sorted by grades
                    System.out.println("OUTPUTTING ALL STUDENT REPORTS...");
                    //just output existing student classes?
                    //every student sorted by grade... may have multiple instances of the same student for different subjects
                    System.out.println("NAME             ID             CLASS          GRADE");
                    System.out.println("----------------------------------------------------");

                     for (int i = 0; i < ClassSubjects.length; i++)
                    {
                        ClassSubjects[i].sortStudent();	 //sort a class by grade, then output each student in the class
                        for (int j = 0; j < ClassSubjects[i].getStudents().size(); j++)
                        {//walk through the arraylist in each subject
                            //output a report for each student
                            reportStudent(ClassSubjects[i].getStudents().get(j),ClassSubjects[i].getName());
                        
                        }
                    }
                    
                    break;
                    
                case 5: //subject report
                    System.out.println("OUTPUTTING SUBJECT REPORT...");
                    //output an existing Subject class
                    //only output that subject's students with the highest and lowest grades, along with the class' overall average grade
                    System.out.println("What subject do you want to report?");
                    for (int x = 0; x < ClassSubjects.length; x++)
                    {//present the possible choices of subject
                        System.out.println("Enter " + x + ": " + ClassSubjects[x].getName() );
                    }
                    //get the index directly from the user
                    userInputSubject = keyboard.nextInt();
                    //validation
                    while (userInputSubject < 0 || userInputSubject > ClassSubjects.length)
                        {//if user input is invalid
                        System.out.println("Your input was invalid, re-enter your choice: ");
                        userInputSubject = keyboard.nextInt();
                        } //keep re-taking input until they are within a valid range
                    
                    //output the highest and lowest students (by grade)
                    //and average grade in the class
                    //this may seem complicated, but we just get the student and output a report
                    System.out.println("For the Subject: " + ClassSubjects[userInputSubject].getName());
                    System.out.println("The highest grade in the class: ");
                    reportStudent(ClassSubjects[userInputSubject].getHighestStudent(), ClassSubjects[userInputSubject].getName());
                    System.out.println("The lowest grade in the class: ");
                    reportStudent(ClassSubjects[userInputSubject].getLowestStudent(), ClassSubjects[userInputSubject].getName());
                    System.out.println("The average grade of the class is: " + ClassSubjects[userInputSubject].getAverageGrade());
                    
                    break;
                    
                default: //default is essentially for invalid input
                    System.out.println("INVALID INPUT. Please enter an integer from 0-5 (inclusive)");
            }// end switch statement
        }    
        while(!(exit == 0)); //repeat this until user enters '0'
        
        
    }// end main

    //Function to save all student information to a text file.
    //THIS FUNCTION CANNOT ACCESS ClassSubjects[i], WILL HAVE TO MOVE IT TO SOMEWHERE IT CAN ACCESS THEM - Jack
    static void saveInfo(String FileN){
        PrintWriter outputFile = new PrintWriter(FileN);
        for (int i = 0; i < ClassSubjects.length; i++){
            ClassSubjects[i].sortStudent();
            for (int j = 0; j < ClassSubjects[i].getStudents().size(); j++){
                outputFile.println(ClassSubjects[i].getName());
                outputFile.println(ClassSubjects[i].getStudents().get(j).getName());
                outputFile.println(ClassSubjects[i].getStudents().get(j).getID());
                outputFile.println(ClassSubjects[i].getStudents().get(j).getGrade());
                outputFile.println();
            }
        }
        outputFile.close();
    }
	
    //Caleb -- Probably good enough. Maybe make formatting better if anything
    static Student createStudent()
    {//method that creates a basic student class
        System.out.println("This method makes students, including names, ID, and grades for classes.");
        Student madeStudent = new Student();
        Scanner keyboard = new Scanner(System.in);
        
    System.out.println("Please enter the student's name");
    String input = keyboard.nextLine();
    madeStudent.setName(input);
    
    System.out.println("Please enter the student's ID number"); //this will probably not need validation as ID can be numbers and letters...
    String inputID = keyboard.nextLine();
    madeStudent.setID(inputID);
    
    System.out.println("Please enter the student's grade"); 
    double inputGrade = keyboard.nextDouble();
        //validation
    while (inputGrade < 0 || inputGrade > 100)
    {
        System.out.print("INVALID INPUT." + 
        "\nPlease enter a valid grade (0-100): ");
        inputGrade = keyboard.nextDouble();
        //just repeat until the user enters a valid input
    }
    madeStudent.setGrade(inputGrade);
        
        return madeStudent;
    }//end createStudent
    
    //reports an individual student
    static void reportStudent(Student someone, String className)
    {//takes a student object and outputs it nicely
        System.out.printf("%-15s %-15s %-15s %-15s\n",someone.getName(),someone.getID(),className,someone.getGrade());
    }
    
}// END OF CLASS

//Caleb
//Student may be an aggregate of the 'Subject' class, because 'Subject' can have many students within it
class Student
{//this class holds information about a student
    //attributes: ID, name, grades, etc.
    private String ID;   	//id won't be manipulated, it's a string
    private String Name; 	//default name for a student
    //single grade for an instance of a student
    private double Grade;

     //accessors//
    public String getName()
    {return Name;}
    public String getID()
    {return ID;}
    public double getGrade()
    {return Grade;}

    //mutators//
    public void setName(String N)
    {Name = N;}
    public void setID(String stuId)
    {ID = stuId;}
    public void setGrade(double g) 
    {Grade = g; }
    
    
    //constructors//
    public Student(String nm,String id,double grd)
    {
        this.Name = nm;
        this.ID = id;
        this.Grade = grd;
    }
    
    //empty constructor
    public Student()
    {
        this.Name = "Class Empty";
        this.ID = "No Student";
        this.Grade = 0.0;
    }

    // Copy Constructor
    public Student(Student other) 
    {
        this.Name = other.Name;
        this.ID = other.ID;
        this.Grade = other.Grade;
    }
    
}

//Caleb 
class Subject
{//this class stores information on a subject like 'Math', 'English', etc
    //attributes are name, grade, etc.
    
    private String ClassName = "No Subject";	//no initial subject
    private String ClassID = "0000";    	//no class id
    //each student will have a single grade in them for an instance of the subject.
	//an array list of students that can be altered for each subject
    private ArrayList<Student> ClassStudents = new ArrayList<Student>(); 

    //accessors
    public String getName()
    {return ClassName;}
    public String getID()
    {return ClassID;}
    public ArrayList<Student> getStudents() 
    {return ClassStudents;}
    
    public double getAverageGrade()
    {// get the average grade of the entire class
        double gradeAvg;        //average we'll return
        double totalGrade = 0;  //running total for grade
        int totalNum = 0;       //number of students (to find average)
        
        //go through each student's grade in student arraylist
        for(int i = 0; i < ClassStudents.size(); i++ )
        {
        totalGrade += ClassStudents.get(i).getGrade() ;
        totalNum += 1;
        }
        
        gradeAvg = totalGrade/totalNum ;
        return gradeAvg;
    }
    
    public Student getHighestStudent()
    {//get student with highest grade in the class
        double hiGrade = ClassStudents.get(0).getGrade() ; //set to first value in array
        Student hiStudent = new Student(ClassStudents.get(0));
        //go through each student's grade in student arraylist
        for(int i = 1; i < ClassStudents.size(); i++ )
        {
            if (hiGrade < ClassStudents.get(i).getGrade()) //find the highest grade
                {
                    hiGrade = ClassStudents.get(i).getGrade();
                    hiStudent = new Student(ClassStudents.get(i)); //reconstruct
                }
        } 
        return hiStudent; //return the Student with the highest grade
    }
    
    public Student getLowestStudent()
    {//get the student with the lowest grade in the class
     double loGrade = ClassStudents.get(0).getGrade();		 //set to first value in array
        Student loStudent = new Student(ClassStudents.get(0));
        //go through each student's grade in student arraylist
        for(int i = 1; i < ClassStudents.size(); i++ )
        {
            if (loGrade > ClassStudents.get(i).getGrade())	 //find the lowest grade
                {
                    loGrade = ClassStudents.get(i).getGrade();
                    loStudent = new Student(ClassStudents.get(i)); //reconstruct
                }
        } 
        return loStudent; //return the Student with the lowest grade
    }

    //mutators
    public void setName(String nm)
    {ClassName = nm;}
    public void setID(String id)
    {ClassID = id;}
    public void addStudent(Student somebody) 	//add a student to the arraylist
    {ClassStudents.add(somebody);}
    public void removeStudent(int index) 		//remove a specific student?
    {ClassStudents.remove(index);} 			//send an index after you find student index, to remove
    
    //sorting the arraylist of students by grade
    public void sortStudent()
    {//selection sort
        int SIZE = ClassStudents.size();
        
        for (int i = 0; i < SIZE-1; i++) //SIZE-1 so as to not encounter errors
        {
        int min_idx = i;

            // Iterate through the unsorted portion
            // to find the actual minimum
            for (int j = i + 1; j < SIZE; j++) 
                {
                if (ClassStudents.get(j).getGrade() < ClassStudents.get(min_idx).getGrade() ) //compare the grades of the students
                    {//if next smallest value is found at j...
                    min_idx = j;
                    }
                }
            Student temp = new Student(ClassStudents.get(i)); 		//get the current value as a temporary value
            ClassStudents.set(i, ClassStudents.get(min_idx) );		//set current to minimum
           ClassStudents.set(min_idx, temp);  				//set old minimum to temp
        }    
    
    }//end sort student

}//end Subject

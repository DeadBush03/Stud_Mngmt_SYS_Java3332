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
- Apply the principles of Encapsulation to ensure data hiding.
- Use Inheritance and Polymorphism where appropriate

REPORTS:
The system should generate reports such as
- Average grade for a specific student
- Highest and lowest grades for a specific subject
- A list of students sorted by grade

Remember to ensure error handling and input validation
*/

//scanner and file manipulation
import java.util.Scanner;
import java.io.*;
//import java.util.ArrayList; no longer used... wow

//Please remember to put your name before the class/method you're working on
//this is to prevent people from accidentally working on the same thing

public class StudentManagementSystem 
{
    public static void main(String[] args) 
    {       
Scanner keyboard = new Scanner(System.in); //user input

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

//----- end testing

System.out.println("STUDENT MANAGEMENT SYSTEM");
int exit; //exit when user says to
do 
{
userMenu(); //call the menu to display
exit = keyboard.nextInt(); //user input
//case for each possible input
switch(exit)
    {
    case 0 -> System.out.println("EXITING PROGRAM...");

    case 1 -> alterStudentInfo(ClassSubjects); //student information
    case 2 -> 
        {
        //outputting to a file or importing from a file
        try { fileInAndOut(ClassSubjects); } //catch for if file is not found for either save option or import option
        catch(FileNotFoundException e)
        { System.out.println("File not found: " + e.getMessage()); }
        }
    case 3 -> singleStudentAverage(ClassSubjects);  //output the average grade for a single student
    case 4 -> fullStudentReport(ClassSubjects);     //student report sorted by grades
    case 5 -> singleSubjectReport(ClassSubjects);   //subject report

    default -> System.out.println("INVALID INPUT. Please enter an integer from 0-5 (inclusive)");
    }// end switch statement
} while(!(exit == 0)); //repeat menu until user enters '0'

}// END MAIN
     
/*
    -----
    BEGIN METHODS 
    -----
*/
    
    
    
                                        /*CASE 0    |
display this at least once                          V*/
static void userMenu()
{//simply display a menu
System.out.println("Enter a number to select from the menu.");
System.out.println("0 - Exit Program\n" + 
                    "1 - Alter Student Information\n" +
                    "2 - Access or Create File\n" + 
                    "3 - Output Student Averages Report\n" +
                    "4 - Output Student Report\n"+
                    "5 - Output Subject Report");
}//end Case 0
                                        /*CASE 1    |
                                                    V*/
static void alterStudentInfo(Subject SomeClass[])
{//ask user if they want to add/remove a student
System.out.println("You chose to Alter Student Information");
System.out.println("Would you like to add or remove a student?\n"
        + "0 - Exit\n"
        + "1 - add student\n"
        + "2 - remove student");
int AddRemove; //nonsense starting value

Scanner keyboard = new Scanner(System.in); //user input
AddRemove = keyboard.nextInt();
//validation//
do //repeat what the user said until they choose to exit
{//if 1 or 0, do something now that we've validated
if (AddRemove == 1)
    {//add
    System.out.println("What subject do you want to add one student to?");
    for (int x = 0; x < SomeClass.length; x++)
        {//present the possible choices of subject
        System.out.println("Enter " + (x+1) + ": " + SomeClass[x].getClassName() );
        }
        System.out.println("Or Enter 0 to exit");
    //get the index directly from the user
    int userInputSubject = keyboard.nextInt() - 1; //minus one to make it easy for user
    //validation//
    while (userInputSubject < -1 || userInputSubject > SomeClass.length)
        {//if user input is invalid
        System.out.println("Your input was invalid, re-enter your choice: ");
        userInputSubject = keyboard.nextInt() - 1;
        } //keep re-taking input until they are within a valid range

    if (userInputSubject == -1) //exit only if user says to
    {return;}

    System.out.println("For the Subject: " + SomeClass[userInputSubject].getClassName());
    SomeClass[userInputSubject].addStudent(createStudent( SomeClass[userInputSubject] ));
}
else if (AddRemove == 2) //only other option is to remove
{//get subject from user
System.out.println("What subject do you want to remove a student from?");
for (int x = 0; x < SomeClass.length; x++)
    {//present the possible choices of subject
    System.out.println("Enter " + (x+1) + ": " + SomeClass[x].getClassName() );
    }
System.out.println("Or Enter 0 to exit");
int subj = keyboard.nextInt() - 1; //get the index directly from the user
//validation//
while (subj < -1 || subj > SomeClass.length)
{//if user input is invalid
    System.out.println("Your input was invalid, re-enter your choice: ");
    subj = keyboard.nextInt() - 1;
} //keep re-taking input until they are within a valid range

if (subj == -1) //exit only if user says to
    {return;}    
//then check if there are no students in the subject
else if (SomeClass[subj].getStudents().isEmpty())
    {//tell the user and exit if Subject is empty
    System.out.println("\nThe Subject: " + SomeClass[subj].getClassName() + " is empty, and has no students.\n");
    return;
    }
System.out.println("From the subject: " + SomeClass[subj].getClassName() + 
        "\nEnter the ID of the student you would like to remove: ");
//Scanner keyboard2 = new Scanner(System.in); //new keyboard for a string ----REDACTED because it's unnecessary
String RemoveStudent = keyboard.nextLine();
int stuID = findStudent(SomeClass[subj], RemoveStudent); //find a student with that ID, return index

if (stuID == -1) //not found
    {System.out.println("No student with that ID was found. No student removed.\n");}
else //remove student if found
    { 
    SomeClass[subj].getStudents().remove(stuID); 
    System.out.println("Student with ID " + RemoveStudent + " was found and removed.\n");
    }
}//end remove student

//otherwise, if user exits
else if (AddRemove == 0)//exit if -1
    {return;}
else //if it's not 0 and not 1 or 2
    { 
    System.out.println("INVALID INPUT. Please re-enter a valid number:"); 
    AddRemove = keyboard.nextInt(); //re-take input if invalid
    }
} 
while (AddRemove != 0); //keep repeating until user chooses to exit, paired with 'do'
}//end Case 1
                                        /*CASE 2    |
                                                    V*/
static void fileInAndOut(Subject SomeClass[]) throws FileNotFoundException
{//Ask user if they want to back out of this option, save to a file, or import from a file
//NEEDS INPUT VALIDATION
    //variable to hold name of file to save to or import from
    //can be used to allow user to enter name of file if we decide to do that
String fileName;
System.out.println("FILE INPUT/OUTPUT...");
System.out.println("What do you want to do with the file?");
System.out.println("Enter 0: Exit to Menu\n" + 
                   "Enter 1: Import new students from a .txt file\n" + 
                   "Enter 2: Save current students to a .txt file");
Scanner keyboard = new Scanner(System.in); //user input
int tempInput = keyboard.nextInt();

switch (tempInput) {
    case 0 -> {
        System.out.println("Exit input recognized. Returning to Main Menu...");
        return;
             }
case 1 ->     {
    //POTENTIAL: maybe look into having the user name the file they are importing from? -Jack
    fileName = "StudentInfo.txt";
    System.out.println("You have chosen to import data from file, 'StudentInfo.txt'.");
    //Make File class object to import data from
    File importFile = new File(fileName);
    Scanner inputFile = new Scanner(importFile);
    //include a try-catch statement here to make sure file is valid
    System.out.println("The file you chose was valid! Importing data now...");
    //use Scanner object to read data from each line, or something, in txt file
    //once using Scanner, assign that line to an object/field corresponding to what that line is for.
    // ^^ Will require the txt file to have its info formatted/saved in a certain way, probably in way not user friendly?
    //Could look into making way info is saved into txt file more user friendly and still having import function work.
    int x;           
    String tempName;
    String tempID;
    double tempGrade;
    //Start of import data code
    //While loop looks at first line(the one that has the class listed) and goes through ClassSubjects until it gets to index that has that same name
    //It then saves that index so student info can be input at that index.
    //It then saves that index so student info can be input at that index.
    while(inputFile.hasNextLine())
        {
        x=0;
        String classIndexName = inputFile.nextLine();
        if(classIndexName.startsWith(" "))
            {
            return;
            }
        while(classIndexName.equals(SomeClass[x].getClassName()))
            {
            if(x >= SomeClass.length )
                {
                System.out.println("The file you chose had an invalid class. Returning to Main Menu...");
                return;
                }
            tempName = inputFile.nextLine();
            tempID = inputFile.nextLine();
            tempGrade = inputFile.nextDouble();
            inputFile.nextLine();
            Student importStudent = new Student(tempName, tempID, tempGrade); //create the student
            SomeClass[x].addStudent( importStudent ); //add the student
            inputFile.nextLine();

            x++; //only add one at the very end
            }

        } //end while hasnextline
    }//end case 1
case 2 -> {
    //POTENTIAL: maybe look into having the user name the file they want to create and save to? - Jack
    fileName = "StudentInfo.txt";
    System.out.println("You have chosen to save your student information to file, 'StudentInfo.txt'.");
    PrintWriter outputFile = new PrintWriter(fileName);
    for (Subject subj : SomeClass) 
        {
        subj.sortStudent();
        for (int j = 0; j < subj.getStudents().size(); j++) 
            {
            //output everything to a file
            outputFile.println(subj.getStudents().get(j).getClassName());
            outputFile.println(subj.getStudents().get(j).getName());
            outputFile.println(subj.getStudents().get(j).getID());
            outputFile.println(subj.getStudents().get(j).getGrade());
            outputFile.println();
            }
        }
    outputFile.close();
    }// end case 2
default -> {
    return;
    }
}
//option to import data from a file, not done with this one yet. Will complete output before this. - Jack
//option to save all student data to a text file. Mostly finished, need to fix saveInfo function, but that's about it. Maybe polish. - Jack
//file input can be for one or multiple students, have input validation to make sure a file doesn't have nonsense
//file output just puts current students into a file, such that it can be read later.
//make it so they can either create a file for i/o or use an existing file
}//end Case 2
                                        /*CASE 3    |
                                                    V*/
static void singleStudentAverage(Subject SomeClass[])
{//have user choose the ID of a student to find the average grade for, then output
System.out.println("OUTPUTTING AVERAGE OF A STUDENT'S GRADE...");
                    
System.out.println("Please enter the ID of the student you want to average. ex. '123':");
Scanner keyboard = new Scanner(System.in); //user input
String findID = keyboard.nextLine();

String FoundName = "No Student"; //name to call the student
int IDindex; //the index of the student (if found)
double totalgrade = 0;
double numtimes = 0;
double averagegrade; //calculated average of all the student's grades

for (Subject subj : SomeClass) 
    {//search through every class
    subj.sortStudent(); //sort each class by grade, then output each student in the class
    for (int j = 0; j < subj.getStudents().size(); j++) 
        {//go through every student
        IDindex = findStudent(subj, findID); //get index of student, returns -1 if not found
        if (IDindex != -1) 
            {
            while (FoundName.equals("No Student")) 
                {//get the name of the student (default name is "No Student")
                FoundName = subj.getStudents().get(IDindex).getName();
                }
            totalgrade += subj.getStudents().get(IDindex).getGrade(); //accumulate total grade of found student
            numtimes++; //number of times the student is found +1
            }
        }
    }    
//if we've found the student zero times...
if (numtimes == 0)
    {
    System.out.println("Sorry, we could not find a student with the ID: " + findID + "\n");
    return;
    } //exit if there are no students with that ID
    //else...
    averagegrade = totalgrade/numtimes;
    System.out.println("\nThe student: " + FoundName + "\nID: " + findID +
            "\nhas an average grade of " + averagegrade + " across all subjects.");
System.out.println(); //space for formatting
}//end Case 3
                                        /*CASE 4    |
                                                    V*/
static void fullStudentReport(Subject SomeClass[])
{//take an array of Subjects and output every student
System.out.println("OUTPUTTING ALL STUDENT REPORTS...");
//just output existing student classes?
//every student sorted by grade... may have multiple instances of the same student for different subjects
System.out.println("NAME             ID             CLASS          GRADE");
System.out.println("----------------------------------------------------");

for (Subject Some : SomeClass) //for the whole Subject
    {
    Some.sortStudent(); //sort a Subject by grade, then output each student in the class
    for (int j = 0; j < Some.getStudents().size(); j++) 
        {
            //walk through the arraylist in each subject
            //output a report for each student
            reportStudent(Some.getStudents().get(j));
        }
    }
System.out.println(); //space for formatting
}// end Case 4
                                        /*CASE 5    |
                                                    V*/
static void singleSubjectReport(Subject SomeClass[])
{//output a single existing Subject class from the array of Subjects
Scanner keyboard = new Scanner(System.in); //user input
    
System.out.println("OUTPUTTING SUBJECT REPORT...");
//only output ONE subject's students with the highest and lowest grades, along with the class' overall average grade
System.out.println("What subject do you want to report?");
for (int x = 0; x < SomeClass.length; x++)
    {//present the possible choices of subject
    System.out.println("Enter " + (x + 1) + ": " + SomeClass[x].getClassName() );
    }
    System.out.println("Or Enter 0 to exit");
int Subj = keyboard.nextInt() - 1; //get the index directly from the user
//validation//
while (Subj < -1 || Subj > SomeClass.length)
    {//if user input is invalid
    System.out.println("Your input was invalid, re-enter your choice: ");
    Subj = keyboard.nextInt();
    } //keep re-taking input until they are within a valid range

if (Subj == -1) //exit if user says to
    {return;}    
else if (SomeClass[Subj].getStudents().isEmpty()) //check if there are no students in the subject
    {//tell the user and break if it's empty
    System.out.println("\nThe Subject: " + SomeClass[Subj].getClassName() + " is empty, and has no students.\n");
    return;
    }

//output the highest and lowest students (by grade) and average grade in the class
System.out.println("For the Subject: " + SomeClass[Subj].getClassName());
System.out.println("The highest grade in the class: ");
reportStudent(SomeClass[Subj].getHighestStudent() );
System.out.println("The lowest grade in the class: ");
reportStudent(SomeClass[Subj].getLowestStudent() );
System.out.println("The average grade of the class is: " + SomeClass[Subj].getAverageGrade());

System.out.println(); //empty space for formatting
}//end Case 5
                                        //END CASES//
//Caleb
static Student createStudent(Subject thisClass) //thisClass for ID validation
{//method that creates a basic student class
System.out.println("This method makes students, including names, ID, and grades for classes.");
Student madeStudent = new Student();
Scanner keyboard = new Scanner(System.in);

System.out.println("Please enter the student's ID number"); //validation to check that no students have the same ID in the same class
String inputID = keyboard.nextLine();
int sameIDfound = findStudent(thisClass, inputID); //see if the ID is already in use
    //validation//
    while(sameIDfound != -1) // -1 means ID is not in use
        {
        System.out.print("INVALID INPUT. \nAnother student has that ID... Re-enter: ");
        inputID = keyboard.nextLine();
        sameIDfound = findStudent(thisClass, inputID); //check if the next ID is valid
        //just repeat until the user enters a valid input
        }
madeStudent.setID(inputID); //set ID as valid input

System.out.println("Please enter the student's name"); //get name after ID to help prevent user gaffs
String input = keyboard.nextLine();
madeStudent.setName(input);

System.out.println("Please enter the student's grade"); 
double inputGrade = keyboard.nextDouble();
    //validation//
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

//give a Subject, and a search ID.
//find the student with that ID
//return the index of that student (in the arraylist), or -1 if not found
static int findStudent(Subject someClass, String someID)
{
int indexID = -1; // the index of the student in the subject

    for (int j = 0; j < someClass.getStudents().size(); j++)
    {//walk through every student
        if (someClass.getStudents().get(j).getID().equals(someID)) //string comparison
        {
        indexID = j; //it is reasonable to assume there is only one instance of a student per class.
        break;
        }
    }//end search through students in a class  


return indexID; //-1 if not found
}

//reports an individual student
static void reportStudent(Student someone)
{//takes a student object and the class name to output each student nicely
    System.out.printf("%-15s %-15s %-15s %-15s\n",someone.getName(),someone.getID(),someone.getClassName(),someone.getGrade());
}
//END OF METHODS
}   // END OF CLASS //

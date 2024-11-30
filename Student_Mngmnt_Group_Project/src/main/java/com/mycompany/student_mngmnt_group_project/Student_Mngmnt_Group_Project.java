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
        
        //----- end testing

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
                    //ask user if they want to add/remove a student
                    System.out.println("Would you like to add or remove a student?\n"
                            + "0 - Exit\n"
                            + "1 - add student\n"
                            + "2 - remove student");
                    int AddRemove = 3; //nonsense starting value
                    
                    //validation//
                    AddRemove = keyboard.nextInt();
                    do //repeat what the user said until they choose to exit
                    {
                    //if 1 or 0, do something now that we've validated
                    if (AddRemove == 1)
                    {
                        //add
                        System.out.println("What subject do you want to add one student to?");
                        for (int x = 0; x < ClassSubjects.length; x++)
                        {//present the possible choices of subject
                            System.out.println("Enter " + (x+1) + ": " + ClassSubjects[x].getName() );
                        }
                            System.out.println("Or Enter 0 to exit");
                        //get the index directly from the user
                        int userInputSubject = keyboard.nextInt() - 1; //minus one to make it easy for user
                        //validation
                        while (userInputSubject < -1 || userInputSubject > ClassSubjects.length)
                            {//if user input is invalid
                            System.out.println("Your input was invalid, re-enter your choice: ");
                            userInputSubject = keyboard.nextInt() - 1;
                            } //keep re-taking input until they are within a valid range

                        if (userInputSubject == -1) //exit only if user says to
                        {break;}
                        
                        System.out.println("For the Subject: " + ClassSubjects[userInputSubject].getName());
                        ClassSubjects[userInputSubject].addStudent(createStudent( ClassSubjects[userInputSubject] ));
                    }
                    else if (AddRemove == 2) //only other option is to remove
                    {
                        //get subject from user
                            System.out.println("What subject do you want to remove a student from?");
                            for (int x = 0; x < ClassSubjects.length; x++)
                            {//present the possible choices of subject
                                System.out.println("Enter " + (x+1) + ": " + ClassSubjects[x].getName() );
                            }
                            System.out.println("Or Enter 0 to exit");
                            //get the index directly from the user
                            int userInputSubject = keyboard.nextInt() - 1;
                            //validation
                            while (userInputSubject < -1 || userInputSubject > ClassSubjects.length)
                            {//if user input is invalid
                                System.out.println("Your input was invalid, re-enter your choice: ");
                                userInputSubject = keyboard.nextInt() - 1;
                            } //keep re-taking input until they are within a valid range

                            if (userInputSubject == -1) //exit only if user says to
                            {break;}    
                            //then check if there are no students in the subject
                            else if (ClassSubjects[userInputSubject].getStudents().isEmpty())
                            {//tell the user and break if it's empty
                                System.out.println("\nThe Subject: " + ClassSubjects[userInputSubject].getName() + " is empty, and has no students.\n");
                                break;
                            }
                        System.out.println("From the subject: " + ClassSubjects[userInputSubject].getName() + 
                                "\nEnter the ID of the student you would like to remove: ");
                        Scanner keyboard2 = new Scanner(System.in); //new keyboard for a string
                        String RemoveStudent = keyboard2.nextLine();
                        
                        int stuID = findStudent(ClassSubjects[userInputSubject], RemoveStudent); //find a student with that ID, return index
                        
                        if (stuID == -1) //not found
                        { System.out.println("No student with that ID was found. No student removed.\n");}
                        else //remove student if found
                        { 
                            ClassSubjects[userInputSubject].getStudents().remove(stuID); 
                            System.out.println("Student with ID " + RemoveStudent + " was found and removed.\n");
                        }
                        
                        
                    }//end remove student
                    
                    //otherwise, if user exits
                    else if (AddRemove == 0)//exit if -1
                    {break;}
                    else //if it's not 0 and not 1 or 2
                        { 
                        System.out.println("INVALID INPUT. Please re-enter a valid number:"); 
                        AddRemove = keyboard.nextInt(); //re-take input if invalid
                        }
                    } 
                    while (AddRemove != 0); //keep repeating until user chooses to exit

                    break;
                    
                case 2: //outputting to a file or importing from a file
			    
		    try
		    {
                        
	                    System.out.println("You have chosen to Save to or Import from a Text File.");
	                    //Asking user if they want to back out of this option, save to a file, or import from a file
			    System.out.println("Please enter the number of the option you want to choose.");
	                    System.out.println("Enter 0: Exit to Menu\n" + 
					       "Enter 1: Import information from a .txt file\n" + 
					       "Enter 2: Save to a .txt file");
	                    tempInput = keyboard.nextInt();
	                    if(tempInput == 0)
			    {
	                        System.out.println("Exit input recognized. Returning to Main Menu...");
	                        break;
	                    }
			    //option to import data from a file, not done with this one yet.
	                    else if (tempInput == 1)
			    {
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
				int x;
				String tempName;
				String tempID;
				double tempGrade;
	                       	//Start of import data code
	                       	//While loop looks at first line(the one that has the class listed) and goes through ClassSubjects until it gets to index that has that same name
	                      	//It then saves that index so student info can be input at that index.
	                       		                      	//It then saves that index so student info can be input at that index.
	                       	while(inputFile.hasNextLine()){
	                        	x=0;
	                        	String classIndexName = inputFile.nextLine();
					if(classIndexName.startsWith(" ")){
						break;
					}
	                        	while(classIndexName.equals(ClassSubjects[x].getName()))
                                        {
	                                	if(x >= ClassSubjects.length )
                                                {
	                                    	System.out.println("The file you chose had an invalid class. Returning to Main Menu...");
	                                    	break;
	                                	}
                                               
                                        tempName = inputFile.nextLine(); //code with 'temp' grabs what that line holds and assigns it to the corresponding part of the Student class.
	                           	tempID = inputFile.nextLine();
	                            	tempGrade = inputFile.nextDouble();
	                            	inputFile.nextLine();
                                        Student importStudent = new Student(tempName, tempID, tempGrade); //create the student
	                            	ClassSubjects[x].addStudent( importStudent ); //add the student
	                            	inputFile.nextLine();
                                        
                                        x++; //only add one at the very end
	                            	}
					
	                       	}
	                        break;
	                    } 
	
			    //option to save all student data to a text file.
	                    else if (tempInput == 2)
			    {
	                        
	                        fileName = "StudentInfo.txt";
	                        System.out.println("You have chosen to save your student information to file, 'StudentInfo.txt'.");
	                        PrintWriter outputFile = new PrintWriter(fileName);
                                for (int i = 0; i < ClassSubjects.length; i++)
                                {
                                    ClassSubjects[i].sortStudent();
                                    for (int j = 0; j < ClassSubjects[i].getStudents().size(); j++) //goes through class objects and outputs the course name, student name, student id, and student's grade in that course, in that order.
                                    {
                                        outputFile.println( ClassSubjects[i].getName());
                                        outputFile.println( ClassSubjects[i].getStudents().get(j).getName() );
                                        outputFile.println( ClassSubjects[i].getStudents().get(j).getID() );
                                        outputFile.println( ClassSubjects[i].getStudents().get(j).getGrade());
                                        outputFile.println();
                                    }
                                }
                                outputFile.close();
                                
	                    }
                    }//catch for if file is not found for either save option or import option
                    catch(FileNotFoundException e)
		    { System.out.println("File not found: " + e.getMessage()); }
                    //file input can be for one or multiple students, have input validation to make sure a file doesn't have nonsense
                    
                    //file output just puts current students into a file, such that it can be read later.
                    //make it so they can either create a file for i/o or use an existing file
                    break;
                    
                case 3: //output the average grade for a single student
                    System.out.println("OUTPUTTING AVERAGE OF A STUDENT'S GRADE...");
                    
                    //have user choose the ID of a student to find the average grade for
                    String SearchID;
                    System.out.println("Please enter the ID of the student you want to average. ex. '123':");
                    //'keyboard' from main doesn't work because it was used for an int, this is a string
                    Scanner keyboard2 = new Scanner(System.in);
                    SearchID = keyboard2.nextLine();
                    
                    String FoundName = "No Student"; //name to call the student
                    int foundIndex;                 //the index of the student (if found)
                    double totalgrade = 0;
                    double numtimes = 0;
                    double averagegrade;            //calculated average of all the student's grades
                    
                    for (int i = 0; i < ClassSubjects.length; i++)
                    {//search through every class
                        ClassSubjects[i].sortStudent(); //sort a class by grade, then output each student in the class
                        
                        for (int j = 0; j < ClassSubjects[i].getStudents().size(); j++)
                        {//go through every student
                            foundIndex = findStudent(ClassSubjects[i], SearchID); //get index of student, returns -1 if not found
                            
                            if(foundIndex != -1)    //index IS found
                            {
                            while (FoundName.equals("No Student")) //only get the student's name once, assume it is correct
                                { FoundName = ClassSubjects[i].getStudents().get(foundIndex).getName(); }
                                //accumulate total grade of found student
                                totalgrade += ClassSubjects[i].getStudents().get(foundIndex).getGrade();
                                //number of times the student is found +1
                                numtimes++;
                            }
                        } 
                    }    
                    //if we've found the student zero times...
                        if (numtimes == 0)
                        {
                            System.out.println("Sorry, we could not find a student with the ID: " + SearchID + "\n");
                            break;
                        } //exit if there are no students with that ID
                        //else...
                        averagegrade = totalgrade/numtimes;
                        System.out.println("\nThe student: " + FoundName + "\nID: " + SearchID +
                                "\nhas an average grade of " + averagegrade + " across all subjects.");
                    System.out.println(); //space for formatting
                    break;
                    
                case 4: //student report sorted by grades
                    System.out.println("OUTPUTTING ALL STUDENT REPORTS...");
                    //just output existing student classes?
                    //every student sorted by grade... may have multiple instances of the same student for different subjects
                    System.out.println("NAME             ID             CLASS          GRADE");
                    System.out.println("----------------------------------------------------");

                     for (int i = 0; i < ClassSubjects.length; i++)
                    {
                        ClassSubjects[i].sortStudent(); //sort a class by grade, then output each student in the class
                        for (int j = 0; j < ClassSubjects[i].getStudents().size(); j++)
                        {//walk through the arraylist in each subject
                            //output a report for each student
                            reportStudent( ClassSubjects[i].getStudents().get(j), ClassSubjects[i].getName());
                        }
                    }
                    System.out.println(); //space for formatting
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
			System.out.println("Or Enter -1 to exit");
                    //get the index directly from the user
                    int userInputSubject = keyboard.nextInt();
                    //validation
                    while (userInputSubject < -1 || userInputSubject > ClassSubjects.length)
                    {//if user input is invalid
                        System.out.println("Your input was invalid, re-enter your choice: ");
                        userInputSubject = keyboard.nextInt();
                    } //keep re-taking input until they are within a valid range
			    
		    if (userInputSubject == -1) //exit only if user says to
                    {break;}    
                    //then check if there are no students in the subject
                    else if (ClassSubjects[userInputSubject].getStudents().isEmpty())
                    {//tell the user and break if it's empty
                        System.out.println("\nThe Subject: " + ClassSubjects[userInputSubject].getName() + " is empty, and has no students.\n");
                        break;
                    }

		    //output the highest and lowest students (by grade) and average grade in the class
                    System.out.println("For the Subject: " + ClassSubjects[userInputSubject].getName());
                    System.out.println("The highest grade in the class: ");
                    reportStudent(ClassSubjects[userInputSubject].getHighestStudent(), ClassSubjects[userInputSubject].getName());
                    System.out.println("The lowest grade in the class: ");
                    reportStudent(ClassSubjects[userInputSubject].getLowestStudent(), ClassSubjects[userInputSubject].getName());
                    System.out.println("The average grade of the class is: " + ClassSubjects[userInputSubject].getAverageGrade());
                    
                    System.out.println(); //empty space for formatting
                    break;
                    
                default: //default is essentially for invalid input
                    System.out.println("INVALID INPUT. Please enter an integer from 0-5 (inclusive)");
            }// end switch statement
        }    
        while(!(exit == 0)); //repeat this until user enters '0'
        
        
    }// end main
	
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
    
    //give a Subject, and a search ID
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
    static void reportStudent(Student someone, String className)
    {//takes a student object and the class name to output each student nicely
        System.out.printf("%-15s %-15s %-15s %-15s\n",someone.getName(),someone.getID(),className,someone.getGrade());
    }
    //end of methods
}// END OF CLASS

//Caleb
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
    //an array list of students that can be altered for each subject
    private ArrayList<Student> ClassStudents = new ArrayList<Student>(); 

    //accessors//
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

    //mutators//
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

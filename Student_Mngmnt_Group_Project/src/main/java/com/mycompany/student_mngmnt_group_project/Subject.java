package com.mycompany.student_mngmnt_group_project;

import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deadb
 */
//caleb
public class Subject
{//this class stores information on a subject like 'Math', 'English', etc
//attributes are name, grade, etc.

private String ClassName = "No Subject";	//no initial subject
private String ClassID = "0000";    	//no class id
//an array list of students that can be altered for each subject
private ArrayList<Student> ClassStudents = new ArrayList<>(); 

    //accessors//
public String getClassName()
    {return ClassName;}
public String getClassID()
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
//@OVERRIDDEN in student
public void setName(String nm)
    {ClassName = nm;}
//@OVERRIDDEN in student
public void setID(String id)
    {ClassID = id;}
public void addStudent(Student somebody) 	//add a student to the arraylist
    {ClassStudents.add(somebody);}
public void removeStudent(int index) 		//remove a specific student?
    {ClassStudents.remove(index);} 		//send an index after you find student index, to remove
    
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

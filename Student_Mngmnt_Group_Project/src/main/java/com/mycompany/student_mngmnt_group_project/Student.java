package com.mycompany.student_mngmnt_group_project;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author deadb
 */
public class Student extends Subject
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
@Override //student name not class name
public void setName(String N)
    {Name = N;}
@Override //student id not class ID
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

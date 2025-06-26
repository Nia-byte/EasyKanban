/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.easykanban;
import  javax.swing.JOptionPane; 

/**
 *
 * @author lab_services_student
 */
public class Task {
    private String taskName;
    private int taskNumber;
    private String taskDescription;
    private String developerDetails;
    private int taskDuration;
    private String taskStatus;
    private int total;
    
    public Task() {
        this.total = 0;
    }
    
    public String getTaskName() {
        return this.taskName;
    }
    
    public void setTaskName( String taskName) {
        this.taskName = taskName;
    }
    
    public int getTaskNumber() {
        return this.taskNumber;
    }
    
    public void setTaskNumber( int taskNumber) {
        this.taskNumber = taskNumber;
    }
    
    public String getTaskDescription() {
        return this.taskDescription;
    }
    
    public void setTaskDescription( String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    public String getDeveloperDetails() {
        return this.developerDetails;
    }
    
    public void setDeveloperDetails( String developerDetails) {
        this.developerDetails = developerDetails;
    }
    
    public int getTaskDuration() {
        return this.taskDuration;
    }
    
    public void setTaskDuration( int taskDuration) {
        this.taskDuration = taskDuration;
    }
    
    public String getTaskStatus() {
        return this.taskStatus;
    }
    
    public void setTaskStatus(final String taskStatus) {
        this.taskStatus = taskStatus;
    }
    
    public int getTotal() {
        return this.total;
    }
    
    public String checkTaskDescription( String taskDescription) {
         int description = taskDescription.length();
        String output = "";
        if (description <= 50) {
            output = "Task Description successfully captured";
        }
        else {
            output = "Please enter a task description of less than 50 characters";
        }
        return output;
    }
    
    public String createTaskID(String taskName,  int taskNumber,  String developer) {
         String firstName = developer.substring(0, developer.indexOf(" ")); // (Satyabrata, 2023)
         String taskId = taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + firstName.substring(firstName.length() - 3).toUpperCase(); //(Boyini, 2020.)
        
         return taskId;
    }
    
    public int returnTotalHours( int numTasks,  int taskDuration) {
         int count = 0;
        
         if (count < numTasks) {
            this.total = this.total + taskDuration;
            count++;
        }
        return this.total;
    }
    
    public String printTaskDetails(String [] developerFullName, String [] taskNames,String [] taskIDs, int [] taskDurations, String [] taskStatuses) {
       
        String output ="";
        for(int i = 0; i < developerFullName.length ; i++){
              output  = output +"\n"+ "TASK DETAILS "+ i  + ":"+ "\n" +"Developer:   "+ developerFullName[i] +"\n"+ "Task Name:   "
                               +taskNames[i] +"\n"+"Task ID:    "+ taskIDs[i] + "\n" + 
                                  "Task Duration:   " + taskDurations[i] +"\n"+ "Task Status:  "+ taskStatuses[i] + "\n";
        }
        return output;
    }
    
    public void display() {
        JOptionPane.showMessageDialog(null, "Total hours for all tasks: " + this.total);
    }
}


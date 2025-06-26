/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.easykanban;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class EasyKanban {

   public static void main(String[] args) {
       int loginRegister = Integer.parseInt(JOptionPane.showInputDialog(null, "LOGIN/REGISTER" + "\n" + "\n" + "Choose one: " + "\n" + "1. Register " + "\n" + "2. Login" ));
        
       Login loginclass = new Login();      
         
        if (loginRegister == 1){
             String  firstName = JOptionPane.showInputDialog(null, "Enter your First Name:");
             String  lastName = JOptionPane.showInputDialog(null, "Enter your Last Name");
             String username = JOptionPane.showInputDialog(null, "Enter your username"); 
             String password = JOptionPane.showInputDialog(null, "Enter your password");
           
            
         // loginclass = new Login();   
            
             loginclass.setFirstname(firstName); //sending all the values to the set methods. (Farrell, 2019)  
             loginclass.setLastname(lastName);
           
             loginclass.setUsername(username);
             loginclass.checkUserName(); //checking that username meets the criteria. (Farrell, 2019) 
            
             loginclass.setPassword(password);
             loginclass.checkPasswordComplexity(); //checking that the password meets the criteria. (Farrell, 2019) 
            
             JOptionPane.showMessageDialog(null, loginclass.registerUser());
            
            
            
            
             
             }
            int loginNot = JOptionPane.showConfirmDialog(null, "Do you want to Login?", "LOGIN?", JOptionPane.YES_NO_OPTION);
             
             if (loginNot == 0){
             String username = JOptionPane.showInputDialog(null, "Enter your username");
             String password = JOptionPane.showInputDialog(null, "Enter your password"); 
            
             loginclass.loginUser(username, password); //Sending values to this method inside class. (Farrell, 2019) 
            
             JOptionPane.showMessageDialog(null, loginclass.returnLoginStaus(username, password));
             if(loginclass.loginUser(username, password) == true){
               JOptionPane.showMessageDialog(null, "Welcome to EasyKanban!");
               }
            }
             
             int menu = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose one feature: \n1. Add Tasks\n2. Show Report(Coming Soon)\n3. Quit"));
       
             Task task = new Task();
             int taskDuration = 0;
             String taskStatus = "";
             
             
      
                 while (menu != 3) { //(Farrell, 2019)
                     if (menu == 1) { //(Farrell, 2019)
                          int numTaks = Integer.parseInt(JOptionPane.showInputDialog(null, "How many task do you want to add?"));
                          
                                     String[] developerFullName; //Declaring the array. (Farrell, 2019)
                                     developerFullName = new String[numTaks];
                                     String[] taskNames;
                                     taskNames = new String[numTaks];
                                     String[] taskIDs;
                                     taskIDs = new String[numTaks];
                                     int[] taskDurations;
                                     taskDurations = new int[numTaks];
                                     String[] taskStatuses;
                                     taskStatuses = new String[numTaks];
                                     
                                     
                                     
                             
                             for (int i = 0; i < numTaks; ++i) { // (Farrell, 2019)
                                 String taskName = JOptionPane.showInputDialog(null, "Please enter name of task");
                                 taskNames[i] = taskName; //Populating the array
                                 
                                 int taskNumber = i;
                                 
                                 String taskDescription = JOptionPane.showInputDialog(null, "Please enter description of task");
                                 JOptionPane.showMessageDialog(null, task.checkTaskDescription(taskDescription));
                                 
                                 String developerDetails = JOptionPane.showInputDialog(null, "Please enter First name and Last name of developer");
                                 developerFullName[i] = developerDetails;
                                 
                                 String taskID = task.createTaskID(taskName, taskNumber, developerDetails);
                                 taskIDs[i] = taskID;
                                 
                                 taskDuration = Integer.parseInt(JOptionPane.showInputDialog(null, "Please enter task duration(*Hours)"));
                                 taskDurations[i] = taskDuration;
                                  
                                 task.returnTotalHours(numTaks, taskDuration);
                           int option = Integer.parseInt(JOptionPane.showInputDialog(null, "Select one task status:\n1. To Do\n2. Done\n3.Doing"));
                     
                                     
                           
                             
                           
                       switch (option) {
                        case 1: {
                            taskStatus = "To Do";
                            task.setTaskStatus(taskStatus);
                             taskStatuses[i] = taskStatus;
                            break;
                        }
                        case 2: {
                            taskStatus = "Done";
                            task.setTaskStatus(taskStatus);
                            taskStatuses[i] = taskStatus;
                            break;
                        }
                        case 3: {
                            taskStatus = "Doing";
                            task.setTaskStatus(taskStatus);
                            taskStatuses[i] = taskStatus;
                            break;
                        }
                        default: {
                            JOptionPane.showMessageDialog(null, "Incorrect Input!");
                            
                        }
                    }
                       
                       
                     task.setTaskName(taskName);
                     task.setTaskDescription(taskDescription);
                     task.setTaskNumber(taskNumber);
                     task.setDeveloperDetails(developerDetails);
                     task.setTaskDuration(taskDuration);
                    
                   
                             }
                     //Passing the array to the method (Software Testing Help, [s.a.])
                     
                      JOptionPane.showMessageDialog(null, task.printTaskDetails( developerFullName, taskNames, taskIDs, taskDurations, taskStatuses));
                     //Displaying total number of hours
                     task.display();
                
                     int biggerDuration = 0;
                     int durationIndex = 0;
                         for(int j = 0 ; j < numTaks ; j++){ //searching the array for longest duration. (tutorialspoint. [s.a.])
                             if (taskDurations[j] > biggerDuration){
                             biggerDuration = taskDurations[j];
                             durationIndex = j;
                             }
                         }
                             //Displaying the developer name and longest duration.
                              JOptionPane.showMessageDialog(null, developerFullName[durationIndex] + " has the longest duration of " + biggerDuration);
                             
                              //searching the array for task name receieved from user input. 
                             String searchTask = JOptionPane.showInputDialog(null, "Enter the Task Name you wish to search");
                             int index = 0;
                             for(int i = 0 ; i < numTaks ; i++){  
                                 if (searchTask == taskNames[i]){
                                     index = i;
                                 }
                             }
                             //Displaying information
                             JOptionPane.showMessageDialog(null,"Task Name: " + taskNames[index] +"\n" + "Developer: "+ developerFullName[index] +"\n" + "Task Status: "+ taskStatuses[index]);
                             
                             
                             //show tasks by searched developer
                             String searchDeveloper = JOptionPane.showInputDialog(null, "Enter the name of Developer you wish to search");
                             String output = "";
                             for(int j = 0 ; j < numTaks ; j++){ //searching the array for developer name receieved from user input. 
                                 if (developerFullName[j].equals(searchDeveloper) == true){ //
                                   output += "\n" + "Task Name: " + taskNames[j] + "\n"+ "Task Status: " + taskStatuses[j] + "\n";
                                 }
                             }
                             //Displaying information
                             JOptionPane.showMessageDialog(null, "Tasks for " + searchDeveloper + ":" + output);
                             
                             
                             //Deleting element
                             String searchTaskRemove = JOptionPane.showInputDialog(null, "Enter the task name you wish to delete");
                             int indexNumber = 0;
                             for(int i = 0 ; i < numTaks ; i++){ //searching task name to delete.  
                                 if (searchTaskRemove == taskNames[i]){
                                     indexNumber = i;
                                 }
                             }
                             
                             for (int j = index; j < numTaks - 1; j++) {
                                 developerFullName[j] = developerFullName[j + 1] ;
                                 taskNames[j] = taskNames[j + 1];
                                 taskStatuses[j] = taskStatuses[j + 1];
                                 taskDurations[j] = taskDurations[j + 1];
                                 taskIDs[j] = taskIDs[j + 1];
                                 
                         }
                             //Making the last element empty
                            
                            
                             // Adding upgraded array to new array
                             int lastElement = developerFullName.length - 1 ;
                                     String[] newDeveloperFullName; //Declaring NEW array for a new array. (Farrell, 2019)
                                     newDeveloperFullName = new String[lastElement];
                                     String[] newTaskNames;
                                     newTaskNames = new String[lastElement];
                                     String[] newTaskIDs;
                                     newTaskIDs = new String[lastElement];
                                     int[] newTaskDurations;
                                     newTaskDurations = new int[lastElement];
                                     String[] newTaskStatuses;
                                     newTaskStatuses = new String[lastElement];
                                     
                                     
                             for (int j = 0; j < lastElement; j++) {
                                     
                                  newDeveloperFullName[j] = developerFullName[j] ;
                                  newTaskNames[j] = taskNames[j] ;
                                  newTaskIDs[j] = taskIDs[j] ;
                                  newTaskDurations[j] = taskDurations[j] ;
                                  newTaskStatuses[j] = taskStatuses[j] ;
                             }


                             JOptionPane.showMessageDialog(null, task.printTaskDetails(newDeveloperFullName, newTaskNames, newTaskIDs, newTaskDurations, newTaskStatuses));
                     }
            else if (menu == 2) {
                JOptionPane.showMessageDialog(null, "Coming Soon!");
            }
            else if (menu == 3) {
                System.exit(0);
            }
            menu = Integer.parseInt(JOptionPane.showInputDialog(null, "Choose one feature: \n1. Add Tasks\n2. Show Report(Coming Soon)\n3. Quit"));
        }
    }
   
   public void populatedArray(){
       
   }
}
/*
Reference List: 

Farrell, J. 2019. Java Programming. 9th ed. Boston: Cengage.  

W3SCHOOLS. [s.a.]. Java String contains() Method, s.a. [ONLINE]. Available at: https://www.w3schools.com/java/ref_string_contains.asp [Accessed 7 June 2023]. 

Mr. Satyabrata. 2022. JAVA Menu Driven Program to Check Character is String, Number or Special Character, 27 December 2022. [ONLINE]. Available at: https://www.tutorialspoint.com/java/java_string_length.htm [Accessed 7 June 2023]. 

Nayak, S. 2021. How To Check String Contains Special Characters In Java?, 31 May 2021. [ONLINE]. Available at: https://codingface.com/how-to-check-string-contains-special-characters-in-java/?utm_content=cmp-true [Accessed 7 June 2023].  

tutorialspoint. [s.a.]. Java - Character isUpperCase() Method, s.a. [ONLINE]. Available at: https://www.tutorialspoint.com/java/lang/character_isuppercase.htm [Accessed 7 June 2023].  

Boyini, K. 2020. Get the substring before the last occurrence of a separator in Java, 27 June 2020. [ONLINE]. Available at: https://www.tutorialspoint.com/get-the-substring-before-the-last-occurrence-of-a-separator-in-java (Accessed 14 June 2023).

Mr. Satyabrata. 2023. Find First and Last Word of a File Containing String in Java, 11 January 2023. [ONLINE]. Available at: https://www.tutorialspoint.com/find-first-and-last-word-of-a-file-containing-string-in-java [Accessed 14 June 2023].  

Software Testing Help. [s.a.]. How To Pass / Return An Array In Java, 18 June 2023. [Online]. Avaiable at: https://www.softwaretestinghelp.com/pass-return-array-in-java/ [Accessed 1 July 2023].

tutorialspoint. [s.a.]. Java Program to find Largest Number in an Array, s.a. [Online]. Available at: https://www.javatpoint.com/java-program-to-find-largest-number-in-an-array [Accessed 1 July 2023].
*/
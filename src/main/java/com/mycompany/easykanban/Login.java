/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.easykanban;
import javax.swing.JOptionPane;

/**
 *
 * @author lab_services_student
 */
public class Login {
     private String firstname ; 
  private String lastname ;
  private String username ;
  private String password; 
   

    
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
     public String getFirstname() {
        return firstname;
    }

  
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
     public String getLastname() {
        return lastname;
    }


    public void setUsername(String username) {
        this.username = username;
    }
      public String getUsername() {
        return username;
    }


    public void setPassword(String password) {
        this.password = password;
    }
     public String getPassword() {
        return password;
    }
 //(Farrell, 2019)
 
  public boolean checkUserName () {
       boolean checkUsername = false ; 
       int legnth = this.username.length();
            
            if (this.username.contains("_") && legnth <= 5) {
                checkUsername = true ; 
             } 
     
            
            while (!(this.username.contains("_") && legnth <= 5)) {
                checkUsername = false ; 
             }
       return checkUsername  ;  // (W3SCHOOLS, [ers.a.]) 
  }
  
  public boolean checkPasswordComplexity() {
      boolean checkPassword = false ; 
      int legnth2 = this.password.length();
      boolean capital = this.password.chars().anyMatch(Character::isUpperCase) ; //(tutorialspoint, [s.a])
      boolean number = this.password.chars().anyMatch(Character::isDigit);
      
       for (int i = 0 ; i < this.password.length() ; i++) { //(Nayak, 2021) 
                 String strCharacter = Character.toString(this.password.charAt(i)); 
                 if (this.password.contains(strCharacter)); 
             
               if (legnth2 >= 8 && capital == true && number == true && this.password.contains(strCharacter) == true ){
                   checkPassword = true; 
                   break ; 
             } else if (!(legnth2 >= 8 && capital == true && number == true && this.password.contains(strCharacter) == true) ){  
                 checkPassword = false ; 
                 break ; 
             }
             }
      
      return checkPassword ; //(Satayabrata, 2022)
  }
  
  public boolean loginUser(String username, String password) {
      boolean matches = false; 
    
      if (username.equals(this.username) && password.equals(this.password)){
        matches = true; 
       } else if (!(username.equals(this.username) && password.equals(this.password))){
           matches = false; 
       } 
      
      return matches ; //(Farrell, 2019)
  }
   public String returnLoginStaus (String username, String password){
       String loginStaus = "" ;
       if(loginUser(username, password) ==  true) {
           loginStaus = "Welcome  " + getFirstname() + ", " + getLastname() + " it is great to see you again.";    
       } else if (loginUser( username, password) ==  false) {
           loginStaus = "Username or password incorrect, please try again";
 
       }
       
       return loginStaus;
   }
   
   public String registerUser() {
       String message = " "; 
       if(checkUserName() == true ) {
           JOptionPane.showMessageDialog(null, "Username successfully captured"); 
    
       } else if (checkUserName() == false ){
           JOptionPane.showMessageDialog(null, "Username is correctly formatted, please ensure that your username contains an underscore and is no more than 5 characters in length " );
       }
       
       if(checkPasswordComplexity() == true) {
           JOptionPane.showMessageDialog(null, "Password successfully captured") ; 
           
       } else if (checkPasswordComplexity() == false){
           
           
           JOptionPane.showMessageDialog(null, "Password is not \n" +
                                                                 "correctly formatted, \n" +
                                                                 "please ensure that \n" +
                                                                 "the password \n" +
                                                                 "contains at least 8 \n" +
                                                                 "characters, a capital \n" +
                                                                 "letter, a number and \n" +
                                                                 "a special character.");
           
               
       }
       
  
       
       if (checkPasswordComplexity() == true && checkUserName() == true){
       message = "Successfully registered";  
       } 
                 
       return message ; 
        
   }  
}


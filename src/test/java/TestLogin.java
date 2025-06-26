/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.easykanban.Login;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class TestLogin {
    
    public TestLogin() {
    }
    
    @Test
    public void testSetFirstname() {
    }

    @Test
    public void testGetFirstname() {
    }

    @Test
    public void testSetLastname() {
    }

    @Test
    public void testGetLastname() {
    }

    @Test
    public void testSetUsername() {
    }

    @Test
    public void testGetUsername() {
    }

    @Test
    public void testSetPassword() {
    }

    @Test
    public void testGetPassword() {
    }

    @Test
    public void testCheckUserName() {
        
        Login loginclass = new Login();
       loginclass.setFirstname("Kyle");
        loginclass.setLastname("Manrou");
        loginclass.setUsername("password");
        loginclass.setPassword("Ch&&sec@ke99!");
        
        assertEquals("Welcome "+ loginclass.getFirstname() + ", " + loginclass.getLastname() + "it is great to see you.", loginclass.returnLoginStaus ("kyl_1", "Ch&&sec@ke99!"));
        
         assertEquals("Username is not correctly formatted, please \n" +
"ensure that your username contains an \n" +
"underscore and is no more than 5 characters in \n" +
"length.", loginclass.returnLoginStaus("kyle!!!!!!!","Ch&&sec@ke99!" ));
    }

    @Test
    public void testCheckPasswordComplexity() {
    }

    @Test
    public void testLoginUser() {
        Login loginclass = new Login();
        loginclass.setUsername("password");
        loginclass.setPassword("Ch&&sec@ke99!");
        
        //assertTrue(loginclass.loginUser(loginclass.getUsername(), loginclass.getPassword()) == true, "Login Successfull");
        //assertTrue(loginclass.loginUser(loginclass.getUsername(), loginclass.getPassword()) == true, "Login Successfull");
    
    
    }

    @Test
    public void testReturnLoginStaus() {
         Login loginclass = new Login();
        loginclass.setUsername("password");
        loginclass.setPassword("Ch&&sec@ke99!");
        assertTrue(loginclass.loginUser(loginclass.getUsername(), loginclass.getPassword()) == true, "Login Successfull");
        
        
        loginclass.setUsername("kyle!!!!!!!");
        loginclass.setPassword("Ch&&sec@ke99!");
        assertTrue(loginclass.loginUser(loginclass.getUsername(), loginclass.getPassword()) == true, "Login Successfull");
    }

    @Test
    public void testRegisterUser() {
         Login loginclass = new Login();
        loginclass.setUsername("password");
        loginclass.setPassword("Ch&&sec@ke99!");
        
        
    }
}
    


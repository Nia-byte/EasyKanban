/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
import com.mycompany.easykanban.Task;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author lab_services_student
 */
public class TestTask {
    
    public TestTask() {
    }
        @Test
    public void testGetTaskName() {
    }
    
    @Test
    public void testSetTaskName() {
    }
    
    @Test
    public void testGetTaskNumber() {
    }
    
    @Test
    public void testSetTaskNumber() {
    }
    
    @Test
    public void testGetTaskDescription() {
    }
    
    @Test
    public void testSetTaskDescription() {
    }
    
    @Test
    public void testGetDeveloperDetails() {
    }
    
    @Test
    public void testSetDeveloperDetails() {
    }
    
    @Test
    public void testGetTaskDuration() {
    }
    
    @Test
    public void testSetTaskDuration() {
    }
    
    @Test
    public void testGetTaskStatus() {
    }
    
    @Test
    public void testSetTaskStatus() {
    }
    
    @Test
    public void testCheckTaskDescriptionTASK1() {
        Task task = new Task();
       
        final String actual = "Create Login to authenticate users";
        final String expected = "Task successfully captured";
        
        assertEquals(expected, task.checkTaskDescription(actual));
    }
    
    @Test
    public void testCheckTaskDescriptionTASK2() {
       Task task = new Task();
        
       final String actual = "Create Add Task feature to add task users...........";
       final String expected = "Task successfully captured";
        
        assertEquals(expected, task.checkTaskDescription(actual));
    }
    
    @Test
    public void testCreateTaskIDTASK1() {
       Task task = new Task();
        final String expected = "LO:0:BYN";
       assertEquals(expected, task.createTaskID("Login Feature", 0, "Robyn Harrison"));
    }
    
    @Test
    public void testCreateTaskIDTASK2() {
        Task task = new Task();
       
        final String expected = "AD:1:IKE";
       
        assertEquals(expected, task.createTaskID("Add Task Feature", 1, "Mike Smith"));
    }
    
    @Test
    public void testPrintTaskDetails() {
    }
    
    @Test
    public void testReturnTotalHours() {
        Task task = new Task();
        
        final int expected = 18;
        
        task.returnTotalHours(2, 10);
        task.returnTotalHours(2, 8);
        
        final int total = task.getTotal();
    
        assertEquals(expected, total);
    }
    
    @Test
    public void testReturnTotalHoursAdditionalTest() {
        Task task = new Task();
        final int expected = 89;
        
        task.returnTotalHours(5, 10);
        task.returnTotalHours(5, 12);
        task.returnTotalHours(5, 55);
        task.returnTotalHours(5, 11);
        task.returnTotalHours(5, 1);
        
        final int total = task.getTotal();
       
        assertEquals(expected, total);
    }
}
    


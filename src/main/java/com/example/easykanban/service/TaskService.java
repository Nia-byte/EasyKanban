/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.service;

import com.example.easykanban.entity.Task;
import com.example.easykanban.entity.TaskStatus;
import com.example.easykanban.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
/**
 *
 * @author lab_services_student
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;
    
    public Task createTask(Task task) {
        if (task.getTaskDescription() != null && task.getTaskDescription().length() > 50) {
            throw new IllegalArgumentException("Task description must be less than 50 characters");
        }
        
        // Generate a temporary task ID before first save
        task.setTaskId(generateTemporaryTaskId(task));
        
        // Save task first to get the auto-generated database ID
        Task savedTask = taskRepository.save(task);
        
        // Now update the task ID with the real database ID
        savedTask.setTaskId(generateFinalTaskId(savedTask));
        
        // Save again with the updated task ID
        return taskRepository.save(savedTask);
    }
    
    private String generateTemporaryTaskId(Task task) {
        if (task.getTaskName() == null || task.getTaskName().length() < 2) {
            return "UN:0:UNK";
        }
        
        if (task.getDeveloperDetails() == null || !task.getDeveloperDetails().contains(" ")) {
            return task.getTaskName().substring(0, 2).toUpperCase() + ":0:UNK";
        }
        
        String firstName = task.getDeveloperDetails().substring(0, task.getDeveloperDetails().indexOf(" "));
        String lastThreeLetters = firstName.length() >= 3 ? 
            firstName.substring(firstName.length() - 3).toUpperCase() : 
            firstName.toUpperCase();
            
        return task.getTaskName().substring(0, 2).toUpperCase() + ":0:" + lastThreeLetters;
    }
    
    private String generateFinalTaskId(Task task) {
        if (task.getTaskName() == null || task.getTaskName().length() < 2) {
            return "UN:" + task.getId() + ":UNK";
        }
        
        if (task.getDeveloperDetails() == null || !task.getDeveloperDetails().contains(" ")) {
            return task.getTaskName().substring(0, 2).toUpperCase() + ":" + task.getId() + ":UNK";
        }
        
        String firstName = task.getDeveloperDetails().substring(0, task.getDeveloperDetails().indexOf(" "));
        String lastThreeLetters = firstName.length() >= 3 ? 
            firstName.substring(firstName.length() - 3).toUpperCase() : 
            firstName.toUpperCase();
            
        return task.getTaskName().substring(0, 2).toUpperCase() + ":" + task.getId() + ":" + lastThreeLetters;
    }
    
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
    
    public List<Task> getTasksByBoard(Long boardId) {
        return taskRepository.findByBoardId(boardId);
    }
    
    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByTaskStatus(status);
    }
    
    public List<Task> getTasksByDeveloper(String developerName) {
        return taskRepository.findByDeveloperDetailsContaining(developerName);
    }
    
    public Optional<Task> updateTaskStatus(Long taskId, TaskStatus status) {
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setTaskStatus(status);
            return Optional.of(taskRepository.save(task));
        }
        return Optional.empty();
    }
    
    public boolean deleteTask(Long taskId) {
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }
    
    public Integer getTotalHours(Long boardId) {
        Integer total = taskRepository.getTotalHoursByBoardId(boardId);
        return total != null ? total : 0;
    }
    
    public List<Task> getTasksWithLongestDuration(Long boardId) {
        return taskRepository.findTasksWithLongestDuration(boardId);
    }
}
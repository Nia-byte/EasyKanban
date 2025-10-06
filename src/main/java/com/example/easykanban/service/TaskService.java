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
    @Autowired //Inject the UserRepository instance automatically
    private TaskRepository taskRepository;
    
    public Task createTask(Task task) { //Validates description and saves task
        if (task.getTaskDescription() != null && task.getTaskDescription().length() > 100) {
            throw new IllegalArgumentException("Task description must be less than 100 characters");
        }
        
        // Save task first to get the auto-generated ID
        Task savedTask = taskRepository.save(task);
        
        // Now update the task ID with the generated database ID
        savedTask.updateTaskId();
        
        // Save again with the updated task ID
        return taskRepository.save(savedTask);
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
    
    public List<Task> getTasksByDeveloper(String developerName) { //Searches tasks by developer name
        return taskRepository.findByDeveloperDetailsContaining(developerName);
    }
    
    public Optional<Task> updateTaskStatus(Long taskId, TaskStatus status) { //Changes task status
        Optional<Task> taskOpt = taskRepository.findById(taskId);
        if (taskOpt.isPresent()) {
            Task task = taskOpt.get();
            task.setTaskStatus(status);
            return Optional.of(taskRepository.save(task));
        }
        return Optional.empty();
    }
    
    public boolean deleteTask(Long taskId) { //Removes task
        if (taskRepository.existsById(taskId)) {
            taskRepository.deleteById(taskId);
            return true;
        }
        return false;
    }
    
    public Integer getTotalHours(Long boardId) { //Calculates total hours
        Integer total = taskRepository.getTotalHoursByBoardId(boardId);
        return total != null ? total : 0;
    }
    
    public List<Task> getTasksWithLongestDuration(Long boardId) { //Finds longest task
        return taskRepository.findTasksWithLongestDuration(boardId);
    }
}

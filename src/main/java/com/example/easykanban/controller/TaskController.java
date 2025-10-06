/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.controller;

import com.example.easykanban.service.TaskService;
import com.example.easykanban.entity.Task;
import com.example.easykanban.entity.TaskStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.Optional;
/**
 *
 * @author lab_services_student
 */
@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {
    
    @Autowired
    private TaskService taskService;
    
    @PostMapping
    public ResponseEntity<?> createTask(@Valid @RequestBody Task task) {
        try {
            Task createdTask = taskService.createTask(task);
            return ResponseEntity.ok(createdTask);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    
    @GetMapping("/board/{boardId}")
    public ResponseEntity<List<Task>> getTasksByBoard(@PathVariable Long boardId) {
        return ResponseEntity.ok(taskService.getTasksByBoard(boardId));
    }
    
    @GetMapping("/status/{status}")
    public ResponseEntity<List<Task>> getTasksByStatus(@PathVariable TaskStatus status) {
        return ResponseEntity.ok(taskService.getTasksByStatus(status));
    }
    
    @GetMapping("/developer/{developerName}")
    public ResponseEntity<List<Task>> getTasksByDeveloper(@PathVariable String developerName) {
        return ResponseEntity.ok(taskService.getTasksByDeveloper(developerName));
    }
    
    @PutMapping("/{taskId}/status")
    public ResponseEntity<?> updateTaskStatus(@PathVariable Long taskId, 
                                            @RequestBody Map<String, String> statusRequest) {
        try {
            TaskStatus status = TaskStatus.valueOf(statusRequest.get("status"));
            Optional<Task> updatedTask = taskService.updateTaskStatus(taskId, status);
            if (updatedTask.isPresent()) {
                return ResponseEntity.ok(updatedTask.get());
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", "Invalid status"));
        }
    }
    
    @DeleteMapping("/{taskId}")
    public ResponseEntity<?> deleteTask(@PathVariable Long taskId) {
        if (taskService.deleteTask(taskId)) {
            return ResponseEntity.ok(Map.of("message", "Task deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/board/{boardId}/total-hours")
    public ResponseEntity<Map<String, Integer>> getTotalHours(@PathVariable Long boardId) {
        Integer totalHours = taskService.getTotalHours(boardId);
        return ResponseEntity.ok(Map.of("totalHours", totalHours != null ? totalHours : 0));
    }
    
    @GetMapping("/board/{boardId}/longest-duration")
    public ResponseEntity<List<Task>> getTasksWithLongestDuration(@PathVariable Long boardId) {
        return ResponseEntity.ok(taskService.getTasksWithLongestDuration(boardId));
    }
}

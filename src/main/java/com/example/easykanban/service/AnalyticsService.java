/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.service;

import com.example.easykanban.entity.Task;
import com.example.easykanban.entity.TaskStatus;
import com.example.easykanban.repository.BoardRepository;
import com.example.easykanban.repository.TaskRepository;
import com.example.easykanban.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;
/**
 *
 * @author lab_services_student
 */
@Service
public class AnalyticsService {
    
    @Autowired
    private TaskRepository taskRepository;
    
    @Autowired
    private BoardRepository boardRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    // Board Analytics
    public Map<String, Object> getBoardAnalytics(Long boardId) {
        List<Task> tasks = taskRepository.findByBoardId(boardId);
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("boardId", boardId);
        analytics.put("totalTasks", tasks.size());
        analytics.put("totalHours", taskRepository.getTotalHoursByBoardId(boardId));
        analytics.put("tasksByStatus", getTaskCountByStatus(tasks));
        analytics.put("completionRate", calculateCompletionRate(tasks));
        analytics.put("averageTaskDuration", calculateAverageTaskDuration(tasks));
        analytics.put("tasksByDeveloper", getTaskCountByDeveloper(tasks));
        
        return analytics;
    }
    
     // Overall System Analytics
    public Map<String, Object> getSystemAnalytics() {
        List<Task> allTasks = taskRepository.findAll();
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("totalUsers", userRepository.count());
        analytics.put("totalBoards", boardRepository.count());
        analytics.put("totalTasks", allTasks.size());
        analytics.put("totalHours", allTasks.stream()
                .mapToInt(Task::getTaskDuration)
                .sum());
        analytics.put("tasksByStatus", getTaskCountByStatus(allTasks));
        analytics.put("completionRate", calculateCompletionRate(allTasks));
        analytics.put("topDevelopers", getTopDevelopers(allTasks, 5));
        
        return analytics;
    }
    
     // Developer Analytics
    public Map<String, Object> getDeveloperAnalytics(String developerName) {
        List<Task> developerTasks = taskRepository.findByDeveloperDetailsContaining(developerName);
        
        Map<String, Object> analytics = new HashMap<>();
        analytics.put("developerName", developerName);
        analytics.put("totalTasks", developerTasks.size());
        analytics.put("totalHours", developerTasks.stream()
                .mapToInt(Task::getTaskDuration)
                .sum());
        analytics.put("tasksByStatus", getTaskCountByStatus(developerTasks));
        analytics.put("completionRate", calculateCompletionRate(developerTasks));
        analytics.put("averageTaskDuration", calculateAverageTaskDuration(developerTasks));
        
        return analytics;
    }
    
    // Task Completion Trends
    public Map<String, Object> getCompletionTrends(Long boardId) {
        List<Task> tasks = taskRepository.findByBoardId(boardId);
        
        Map<String, Object> trends = new HashMap<>();
        trends.put("completedTasks", tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatus.DONE)
                .count());
        trends.put("inProgressTasks", tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatus.DOING)
                .count());
        trends.put("pendingTasks", tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatus.TO_DO)
                .count());
        trends.put("completionPercentage", calculateCompletionRate(tasks));
        
        return trends;
    }
    
     // Helper Methods
    private Map<TaskStatus, Long> getTaskCountByStatus(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getTaskStatus, Collectors.counting()));
    }
    
    private double calculateCompletionRate(List<Task> tasks) {
        if (tasks.isEmpty()) return 0.0;
        
        long completedTasks = tasks.stream()
                .filter(t -> t.getTaskStatus() == TaskStatus.DONE)
                .count();
        
        return (double) completedTasks / tasks.size() * 100;
    }
    
    private double calculateAverageTaskDuration(List<Task> tasks) {
        if (tasks.isEmpty()) return 0.0;
        
        return tasks.stream()
                .mapToInt(Task::getTaskDuration)
                .average()
                .orElse(0.0);
    }
    
    private Map<String, Long> getTaskCountByDeveloper(List<Task> tasks) {
        return tasks.stream()
                .collect(Collectors.groupingBy(Task::getDeveloperDetails, Collectors.counting()));
    }
    
    private List<Map<String, Object>> getTopDevelopers(List<Task> tasks, int limit) {
        Map<String, Long> taskCounts = getTaskCountByDeveloper(tasks);
        
        return taskCounts.entrySet().stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(limit)
                .map(entry -> {
                    Map<String, Object> dev = new HashMap<>();
                    dev.put("developer", entry.getKey());
                    dev.put("taskCount", entry.getValue());
                    
                    List<Task> devTasks = tasks.stream()
                            .filter(t -> t.getDeveloperDetails().equals(entry.getKey()))
                            .collect(Collectors.toList());
                    
                    dev.put("totalHours", devTasks.stream()
                            .mapToInt(Task::getTaskDuration)
                            .sum());
                    
                    return dev;
                })
                .collect(Collectors.toList());
    }
    
}

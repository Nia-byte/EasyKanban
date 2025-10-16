/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;
/**
 *
 * @author lab_services_student
 */
@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotBlank
    @Column(name = "task_name")
    private String taskName;
    
    @Size(max = 50, message = "Task description must be less than 50 characters")
    @Column(name = "task_description")
    private String taskDescription;
    
    @Column(name = "task_id", unique = true)
    private String taskId;
    
    @NotBlank
    @Column(name = "developer_details")
    private String developerDetails;
    
    @NotNull
    @Column(name = "task_duration")
    private Integer taskDuration;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "task_status")
    private TaskStatus taskStatus = TaskStatus.TO_DO;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();
    
    @ManyToOne
    @JoinColumn(name = "board_id")
    @JsonIgnore 
    private Board board;
    
    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore 
    private User user;
    
    // Default constructor
    public Task() {}
    
    // Constructor with parameters
    public Task(String taskName, String taskDescription, String developerDetails, 
                Integer taskDuration, Board board, User user) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.developerDetails = developerDetails;
        this.taskDuration = taskDuration;
        this.board = board;
        this.user = user;
        this.taskId = generateTaskId();
    }
    
    // Method to generate task ID (from your original logic)
    private String generateTaskId() {
        if (this.taskName == null || this.taskName.length() < 2) {
            return "UN:0:UNK"; // Unknown task ID for incomplete data
        }
        
        if (this.developerDetails == null || !this.developerDetails.contains(" ")) {
            return this.taskName.substring(0, 2).toUpperCase() + ":0:UNK";
        }
        
        String firstName = this.developerDetails.substring(0, this.developerDetails.indexOf(" "));
        Long taskNumber = this.id != null ? this.id : 0L;
        String lastThreeLetters = firstName.length() >= 3 ? 
            firstName.substring(firstName.length() - 3).toUpperCase() : 
            firstName.toUpperCase();
            
        return this.taskName.substring(0, 2).toUpperCase() + ":" + 
               taskNumber + ":" + lastThreeLetters;
    }
    
    // Method to update task ID after entity is saved
    public void updateTaskId() {
        this.taskId = generateTaskId();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getTaskName() {
        return taskName;
    }
    
    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }
    
    public String getTaskDescription() {
        return taskDescription;
    }
    
    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }
    
    public String getTaskId() {
        return taskId;
    }
    
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }
    
    public String getDeveloperDetails() {
        return developerDetails;
    }
    
    public void setDeveloperDetails(String developerDetails) {
        this.developerDetails = developerDetails;
    }
    
    public Integer getTaskDuration() {
        return taskDuration;
    }
    
    public void setTaskDuration(Integer taskDuration) {
        this.taskDuration = taskDuration;
    }
    
    public TaskStatus getTaskStatus() {
        return taskStatus;
    }
    
    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }
    
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
    
    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
    
    public Board getBoard() {
        return board;
    }
    
    public void setBoard(Board board) {
        this.board = board;
    }
    
    public User getUser() {
        return user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    
    // Method to update the updatedAt timestamp
    @PreUpdate
    public void preUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
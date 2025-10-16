/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.easykanban.repository;

import com.example.easykanban.entity.Task;
import com.example.easykanban.entity.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
/**
 *
 * @author lab_services_student
 */
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByBoardId(Long boardId); //Gets all tasks for a specific board
    List<Task> findByUserId(Long userId); //Gets all tasks for a specific user
    List<Task> findByTaskStatus(TaskStatus taskStatus); //Gets all tasks with a specific status
    List<Task> findByDeveloperDetailsContaining(String developerName); //Searches tasks by developer name
    
    @Query("SELECT SUM(t.taskDuration) FROM Task t WHERE t.board.id = ?1")
    Integer getTotalHoursByBoardId(Long boardId); // Calculates total hours for all tasks in a board
    
    @Query("SELECT t FROM Task t WHERE t.taskDuration = (SELECT MAX(t2.taskDuration) FROM Task t2 WHERE t2.board.id = ?1)")
    List<Task> findTasksWithLongestDuration(Long boardId); //Finds tasks with the longest duration in a board
}

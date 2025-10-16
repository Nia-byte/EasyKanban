/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.controller;

import com.example.easykanban.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
/**
 *
 * @author lab_services_student
 */

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {
    
    @Autowired
    private AnalyticsService analyticsService;
    
    @GetMapping("/board/{boardId}")
    public ResponseEntity<Map<String, Object>> getBoardAnalytics(@PathVariable Long boardId) {
        return ResponseEntity.ok(analyticsService.getBoardAnalytics(boardId));
    }
    
    @GetMapping("/system")
    public ResponseEntity<Map<String, Object>> getSystemAnalytics() {
        return ResponseEntity.ok(analyticsService.getSystemAnalytics());
    }
    
    @GetMapping("/developer/{developerName}")
    public ResponseEntity<Map<String, Object>> getDeveloperAnalytics(@PathVariable String developerName) {
        return ResponseEntity.ok(analyticsService.getDeveloperAnalytics(developerName));
    }
    
    @GetMapping("/board/{boardId}/trends")
    public ResponseEntity<Map<String, Object>> getCompletionTrends(@PathVariable Long boardId) {
        return ResponseEntity.ok(analyticsService.getCompletionTrends(boardId));
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.controller;


import com.example.easykanban.entity.Board;
import com.example.easykanban.service.BoardService;
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
@RequestMapping("/api/boards")
@CrossOrigin(origins = "*")
public class BoardController {
    
    @Autowired
    private BoardService boardService;
    
    @PostMapping
    public ResponseEntity<?> createBoard(@Valid @RequestBody Board board) {
        try {
            Board createdBoard = boardService.createBoard(board);
            return ResponseEntity.ok(createdBoard);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
    
     @GetMapping
    public ResponseEntity<List<Board>> getAllBoards() {
        return ResponseEntity.ok(boardService.getAllBoards());
    }
    
    @GetMapping("/{boardId}")
    public ResponseEntity<?> getBoardById(@PathVariable Long boardId) {
        Optional<Board> board = boardService.getBoardById(boardId);
        if (board.isPresent()) {
            return ResponseEntity.ok(board.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
     @GetMapping("/user/{userId}")
    public ResponseEntity<List<Board>> getBoardsByUser(@PathVariable Long userId) {
        return ResponseEntity.ok(boardService.getBoardsByUser(userId));
    }
    
     @PutMapping("/{boardId}")
    public ResponseEntity<?> updateBoard(@PathVariable Long boardId, 
                                        @Valid @RequestBody Board boardDetails) {
        Optional<Board> updatedBoard = boardService.updateBoard(boardId, boardDetails);
        if (updatedBoard.isPresent()) {
            return ResponseEntity.ok(updatedBoard.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @DeleteMapping("/{boardId}")
    public ResponseEntity<?> deleteBoard(@PathVariable Long boardId) {
        if (boardService.deleteBoard(boardId)) {
            return ResponseEntity.ok(Map.of("message", "Board deleted successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

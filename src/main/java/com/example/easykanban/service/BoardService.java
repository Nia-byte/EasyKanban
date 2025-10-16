/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.easykanban.service;

import com.example.easykanban.entity.Board;
import com.example.easykanban.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author lab_services_student
 */
@Service
public class BoardService {
    @Autowired //Inject the UserRepository instance automatically
    private BoardRepository boardRepository;
    
    public Board createBoard(Board board) { //Creates and saves board to db 
        return boardRepository.save(board);
    }
    
    public List<Board> getAllBoards() {
        return boardRepository.findAll();
    }
    
    public List<Board> getBoardsByUser(Long userId) {
        return boardRepository.findByUserId(userId);
    }
    
    public Optional<Board> getBoardById(Long boardId) {
        return boardRepository.findById(boardId);
    }
    
    public Optional<Board> updateBoard(Long boardId, Board boardDetails){
        Optional<Board> boardOpt = boardRepository.findById(boardId);
        
        if (boardOpt.isPresent()){
            Board board = boardOpt.get();
            board.setName(boardDetails.getName());
            board.setDescription(boardDetails.getDescription());
            return Optional.of(boardRepository.save(board));
        }
        
        return Optional.empty();
    }
            
            
    public boolean deleteBoard(Long boardId) {
        if (boardRepository.existsById(boardId)) {
            boardRepository.deleteById(boardId);
            return true;
        }
        return false;
    }
}

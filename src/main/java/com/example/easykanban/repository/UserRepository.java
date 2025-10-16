/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.example.easykanban.repository;

import com.example.easykanban.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;
/**
 *
 * @author lab_services_student
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> { //Provides basic CRUD operations for User entity
    Optional<User> findByUsername(String username); //Finds user by username
    boolean existsByUsername(String username); //Checks if username already exists
}

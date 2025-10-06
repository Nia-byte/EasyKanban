CREATE DATABASE easykanban;
   USE easykanban;
   
    CREATE TABLE users (
       id BIGINT PRIMARY KEY AUTO_INCREMENT,
       first_name VARCHAR(255) NOT NULL,
       last_name VARCHAR(255) NOT NULL,
       username VARCHAR(255) UNIQUE NOT NULL,
       password VARCHAR(255) NOT NULL,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
   );
   
   CREATE TABLE boards (
       id BIGINT PRIMARY KEY AUTO_INCREMENT,
       name VARCHAR(255) NOT NULL,
       description TEXT,
       user_id BIGINT,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   
   CREATE TABLE tasks (
       id BIGINT PRIMARY KEY AUTO_INCREMENT,
       task_name VARCHAR(255) NOT NULL,
       task_description VARCHAR(255),
       task_id VARCHAR(255) UNIQUE NOT NULL,
       developer_details VARCHAR(255) NOT NULL,
       task_duration INT NOT NULL,
       task_status ENUM('TO_DO', 'DOING', 'DONE') DEFAULT 'TO_DO',
       board_id BIGINT,
       user_id BIGINT,
       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
       FOREIGN KEY (board_id) REFERENCES boards(id),
       FOREIGN KEY (user_id) REFERENCES users(id)
   );
   
   SHOW TABLES;
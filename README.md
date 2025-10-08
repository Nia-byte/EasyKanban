![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-Java%2021-red.svg)
![Framework](https://img.shields.io/badge/framework-Spring%20Boot-green.svg)
![IDE](https://img.shields.io/badge/IDE-NetBeans-orange.svg)
# EasyKanban - Task Management REST API
A modern Spring Boot REST API for task management, built to transform a console-based Java application into a scalable, cloud-ready solution with RESTful endpoints for task and board management.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Database Setup](#firebase-setup)
- [Building and Running](#building-and-running)
- [Testing the API](#user-roles)
- [Testing the API](#app-features-by-role)
- [Future Enhancements](#screenshots)
- [Deployment](#contributing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

  ## 🎯 Overview
**EasyKanban** is a comprehensive task management REST API designed for enterprise workflow automation, reflecting ERP-style process management similar to SAP's workflow modules. The application evolved from a console-based Java Swing application into a modern Spring Boot REST API with MySQL persistence, validation, and cloud deployment capabilities.

### Key Highlights

- **RESTful Architecture**: Clean API design following REST principles
- **Validation**: Username, password, and task validation from original business logic
- **MySQL Integration**: Relational database for structured task, board, and user data
- **Spring Data JPA**: Automatic repository implementation with custom queries
- **Task ID Generation**: Unique business identifiers (e.g., LO:1:IKE)
- **Cloud-Ready**: Configured for AWS deployment (Elastic Beanstalk + RDS)
- **Comprehensive Analytics**: Total hours, longest duration, and developer-based task tracking

## ✨ Features

### User Management
- 🔐 **User Registration**: Create accounts with validation
      - Username must contain underscore and be max 5 characters
      - Password must be 8+ characters with uppercase, number, and special character
- 🔑 **User Login**: Secure authentication with credential validation
- 👤 **Profile Management**: Track user information and creation timestamps

### Task Management

- ✅ **Task Creation**: Create tasks with comprehensive details
      - Auto-generated Task IDs (format: TaskName:ID:Developer)
      - Task description validation (max 50 characters)
      - Developer assignment and duration tracking
- 📊 **Task Status Management**: Track progress (TO_DO, DOING, DONE)
- 🔍 **Advanced Search**:
      - Search tasks by developer name
      - Filter by status
      - Find tasks by board
- 📈 **Analytics**:
      - Calculate total hours per board
      - Find tasks with longest duration
      - Developer workload tracking
- ❌ **Task Deletion**: View and manage incoming orders

### Board Management
- 📋 **Board Creation**: Organize tasks into boards
- 👥 **User-Board Linking**: Associate boards with users
- 📁 **Board Organization**: Group related tasks together

###  Validation & Business Rules
- 📊 **Username Validation**: Must contain _ and be ≤ 5 characters
- 📋 **Password Complexity**: Min 8 chars, uppercase, number, special character
- 🎯 **Task Description**: Max 50 characters
- 📈 **Duplicate Prevention**: No duplicate usernames
- ✅ **Relational Integrity**: Foreign key constraints enforced


## 🛠 Technology Stack

### Core Technologies
- **Language**: Java 21
- **Framework**: Spring Boot 3.4.9
- **Build Tool**: Maven 3.9+
- **Database**: MySQL 8.0
- **Architecture**: RESTful API with MVC pattern

### Libraries & Dependencies

#### Core Spring Boot

- `spring-boot-starter-web` - RESTful web services
- `spring-boot-starter-data-jpa` - JPA and Hibernate
- `spring-boot-starter-validation` - Bean validation
- `spring-boot-devtool`s - Development tools

#### Database

- `mysql-connector-j` - MySQL JDBC driver
- **Hibernate**: ORM framework (included with Spring Data JPA)
- **HikariCP**: High-performance connection pooling

#### Additional Dependencies

- `jakarta.persistence-api` - JPA specification
- `jakarta.validation-api` - Validation annotations
- `jackson-databind` - JSON serialization/deserialization
  
#### Testing

- `jspring-boot-starter-test` - JPA specification
- `junit-jupiter` - Unit testing
- `mockito` - Unit testing

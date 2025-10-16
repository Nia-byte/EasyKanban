# EasyKanban - Task Management
![License](https://img.shields.io/badge/license-MIT-blue.svg)
![Platform](https://img.shields.io/badge/platform-Spring%20Boot-green.svg)
![Java](https://img.shields.io/badge/java-21-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen.svg)

A modern RESTful API backend for kanban task management, built with Spring Boot to provide project and task management capabilities with user authentication, analytics, and real-time task tracking.

## 📋 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Project Structure](#project-structure)
- [Database Setup](#database-setup)
- [Building and Running](#building-and-running)
- [API Endpoints](#api-endpoints)
- [Security & Authentication](#security--authentication)
- [Contributing](#contributing)
- [License](#license)

## 🎯 Overview

**EasyKanban** is a task management backend API designed for agile project management. The application provides a robust RESTful API for managing users, boards, tasks, and analytics with JWT-based authentication and role-based access control.

### Key Highlights

- **RESTful API Architecture**: Clean, scalable REST endpoints
- **JWT Authentication**: Secure token-based authentication
- **Task Management**: Full CRUD operations for tasks with status tracking
- **Board Organization**: Multi-board support for project organization
- **Analytics Dashboard**: Reporting and metrics
- **MySQL Database**: Reliable relational database storage
- **Spring Security**: Security implementation

## ✨ Features

### User Management
- **Secure Authentication**: JWT token-based authentication
- **User Registration**: Username and password validation
- **Password Encryption**: BCrypt password hashing
- **Username Validation**: Must contain underscore and max 5 characters
- **Password Requirements**: Minimum 8 characters with uppercase, number, and special character

### Task Management
- **Task CRUD Operations**: Create, read, update, and delete tasks
- **Task Status Tracking**: TO_DO, DOING, DONE status workflow
- **Duration Tracking**: Task duration in hours
- **Developer Assignment**: Assign tasks to developers
- **Auto-generated Task IDs**: Format: [TASK_NAME:ID:DEVELOPER]
- **Task Filtering**: Filter by board, status, or developer
- **Longest Duration Query**: Find tasks with maximum duration
- **Total Hours Calculation**: Calculate total hours per board

### Board Management
- **Board Creation**: Organize tasks into separate boards
- **Multi-board Support**: Multiple boards per user
- **Board Editing**: Update board name and description
- **Board Deletion**: Remove boards with cascade handling
- **User-specific Boards**: Filter boards by user

### Analytics & Reports
- **Board Analytics**: Task counts, completion rates, hours by board
- **System Analytics**: Overall system metrics and statistics
- **Developer Analytics**: Individual developer performance metrics
- **Completion Trends**: Task completion tracking over time
- **Top Developers**: Leaderboard of most productive developers
- **Completion Rate**: Percentage of completed tasks
- **Average Duration**: Average task duration calculations

### Security Features
- **JWT Token Generation**: Secure token creation with expiration
- **Token Validation**: Middleware for request authentication
- **CORS Configuration**: Cross-origin request handling
- **BCrypt Encryption**: Industry-standard password hashing
- **Stateless Sessions**: Token-based stateless authentication

## 🛠 Technology Stack

### Core Technologies
- **Language**: Java 21
- **Framework**: Spring Boot 3.x
- **Architecture**: RESTful API
- **Build System**: Maven
- **Database**: MySQL

### Libraries & Dependencies

#### Spring Framework
- `spring-boot-starter-web` - REST API development
- `spring-boot-starter-data-jpa` - Database ORM
- `spring-boot-starter-security` - Security and authentication
- `spring-boot-starter-validation` - Bean validation

#### Security
- `jjwt-api:0.12.3` - JWT token handling
- `jjwt-impl:0.12.3` - JWT implementation
- `jjwt-jackson:0.12.3` - JSON processing for JWT
- `spring-security-crypto` - Password encryption

#### Database
- `mysql-connector-java` - MySQL JDBC driver
- `spring-data-jpa` - JPA repository support

#### Testing
- `spring-boot-starter-test` - Testing framework
- `junit-jupiter` - Unit testing
- `spring-security-test` - Security testing

## 📋 Prerequisites

Before you begin, ensure you have the following installed:

- **Java Development Kit**: JDK 21 or later
- **Maven**: 3.8 or later
- **MySQL**: 8.0 or later
- **IDE**: IntelliJ IDEA, Eclipse, or VS Code with Java extensions
- **Postman**: For API testing

### Minimum System Requirements
- 4GB RAM minimum (8GB recommended)
- 500MB free disk space
- Internet connection for dependency downloads

## 📦 Installation

### 1. Clone the Repository

```
git clone [https://github.com/yourusername/easykanban-backend.git](https://github.com/Nia-byte/EasyKanban.git)
cd easykanban
```

### 2. Configure Database

Create MySQL database:
```
CREATE DATABASE easykanban;
USE easykanban;
```

Run the database schema script:
```
mysql -u root -p easykanban < EASYKANBAN_SCRIPT.sql
```

### 3. Configure Application Properties

Edit `src/main/resources/application.properties`:

```properties
# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/easykanban
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# JWT Configuration
jwt.secret=yourSecretKeyHere
jwt.expiration=86400000

# Server Configuration
server.port=8080
```

### 4. Install Dependencies

```
mvn clean install
```

## 📁 Project Structure

```
easykanban-backend/
├── src/
│   ├── main/
│   │   ├── java/com/example/easykanban/
│   │   │   ├── controller/         # REST API Controllers
│   │   │   │   ├── AnalyticsController.java
│   │   │   │   ├── BoardController.java
│   │   │   │   ├── TaskController.java
│   │   │   │   └── UserController.java
│   │   │   ├── entity/            # JPA Entity Models
│   │   │   │   ├── Board.java
│   │   │   │   ├── Task.java
│   │   │   │   ├── TaskStatus.java
│   │   │   │   └── User.java
│   │   │   ├── repository/        # Data Access Layer
│   │   │   │   ├── BoardRepository.java
│   │   │   │   ├── TaskRepository.java
│   │   │   │   └── UserRepository.java
│   │   │   ├── service/           # Business Logic Layer
│   │   │   │   ├── AnalyticsService.java
│   │   │   │   ├── BoardService.java
│   │   │   │   ├── TaskService.java
│   │   │   │   └── UserService.java
│   │   │   ├── security/          # Security Configuration
│   │   │   │   ├── JwtAuthenticationFilter.java
│   │   │   │   ├── JwtTokenProvider.java
│   │   │   │   └── SecurityConfig.java
│   │   │   └── EasyKanbanApplication.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── EASYKANBAN_SCRIPT.sql
│   └── test/                      # Unit Tests
│       └── java/com/example/easykanban/
├── pom.xml                        # Maven configuration
├── LICENSE                        # MIT License
└── README.md                      # This file
```

## 🗄️ Database Setup

### Running the Schema Script

```
# Connect to MySQL
mysql -u root -p

# Run the script
source /path/to/EASYKANBAN_SCRIPT.sql
```

## 🚀 Building and Running

```
# Right click 
EasykanbanApplication.java

# Select run file
```

The application will start at `http://localhost:8080`

## 🔌 API Endpoints

### Authentication Endpoints

#### Register User
```
POST /api/users/register
Content-Type: application/json

{
    "firstName": "Nia",
    "lastName": "Diale",
    "username": "ni_ni",
    "password": "Password123*"
}
```

**Response:**
```
{
    "message": "User registered successfully",
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "user": {
        "id": 1,
        "username": "ni_ni",
        "firstName": "Nia",
        "lastName": "Diale"
    }
}
```

#### Login User
```
POST /api/users/login
Content-Type: application/json

{
    "username": "ni_ni",
    "password": "Password123*"
}
```

**Response:**
```
{
    "message": "Login successful",
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "user": {
        "id": 1,
        "username": "ni_ni",
        "firstName": "Nia",
        "lastName": "Diale"
    }
}
```

### Board Endpoints

#### Create Board
```
POST /api/boards
Authorization: Bearer {token}
Content-Type: application/json

{
    "name": "Sprint 1 ",
    "description": "Planning & Research ",
    "userId": 1
}
```

#### Get All Boards
```
GET /api/boards
Authorization: Bearer {token}
```

#### Get Board by ID
```
GET /api/boards/{boardId}
Authorization: Bearer {token}
```

#### Get Boards by User
```
GET /api/boards/user/{userId}
Authorization: Bearer {token}
```

#### Update Board
```
PUT /api/boards/{boardId}
Authorization: Bearer {token}
Content-Type: application/json

{
    "name": "Sprint 1 - City Of Tshwane",
    "description": "Reasearch system"
}
```

#### Delete Board
```
DELETE /api/boards/{boardId}
Authorization: Bearer {token}
```

### Task Endpoints

#### Create Task
```
POST /api/tasks
Authorization: Bearer {token}
Content-Type: application/json

{
    "taskName": "Login Feature",
    "taskDescription": "Design the UI",
    "developerDetails": "Robyn Harrison",
    "taskDuration": 8,
    "taskStatus": "TO_DO",
    "boardId": 1,
    "userId": 1
}
```

**Response:**
```
{
    "id": 1,
    "taskName": "Login Feature",
    "taskDescription": "Design the UI",
    "taskId": "LO:1:BYN",
    "developerDetails": "Robyn Harrison",
    "taskDuration": 8,
    "taskStatus": "TO_DO",
    "createdAt": "2025-03-22T01:30:00"
}
```

#### Get All Tasks
```
GET /api/tasks
Authorization: Bearer {token}
```

#### Get Tasks by Board
```
GET /api/tasks/board/1
Authorization: Bearer {token}
```

#### Get Tasks by Status
```
GET /api/tasks/status/TO_DO
Authorization: Bearer {token}
```

#### Get Tasks by Developer
```
GET /api/tasks/developer/Robyn Harrison
Authorization: Bearer {token}
```

#### Update Task Status
```
PUT /api/tasks/LO:1:BYN/status
Authorization: Bearer {token}
Content-Type: application/json

{
    "status": "DOING"
}
```

#### Delete Task
```
DELETE /api/tasks/LO:1:BYN
Authorization: Bearer {token}
```

#### Get Total Hours by Board
```
GET /api/tasks/board/1/total-hours
Authorization: Bearer {token}
```

**Response:**
```
{
    "totalHours": 45
}
```

#### Get Tasks with Longest Duration
```
GET /api/tasks/board/1/longest-duration
Authorization: Bearer {token}
```

### Analytics Endpoints

#### Get Board Analytics
```
GET /api/analytics/board/1
Authorization: Bearer {token}
```

#### Get System Analytics
```
GET /api/analytics/system
Authorization: Bearer {token}
```

#### Get Developer Analytics
```
GET /api/analytics/developer/Robyn Harrison
Authorization: Bearer {token}
```

#### Get Completion Trends
```http
GET /api/analytics/board/1/trends
Authorization: Bearer {token}
```

## 🔒 Security & Authentication

### JWT Token Flow

1. **User Registration/Login**: Client sends credentials
2. **Token Generation**: Server generates JWT token with user info
3. **Token Storage**: Client stores token (localStorage, sessionStorage)
4. **Authenticated Requests**: Client includes token in Authorization header
5. **Token Validation**: Server validates token on each request

### Password Validation Rules

- Minimum 8 characters
- At least one uppercase letter
- At least one number
- At least one special character

### Username Validation Rules

- Must contain underscore (_)
- Maximum 5 characters

### Using JWT Token in Requests

```
GET /api/tasks
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIs...
```


## 📊 Key Features Explained

### Task ID Generation

Tasks automatically receive unique IDs in the format: `[TASK_NAME:ID:DEVELOPER]`

Example: `LO:1:BYN` for:
- Task Name: **LO**gin Feature
- Database ID: **1**
- Developer: Ro**BYN** Harrison

### Analytics Calculations

- **Completion Rate**: (Completed Tasks / Total Tasks) × 100
- **Average Duration**: Total Hours / Total Tasks
- **Top Developers**: Sorted by task count and total hours

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the Repository**
```bash
git clone [https://github.com/yourusername/easykanban-backend.git](https://github.com/Nia-byte/EasyKanban.git)
```

2. **Create a Feature Branch**
```bash
git checkout -b feature/YourFeatureName
```

3. **Commit Your Changes**
```bash
git commit -m "Add some feature"
```

4. **Push to Branch**
```bash
git push origin feature/YourFeatureName
```

5. **Open a Pull Request**

## 📄 License

```
MIT License

Copyright (c) 2025 Nia Diale

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```


## 🙏 Acknowledgments

- **Spring Framework** - Comprehensive Java framework
- **JWT.io** - JWT token implementation
- **MySQL** - Reliable database system
- **Maven** - Dependency management
- **Java Community** - Excellent libraries and tools

## 📚 Additional Resources

### Documentation
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security](https://spring.io/projects/spring-security)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [JWT.io](https://jwt.io/)
- [MySQL Documentation](https://dev.mysql.com/doc/)
- [REST API Guidelines](https://restfulapi.net/)

### Tutorials
- [Spring Boot REST API Tutorial](https://spring.io/guides/gs/rest-service/)
- [Spring Security with JWT](https://www.toptal.com/spring/spring-security-tutorial)
- [Spring Data JPA Guide](https://spring.io/guides/gs/accessing-data-jpa/)

---

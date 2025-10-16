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
- [Testing](#testing)
- [Contributing](#contributing)
- [License](#license)
- [Contact](#contact)

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
- 📈 **Board Analytics**: Task counts, completion rates, hours by board
- 🌐 **System Analytics**: Overall system metrics and statistics
- 👨‍💻 **Developer Analytics**: Individual developer performance metrics
- 📊 **Completion Trends**: Task completion tracking over time
- 🏆 **Top Developers**: Leaderboard of most productive developers
- 💯 **Completion Rate**: Percentage of completed tasks
- ⏱️ **Average Duration**: Average task duration calculations

### Security Features
- 🔒 **JWT Token Generation**: Secure token creation with expiration
- 🛡️ **Token Validation**: Middleware for request authentication
- 🚫 **CORS Configuration**: Cross-origin request handling
- 🔐 **BCrypt Encryption**: Industry-standard password hashing
- 🎫 **Stateless Sessions**: Token-based stateless authentication

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
- **Postman/Insomnia**: For API testing (optional)
- **Git**: For version control

### Minimum System Requirements
- 4GB RAM minimum (8GB recommended)
- 500MB free disk space
- Internet connection for dependency downloads

## 📦 Installation

### 1. Clone the Repository

```bash
git clone https://github.com/yourusername/easykanban-backend.git
cd easykanban-backend
```

### 2. Configure Database

Create MySQL database:
```sql
CREATE DATABASE easykanban;
USE easykanban;
```

Run the database schema script:
```bash
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

```bash
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

### Database Schema

The application uses MySQL with the following structure:

#### Users Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Boards Table
```sql
CREATE TABLE boards (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id)
);
```

#### Tasks Table
```sql
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
```

### Running the Schema Script

```bash
# Connect to MySQL
mysql -u root -p

# Run the script
source /path/to/EASYKANBAN_SCRIPT.sql
```

## 🚀 Building and Running

### Using Maven

```bash
# Build the project
mvn clean package

# Run the application
mvn spring-boot:run

# Run on custom port
mvn spring-boot:run -Dspring-boot.run.arguments=--server.port=9090
```

### Using Java

```bash
# Build JAR file
mvn clean package -DskipTests

# Run JAR
java -jar target/easykanban-0.0.1-SNAPSHOT.jar
```

### Development Mode

```bash
# Run with hot reload
mvn spring-boot:run -Dspring-boot.run.jvmArguments="-Dspring.devtools.restart.enabled=true"
```

The application will start at `http://localhost:8080`

## 🔌 API Endpoints

### Authentication Endpoints

#### Register User
```http
POST /api/users/register
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "username": "john_",
    "password": "Password123!"
}
```

**Response:**
```json
{
    "message": "User registered successfully",
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "user": {
        "id": 1,
        "username": "john_",
        "firstName": "John",
        "lastName": "Doe"
    }
}
```

#### Login User
```http
POST /api/users/login
Content-Type: application/json

{
    "username": "john_",
    "password": "Password123!"
}
```

**Response:**
```json
{
    "message": "Login successful",
    "token": "eyJhbGciOiJIUzUxMiJ9...",
    "user": {
        "id": 1,
        "username": "john_",
        "firstName": "John",
        "lastName": "Doe"
    }
}
```

### Board Endpoints

#### Create Board
```http
POST /api/boards
Authorization: Bearer {token}
Content-Type: application/json

{
    "name": "Project Alpha",
    "description": "Main development board",
    "userId": 1
}
```

#### Get All Boards
```http
GET /api/boards
Authorization: Bearer {token}
```

#### Get Board by ID
```http
GET /api/boards/{boardId}
Authorization: Bearer {token}
```

#### Get Boards by User
```http
GET /api/boards/user/{userId}
Authorization: Bearer {token}
```

#### Update Board
```http
PUT /api/boards/{boardId}
Authorization: Bearer {token}
Content-Type: application/json

{
    "name": "Updated Board Name",
    "description": "Updated description"
}
```

#### Delete Board
```http
DELETE /api/boards/{boardId}
Authorization: Bearer {token}
```

### Task Endpoints

#### Create Task
```http
POST /api/tasks
Authorization: Bearer {token}
Content-Type: application/json

{
    "taskName": "Login Feature",
    "taskDescription": "Implement user authentication",
    "developerDetails": "Robyn Harrison",
    "taskDuration": 8,
    "taskStatus": "TO_DO",
    "boardId": 1,
    "userId": 1
}
```

**Response:**
```json
{
    "id": 1,
    "taskName": "Login Feature",
    "taskDescription": "Implement user authentication",
    "taskId": "LO:1:BYN",
    "developerDetails": "Robyn Harrison",
    "taskDuration": 8,
    "taskStatus": "TO_DO",
    "createdAt": "2025-10-16T10:30:00"
}
```

#### Get All Tasks
```http
GET /api/tasks
Authorization: Bearer {token}
```

#### Get Tasks by Board
```http
GET /api/tasks/board/{boardId}
Authorization: Bearer {token}
```

#### Get Tasks by Status
```http
GET /api/tasks/status/{status}
Authorization: Bearer {token}
```

#### Get Tasks by Developer
```http
GET /api/tasks/developer/{developerName}
Authorization: Bearer {token}
```

#### Update Task Status
```http
PUT /api/tasks/{taskId}/status
Authorization: Bearer {token}
Content-Type: application/json

{
    "status": "DOING"
}
```

#### Delete Task
```http
DELETE /api/tasks/{taskId}
Authorization: Bearer {token}
```

#### Get Total Hours by Board
```http
GET /api/tasks/board/{boardId}/total-hours
Authorization: Bearer {token}
```

**Response:**
```json
{
    "totalHours": 45
}
```

#### Get Tasks with Longest Duration
```http
GET /api/tasks/board/{boardId}/longest-duration
Authorization: Bearer {token}
```

### Analytics Endpoints

#### Get Board Analytics
```http
GET /api/analytics/board/{boardId}
Authorization: Bearer {token}
```

**Response:**
```json
{
    "boardId": 1,
    "totalTasks": 15,
    "totalHours": 120,
    "tasksByStatus": {
        "TO_DO": 5,
        "DOING": 3,
        "DONE": 7
    },
    "completionRate": 46.67,
    "averageTaskDuration": 8.0,
    "tasksByDeveloper": {
        "John Doe": 5,
        "Jane Smith": 10
    }
}
```

#### Get System Analytics
```http
GET /api/analytics/system
Authorization: Bearer {token}
```

**Response:**
```json
{
    "totalUsers": 25,
    "totalBoards": 40,
    "totalTasks": 350,
    "totalHours": 2800,
    "tasksByStatus": {
        "TO_DO": 120,
        "DOING": 80,
        "DONE": 150
    },
    "completionRate": 42.86,
    "topDevelopers": [
        {
            "developer": "John Doe",
            "taskCount": 45,
            "totalHours": 360
        }
    ]
}
```

#### Get Developer Analytics
```http
GET /api/analytics/developer/{developerName}
Authorization: Bearer {token}
```

#### Get Completion Trends
```http
GET /api/analytics/board/{boardId}/trends
Authorization: Bearer {token}
```

## 🔒 Security & Authentication

### JWT Token Flow

1. **User Registration/Login**: Client sends credentials
2. **Token Generation**: Server generates JWT token with user info
3. **Token Storage**: Client stores token (localStorage, sessionStorage)
4. **Authenticated Requests**: Client includes token in Authorization header
5. **Token Validation**: Server validates token on each request

### JWT Token Structure

```json
{
    "sub": "username",
    "userId": 1,
    "iat": 1634567890,
    "exp": 1634654290
}
```

### Password Validation Rules

- Minimum 8 characters
- At least one uppercase letter
- At least one number
- At least one special character

### Username Validation Rules

- Must contain underscore (_)
- Maximum 5 characters

### Using JWT Token in Requests

```http
GET /api/tasks
Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VybmFtZSIsInVzZXJJZCI6MSwiaWF0IjoxNjM0NTY3ODkwLCJleHAiOjE2MzQ2NTQyOTB9...
```

## 🧪 Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=UserServiceTest
```

### Run with Coverage

```bash
mvn clean test jacoco:report
```

### Test Categories

- **Unit Tests**: Service layer business logic
- **Integration Tests**: Repository and database operations
- **Controller Tests**: REST API endpoint testing
- **Security Tests**: Authentication and authorization

### Example Test Structure

```java
@SpringBootTest
class TaskServiceTest {
    
    @Autowired
    private TaskService taskService;
    
    @Test
    void testCreateTask() {
        // Test implementation
    }
}
```

## 📊 Key Features Explained

### Task ID Generation

Tasks automatically receive unique IDs in the format: `[TASK_NAME:ID:DEVELOPER]`

Example: `LO:1:BYN` for:
- Task Name: **LO**gin Feature
- Database ID: **1**
- Developer: Ro**BYN** Harrison

### Task Status Workflow

```
TO_DO → DOING → DONE
```

### Analytics Calculations

- **Completion Rate**: (Completed Tasks / Total Tasks) × 100
- **Average Duration**: Total Hours / Total Tasks
- **Top Developers**: Sorted by task count and total hours

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the Repository**
```bash
git clone https://github.com/yourusername/easykanban-backend.git
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

### Coding Standards

- Follow Java naming conventions
- Use meaningful variable and method names
- Add JavaDoc comments for public methods
- Write unit tests for new features
- Ensure all tests pass before submitting PR
- Follow REST API best practices

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

```
MIT License

Copyright (c) 2025 EasyKanban Development Team

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

## 📞 Contact

**Project Repository**: [EasyKanban Backend](https://github.com/yourusername/easykanban-backend)

### Support

If you encounter any issues or have questions:

1. Check existing [Issues](https://github.com/yourusername/easykanban-backend/issues)
2. Create a new issue with detailed description
3. Contact the repository maintainer

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

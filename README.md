# EasyKaban
EasyKanban is a Java application that mimics a simple Kanban board system. It allows users to register, log in, and manage their tasks.

## 📦 Features

- User Registration & Login
- Add Multiple Tasks
- Search for Tasks by Name or Developer
- Delete Tasks
- Calculate Total Task Duration
- Identify Task with Longest Duration
- Display All Tasks


## 🛠️ Technologies Used

- Java

## 🔧 How It Works

1. **User selects** to Register or Login.
2. **Registration** checks username and password validity:
   - Username must include an underscore and be less than or equal to 5 characters.
   - Password must include a capital letter, number, and special character.
3. **Login** authenticates user against saved credentials.
4. Once logged in, the user can:
   - Add tasks with full details (name, developer, duration, status).
   - View and search tasks by developer or task name.
   - Delete tasks and update the task list.
   - Identify the task with the longest duration.

## ✅ Task Status Options

- To Do
- Doing
- Done
  
##  Programming Concepts Used

###  **Object-Oriented Programming (OOP)**
- **Encapsulation**
- **Abstraction**
- **Separation of Concerns**

###  **Control Structures**
- Conditional statements (`if`, `else if`, `else`)
- Loops (`for`, `while`)

###  **Arrays**
- Used to store tasks and iterate through them for searching, filtering, or displaying.

### **Methods and Functions**
- Use of custom methods for actions like adding a task, calculating total duration, validating login, etc.

###  **GUI with JOptionPane**

###  **Basic Authentication Logic**
- Usernames and passwords are validated using string manipulation and regular expressions (if extended).

---## 🗃️ Structure

Main class:
- `EasyKanban.java`: Main application logic

Supporting classes (not included in this snippet but required to run):
- `Login.java`: Handles registration, login, validation
- `Task.java`: Handles task creation, validation, storage, and display

## 📌 To Run

1. Clone the repo:
   ```bash
   git clone https://github.com/Nia-byte/EasyKanban.git 
   cd EasyKanban

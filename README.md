# Task Management System

A simple Java application for managing tasks with CRUD operations (Create, Read, Update, Delete).

## Features

- Create new tasks with title, description, and due date
- View all tasks
- Update existing tasks
- Delete tasks
- Mark tasks as completed
- Search tasks by title

## Requirements

- Java 11 or higher
- Maven (for dependency management)

## How to Run

1. Make sure you have Java installed. Check with:
   ```
   java -version
   ```

2. Compile the project:
   ```
   javac -d bin src/com/taskmanager/*.java
   ```

3. Run the application:
   ```
   java -cp bin com.taskmanager.Main
   ```

## Usage Instructions

When you run the application, you'll see a menu with the following options:

1. **Add Task**: Create a new task by providing a title, description, and due date
2. **View All Tasks**: Display all tasks in the system
3. **Update Task**: Modify an existing task by ID
4. **Delete Task**: Remove a task from the system by ID
5. **Mark Task as Completed**: Change the status of a task to completed
6. **Search Tasks**: Find tasks by title keyword
7. **Exit**: Close the application

Follow the on-screen prompts to navigate through the application.

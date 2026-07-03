# Task Management API

A RESTful Task Management API built using **Java**, **Spring Boot**, and **Domain-Driven Design (DDD)** principles.

The application allows users to create, retrieve, update, delete, and list tasks. Data is stored in an **in-memory repository** using `ConcurrentHashMap`, so no database setup is required.

---

## Features

- Create a task
- Get a task by ID
- Update a task
- Delete a task
- List all tasks
- Sort tasks by due date
- Filter tasks by status
- Pagination support
- Request validation
- Global exception handling
- Unit Tests
- Integration Tests

---

## Tech Stack

- Java 17 (Recommended)
- Spring Boot
- Maven
- Lombok
- JUnit 5
- Mockito
- MockMvc

---

## Project Structure

```
src
├── main
│   ├── java
│   │   └── com.example.taskmanagement
│   │       ├── controller
│   │       ├── domain
│   │       ├── dto
│   │       ├── exception
│   │       ├── repository
│   │       ├── service
│   │       └── TaskManagementApplication.java
│   └── resources
│       └── application.properties
│
└── test
    ├── controller
    ├── repository
    ├── service
    └── integration
```

---

## Prerequisites

- Java 17 or later
- Maven 3.8+
- IntelliJ IDEA (Recommended)

---

## Clone the Repository

```bash
git clone https://github.com/Kamana04/task-management-api.git
```

Go inside the project

---

## Build the Project

Using Maven

```bash
mvn clean install
```

---

## Run the Application

```
TaskManagementApplication.java
```

from IntelliJ.

The application starts on

```
http://localhost:8080
```

---

# API Endpoints

## 1. Create Task

**POST**

```
POST /tasks
```

Request

```json
{
  "title": "Task Management Assignment",
  "description": "Complete coding challenge",
  "status": "PENDING",
  "dueDate": "2026-07-15"
}
```

Response

```json
{
  "id": "generated-uuid",
  "title": "Task Management Assignment",
  "description": "Complete coding challenge",
  "status": "PENDING",
  "dueDate": "2026-07-15"
}
```

---

## 2. Get Task

```
GET /tasks/{id}
```

Example

```
GET /tasks/1
```

---

## 3. Get All Tasks

```
GET /tasks
```

Returns all tasks sorted by due date.

---

## 4. Filter by Status

```
GET /tasks?status=PENDING
```

Possible values

- PENDING
- IN_PROGRESS
- DONE

---

## 5. Pagination

```
GET /tasks?page=0&size=5
```

---

## 6. Pagination + Filter

```
GET /tasks?page=0&size=5&status=DONE
```

---

## 7. Update Task

```
PUT /tasks/{id}
```

Request

```json
{
  "title": "Updated Task",
  "description": "Assignment completed",
  "status": "DONE",
  "dueDate": "2026-07-20"
}
```

---

## 8. Delete Task

```
DELETE /tasks/{id}
```

Returns

```
204 No Content
```

---

## Validation

The following validations are implemented:

- Title is mandatory
- Due Date is mandatory
- Due date must be in the future or present
- Status must be one of:
    - PENDING
    - IN_PROGRESS
    - DONE

---

## Error Handling

The application provides centralized exception handling using `@RestControllerAdvice`.

Example:

```json
{
    "timestamp":"2026-07-03T15:30:20",
    "status":404,
    "error":"Task Not Found",
    "message":"Task not found with id : 1"
}
```

## Running Tests

Run all tests

Tests include:

- Repository Tests
- Service Tests
- Controller Tests
- Integration Tests

---

## Author

**Kamana Mathur**

GitHub: https://github.com/Kamana04

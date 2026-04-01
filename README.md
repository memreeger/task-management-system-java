# 🚀 Task Management System (Java)

A simple Task Management System built with Java using Object-Oriented Programming principles.

---

## 📌 Overview

This project simulates a real-world task management system where:

* Users can be created
* Projects can be managed
* Tasks can be assigned and tracked
* Comments can be added to tasks

The goal of this project is to practice **OOP design**, **service-layer architecture**, and **clean code principles**.

---

## ⚙️ Features

* ✅ Create Users
* ✅ Create Projects
* ✅ Add Users to Projects
* ✅ Create Tasks
* ✅ Assign Users to Tasks
* ✅ Update Task Status (TODO / IN_PROGRESS / DONE)
* ✅ Add Comments to Tasks
* ✅ Query Tasks:

    * by title
    * by status
    * by assignee
* ✅ Remove Tasks
* ✅ Remove Users from Projects
* ✅ Overdue Task Check
* ✅ Edge Case Handling (duplicate user, invalid IDs, etc.)

---

## 🏗️ Project Structure

```
src
├── main
│   └── Main.java
├── exception
│   └── TaskManagerException.java
├── model
│   ├── User.java
│   ├── Project.java
│   ├── Task.java
│   ├── Comment.java
│   ├── Priority.java
│   └── TaskStatus.java
├── service
│   └── TaskManagementSystem.java
```

---
### Exception (Planned)

Custom exception handling structure has been prepared for future improvements, although not fully utilized yet.

## 🧠 Technologies Used

* Java
* OOP Principles
* Java Collections (Map, Set, List)
* UUID
* LocalDateTime

---

## ▶️ Demo

The `Main` class demonstrates a full system flow:

1. Create users
2. Create project
3. Add users to project
4. Create tasks
5. Assign tasks
6. Update status
7. Add comments
8. Query tasks
9. Remove operations
10. Handle edge cases

---

## 💡 Example Output

```
========== FULL SYSTEM TEST ==========

Users:
User{id=..., name='Emre', ...}
User{id=..., name='Cemre', ...}

Project:
Project{id='...', name='OXXO Task App', ...}

Tasks Created:
Task{id='...', title='Login Page', ...}

Assigning tasks...
Status updated...
Comments added...

========== TEST FINISHED ==========
```

---

## 🚀 Future Improvements

* Add JUnit tests
* Convert to Spring Boot REST API
* Add persistence (Database)
* Add authentication system

---

## 👨‍💻 Author

Developed by Emre Eger as a Java OOP practice project.

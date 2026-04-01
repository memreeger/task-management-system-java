package service;

import model.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class TaskManagementSystem {
    private Map<String, User> users;
    private Map<String, Project> projects;

    public TaskManagementSystem() {
        users = new HashMap<>();
        projects = new HashMap<>();
    }

    public User createUser(String name, String email) {
        if (name == null || name.isBlank() || email == null || email.isBlank()) {
            throw new IllegalArgumentException("Name or email cannot be null or empty");
        }

        boolean exists = users.values().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(email));

        if (exists) {
            throw new IllegalArgumentException("User already exists.");
        }

        User user = new User(name, email);
        users.put(user.getId(), user);
        return user;
    }

    public Project createProject(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }

        boolean exists = projects.values().stream()
                .anyMatch(p -> p.getName().equalsIgnoreCase(name));
        if (exists) {
            throw new IllegalArgumentException("Project already exist");
        }

        Project project = new Project(name, description);
        projects.put(project.getId(), project);
        return project;
    }

    public boolean addUserToProject(String userId, String projectId) {
        if (userId == null || userId.isBlank() || projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("UserId or projectId cannot be null or empty");
        }
        User user = users.get(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Project project = projects.get(projectId);

        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }
        return project.addMember(user);

    }

    public Task createTask(String projectId, String title, String description, Priority priority, LocalDateTime deadline) {
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("ProjectId cannot be null or empty");
        }

        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title cannot be empty or null");
        }

        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be null");
        }

        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be null");
        }

        Project project = projects.get(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found.");
        }

        Task task = new Task(title, description, priority, deadline);
        project.addTask(task);
        return task;
    }

    public boolean assignUserToTask(String userId, String taskId, String projectId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or empty");
        }
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("ProjectId cannot be null or empty");
        }
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("TaskId cannot be null or empty");
        }

        Project project = projects.get(projectId);

        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return project.assignTask(taskId, user);
    }

    public User getUserById(String userId) {
        if (userId == null || userId.isBlank()) {
            throw new IllegalArgumentException("UserId cannot be null or empty");
        }

        User user = users.get(userId);

        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        return user;
    }

    public Project getProjectById(String projectId) {
        if (projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("ProjectId cannot be null or empty");
        }

        Project project = projects.get(projectId);

        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        return project;
    }

    public boolean removeUserFromProject(String userId, String projectId) {
        if (userId == null || userId.isBlank() || projectId == null || projectId.isBlank()) {
            throw new IllegalArgumentException("UserId or ProjectId cannot be null or empty");
        }

        User user = users.get(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Project project = projects.get(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        return project.removeMember(user);
    }

    public boolean removeTask(String projectId, String taskId) {
        if (projectId == null || projectId.isBlank() || taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("ProjectId or TaskId cannot be null or empty");
        }

        Project project = projects.get(projectId);
        if (project == null) {
            throw new IllegalArgumentException("Project not found");
        }

        return project.removeTask(taskId);
    }
}
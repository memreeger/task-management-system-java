package model;

import java.time.LocalDateTime;
import java.util.*;

public class Project {
    private String id;
    private String name;
    private String description;
    private Set<User> members;
    private Map<String, Task> tasks;
    private LocalDateTime createdAt;

    public Project(String name, String description) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Project name cannot be null");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description == null ? "" : description;
        this.createdAt = LocalDateTime.now();
        this.members = new HashSet<>();
        this.tasks = new HashMap<>();
    }

    public boolean addMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }

        return members.add(user);
    }

    public boolean addTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        if (tasks.containsKey(task.getId())) {
            throw new IllegalArgumentException("Task already exists.");
        }
        tasks.put(task.getId(), task);
        return true;
    }

    public boolean removeTask(String taskId) {
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("TaskId cannot be null or empty");
        }

        return tasks.remove(taskId) != null;
    }

    public List<Task> getTasksByTitle(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be null.");
        }

        List<Task> result = tasks.values().stream()
                .filter(task -> task.getTitle().equalsIgnoreCase(title))
                .toList();

        return result;
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null.");
        }
        List<Task> result = tasks.values().stream()
                .filter(task -> task.getStatus() == status)
                .toList();
        return result;
    }

    public List<Task> getTasksByAssignee(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        List<Task> result = tasks.values().stream()
                .filter(task -> task.getAssignee() != null && task.getAssignee().equals(user))
                .toList();

        return result;
    }

    public Task getTaskById(String taskId) {
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        return tasks.get(taskId);

    }

    public boolean removeMember(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }

        return members.remove(user);
    }

    public boolean assignTask(String taskId, User user) {
        if (taskId == null || taskId.isBlank()) {
            throw new IllegalArgumentException("TaskId cannot be null");
        }
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }


        Task task = tasks.get(taskId);

        if (task == null) {
            return false;
        }

        if (!members.contains(user)) {
            return false;
        }

        task.assignUser(user);
        return true;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<User> getMembers() {
        return new HashSet<>(members);
    }

    public Map<String, Task> getTasks() {
        return new HashMap<>(tasks);
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "Project{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", memberCount=" + members.size() +
                ", taskCount=" + tasks.size() +
                '}';
    }
}

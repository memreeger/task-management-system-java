package model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Task {
    private String id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private User assignee;
    private LocalDateTime createdAt;
    private LocalDateTime deadline;
    private List<Comment> comments;

    public Task(String title, String description, Priority priority, LocalDateTime deadline) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }

        if (priority == null) {
            throw new IllegalArgumentException("Priority cannot be empty");
        }

        if (deadline == null) {
            throw new IllegalArgumentException("Deadline cannot be empty");
        }

        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description == null ? "" : description;
        this.status = TaskStatus.TODO;
        this.priority = priority;
        this.createdAt = LocalDateTime.now();
        this.deadline = deadline;
        this.comments = new ArrayList<>();
    }

    public void changeStatus(TaskStatus status) {
        if (status == null) {
            throw new IllegalArgumentException("Status cannot be null");
        }

        this.status = status;
    }

    public void assignUser(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null");
        }
        this.assignee = user;
    }

    public boolean isOverdue() {
        return deadline.isBefore(LocalDateTime.now()) && status != TaskStatus.DONE;
    }


    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description == null ? "" : description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public Priority getPriority() {
        return priority;
    }

    public User getAssignee() {
        return assignee;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }
}

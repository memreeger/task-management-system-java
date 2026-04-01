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
            throw new IllegalArgumentException("Project name cannot be empty");
        }

        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description == null ? "" : description;
        this.createdAt = LocalDateTime.now();
        this.members = new HashSet<>();
        this.tasks = new HashMap<>();
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
}

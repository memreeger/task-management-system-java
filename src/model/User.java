package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class User {
    private String id;
    private String name;
    private String email;
    private LocalDateTime createdAt;

    public User(String name, String email) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("Email cannot be empty");
        }

        if (!email.contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }

        this.name = name;
        this.email = email;
        this.id = UUID.randomUUID().toString();
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;
        return email != null && email.equalsIgnoreCase(user.email);
    }

    @Override
    public int hashCode() {
        return email.toLowerCase().hashCode();
    }
}

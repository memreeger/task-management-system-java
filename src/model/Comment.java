package model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Comment {
    private String id;
    private String content;
    private User author;
    private LocalDateTime createdAt;


    public Comment(String content, User author) {
        if (content == null || content.isBlank()) {
            throw new IllegalArgumentException("Comment cannot be empty");
        }

        if (author == null) {
            throw new IllegalArgumentException("Author cannot be null");
        }

        this.id = UUID.randomUUID().toString();
        this.content = content;
        this.author = author;
        this.createdAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public User getAuthor() {
        return author;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}

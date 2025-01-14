package com.zelling.forum_hub.entity.topic;

import java.time.LocalDateTime;

public record TopicSimpleDTO(
    String title,
    String message,
    LocalDateTime createdAt,
    String author
){
    public TopicSimpleDTO(String title, String message, LocalDateTime createdAt, String author){
        this.title = title;
        this.message = message;
        this.createdAt = createdAt;
        this.author = author;
    }

    public TopicSimpleDTO(Topic topic){
        this(
            topic.getTitle(),
            topic.getMessage(),
            topic.getCreatedAt(),
            topic.getAuthor().getName()
        );
    }
}

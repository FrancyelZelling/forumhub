package com.zelling.forum_hub.entity.topic;

public record TopicUpdateDTO(
    String title,
    String message,
    TopicStatus status,
    Long courseId
) {
    
}
